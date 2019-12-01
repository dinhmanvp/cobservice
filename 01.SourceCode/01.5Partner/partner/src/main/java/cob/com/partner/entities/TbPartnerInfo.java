package cob.com.partner.entities;

import java.util.ArrayList;
import java.util.List;

import cob.com.partner.ws.models.PartnerInfoItem;

public class TbPartnerInfo {

	public PartnerInfoItem ListPartner = new PartnerInfoItem();
	public List<TbPartnerBizcate> PartnerBizcate = new ArrayList<TbPartnerBizcate>(); 
	public List<TbPartnerBusinessService> PartnerBusinessService = new ArrayList<TbPartnerBusinessService>();
}
