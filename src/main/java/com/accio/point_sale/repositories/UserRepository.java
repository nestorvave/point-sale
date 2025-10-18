package com.accio.point_sale.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accio.point_sale.domain.entities.User.User;

public interface UserRepository extends JpaRepository<User, UUID> {

	User findByName(String name);
	boolean existsByName(String name);

}
