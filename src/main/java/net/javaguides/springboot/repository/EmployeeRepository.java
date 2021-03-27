package net.javaguides.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	@Query("SELECT e FROM Employee e WHERE e.firstName LIKE %:firstName%")
	List<Employee> findByFirstNameLike(@Param("firstName") String firstName);
}
