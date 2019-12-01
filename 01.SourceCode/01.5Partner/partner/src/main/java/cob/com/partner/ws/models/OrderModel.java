package cob.com.partner.ws.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OrderModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="s_order_number")
	private String sordernumber;
	
	@Column(name="n_appointment_number")
	private String nappointmentnumber;
	
	@Column(name="s_patient_name")
	private String spatientname;
	
	@Column(name="s_patient_gender")
	private String spatientgender;
	
	@Column(name="s_patient_age")
	private String spatientage;
	
	@Column(name="s_appointment_time")
	private String sappointmenttime;
	
	@Column(name="n_bhyt")
	private String nbhyt;
	
	@Column(name="s_address")
	private String saddress;
	
	@Column(name="s_full_name")
	private String sfullname;

	public String getSordernumber() {
		return sordernumber;
	}

	public String getSfullname() {
		return sfullname == null ? "" : sfullname;
	}

	public void setSfullname(String sfullname) {
		this.sfullname = sfullname;
	}

	public void setSordernumber(String sordernumber) {
		this.sordernumber = sordernumber;
	}

	public String getNappointmentnumber() {
		return nappointmentnumber;
	}

	public void setNappointmentnumber(String nappointmentnumber) {
		this.nappointmentnumber = nappointmentnumber;
	}

	public String getSpatientname() {
		return spatientname;
	}

	public void setSpatientname(String spatientname) {
		this.spatientname = spatientname;
	}

	public String getSpatientgender() {
		return spatientgender;
	}

	public void setSpatientgender(String spatientgender) {
		this.spatientgender = spatientgender;
	}

	public String getSpatientage() {
		return spatientage;
	}

	public void setSpatientage(String spatientage) {
		this.spatientage = spatientage;
	}

	public String getSappointmenttime() {
		return sappointmenttime;
	}

	public void setSappointmenttime(String sappointmenttime) {
		this.sappointmenttime = sappointmenttime;
	}

	public String getNbhyt() {
		return nbhyt;
	}

	public void setNbhyt(String nbhyt) {
		this.nbhyt = nbhyt;
	}

	public String getSaddress() {
		return saddress;
	}

	public void setSaddress(String saddress) {
		this.saddress = saddress;
	}

	public OrderModel() {
		super();
	}

}
