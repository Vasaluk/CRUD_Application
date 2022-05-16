package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserServiceImp;

@Controller
@RequestMapping("/")
public class UsersController {

	@Autowired
	private UserServiceImp userService;

	@GetMapping()
	public String printUsers(ModelMap model) {
		model.addAttribute("users", userService.listAll());
		return "users/printUsers";
	}

	@GetMapping("/new")
	public String createUserForm(@ModelAttribute("user") User user) {
		return "users/newUser";
	}

	@PostMapping()
	public String createUser(@ModelAttribute("user") User user) {
		userService.saveUser(user);
		return "redirect:/";
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") Long id) {
		userService.deleteUser(id);
		return "redirect:/";
	}

	@GetMapping("/update/{id}")
	public String updateUserForm(@PathVariable("id") Long id, ModelMap model){
		model.addAttribute("user", userService.getUser(id));
		return "users/updateUser";
	}

	@PatchMapping("/{id}")
	public String updateUser(User user){
		userService.saveUser(user);
		return "redirect:/";
	}
}