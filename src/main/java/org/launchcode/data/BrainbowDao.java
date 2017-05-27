package org.launchcode.data;

import org.launchcode.models.Brainbow;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by jeannie on 5/27/17.
 */
@Repository
@Transactional
public interface BrainbowDao extends CrudRepository<Brainbow, Integer>{
}
