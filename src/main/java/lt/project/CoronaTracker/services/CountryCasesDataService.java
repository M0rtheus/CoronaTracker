package lt.project.CoronaTracker.services;

import lt.project.CoronaTracker.csv.CSVHelper;
import lt.project.CoronaTracker.models.CountryCases;
import lt.project.CoronaTracker.repository.CountryCasesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@Service
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

    public void fetchCountryCases(String country, String province) throws IOException, InterruptedException {
        csvHelper.fetchCountryCases(country, province);
    }

    public List<String> getDates() {
        return csvHelper.getDates();
    }

    public List<Integer> getDailyCases() {
        return csvHelper.getDailyCases();
    }

}
