package com.test.microwarehouse.controller;

import com.test.microwarehouse.dto.TopCuisineDto;
import com.test.microwarehouse.service.SparkService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class SparkController {

    private final SparkService sparkService;

    private final static String TOP_CUISINE_LIST = "topCuisineList";

    public SparkController(SparkService sparkService) {
        this.sparkService = sparkService;
    }

    @PostMapping("/process")
    public String process(RedirectAttributes redirectAttributes) {
        List<TopCuisineDto> topCuisineList = sparkService.runSparkJob();
        redirectAttributes.addFlashAttribute(TOP_CUISINE_LIST, topCuisineList);
        return "redirect:/";
    }

    @GetMapping("/")
    public String home(Model model) {
        List<TopCuisineDto> topCuisineList = (List<TopCuisineDto>) model.asMap().get(TOP_CUISINE_LIST);
        if (topCuisineList != null) {
            model.addAttribute(TOP_CUISINE_LIST, topCuisineList);
        }
        return "home";
    }
}
