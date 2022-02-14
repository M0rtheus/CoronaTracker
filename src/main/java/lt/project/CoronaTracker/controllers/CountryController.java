package lt.project.CoronaTracker.controllers;

import lt.project.CoronaTracker.models.CountryCases;
import lt.project.CoronaTracker.services.CountryCasesDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;


@Controller
public class CountryController {

    @Autowired
    CountryCasesDataService countryCasesDataService;

    @GetMapping("/countries/{countryId}")
    public String country(@PathVariable("countryId") String countryId, Model model) throws IOException, InterruptedException {
        CountryCases countryCase = countryCasesDataService.getAllCases().get(Integer.parseInt(countryId) - 1);
        countryCasesDataService.fetchCountryCases(countryCase.getCountry(), countryCase.getProvince());
        model.addAttribute("countryCase", countryCase);
        model.addAttribute("dates", countryCasesDataService.getDates());
        model.addAttribute("cases", countryCasesDataService.getDailyCases());
        model.addAttribute("today", countryCasesDataService.getDates().get(countryCasesDataService.getDates().size() - 1));
        return "country";
    }
}
