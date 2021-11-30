package pl.edu.uj.sender;

public class PushRecipient extends Recipient {

    public String adress;

    public PushRecipient(String adress) {
        this.adress = adress;
    }

    public String getRecipientAddress() {
        return adress;
    }

}
