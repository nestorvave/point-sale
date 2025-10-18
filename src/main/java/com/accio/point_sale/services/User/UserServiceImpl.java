package com.accio.point_sale.services.User;

import java.util.List;

import org.springframework.stereotype.Service;

import com.accio.point_sale.domain.entities.User.User;
import com.accio.point_sale.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Override
	public User createUser(User user) {
		return userRepository.save(user);

	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

}
