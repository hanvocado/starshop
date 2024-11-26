package com.starshop.controllers.admin;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.starshop.models.MonthlyReport;
import com.starshop.services.StatsService;

@Controller
@RequestMapping("/admin/dashboard")
public class DashboardController {
	@Autowired
	private StatsService statsService;
	
	@RequestMapping()	
	public String index(Model model, String date) {
		List<MonthlyReport> report = statsService.getMonthlyReport();
		
		List<String> labels = report.stream().map(o -> o.getLabel()).collect(Collectors.toList());
		List<Double> revenue = report.stream()
	            .map(MonthlyReport::getRevenue)
	            .collect(Collectors.toList());
		
		List<Double> profit = report.stream()
	            .map(MonthlyReport::getProfit)
	            .collect(Collectors.toList());
		
		model.addAttribute("numberOfOrders", statsService.getNumbersOfOrders());
		model.addAttribute("numberOfDeliveredOrders", statsService.getNumberOfDeliveredOrders());
		model.addAttribute("totalRevenue", statsService.getTotalRevenue());
		model.addAttribute("totalProfit", statsService.getTotalProfit());	
		model.addAttribute("grossProfitMargin", statsService.getGrossProfitMargin());
		
		model.addAttribute("report", report);
		model.addAttribute("labels", labels);
		model.addAttribute("revenue", revenue);
		model.addAttribute("profit", profit);
		
				
		return "admin/report";
	}
}
