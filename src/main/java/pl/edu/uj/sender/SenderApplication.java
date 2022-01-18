package pl.edu.uj.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class SenderApplication {
    private static final Logger logger = LoggerFactory.getLogger(SenderApplication.class);

    public static void main(String[] args) throws InterruptedException {

        if (args.length == 2) {
            final int numberOfEnqueuingThreads = Integer.parseInt(args[0]);
            final int numberOfSendingThreads = Integer.parseInt(args[1]);
            logger.info("There will be %d enqueuing threads and %d sender threads".formatted(numberOfEnqueuingThreads, numberOfSendingThreads));

            EmailSender emailSender = new EmailSender();
            EmailMessageProvider messageProvider = new EmailMessageProvider();
            RecipientProvider recipientProvider = new EmailRecipientProvider();

            List<EmailPackage> queue = new ArrayList<>();

            List<Thread> threads = new ArrayList<>();
            for (int i = 0; i < numberOfEnqueuingThreads; i++) {
                threads.add(new Thread(new EmailEnquerRunnable(messageProvider, recipientProvider, queue)));
            }
            for (int i = 0; i < numberOfSendingThreads; i++) {
                threads.add(new Thread(new EmailSenderRunnable(queue, emailSender)));
            }

            for (Thread thread : threads) {
                thread.start();
            }
            for (Thread thread : threads) {
                thread.join();
            }

        } else {
            logger.error("Params should be: enqueuing-threads-count sender-threads-count");
            System.exit(-1);
        }
    }

    private static class EmailPackage {
        final Message nextMessage;
        final Recipient nextRecipient;

        public EmailPackage(Message nextMessage, Recipient nextRecipient) {
            this.nextMessage = nextMessage;
            this.nextRecipient = nextRecipient;
        }
    }

    private static class EmailEnquerRunnable implements Runnable {
        private final EmailMessageProvider messageProvider;
        private final RecipientProvider recipientProvider;
        private final List<EmailPackage> queue;

        public EmailEnquerRunnable(EmailMessageProvider messageProvider, RecipientProvider recipientProvider, List<EmailPackage> queue) {
            this.messageProvider = messageProvider;
            this.recipientProvider = recipientProvider;
            this.queue = queue;
        }

        @Override
        public void run() {
            Message nextMessage;
            try {
                do {
                    nextMessage = messageProvider.getNextMessage();
                    if (nextMessage != null) {
                        final Recipient nextRecipient = recipientProvider.getNextRecipient();
                        synchronized (queue) {
                            logger.info("Enqueueing message.");
                            queue.add(new EmailPackage(nextMessage, nextRecipient));
                        }
                    }
                } while (nextMessage != null);
            } catch (InterruptedException e) {
                logger.error("Couldn't enqueue message", e);
            }
        }
    }

    private static class EmailSenderRunnable implements Runnable {
        private final List<EmailPackage> queue;
        private final EmailSender emailSender;
        private int counter = 0;

        public EmailSenderRunnable(List<EmailPackage> queue, EmailSender emailSender) {
            this.queue = queue;
            this.emailSender = emailSender;
        }

        @Override
        public void run() {
            do {
                try {
                    EmailPackage emailPackage = null;
                    synchronized (queue) {
                        if (!queue.isEmpty()) {
                            logger.info("Getting message package to send.");
                            emailPackage = queue.remove(0);
                        }
                    }
                    if (emailPackage != null) {
                        logger.info("Delivering message to send.");
                        emailSender.send(emailPackage.nextMessage, emailPackage.nextRecipient);
                        counter = 0;
                    } else {
                        logger.info("No email to send, waiting.");
                        counter++;
                        sleep(1000); // wait for new element in the queue
                    }
                } catch (SenderException | InterruptedException e) {
                    logger.error("Couldn't send a message", e);
                }
            } while (counter < 100);
        }
    }
}
