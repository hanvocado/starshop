package com.starshop.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthlyShipperRecord implements Serializable {
	private int year;
    private int month;
    private Long successCount;
    private Long totalCount;
    
    public String getLabel() {
    	String value = String.valueOf(month) + "/" + String.valueOf(year);
    	return String.format("\"%s\"", value);
    }
}
