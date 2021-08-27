package com.user.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.user.Entity.User;
import com.user.Service.UserService;

@RequestMapping("user")
@RestController
public class UserController {
	
	private UserService userService;
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<User>> getAll() {
		return ResponseEntity.ok(userService.findAll());
	}
	
	@GetMapping("/{uid}")
	public ResponseEntity<User> findById(@PathVariable Long uid) {
		return ResponseEntity.ok(userService.findById(uid).orElse(null));
	}
	
	@PutMapping("/add")
	public ResponseEntity<User> createNew(@RequestBody User u) {
		return ResponseEntity.ok(userService.save(u));
	}
	
	@PostMapping("/update")
	public ResponseEntity<User> update(@RequestBody User u) {
		return ResponseEntity.ok(userService.save(u));
	}
	
	

	@DeleteMapping("/{uid}")
	public ResponseEntity<User> delete(@PathVariable Long uid) {
		userService.findById(uid).ifPresent(userService::delete);
		return ResponseEntity.ok().build();
	}

}
