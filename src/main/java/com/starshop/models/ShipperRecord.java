package com.starshop.models;

import com.starshop.entities.User;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipperRecord {
	private User shipper;
	
	private Long successCount;
	
	private Long failedCount;
	
	private Long shippingCount;
}
