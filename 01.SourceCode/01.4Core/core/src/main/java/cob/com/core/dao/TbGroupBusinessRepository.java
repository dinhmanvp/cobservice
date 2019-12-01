package cob.com.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cob.com.core.entities.TbGroupBusiness;


public interface TbGroupBusinessRepository extends JpaRepository<TbGroupBusiness,Integer>{
	@Query("SELECT t FROM TbGroupBusiness t WHERE t.sGroupBusinessId = :id")
	public TbGroupBusiness findByGroupBusinessId(@Param("id") String id);
}
