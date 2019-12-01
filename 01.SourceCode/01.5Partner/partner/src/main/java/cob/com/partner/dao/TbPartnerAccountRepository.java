package cob.com.partner.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cob.com.partner.entities.TbPartnerAccount;

@Repository
public interface TbPartnerAccountRepository extends JpaRepository<TbPartnerAccount,String>{

	@Query("SELECT t FROM TbPartnerAccount t where t.sPartnerId = :sPartnerId and nIsDeleted = 0") 
	public List<TbPartnerAccount> findByuserid(@Param("sPartnerId")String sPartnerId);
	
	@Query("SELECT t FROM TbPartnerAccount t where t.sPartnerAccountId = :sPartnerAccountId") 
	public TbPartnerAccount getPartnerAccount(@Param("sPartnerAccountId")String sPartnerAccountId);
	
	@Query("DELETE TbPartnerAccount t where t.sPartnerAccountId = :sPartnerAccountId") 
	public void removePartnerAccountbyid(@Param("sPartnerAccountId")String sPartnerAccountId);
}
