package com.example.petlover.service.externo.email.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailDetailsDTO {
  // Class data members
  private String recipient;
  private String msgBody;
  private String subject;
  private String attachment;

  public String getRecipient() {
    return recipient;
  }

  public String getMsgBody() {
    return msgBody;
  }

  public String getSubject() {
    return subject;
  }

  public void setRecipient(String recipient) {
    this.recipient = recipient;
  }

  public void setMsgBody(String msgBody) {
    this.msgBody = msgBody;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public void setAttachment(String attachment) {
    this.attachment = attachment;
  }
}