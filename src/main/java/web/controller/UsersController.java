package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/")
public class UsersController {

	@Autowired
	private UserService userService;

	@GetMapping()
	public String printUsers(ModelMap model) {
		model.addAttribute("users", userService.listAll());
		return "users/printUsers";
	}

	@GetMapping("/new")
	public String createFormUser(@ModelAttribute("user") User user) {
		return "users/newUser";
	}

	@PostMapping()
	public String createUser(@ModelAttribute("user") User user) {
		userService.saveUser(user);
		return "redirect:/";
	}

	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") Long id){
		userService.delete(id);
		return "redirect:/";
	}

	@GetMapping("/update/{id}")
	public String updateUserForm(@PathVariable("id") Long id, ModelMap model){
		User user = userService.get(id);
		model.addAttribute("user", user);
		return "users/updateUser";
	}

	@PostMapping("/update")
	public String updateUser(User user){
		userService.saveUser(user);
		return "redirect:/";
	}
}