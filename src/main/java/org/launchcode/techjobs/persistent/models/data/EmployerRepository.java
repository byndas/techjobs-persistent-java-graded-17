package org.launchcode.techjobs.persistent.models.data;

import org.launchcode.techjobs.persistent.models.Employer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployerRepository extends CrudRepository<Employer, Integer> {
//	JpaRepository provides more methods than CrudRepository
//  can write very specific custom sql query in a method here
//	for example:  Optional<Employer> findByLocation(String location);
}
