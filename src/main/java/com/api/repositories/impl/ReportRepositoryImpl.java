package com.api.repositories.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.api.repositories.ReportRepository;

@Repository
public class ReportRepositoryImpl implements ReportRepository{
	
	public static Logger logger = LoggerFactory.getLogger(ReportRepositoryImpl.class);

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Object[]> getReport(int p_district_id, int p_sub_district_id, int p_city_id, String p_date_from, String p_date_to,
			int p_type_point, String p_group_by) {
		List<Object[]> rs = new ArrayList<Object[]>();
		try {
			StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("prc_report_get_report");
			storedProcedure.registerStoredProcedureParameter("p_district_id", Integer.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("p_sub_district_id", Integer.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("p_city_id", Integer.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("p_date_from", String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("p_date_to", String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("p_type_point", Integer.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("p_group_by", String.class, ParameterMode.IN);
			storedProcedure.setParameter("p_district_id", p_district_id);
			storedProcedure.setParameter("p_sub_district_id", p_sub_district_id);
			storedProcedure.setParameter("p_city_id", p_city_id);
			storedProcedure.setParameter("p_date_from", p_date_from);
			storedProcedure.setParameter("p_date_to", p_date_to);
			storedProcedure.setParameter("p_type_point", p_type_point);
			storedProcedure.setParameter("p_group_by", p_group_by);
			storedProcedure.execute();
			rs = storedProcedure.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Get Report -> ", e.getMessage());
		}
		return rs;
	}

}
