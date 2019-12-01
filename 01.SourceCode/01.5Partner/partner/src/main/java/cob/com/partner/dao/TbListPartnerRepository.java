package cob.com.partner.dao;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cob.com.partner.entities.TbListPartner;
import cob.com.partner.entities.TbPartnerName;

public interface TbListPartnerRepository extends JpaRepository<TbListPartner,Integer>{
	
	@Query("SELECT t FROM TbListPartner t where t.sPartnerId = :sPartnerId") 
	public TbListPartner findBypartnerid(@Param("sPartnerId")String sPartnerId);
	
	@Query("SELECT t FROM TbListPartner t where t.sPartnerId = :sPartnerId") 
	public TbListPartner getParbyUserid(@Param("sPartnerId")String sPartnerId);
	
	@Query("SELECT t FROM TbListPartner t where t.sGroupBusinessId = :sGroupBusinessId") 
	public List<TbListPartner> getParbyGroupBuid(@Param("sGroupBusinessId")String sGroupBusinessId);
	
	@Query("SELECT new cob.com.partner.entities.TbPartnerName(t.sPartnerId, t.sPartnerNameEn, t.sPartnerNameVn, t.sPartnerNameCn) FROM TbListPartner t")
	public List<TbPartnerName> getPartnerNames();
}
