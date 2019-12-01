package cob.com.cobservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cob.com.cobservice.entities.TbCurrencyExchange;

public interface TbCurrencyExchangeRepository extends JpaRepository<TbCurrencyExchange, Integer>{
	@Query("select t from TbCurrencyExchange t where t.sFromCurrencyId = :from and t.nIsAvailable = 1")
	public TbCurrencyExchange getCurrencyFrom(@Param("from") String from);
}
