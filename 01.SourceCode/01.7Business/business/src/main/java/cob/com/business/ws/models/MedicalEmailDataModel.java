package cob.com.business.ws.models;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MedicalEmailDataModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "username")
	private String username;

	@Column(name = "email")
	private String email;

	@Column(name = "d_appointment_date")
	private Date d_appointment_date;

	@Column(name = "s_appointment_time")
	private String s_appointment_time;

	@Column(name = "p_name")
	private String p_name;

	@Column(name = "p_age")
	private String p_age;

	@Column(name = "gender")
	private String gender;

	@Id
	@Column(name = "ordernumber")
	private Integer ordernumber;

	@Column(name = "hospital")
	private String hospital;

	@Column(name = "department")
	private String department;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getD_appointment_date() throws ParseException {
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		return df.format(d_appointment_date);
	}

	public void setD_appointment_date(Date d_appointment_date) {
		this.d_appointment_date = d_appointment_date;
	}

	public String getS_appointment_time() {
		return s_appointment_time;
	}

	public void setS_appointment_time(String s_appointment_time) {
		this.s_appointment_time = s_appointment_time;
	}

	public String getP_name() {
		return p_name;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

	public String getP_age() {
		return p_age;
	}

	public void setP_age(String p_age) {
		this.p_age = p_age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getOrdernumber() {
		return "ECO-" + ordernumber;
	}

	public void setOrdernumber(Integer ordernumber) {
		this.ordernumber = ordernumber;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

}
