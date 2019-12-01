package cob.com.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cob.com.core.entities.TbPromotionHistory;

public interface TbPromotionHistoryRepository extends JpaRepository<TbPromotionHistory, Integer> {
	@Query("Select t from TbPromotionHistory t where t.sUserId = :userId and t.sLoyaltyProgramId = :programId")
	TbPromotionHistory getPromotionByUserIdPromotionId(@Param("userId") String userId,
			@Param("programId") String programId);
}
