package pl.edu.uj.sender;

public interface Sender {
    void send(EmailMessage message, EmailRecipient recipient);
}
