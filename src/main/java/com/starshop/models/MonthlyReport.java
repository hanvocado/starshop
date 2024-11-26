package com.starshop.models;
import java.io.Serializable;

import lombok.*;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthlyReport implements Serializable {
    private int year;
    private int month;
    private double revenue;
    private double profit;
    
    public String getLabel() {
    	String value = String.valueOf(month) + "/" + String.valueOf(year);
    	return String.format("\"%s\"", value);
    }
}
