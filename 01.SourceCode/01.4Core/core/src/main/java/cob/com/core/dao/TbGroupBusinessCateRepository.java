package cob.com.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cob.com.core.entities.TbGroupbusinessCate;
import cob.com.core.entities.TbGroupbusinessCateName;

public interface TbGroupBusinessCateRepository extends JpaRepository<TbGroupbusinessCate, Integer>{
	@Query("SELECT t FROM TbGroupbusinessCate t where t.sGroupBusinessCateId = :id")
	public TbGroupbusinessCate findByGroupBusinessCateId(@Param("id") String id);
	
	@Query("SELECT new cob.com.core.entities.TbGroupbusinessCateName(t.sGroupBusinessCateId,t.sGroupBusinessCateNameVn,t.sGroupBusinessNameCateCn,t.sGroupBusinessNameCateEn) FROM TbGroupbusinessCate t")
	public List<TbGroupbusinessCateName> getAllGroupbusinessCateName();
}
