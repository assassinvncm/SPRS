package com.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository {
	//@Query(value = "CALL prc_report_get_report(:user_id,:status,:type,:current_page,:page_size);", nativeQuery = true)
//	List<Object[]> getReport(@Param("p_district") String p_district
//										,@Param("p_sub_district") String p_sub_district
//										,@Param("p_city") String p_city
//										,@Param("p_month") int p_month
//										,@Param("p_year") int p_year
//										,@Param("p_type_point") int p_type_point);
	List<Object[]> getReport(String p_district
							, String p_sub_district
							, String p_city
							, int p_month
							, int p_year
							, int p_type_point);
}
