package cob.com.marketing.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import cob.com.marketing.dao.TbAdvertiseRepository;
import cob.com.marketing.entities.TbAdvertise;
import cob.com.marketing.ws.param.Parameter;

@Component
public class MarketingImpl implements Marketing{

	@Autowired 
	TbAdvertiseRepository tbAdvertiseRepository;
	@PersistenceContext
	private EntityManager em;
	@SuppressWarnings("unchecked")
	@Override
	public List<TbAdvertise> getAdvertises(Integer isHome) {
		List<TbAdvertise> ad = new ArrayList<>();
		try {
			StoredProcedureQuery sp = em.createStoredProcedureQuery("mdl_marketing.GETBYHOME", TbAdvertise.class)
					.registerStoredProcedureParameter("is_home",Integer.class, ParameterMode.IN)
					.setParameter("is_home", isHome);
					
			 ad = (List<TbAdvertise>) sp.getResultList();
		} catch (Exception e) {
			return ad;
		}
		return ad;
	}
	@Override
	public String getImage(String tablename, String id) {
		Gson gson = new Gson();
		String imageData = StringUtils.EMPTY;
		if (Parameter.TABLE_ADVERTISE.equals(tablename.trim())) {
			TbAdvertise image = tbAdvertiseRepository.findById(new Integer(id)).get();
			// parse
			JsonElement jElement = gson.toJsonTree(image).getAsJsonObject();
			JsonObject jObject = jElement.getAsJsonObject();
			imageData = jObject.get("sImage").getAsString();
		}
		return imageData;
	}

}
