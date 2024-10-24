package com.realState.realEstate.user;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.realState.realEstate.Estate.Estate;

@RestController
@RequestMapping(path = "api/users")
public class UserController {
	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public List<Map<String, Object>> getUsers() {
		List<UserApp> users = this.userService.getUsers();
	    return users.stream().map(user -> user.toJson() ).collect(Collectors.toList());
	}
	
	@GetMapping(path = "{userId}")
	public Map<String, Object> getUser(@PathVariable("userId") Long id) {
		UserApp user = this.userService.getUser(id);
	    return user.toJson();
	}
	
	@PostMapping
	public void postUser(@RequestBody UserApp user) {
		this.userService.addUser(user);
	}
	
	@PutMapping(path = "{userId}")
	public void updateUser(
			@PathVariable("userId") Long userId,
			@RequestParam(required = false) String firstname,
			@RequestParam(required = false) String lastname,
			@RequestParam(required = false) String email
			) {
		this.userService.updateUser(userId, firstname, lastname, email);
	}
	
	@DeleteMapping(path = "{userId}")
	public void deleteUser(@PathVariable("userId") Long id){
		this.userService.deleteUser(id);
	}

}
