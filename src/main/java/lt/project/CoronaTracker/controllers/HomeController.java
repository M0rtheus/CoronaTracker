package lt.project.CoronaTracker.controllers;

import lt.project.CoronaTracker.models.CountryCases;
import lt.project.CoronaTracker.services.CountryCasesDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CountryCasesDataService countryCasesDataService;

    @GetMapping("/")
    public String home(Model model) {
        List<CountryCases> allCases = countryCasesDataService.getAllCases();
        int totalCases = allCases.stream().mapToInt(cases -> cases.getLatestTotal()).sum();
        model.addAttribute("countryCases", allCases);
        model.addAttribute("totalCases", totalCases);

        return "home";
    }

}
