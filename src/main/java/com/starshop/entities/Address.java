package com.starshop.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "address")
public class Address {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer; 

    private String houseNumber;
    private String street;
    private String ward;
    private String district;
    private String city;
    
    private Double latitude;
    private Double longitude;
    
	@Override
	public String toString() {
		return houseNumber + " " + street + " " + ward + " "
				+ district + " " + city;
	}  
    
}
