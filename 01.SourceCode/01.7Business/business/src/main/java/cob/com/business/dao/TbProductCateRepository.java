package cob.com.business.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cob.com.business.entities.TbProductCate;

public interface TbProductCateRepository extends JpaRepository<TbProductCate,Long>{

	@Query("SELECT t FROM TbProductCate t WHERE t.sProductCateId =:sProductCateId")
	public TbProductCate getProCatebyId(@Param("sProductCateId") String sProductCateId);
	
	@Query("SELECT t FROM TbProductCate t WHERE t.sPartnerId =:sPartnerId")
	public List<TbProductCate> getProCatebypartid(@Param("sPartnerId") String sPartnerId);
}
