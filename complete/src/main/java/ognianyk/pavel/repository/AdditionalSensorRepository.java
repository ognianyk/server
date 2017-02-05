package ognianyk.pavel.repository;

import ognianyk.pavel.entity.AdditionalSensorEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by pavelognianyk on 10/24/16.
 */
@Repository
public interface AdditionalSensorRepository extends CrudRepository<AdditionalSensorEntity, Long>,
        PagingAndSortingRepository<AdditionalSensorEntity, Long>{
}
