package pl.edu.uj.sender;

public class EmailRecipient extends Recipient {
    private final String recipientAddress;

    public EmailRecipient(String recipientAddress) {
        this.recipientAddress = recipientAddress;
    }

    public String getRecipientAddress() {
        return recipientAddress;
    }
}
