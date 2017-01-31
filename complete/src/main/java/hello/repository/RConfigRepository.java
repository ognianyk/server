package hello.repository;

import hello.entity.DataFromRaspberry;
import hello.entity.RConfig;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by pavelognianyk on 10/24/16.
 */
@Repository
public interface RConfigRepository extends CrudRepository<RConfig, Long>{

}
