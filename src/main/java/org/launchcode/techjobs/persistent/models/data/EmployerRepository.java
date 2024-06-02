package org.launchcode.techjobs.persistent.models.data;

import org.launchcode.techjobs.persistent.models.Employer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployerRepository extends CrudRepository<Employer, Integer> {
// possible to write very specific custom sql queries in methods here
}
