package cob.com.cobservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cob.com.cobservice.entities.TbCurrency;

public interface TbCurrencyRepository extends JpaRepository<TbCurrency, Integer>{
	@Query("Select t from TbCurrency t where t.sCurrencyId = :sCurrencyId")
	public TbCurrency getByCurrencyId(@Param("sCurrencyId") String sCurrencyId);
}
