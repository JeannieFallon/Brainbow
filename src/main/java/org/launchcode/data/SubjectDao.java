package org.launchcode.data;

import org.launchcode.models.Subject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by jeannie on 5/22/17.
 */
@Repository
@Transactional
public interface SubjectDao extends CrudRepository<Subject, Integer>{
}
