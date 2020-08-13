package com.terry.karros.gpx.demo.repository;

import com.terry.karros.gpx.demo.entity.GPXEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GPXRepository extends PagingAndSortingRepository<GPXEntity, Long> {
}
