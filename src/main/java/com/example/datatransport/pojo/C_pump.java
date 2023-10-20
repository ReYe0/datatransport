package com.example.datatransport.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class C_pump implements Serializable {

	private static final long serialVersionUID = -2048771866210392553L;
	private String pump_id;
	private String machine_id;
	private String pump_name;
	private Integer pump_type;
	private int status; //状态 0：启用  1：禁用
	@JsonIgnore
	private String xml_config;
	private String xml_channel;
	public String getPump_id() {
		return pump_id;
	}
	public void setPump_id(String pump_id) {
		this.pump_id = pump_id;
	}
	public String getMachine_id() {
		return machine_id;
	}
	public void setMachine_id(String machine_id) {
		this.machine_id = machine_id;
	}
	public String getPump_name() {
		return pump_name;
	}
	public void setPump_name(String pump_name) {
		this.pump_name = pump_name;
	}
	public Integer getPump_type() {
		return pump_type;
	}
	public void setPump_type(Integer pump_type) {
		this.pump_type = pump_type;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getXml_config() {
		return xml_config;
	}
	public void setXml_config(String xml_config) {
		this.xml_config = xml_config;
	}
	public String getXml_channel() {
		return xml_channel;
	}
	public void setXml_channel(String xml_channel) {
		this.xml_channel = xml_channel;
	}
}
