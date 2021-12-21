package sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

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
                threads.add(new Thread(new EmailEnquerRunnable.EmailSenderRunnable(queue, emailSender)));
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

        public Message getNextMessage() {
            return nextMessage;
        }

        public Recipient getNextRecipient() {
            return nextRecipient;
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
            while(queue.size() != 0) {
                try {
                    Thread.sleep(1000);
                }catch(InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (queue) {
                    queue.notify();
                }
            }
        }

    private static class EmailSenderRunnable implements Runnable {
        private final List<EmailPackage> queue;
        private final EmailSender emailSender;

        public EmailSenderRunnable(List<EmailPackage> queue, EmailSender emailSender) {
            this.queue = queue;
            this.emailSender = emailSender;
        }

        @Override
        public void run() {
            while(!queue.isEmpty()) {
                synchronized (queue) {
                    try {
                        emailSender.send(queue.get(0).getNextMessage(), queue.get(0).getNextRecipient());
                    } catch (SenderException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    queue.remove(0);
                    try {
                        Thread.sleep(2000);
                    }catch(InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    }
}
