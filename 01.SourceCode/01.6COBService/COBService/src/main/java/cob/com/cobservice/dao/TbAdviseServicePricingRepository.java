package cob.com.cobservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cob.com.cobservice.entities.TbAdviseServicePricing;

public interface TbAdviseServicePricingRepository extends JpaRepository<TbAdviseServicePricing,Integer>{
	
	@Query("SELECT t FROM TbAdviseServicePricing t WHERE t.sAdviseServiceId = :sAdviseServiceId AND t.sServiceUnitId =:sServiceUnitId")
	public TbAdviseServicePricing findsepribyID(@Param("sAdviseServiceId")String sAdviseServiceId, @Param("sServiceUnitId")String sServiceUnitId);

}
