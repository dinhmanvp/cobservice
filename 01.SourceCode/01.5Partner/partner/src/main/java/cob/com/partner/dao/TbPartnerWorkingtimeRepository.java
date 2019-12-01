package cob.com.partner.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cob.com.partner.entities.TbPartnerWorkingtime;

public interface TbPartnerWorkingtimeRepository extends JpaRepository<TbPartnerWorkingtime,Integer>{
	@Query("SELECT t FROM TbPartnerWorkingtime t WHERE (t.sPartnerId =:sPartnerId and t.sbusinessServiceId = :sbusinessServiceId and '' <> :sbusinessServiceId) or (t.sPartnerId = :sPartnerId and '' = :sbusinessServiceId)")
	public List<TbPartnerWorkingtime> getworkingbypartnerid(@Param("sPartnerId") String sPartnerId, @Param("sbusinessServiceId") String sbusinessServiceId );
}
