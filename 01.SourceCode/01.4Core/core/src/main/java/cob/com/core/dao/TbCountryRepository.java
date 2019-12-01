package cob.com.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cob.com.core.entities.TbCountry;

public interface TbCountryRepository extends JpaRepository<TbCountry,Integer> {
	@Query("SELECT t FROM TbCountry t WHERE t.sCountryId = :id")
	public TbCountry findByCountryId(@Param("id") String id);
}
