package com.accio.point_sale.services.User;

import java.util.List;
import java.util.UUID;

import com.accio.point_sale.domain.entities.User;

public interface UserService {
	User createUser(User user);

	List<User> getAllUsers();

	User findById(UUID id);
}
