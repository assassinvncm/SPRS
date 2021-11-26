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
	public List<Object[]> getReport(String p_district, String p_sub_district, String p_city, int p_month, int p_year,
			int p_type_point) {
		List<Object[]> rs = new ArrayList<Object[]>();
		try {
			StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("prc_report_get_report");
			storedProcedure.registerStoredProcedureParameter("p_district", String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("p_sub_district", String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("p_city", String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("p_month", Integer.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("p_year", Integer.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("p_type_point", Integer.class, ParameterMode.IN);
			storedProcedure.setParameter("p_district", p_district);
			storedProcedure.setParameter("p_sub_district", p_sub_district);
			storedProcedure.setParameter("p_city", p_city);
			storedProcedure.setParameter("p_month", p_month);
			storedProcedure.setParameter("p_year", p_year);
			storedProcedure.setParameter("p_type_point", p_type_point);
			storedProcedure.execute();
			rs = storedProcedure.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Get Report -> ", e.getMessage());
		}
		return rs;
	}

}
