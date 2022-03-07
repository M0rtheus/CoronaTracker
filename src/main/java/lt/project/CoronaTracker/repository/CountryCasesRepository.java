package lt.project.CoronaTracker.repository;

import lt.project.CoronaTracker.models.CountryCases;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryCasesRepository extends JpaRepository<CountryCases, Integer> {

    Optional<CountryCases> findCountryCasesById(int id);
}