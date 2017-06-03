package org.launchcode.data;

import org.launchcode.models.Log;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by jeannie on 6/1/17.
 */
@Repository
@Transactional
public interface LogDao extends CrudRepository<Log, Integer>{
}
