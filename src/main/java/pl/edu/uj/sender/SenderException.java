package pl.edu.uj.sender;

public class SenderException extends Exception {
    public SenderException(String format, Exception e) {
        super(format, e);
    }
}
