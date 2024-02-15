package com.example.ecommerce.view;

import javax.persistence.Column;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="ResponseObject")
public class ResponseObject {
//    public ResponseObject(int i, String string) {
//		// TODO Auto-generated constructor stub
//	}
    
    @Column(name="status")
	private int status;
    @Column(name="statusMessage")
    private String statusMessage;
	public ResponseObject() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ResponseObject(int status, String statusMessage) {
		super();
		this.status = status;
		this.statusMessage = statusMessage;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	@Override
	public String toString() {
		return "ResponseObject [status=" + status + ", statusMessage=" + statusMessage + ", getStatus()=" + getStatus()
				+ ", getStatusMessage()=" + getStatusMessage() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
    
    
}
