package lt.project.CoronaTracker.services;

import lt.project.CoronaTracker.csv.CSVHelper;
import lt.project.CoronaTracker.exception.CountryNotFoundException;
import lt.project.CoronaTracker.models.CountryCases;
import lt.project.CoronaTracker.repository.CountryCasesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CountryCasesDataService {

    @Autowired
    private CountryCasesRepository countryCasesRepository;
    @Autowired
    private CSVHelper csvHelper;

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void save() throws IOException, InterruptedException {
        countryCasesRepository.deleteAll();
        List<CountryCases> countryCases = csvHelper.fetchData();
        countryCasesRepository.saveAll(countryCases);
    }

    public List<CountryCases> getAllCases() {
        return countryCasesRepository.findAll();
    }

    public Map<String, String> getCountryCases(String country, String province) throws IOException, InterruptedException {
        return csvHelper.getCountryCases(country, province);
    }

    public CountryCases findCountryCasesById(int id) {
        return countryCasesRepository.findCountryCasesById(id)
                .orElseThrow(() -> new CountryNotFoundException("Country by id " + id + " was not found"));
    }
}
