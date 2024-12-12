package com.starshop.services;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.starshop.entities.User;

@Service
public class ChatQueue {
	private static List<User> store = new LinkedList<>();
	
	public List<User> getMembersList() {
		return store;
	}
	
	public List<User> filterMemberListByUser(User user) {
		return store.stream()
				.filter(filterUser -> filterUser.getId() != user.getId())
				.toList();
	}
	
	public User getMember(UUID userId) {
		return store.stream()
				.filter(user -> user.getId() == userId)
				.findFirst().orElseThrow();
	}
	
	public void addMember(User member) {
		store.add(member);
	}
	
	public void removeMember(User member) {
		store.remove(member);
	}
}
