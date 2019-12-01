package cob.com.accounts.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cob.com.accounts.entities.TbMypocket;

public interface TbMypocketRepository extends JpaRepository<TbMypocket, Integer> {
	@Query("SELECT t from TbMypocket t WHERE t.sCurrencyId = :sCurrencyId and t.sUserId = :sUserId")
	public List<TbMypocket> getPocketByUserIdAndCurrencyI(@Param("sCurrencyId") String currencyId,
			@Param("sUserId") String sUserId);
}
