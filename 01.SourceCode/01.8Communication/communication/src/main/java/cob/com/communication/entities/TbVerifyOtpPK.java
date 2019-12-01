package cob.com.communication.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tb_verify_otp database table.
 * 
 */
@Embeddable
public class TbVerifyOtpPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="s_transaction_id")
	private String sTransactionId;

	@Column(name="s_username")
	private String sUsername;

	public TbVerifyOtpPK() {
	}
	public String getSTransactionId() {
		return this.sTransactionId;
	}
	public void setSTransactionId(String sTransactionId) {
		this.sTransactionId = sTransactionId;
	}
	public String getSUsername() {
		return this.sUsername;
	}
	public void setSUsername(String sUsername) {
		this.sUsername = sUsername;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TbVerifyOtpPK)) {
			return false;
		}
		TbVerifyOtpPK castOther = (TbVerifyOtpPK)other;
		return 
			this.sTransactionId.equals(castOther.sTransactionId)
			&& this.sUsername.toLowerCase().equals(castOther.sUsername.toLowerCase());
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.sTransactionId.hashCode();
		hash = hash * prime + this.sUsername.hashCode();
		
		return hash;
	}
}