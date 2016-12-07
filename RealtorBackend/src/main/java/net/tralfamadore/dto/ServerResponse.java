package net.tralfamadore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ServerResponse {
	@JsonProperty("_status")
	private String status;
	@JsonProperty("_statusCode")
	private int statusCode;
	@JsonProperty("_statusMessage")
	private String statusMessage;
	
	public ServerResponse() {
	}
	
	public ServerResponse(String status, int statusCode, String statusMessage) {
		this.status = status;
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	@Override
	public String toString() {
		return "ServerResponse [status=" + status + ", statusCode=" + statusCode + ", statusMessage=" + statusMessage
				+ "]";
	}
}
