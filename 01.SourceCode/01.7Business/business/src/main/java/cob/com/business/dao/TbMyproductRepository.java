package cob.com.business.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cob.com.business.entities.TbMyproduct;

public interface TbMyproductRepository extends JpaRepository<TbMyproduct,Long>{
	@Query("SELECT t FROM TbMyproduct t WHERE t.sProductId =:sProductId")
	public TbMyproduct getProbyid(@Param("sProductId") String sProductId);
	
	/*
	@Query("SELECT t FROM TbMyproduct t WHERE t.sProductId =:sProductId")
	public List<TbMyproduct> getProbyproid(@Param("sProductId") String sProductId);
	
	 @Query("SELECT t FROM TbMyproduct t WHERE t.sProductId =:sProductId and t.sUserId =:sUserId")
	 public List<TbMyproduct> getProbyproiduserid(@Param("sProductId") String sProductId, @Param("sUserId") String sUserId);
	 
	 @Query("SELECT t FROM TbMyproduct t WHERE t.sUserId =:sUserId")
	 public List<TbMyproduct> getProbyuserid(@Param("sUserId") String sUserId);
	 
	 @Query("SELECT t FROM TbMyproduct t WHERE t.sProductId =:sProductId and t.sPartnerId =:sPartnerId")
	 public List<TbMyproduct> getProbypartidproid(@Param("sProductId") String sProductId, @Param("sPartnerId") String sPartnerId);
	 
	 @Query("SELECT t FROM TbMyproduct t WHERE t.sPartnerId =:sPartnerId")
	 public List<TbMyproduct> getProbypartid(@Param("sPartnerId") String sPartnerId);
	 
	 @Query("SELECT t FROM TbMyproduct t WHERE t.sPartnerId =:sPartnerId and t.sProductId =:sProductId and t.sUserId =:sUserId")
	 public List<TbMyproduct> getProbypartiduseridproid(@Param("sPartnerId") String sPartnerId, @Param("sProductId") String sProductId, @Param("sUserId") String sUserId);
	 
	 @Query("SELECT t FROM TbMyproduct t WHERE t.sPartnerId =:sPartnerId and t.sUserId =:sUserId")
	 public List<TbMyproduct> getProbypartiduserid(@Param("sPartnerId") String sPartnerId, @Param("sUserId") String sUserId);
	 */
}
