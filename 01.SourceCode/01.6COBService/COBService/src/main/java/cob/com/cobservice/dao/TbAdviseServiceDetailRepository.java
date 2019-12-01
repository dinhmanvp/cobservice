package cob.com.cobservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cob.com.cobservice.entities.TbAdviseServiceDetail;

public interface TbAdviseServiceDetailRepository extends JpaRepository<TbAdviseServiceDetail,Integer>{

	@Query("SELECT t FROM TbAdviseServiceDetail t WHERE t.sAdviseServiceId =:sAdviseServiceId")
	public List<TbAdviseServiceDetail> findAdseByID(@Param("sAdviseServiceId")String sAdviseServiceId);
	
	@Query("SELECT t FROM TbAdviseServiceDetail t WHERE t.adviseServiceDetailId =:id")
	public TbAdviseServiceDetail findByAdviseServiceDetailId(@Param("id")String id);
}
