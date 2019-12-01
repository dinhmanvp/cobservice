package cob.com.core.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cob.com.core.entities.TbDashboard;



public interface TbDashboardRepository extends JpaRepository<TbDashboard,Integer>{
	@Query("SELECT t FROM TbDashboard t WHERE t.nId = :nId and t.sDashboardId = :dashboardId")
	public TbDashboard findById(@Param("nId") String id, @Param("dashboardId") String dashboardId);
	@Query("SELECT t FROM TbDashboard t WHERE t.sDashboardId = :dashboardId")
	public TbDashboard findByDashBoardId(@Param("dashboardId") String dashboardId);
}
