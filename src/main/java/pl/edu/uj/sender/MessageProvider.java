package pl.edu.uj.sender;

import java.util.Optional;

public abstract class MessageProvider {
    public abstract Message getNextMessage() throws InterruptedException;
}

