package com.api.repositories.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Repository;

import com.api.dto.MapPointsDto;
import com.api.dto.Prediction;
import com.api.dto.SearchResponse;
import com.api.repositories.MapRepository;

@Repository
public class MapRepositoryImpl implements MapRepository{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<MapPointsDto> getPoints(double lati, double longti, double radius) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SearchResponse> search(String text, double lati, double longti) {
		// TODO Auto-generated method stub
		SearchResponse searchRes = new SearchResponse();
		List<Prediction> prediction = new ArrayList<Prediction>();
		StoredProcedureQuery storedProcedure = em.createNamedStoredProcedureQuery("sp_searchMap");
		storedProcedure.setParameter(0, text);
		storedProcedure.setParameter(1, lati);
		storedProcedure.setParameter(2, longti);
		storedProcedure.execute();
		List<Object[]> lstRs = storedProcedure.getResultList();
		for(Object[] obj : lstRs) {
			Prediction pre = new Prediction();
			pre.setPlace_id((String)obj[1]);
			pre.setDescription((String)obj[2]);
			pre.setLocation((String)obj[3]);
			pre.setType("db");
			
		}
		
		return null;
	}
	
}
