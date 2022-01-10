package pl.edu.uj.sender.db;

import pl.edu.uj.sender.DatabaseException;
import pl.edu.uj.sender.EmailRecipient;

import java.util.Optional;

public class EmailRecipientDAO implements SenderDAO<EmailRecipient> {
    SenderConnection dbConnection;
    EmailRecipientMapper recipientMapper;

    public EmailRecipientDAO(SenderConnection dbConnection, EmailRecipientMapper recipientMapper) {
        this.dbConnection = dbConnection;
        this.recipientMapper = recipientMapper;
    }

    @Override
    public Optional<EmailRecipient> get(long id) throws DatabaseException {
        return dbConnection.executeQuery(("SELECT email_recipient_id from email_recipient").formatted(id), recipientMapper);
    }

    @Override
    public Optional<EmailRecipient> get(int statusId) throws DatabaseException {
        throw new DatabaseException(NOT_IMPLEMENTED);
    }

    @Override
    public Optional<EmailRecipient> get(String id) throws DatabaseException {
        return dbConnection.executeQuery(("SELECT email_recipient_address from email_recipient").formatted(id), recipientMapper);
    }

    @Override
    public Long save(EmailRecipient emailRecipient) throws DatabaseException {
        final String statement = ("INSERT into email_recipient VALUES").formatted(emailRecipient.getCreationDate(),
                emailRecipient.getRecipientAddress());
        return dbConnection.executeUpdate(statement, true);
    }

    @Override
    public long update(EmailRecipient newEmailRecipient, EmailRecipient expectedEmailRecipient) throws DatabaseException {
        throw new DatabaseException(NOT_IMPLEMENTED);
    }

    @Override
    public void delete(EmailRecipient emailRecipient) throws DatabaseException {
        throw new DatabaseException(NOT_IMPLEMENTED);
    }
}
