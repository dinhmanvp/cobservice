package cob.com.business.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cob.com.business.entities.TbOrder;

public interface TbOrderRepository extends JpaRepository<TbOrder, Integer> {

	@Query("select t from TbOrder t where t.sOrderNumber = :sOrderNumber")
	public TbOrder Orderbyid(@Param("sOrderNumber") String sOrderNumber);

	@Query("select t from TbOrder t where (t.sSellerPartnerId = :sSellerPartnerId and t.sBusinessServiceId = :businessServiceId) and (t.nOrderStatusId = 1 or sSellerUserId = null)")
	public List<TbOrder> getOrderby(@Param("sSellerPartnerId") String PartnerId,
			@Param("businessServiceId") String businessServiceId);

	@Query("select t from TbOrder t where t.sBuyerUserId = :sBuyerUserId and t.nOrderStatusId = :nOrderStatusId ")
	public List<TbOrder> getOrderByBuyerUserId(@Param("sBuyerUserId") String sBuyerUserId, @Param("nOrderStatusId") Integer nOrderStatusId);

	@Query("select t from TbOrder t where t.sSellerPartnerId = :sSellerPartnerId and t.nOrderStatusId = :nOrderStatusId")
	public List<TbOrder> getOrderBySellerPartnerIdAndStatus(@Param("sSellerPartnerId") String sSellerPartnerId,
			@Param("nOrderStatusId") Integer status);
}
