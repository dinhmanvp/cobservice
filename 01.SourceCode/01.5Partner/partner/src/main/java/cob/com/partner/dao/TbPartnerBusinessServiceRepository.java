package cob.com.partner.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import cob.com.partner.entities.TbPartnerBusinessService;

@Repository
public interface TbPartnerBusinessServiceRepository extends JpaRepository<TbPartnerBusinessService,String>{

	@Query("SELECT t FROM TbPartnerBusinessService t where t.sPartnerId = :sPartnerId and t.nIsActivated = 1") 
	public List<TbPartnerBusinessService> findBusiid(@Param("sPartnerId")String sPartnerId);
}
