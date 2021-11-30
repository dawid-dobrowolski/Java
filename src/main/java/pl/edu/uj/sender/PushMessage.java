package pl.edu.uj.sender;

import java.math.BigInteger;

public class PushMessage extends Message {

    public String messageTitle;
    public String messageBody;

    public PushMessage(String messageTitle, String messageBody) {
        this.messageBody = messageBody;
        this.messageTitle = messageTitle;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public String md5Message(String messageBody) {

        byte[] messageDigest = messageBody.getBytes();
        BigInteger no = new BigInteger(1, messageDigest);
        String hashtext = no.toString(16);
        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }
        return hashtext;
    }

}
