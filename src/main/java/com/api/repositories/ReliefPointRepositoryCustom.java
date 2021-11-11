package com.api.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.api.dto.ReliefPointFilterDto;
import com.api.entity.ReliefPoint;

public interface ReliefPointRepositoryCustom {

	List<ReliefPoint> findByTypeOrStatus(Long uId, ReliefPointFilterDto reliefPointFilterDto);
}
