package com.accio.point_sale.services.User;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.accio.point_sale.domain.entities.User;
import com.accio.point_sale.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;
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

	@Override
	public User findById(UUID id) {
		return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
	}

}
