package cob.com.partner.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cob.com.partner.entities.TbPartnerHoliday;

public interface TbPartnerHolidayRepository extends JpaRepository<TbPartnerHoliday,Integer>{
	@Query("SELECT t FROM TbPartnerHoliday t WHERE  t.sPartnerHolidayId = :sPartnerHolidayId")
	public TbPartnerHoliday getPartHo(@Param("sPartnerHolidayId") String sPartnerHolidayId);
}
