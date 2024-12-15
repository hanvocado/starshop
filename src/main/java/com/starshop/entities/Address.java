package com.starshop.entities;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
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

	public String getAddress() {
		return String
				.join(", ", houseNumber == null ? "" : houseNumber, street == null ? "" : street,
						ward == null ? "" : ward, district == null ? "" : district, city == null ? "" : city)
				.replaceAll(",\\s*,", ",").replaceAll("(^,\\s*|,\\s*$)", "");
	}

}
