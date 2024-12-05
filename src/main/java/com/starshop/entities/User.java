package com.starshop.entities;

import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role")
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED)
@Entity
@SuperBuilder
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = "user_name"),
		@UniqueConstraint(columnNames = "email") })
public abstract class User {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@JdbcTypeCode(SqlTypes.CHAR)	
	@Column(name = "user_id", updatable = false, nullable = false, columnDefinition = "char(36)")
	UUID id;

	@Column(name = "first_name", columnDefinition = "nvarchar(250)")
	String firstName;

	@Column(name = "last_name", columnDefinition = "nvarchar(250)")
	String lastName;

	@Size(min = 3, message = "Tên người dùng không hợp lệ")
	@Column(name = "user_name", unique=true)
	String userName;

	@Column(name = "phone_number")
	String phoneNumber;

	@Email
	@Column(nullable = false, unique = true)
	String email;

	@NotNull(message = "Mật khẩu không được để trống")
	@Size(min = 8, message = "Mật khẩu phải có ít nhất 8 ký tự")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z]).{8,}$", message = "Mật khẩu phải chứa ít nhất một chữ cái và một chữ số")
	@Column(nullable = false)
	String password;

	@Column(length = 500)
	String profileImg;
	
	public String getRole() {
        return this.getClass().getSimpleName();
    }

}
