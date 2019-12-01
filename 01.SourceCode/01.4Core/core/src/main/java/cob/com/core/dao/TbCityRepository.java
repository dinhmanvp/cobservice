package cob.com.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cob.com.core.entities.TbCity;

public interface TbCityRepository extends JpaRepository<TbCity, Integer>{
	@Query("SELECT t FROM TbCity t WHERE t.sCountryId = :id")
	public TbCity findByCityId(@Param("id") String id);
}
