package pl.edu.uj.sender;

import java.sql.Timestamp;

public class EmailMessage extends Message {
  private final Timestamp creationDate;
  private final String messageTitle;
  private final String messageBody;
  private Long emailMessageId = null;

  public EmailMessage(Long emailMessageId, Timestamp creationDate, String messageTitle, String messageBody) {
    this.emailMessageId = emailMessageId;
    this.creationDate = creationDate;
    this.messageTitle = messageTitle;
    this.messageBody = messageBody;
  }

  public EmailMessage(Timestamp creationDate, String messageTitle, String messageBody) {
    this.creationDate = creationDate;
    this.messageTitle = messageTitle;
    this.messageBody = messageBody;
  }

  public Long getEmailMessageId() {
    return emailMessageId;
  }

  public void setEmailMessageId(Long emailMessageId) {
    this.emailMessageId = emailMessageId;
  }

  public Timestamp getCreationDate() {
    return creationDate;
  }

  @Override
  public String getMessageTitle() {
    return messageTitle;
  }

  @Override
  public String getMessageBody() {
    return messageBody;
  }
}
