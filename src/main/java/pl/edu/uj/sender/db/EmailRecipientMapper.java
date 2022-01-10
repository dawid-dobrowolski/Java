package pl.edu.uj.sender.db;

import pl.edu.uj.sender.EmailRecipient;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;
//aby zachowac nasz obiekt z bazy do javy
public class EmailRecipientMapper implements SenderMapper<EmailRecipient> {

    //rs to obiekt z bazy
    public Optional<EmailRecipient> get(ResultSet rs) throws SQLException {
        // spr czy nie jest puste
        if (rs.next()) {
            final long emailRecipientId = rs.getInt("email_recipient_id");
            final Timestamp creationDate = rs.getTimestamp("creation_date");
            final String recipientAddress = rs.getString("recipient_address");
            return Optional.of(new EmailRecipient(emailRecipientId, creationDate, recipientAddress));
        }
        return Optional.empty();
    }
}
//
//Optional to klasa/wrzozec obiektowy. Mamy jakiś obiekt al nie wiadomo czy będzie to null czy obiekt. Zeby w razie w
//nie dostać nullPointerException. Nie mamy pewności co to będzie więc rzucamy Optionala.
//Wymusza sprawdzenie czy obiekt nie jest nullem