package io.tutorialtracker.coronatracker.controller;

import io.tutorialtracker.coronatracker.CoronaTrackerApplication;
import io.tutorialtracker.coronatracker.models.LocationStats;
import io.tutorialtracker.coronatracker.services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/")
    public String homePage(Model model){
        List<LocationStats> allStats=coronaVirusDataService.getAllStats();
        int totalCases= allStats.stream().mapToInt(stat->stat.getLatestTotalCases()).sum();
        int totalNewCases= allStats.stream().mapToInt(stat->stat.getDiffFromPreDay()).sum();

        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalReportedCases", totalCases );
        model.addAttribute("totalNewCases", totalNewCases );
        return "homePage";

    }
}
