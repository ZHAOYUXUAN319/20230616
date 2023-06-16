package com.example.demo.obj;

public class MessageDto {
  private Long id;
  private String messages;
  private String status;
  
  public MessageDto(Long id, String messages, String status) {
    this.id = id;
    this.messages = messages;
    this.status = status;
  }
  

  public MessageDto() {
	// TODO Auto-generated constructor stub
}


public Long getId() {
    return id;
  }
  
  public void setId(Long id) {
    this.id = id;
  }
  
  public String getMessages() {
    return messages;
  }
  
  public void setMessages(String messages) {
    this.messages = messages;
  }
  
  public String getStatus() {
    return status;
  }
  
  public void setStatus(String status) {
    this.status = status;
  }
}
