package com.api.repositories.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Repository;

import com.api.dto.MapPointsDto;
import com.api.dto.Prediction;
import com.api.dto.SearchGoongMap;
import com.api.repositories.MapRepository;

@Repository
public class MapRepositoryImpl implements MapRepository{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Object[]> getPoints(double lati, double longti, double radius, String typePoint) {
		// TODO Auto-generated method stub
		StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("prc_map_loadPointOnMap");
		storedProcedure.registerStoredProcedureParameter("lati", String.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter("longti", String.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter("radius", Double.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter("typeStr", String.class, ParameterMode.IN);
		storedProcedure.setParameter("lati", String.valueOf(lati));
		storedProcedure.setParameter("longti", String.valueOf(longti));
		storedProcedure.setParameter("radius", radius);
		storedProcedure.setParameter("typeStr", typePoint);
		storedProcedure.execute();
		List<Object[]> mapPoints = storedProcedure.getResultList();
		return mapPoints;
	}

	@Override
	public List<Object[]> search(String text, double lati, double longti, int numberOfRecord) {
		// TODO Auto-generated method stub
		StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("sp_searchMap_v2");
		storedProcedure.registerStoredProcedureParameter("searchText", String.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter("lati", String.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter("longti", String.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter("numberOfRecord", Integer.class, ParameterMode.IN);
		storedProcedure.setParameter("searchText", text);
		storedProcedure.setParameter("lati", String.valueOf(lati));
		storedProcedure.setParameter("longti", String.valueOf(longti));
		storedProcedure.setParameter("numberOfRecord", numberOfRecord);
		storedProcedure.execute();
		List<Object[]> searchRs = storedProcedure.getResultList();
		
		return searchRs;
	}
	
}
