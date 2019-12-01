package cob.com.business.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cob.com.business.entities.TbMydeal;

public interface TbMydealRepository extends JpaRepository<TbMydeal,Long>{

	@Query("SELECT t FROM TbMydeal t WHERE t.sMydealId = :sMydealId")
	public TbMydeal Mydealbyid(@Param("sMydealId")String sMydealId);
}
