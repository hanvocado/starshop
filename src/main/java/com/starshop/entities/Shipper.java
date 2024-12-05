package com.starshop.entities;

import java.util.List;

import com.starshop.utils.RoleName;

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
	
	@OneToMany(mappedBy = "shipper")
	private List<Order> orders;

	@Override
    public String getRole() {
        return RoleName.SHIPPER.name();
    }
}
