package com.starshop.services;

import java.util.List;

import com.starshop.models.MonthlyReport;

public interface StatsService {

	List<MonthlyReport> getMonthlyReport();
	
	long getTotalRevenue();
	
	long getTotalProfit();
	
	double getGrossProfitMargin();

	long getNumbersOfOrders();
	
	long getNumberOfDeliveredOrders();

	long getNumbersOfCustomers();
}
