package pl.edu.uj.sender;

public class EmailRecipient extends Recipient {

    public String adress;

    public EmailRecipient(String adress) {
        this.adress = adress;
    }

    public String getRecipientAddress() {
        return adress;
    }

    public String makeCryptAdress(String adress) {
        int size = adress.length();
        String cryptadress = null;
        String last5 = adress.substring(size - 6, size);
        // adress = adress.replaceAll()
        return cryptadress;
    }

}
