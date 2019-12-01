package cob.com.business.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import cob.com.business.entities.TbMydealCountry;

public interface TbMydealCountryRepository extends JpaRepository<TbMydealCountry,Integer>{
	@Query("SELECT t FROM TbMydealCountry t WHERE t.sMydealId = :sMydealId")
	public List<TbMydealCountry> Mydecountbydeid(@Param("sMydealId")String sMydealId);
	
	@Query("delete FROM TbMydealCountry t WHERE t.sMydealId = :sMydealId")
	public void deletebymyid(@Param("sMydealId") String sMydealId);
	
	
	@Transactional
    @Procedure(procedureName = "mdl_business.deletebymydealid")
    public String deletebydealid(@Param("mydealId") String mydealId);
}
