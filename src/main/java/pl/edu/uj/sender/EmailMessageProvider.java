package pl.edu.uj.sender;

public class EmailMessageProvider extends MessageProvider {
    int messagesCount = 100;

    @Override
    public Message getNextMessage() throws InterruptedException {
        if (messagesCount > 0) {
            messagesCount--;
            Thread.sleep(500);
            return new EmailMessage("Title: message nr " + messagesCount, "Body");
        } else {
            return null;
        }
    }
}
