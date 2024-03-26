package website.skillforge.be.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import website.skillforge.be.dto.createDTO.ChartRequest;
import website.skillforge.be.dto.createDTO.ChartResponse;
import website.skillforge.be.services.ChartService;

@RestController
@CrossOrigin("*")
@SecurityRequirement(name = "api")
public class ChartController {
    @Autowired
    private ChartService chartService;

    @PostMapping("/chart")
    public ChartResponse getChart(@RequestBody ChartRequest request) {
        return chartService.getMonthlyChart(request);
    }
}
