package com.starshop.entities;

import java.time.LocalDateTime;
import java.util.List;

import com.starshop.utils.Converter;
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
}
