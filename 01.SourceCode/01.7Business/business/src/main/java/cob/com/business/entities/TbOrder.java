package cob.com.business.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the tb_order database table.
 * 
 */
@Entity
@Table(name = "tb_order")
@NamedQuery(name = "TbOrder.findAll", query = "SELECT t FROM TbOrder t")
public class TbOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "s_order_number")
	private String sOrderNumber;

	@Temporal(TemporalType.DATE)
	@Column(name = "d_appointment_date")
	private Date dAppointmentDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "d_order_date")
	private Date dOrderDate;

	@Column(name = "n_appointment_number")
	private Integer nAppointmentNumber;

	@Column(name = "n_bhyt")
	private Integer nBhyt;

	@Column(name = "n_buyer_raking")
	private Integer nBuyerRaking;

	@Id
	@Column(name = "n_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer nId;

	@Column(name = "n_is_buyer_partner_confirmed")
	private Integer nIsBuyerPartnerConfirmed;

	@Column(name = "n_is_done")
	private Integer nIsDone;

	@Column(name = "n_is_paid")
	private Integer nIsPaid;

	@Column(name = "n_is_seller_partner_confirmed")
	private Integer nIsSellerPartnerConfirmed;

	@Column(name = "n_order_status_id")
	private Integer nOrderStatusId;

	@Column(name = "n_total_amount")
	private Integer nTotalAmount;

	@Column(name = "s_appointment_time")
	private String sAppointmentTime;

	@Column(name = "s_business_service_id")
	private String sBusinessServiceId;

	@Column(name = "s_buyer_partner_confirmed_by")
	private String sBuyerPartnerConfirmedBy;

	@Column(name = "s_buyer_partner_id")
	private String sBuyerPartnerId;

	@Column(name = "s_buyer_user_id")
	private String sBuyerUserId;

	@Column(name = "s_currency")
	private String sCurrency;

	@Column(name = "s_group_business_cate_id")
	private String sGroupBusinessCateId;

	@Column(name = "s_oder_summary")
	private String sOderSummary;

	@Column(name = "s_patient_age")
	private String sPatientAge;

	@Column(name = "s_patient_gender")
	private String sPatientGender;

	@Column(name = "s_patient_name")
	private String sPatientName;

	@Column(name = "s_seller_partner_confirmed_by")
	private String sSellerPartnerConfirmedBy;

	@Column(name = "s_seller_partner_id")
	private String sSellerPartnerId;

	@Column(name = "s_seller_user_id")
	private String sSellerUserId;

	@Column(name = "s_card_id_no")
	private String sCardIdNo;

	public TbOrder() {
	}

	public String getsCardIdNo() {
		return sCardIdNo;
	}

	public void setsCardIdNo(String sCardIdNo) {
		this.sCardIdNo = sCardIdNo;
	}

	public String getSOrderNumber() {
		return this.sOrderNumber;
	}

	public void setSOrderNumber(String sOrderNumber) {
		this.sOrderNumber = sOrderNumber;
	}

	public Date getDAppointmentDate() {
//		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
//		return sFormat.format(this.dAppointmentDate);
		return this.dAppointmentDate;
	}

	public void setDAppointmentDate(Date dAppointmentDate) {
		this.dAppointmentDate = dAppointmentDate;
	}

	public Date getDOrderDate() {
		return this.dOrderDate;
	}

	public void setDOrderDate(Date dOrderDate) {
		this.dOrderDate = dOrderDate;
	}

	public String getNAppointmentNumber() {
		return "ECO-" + this.nAppointmentNumber;
	}

	public void setNAppointmentNumber(Integer nAppointmentNumber) {
		this.nAppointmentNumber = nAppointmentNumber;
	}

	public Integer getNBhyt() {
		return this.nBhyt;
	}

	public void setNBhyt(Integer nBhyt) {
		this.nBhyt = nBhyt;
	}

	public Integer getNBuyerRaking() {
		return this.nBuyerRaking;
	}

	public void setNBuyerRaking(Integer nBuyerRaking) {
		this.nBuyerRaking = nBuyerRaking;
	}

	public Integer getNId() {
		return this.nId;
	}

	public void setNId(Integer nId) {
		this.nId = nId;
	}

	public Integer getNIsBuyerPartnerConfirmed() {
		return this.nIsBuyerPartnerConfirmed;
	}

	public void setNIsBuyerPartnerConfirmed(Integer nIsBuyerPartnerConfirmed) {
		this.nIsBuyerPartnerConfirmed = nIsBuyerPartnerConfirmed;
	}

	public Integer getNIsDone() {
		return this.nIsDone;
	}

	public void setNIsDone(Integer nIsDone) {
		this.nIsDone = nIsDone;
	}

	public Integer getNIsPaid() {
		return this.nIsPaid;
	}

	public void setNIsPaid(Integer nIsPaid) {
		this.nIsPaid = nIsPaid;
	}

	public Integer getNIsSellerPartnerConfirmed() {
		return this.nIsSellerPartnerConfirmed;
	}

	public void setNIsSellerPartnerConfirmed(Integer nIsSellerPartnerConfirmed) {
		this.nIsSellerPartnerConfirmed = nIsSellerPartnerConfirmed;
	}

	public Integer getNOrderStatusId() {
		return this.nOrderStatusId;
	}

	public void setNOrderStatusId(Integer nOrderStatusId) {
		this.nOrderStatusId = nOrderStatusId;
	}

	public Integer getNTotalAmount() {
		return this.nTotalAmount;
	}

	public void setNTotalAmount(Integer nTotalAmount) {
		this.nTotalAmount = nTotalAmount;
	}

	public String getSAppointmentTime() {
		return this.sAppointmentTime;
	}

	public void setSAppointmentTime(String sAppointmentTime) {
		this.sAppointmentTime = sAppointmentTime;
	}

	public String getSBusinessServiceId() {
		return this.sBusinessServiceId;
	}

	public void setSBusinessServiceId(String sBusinessServiceId) {
		this.sBusinessServiceId = sBusinessServiceId;
	}

	public String getSBuyerPartnerConfirmedBy() {
		return this.sBuyerPartnerConfirmedBy;
	}

	public void setSBuyerPartnerConfirmedBy(String sBuyerPartnerConfirmedBy) {
		this.sBuyerPartnerConfirmedBy = sBuyerPartnerConfirmedBy;
	}

	public String getSBuyerPartnerId() {
		return this.sBuyerPartnerId;
	}

	public void setSBuyerPartnerId(String sBuyerPartnerId) {
		this.sBuyerPartnerId = sBuyerPartnerId;
	}

	public String getSBuyerUserId() {
		return this.sBuyerUserId;
	}

	public void setSBuyerUserId(String sBuyerUserId) {
		this.sBuyerUserId = sBuyerUserId;
	}

	public String getSCurrency() {
		return this.sCurrency;
	}

	public void setSCurrency(String sCurrency) {
		this.sCurrency = sCurrency;
	}

	public String getSGroupBusinessCateId() {
		return this.sGroupBusinessCateId;
	}

	public void setSGroupBusinessCateId(String sGroupBusinessCateId) {
		this.sGroupBusinessCateId = sGroupBusinessCateId;
	}

	public String getSOderSummary() {
		return this.sOderSummary;
	}

	public void setSOderSummary(String sOderSummary) {
		this.sOderSummary = sOderSummary;
	}

	public String getSPatientAge() {
		return this.sPatientAge;
	}

	public void setSPatientAge(String sPatientAge) {
		this.sPatientAge = sPatientAge;
	}

	public String getSPatientGender() {
		return this.sPatientGender;
	}

	public void setSPatientGender(String sPatientGender) {
		this.sPatientGender = sPatientGender;
	}

	public String getSPatientName() {
		return this.sPatientName;
	}

	public void setSPatientName(String sPatientName) {
		this.sPatientName = sPatientName;
	}

	public String getSSellerPartnerConfirmedBy() {
		return this.sSellerPartnerConfirmedBy;
	}

	public void setSSellerPartnerConfirmedBy(String sSellerPartnerConfirmedBy) {
		this.sSellerPartnerConfirmedBy = sSellerPartnerConfirmedBy;
	}

	public String getSSellerPartnerId() {
		return this.sSellerPartnerId;
	}

	public void setSSellerPartnerId(String sSellerPartnerId) {
		this.sSellerPartnerId = sSellerPartnerId;
	}

	public String getSSellerUserId() {
		return this.sSellerUserId;
	}

	public void setSSellerUserId(String sSellerUserId) {
		this.sSellerUserId = sSellerUserId;
	}

}