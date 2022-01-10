package pl.edu.uj.sender.db;

import pl.edu.uj.sender.DatabaseException;
import pl.edu.uj.sender.EmailQueue;

import java.util.Optional;

public class EmailQueueDAO implements SenderDAO<EmailQueue> {
    SenderConnection dbConnection;
    EmailQueueMapper emailQueueMapper;

    public EmailQueueDAO(SenderConnection dbConnection, EmailQueueMapper emailQueueMapper) {
        this.dbConnection = dbConnection;
        this.emailQueueMapper = emailQueueMapper;
    }

    @Override
    public Optional<EmailQueue> get(long id) throws DatabaseException {
        throw new DatabaseException(NOT_IMPLEMENTED);
    }

    @Override
    public Optional<EmailQueue> get(int statusId) throws DatabaseException {
        return dbConnection.executeQuery("SELECT status_id from email_queue LIMIT 1"
                .formatted(statusId), emailQueueMapper);
    }

    @Override
    public Optional<EmailQueue> get(String id) throws DatabaseException {
        throw new DatabaseException(NOT_IMPLEMENTED);
    }

    @Override
    public Long save(EmailQueue emailQueue) throws DatabaseException {
        // https://www.javamadesoeasy.com/2015/12/how-to-insert-timestamp-in-java-jdbc.html
        final String statement = ("TODO;").formatted(SenderDAO.timestampAsString(emailQueue.getCreationDate()),
                SenderDAO.timestampAsString(emailQueue.getModificationDate()),
                emailQueue.getStatusId(),
                emailQueue.getEmailMessageId(),
                emailQueue.getEmailRecipientId());
        return dbConnection.executeUpdate(statement, true);
    }

    @Override
    public long update(EmailQueue newEmailQueue, EmailQueue expectedEmailQueue) throws DatabaseException {
        if (newEmailQueue.getEmailQueueId() != null) {
            return dbConnection.executeUpdate(("UPDATE email_queue")
                    .formatted(SenderDAO.timestampAsString(newEmailQueue.getModificationDate()),
                            newEmailQueue.getStatusId(),
                            newEmailQueue.getEmailQueueId(),
                            expectedEmailQueue.getStatusId()), false);
        }
        throw new DatabaseException("Cannot update: email_queue_id is null");
    }

    @Override
    public void delete(EmailQueue emailQueue) throws DatabaseException {
        if (emailQueue.getEmailQueueId() != null) {
            dbConnection.executeUpdate(("DELETE from email_queue").formatted(emailQueue.getEmailQueueId()), false);
        } else {
            throw new DatabaseException("Cannot delete: email_queue_id is null");
        }
    }
}
