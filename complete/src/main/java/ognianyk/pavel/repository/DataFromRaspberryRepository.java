package ognianyk.pavel.repository;

import ognianyk.pavel.entity.DataFromRaspberry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by pavelognianyk on 10/24/16.
 */
@Repository
public interface DataFromRaspberryRepository extends CrudRepository<DataFromRaspberry, Long>, PagingAndSortingRepository<DataFromRaspberry, Long>{
}
