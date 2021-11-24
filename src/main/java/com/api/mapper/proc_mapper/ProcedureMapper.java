package com.api.mapper.proc_mapper;

import java.math.BigInteger;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.api.entity.Store;

@Component
public class ProcedureMapper {
	public List<Store> getStoreByStatusOrType_Mapper(List<Object[]> lstObj){
		List<Store> rs = new ArrayList<Store>();
		for(Object[] obj : lstObj) {
			Store st = new Store();
			st.setId(((BigInteger) obj[0]).longValue());
			st.setClose_time((Time)obj[1]);
			st.setDescription((String)obj[2]);
			st.setName((String)obj[3]);
			st.setOpen_time((Time)obj[4]);
			st.setStatus((Integer) obj[5]);
			rs.add(st);
		}
		return rs;
	}
	
}
