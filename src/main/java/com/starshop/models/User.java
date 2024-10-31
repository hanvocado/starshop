package com.starshop.models;

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
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "user_name"))
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "user_id", updatable = false)
	private UUID id;

    @Size(min = 3, message = "Tên người dùng không hợp lệ")
	@Column(name="user_name" ,nullable = false)
	private String userName;

	@Column(name="phone_number", nullable = false, unique = true)
	private String phoneNumber;

	@Email
	@Column(nullable = false, unique = true)
	private String email;

	@Column(name="default_address_id")
	private int defaultAddressId;

	@NotNull(message = "Mật khẩu không được để trống")
    @Size(min = 8, message = "Mật khẩu phải có ít nhất 8 ký tự")
    @Pattern(
        regexp = "^(?=.*[0-9])(?=.*[a-zA-Z]).{8,}$",
        message = "Mật khẩu phải chứa ít nhất một chữ cái và một chữ số"
    )
	@Column(nullable = false)
	private String password;

	@Column(length = 500)
	private String profileImg;

	private int roleId;

	@OneToMany(mappedBy = "user")
	Set<Wishlist> wishlists;
}
