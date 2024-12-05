package com.starshop.entities;

import com.starshop.utils.RoleName;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("ADMIN")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Admin extends User {
	@Override
    public String getRole() {
        return RoleName.ADMIN.name();
    }
}
