package pl.edu.uj.sender;

public class EmailSender implements Sender {


    public void send(EmailMessage message, EmailRecipient recipient) throws SenderException {
        String hashtext = message.md5Message(message.getMessageBody());
        String adress = recipient.getRecipientAddress();
        String messageTitle = message.getMessageTitle();
        System.out.println("[Email] Messege sent, title = " + messageTitle + ", body MD5 = " + hashtext + " recipent = " + adress);
    }
}
