package cob.com.partner.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cob.com.partner.entities.TbPartnerBizcate;

@Repository
public interface TbPartnerBizcateRepository extends JpaRepository<TbPartnerBizcate,String>{
	
	@Query("SELECT t FROM TbPartnerBizcate t where t.sPartnerId = :sPartnerId and t.nIsActivated = 1") 
	public List<TbPartnerBizcate> findbizid(@Param("sPartnerId")String sPartnerId);
	
}

