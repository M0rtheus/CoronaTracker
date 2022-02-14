package lt.project.CoronaTracker.repository;

import lt.project.CoronaTracker.models.CountryCases;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  CountryCasesRepository extends JpaRepository<CountryCases, Integer>{

}