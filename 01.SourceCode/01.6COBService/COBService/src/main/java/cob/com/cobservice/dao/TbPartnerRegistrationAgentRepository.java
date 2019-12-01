package cob.com.cobservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cob.com.cobservice.entities.TbPartnerRegistrationAgent;

public interface TbPartnerRegistrationAgentRepository extends JpaRepository<TbPartnerRegistrationAgent,Integer>{

	@Query("SELECT t FROM TbPartnerRegistrationAgent t WHERE t.sAgentId =:sAgentId")
	public TbPartnerRegistrationAgent getAgentByID(@Param("sAgentId")String sAgentId);
	
}
