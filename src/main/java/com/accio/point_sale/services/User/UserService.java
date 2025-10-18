package com.accio.point_sale.services.User;

import java.util.List;

import com.accio.point_sale.domain.entities.User.User;

public interface UserService {
	User createUser(User user);

	List<User> getAllUsers();
}
