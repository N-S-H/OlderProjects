package com.nit.beans;

public class Packet {
	private int id;
	private String sender;
	private String receiver;
	private String data;
	private String packets;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getPackets() {
		return packets;
	}
	public void setPackets(String packets) {
		this.packets = packets;
	}
	
	
	
	

}
