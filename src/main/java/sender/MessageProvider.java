package sender;

public abstract class MessageProvider {
    public abstract Message getNextMessage() throws InterruptedException;
}
