package com.starshop.entities;

import com.starshop.utils.OrderStatus;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.starshop.utils.Converter;
import com.starshop.utils.OrderStatus;
import com.starshop.utils.RoleName;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("SHIPPER")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Shipper extends User {
	@Column(columnDefinition = "boolean default true")
	private boolean isActive;
	
	@OneToMany(mappedBy = "shipper")
	private List<Order> orders;
	
	private LocalDateTime createdAt;

	@Override
    public String getRole() {
        return RoleName.SHIPPER.name();
    }
	
	public int getNumberOfOrders() {
		return this.orders.size();
	}
	
	public String getFormattedCreatedAt() {
		return Converter.localDateTimeToDateWithSlash(createdAt);
	}
	
	public int getSuccessRate() {
		float totalOrders = orders.size();		
		if (totalOrders == 0) 
			return 0;
		
		float successOrders = orders.stream().filter(o -> o.getCurrentStatus() == OrderStatus.DELIVERED)
					.collect(Collectors.toList()).size();
		int rate = Math.round((successOrders / totalOrders) * 100);
		return rate;
	}
}
