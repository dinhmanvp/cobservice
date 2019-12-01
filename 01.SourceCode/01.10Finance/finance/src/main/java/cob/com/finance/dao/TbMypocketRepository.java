package cob.com.finance.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cob.com.finance.entities.TbMypocket;

public interface TbMypocketRepository extends JpaRepository<TbMypocket, Integer>{
	@Query("SELECT t FROM TbMypocket t WHERE t.sUserId = :sUserId")
	public List<TbMypocket> getPocketByUserId(@Param("sUserId") String sUserId);
	
	@Query("UPDATE TbMypocket SET nAvailableBalance = :avai, nBalance = :bal WHERE sPocketId = :pocketId")
	@Modifying
	public void updatePocketBalance(@Param("avai") String avaiBalance, @Param("bal") String balance, @Param("pocketId") String pocketId);
}
