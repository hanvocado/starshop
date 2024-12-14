package com.starshop.controllers.shipper;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.starshop.models.MonthlyShipperRecord;
import com.starshop.models.ShipperRecord;
import com.starshop.services.ShipperService;

@Controller
@RequestMapping("/shipper/dashboard")
public class ShipperDashboardController {
	@Autowired
	private ShipperService shipperService;
	
	@GetMapping("")
	public String index(Model model, Integer year) {
		String shipperUsername = SecurityContextHolder.getContext().getAuthentication().getName();
		ShipperRecord shipperRecord = shipperService.getRecordByShipperUsername(shipperUsername);
		model.addAttribute("record", shipperRecord);
		
		if (year == null) {
			year = LocalDate.now().getYear();
		}
		model.addAttribute("selectedYear", year);
		
		List<MonthlyShipperRecord> monthlyRecords = shipperService.getMonthlyRecordByShipperUsername(shipperUsername, year);
		
		List<String> labels = monthlyRecords.stream().map(o -> o.getLabel()).collect(Collectors.toList());
		List<Long> totalCounts = monthlyRecords.stream()
	            .map(MonthlyShipperRecord::getTotalCount)
	            .collect(Collectors.toList());		
		List<Long> successCounts = monthlyRecords.stream()
	            .map(MonthlyShipperRecord::getSuccessCount)
	            .collect(Collectors.toList());
		
		model.addAttribute("labels", labels);
		model.addAttribute("totalCounts", totalCounts);
		model.addAttribute("successCounts", successCounts);
		
		return "shipper/dashboard";
	}
}
