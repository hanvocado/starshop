package com.starshop.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starshop.entities.Role;
import com.starshop.models.MonthlyReport;
import com.starshop.repositories.OrderRepository;
import com.starshop.repositories.UserRepository;
import com.starshop.services.StatsService;
import com.starshop.utils.OrderStatus;
import com.starshop.utils.RoleName;

@Service
public class StatsServiceImpl implements StatsService {
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public List<MonthlyReport> getMonthlyReport() {
		return orderRepo.getMonthlyReport();
	}

	@Override
	public long getTotalRevenue() {
		return orderRepo.getTotalRevenue();
	}

	@Override
	public long getNumbersOfOrders() {
		return orderRepo.count();
	}

	@Override
	public long getNumberOfDeliveredOrders() {
		return orderRepo.countByCurrentStatus(OrderStatus.DELIVERED);
	}

	@Override
	public long getTotalProfit() {
		return orderRepo.getTotalProfit();
	}

	@Override
	public double getGrossProfitMargin() {
		double res = getTotalProfit() / getTotalRevenue();
		return Math.round(res * 100.0) / 100.0;
	}

}
