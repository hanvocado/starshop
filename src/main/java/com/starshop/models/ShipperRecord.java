package com.starshop.models;

import com.starshop.entities.Shipper;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipperRecord {
	private Shipper shipper;
	
	private Long successCount;
	
	private Long failedCount;
	
	private Long shippingCount;
}
