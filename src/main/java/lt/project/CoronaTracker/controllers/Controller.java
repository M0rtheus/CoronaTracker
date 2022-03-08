package lt.project.CoronaTracker.controllers;

import lt.project.CoronaTracker.models.CountryCases;
import lt.project.CoronaTracker.services.CountryCasesDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class Controller {

    @Autowired
    CountryCasesDataService countryCasesDataService;

    @GetMapping("/all")
    public ResponseEntity<List<CountryCases>> getAllCases() {
        List<CountryCases> allCases = countryCasesDataService.getAllCases();
        return new ResponseEntity<>(allCases, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<CountryCases> getCountryById(@PathVariable("id") int id) {
        CountryCases countryCases = countryCasesDataService.findCountryCasesById(id);
        return new ResponseEntity<>(countryCases, HttpStatus.OK);
    }

    @GetMapping("/countrycase/{id}")
    public ResponseEntity<Map<String, String>> getCountryCase (@PathVariable("id") int id) throws IOException, InterruptedException {
        CountryCases countryCase = countryCasesDataService.getAllCases().get(id - 1);
        Map<String, String> countryCases = countryCasesDataService.getCountryCases(countryCase.getCountry(), countryCase.getProvince());
        return new ResponseEntity<>(countryCases, HttpStatus.OK);
    }
}
