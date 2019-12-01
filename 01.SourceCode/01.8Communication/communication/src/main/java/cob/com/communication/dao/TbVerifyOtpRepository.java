package cob.com.communication.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cob.com.communication.entities.TbVerifyOtp;
import cob.com.communication.entities.TbVerifyOtpPK;

public interface TbVerifyOtpRepository extends JpaRepository<TbVerifyOtp, TbVerifyOtpPK> {
	@Query("SELECT t from TbVerifyOtp t WHERE t.id.sTransactionId = :sTransactionId and LOWER(t.id.sUsername) = LOWER(:sUsername)")
	public TbVerifyOtp getById(@Param("sTransactionId") String transactionId, @Param("sUsername") String username);
}
