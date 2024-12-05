package com.starshop.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starshop.models.MonthlyReport;
import com.starshop.repositories.OrderRepository;
import com.starshop.repositories.UserRepository;
import com.starshop.services.StatsService;
import com.starshop.utils.OrderStatus;

@Service
public class StatsServiceImpl implements StatsService {
	@Autowired
	private OrderRepository orderRepo;
	
	@Override
	public List<MonthlyReport> getMonthlyReport() {
		return orderRepo.getMonthlyReport();
	}

	@Override
	public long getTotalRevenue() {
		Long revenue = orderRepo.getTotalRevenue();
		return revenue == null ? 0 : revenue;
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
		Long profit = orderRepo.getTotalProfit();
		return profit == null ? 0 : profit;
	}

	@Override
	public double getGrossProfitMargin() {
		long revenue = getTotalRevenue();
		if (revenue == 0)
			return 0;
		double res = getTotalProfit() / getTotalRevenue();
		return Math.round(res * 100.0) / 100.0;
	}

}
