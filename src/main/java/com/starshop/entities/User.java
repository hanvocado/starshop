package com.starshop.entities;

import java.util.Set;
import java.util.UUID;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = "user_name"),
		@UniqueConstraint(columnNames = "email") })
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "user_id", updatable = false)
	private UUID id;

	@Column(name = "first_name", columnDefinition = "nvarchar(250)")
	private String firstName;

	@Column(name = "last_name", columnDefinition = "nvarchar(250)")
	private String lastName;

	@Size(min = 3, message = "Tên người dùng không hợp lệ")
	@Column(name = "user_name", unique=true)
	private String userName;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Email
	@Column(nullable = false, unique = true)
	private String email;

	@Column(name = "default_address_id")
	private int defaultAddressId;

	@NotNull(message = "Mật khẩu không được để trống")
	@Size(min = 8, message = "Mật khẩu phải có ít nhất 8 ký tự")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z]).{8,}$", message = "Mật khẩu phải chứa ít nhất một chữ cái và một chữ số")
	@Column(nullable = false)
	private String password;

	@Column(length = 500)
	private String profileImg;

	@OneToMany(mappedBy = "user")
	private Set<Wishlist> wishlists;

	@ManyToOne
	@JoinColumn(name = "role_id", nullable = false)
	private Role role;

}
