package idu.cs.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import idu.cs.domain.User;
import idu.cs.entity.UserEntity;
import idu.cs.exception.ResourceNotFoundException;
import idu.cs.repository.UserRepository;
import idu.cs.service.UserService;

@Controller
//@Component, @Service, @Repository, @Index
//Spring Framework에게 클래스로 부터 작성된 객체는 Controller 역할을 함을 알려줌
//Spring 이 클래스로 부터  Bean 객체를 생성해서 등록할 수 있음
public class UserController {
	@Autowired UserService userService; // Dependency Injection
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("test", "인덕 컴소");
		model.addAttribute("egy", "유응구");
		return "index";
	}
	@GetMapping("/user-login-form")
	public String getLoginForm(Model model) {
		return "login";
	}
	
	@PostMapping("/login")
	public String loginUser(@Valid UserEntity user, HttpSession session) {
		System.out.println("login process : " + user.getUserId());
		User sessionUser = userService.getUserByUserId(user.getUserId());
		if(sessionUser == null) {
			System.out.println("id error : ");
			return "redirect:/user-login-form";
		}
		if(!sessionUser.getUserPw().equals(user.getUserPw())) {
			System.out.println("pw error : ");
			return "redirect:/user-login-form";	
		}
		System.out.println("로그인 성공 : ");
		session.setAttribute("user", sessionUser);
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logoutUser(HttpSession session) {
		session.removeAttribute("user");
		//session.invalidate(); 세션에 있는 것 다 날려버림
		return "redirect:/";
	}
	
	@GetMapping("/users")
	public String getAllUser(Model model, HttpSession session) {		
		//model.addAttribute("users", userRepo.findAll())''
		model.addAttribute("users", userService.getUsers());
		return "userlist";		
	}
	
	@GetMapping("/user-register-form")
	public String getRegForm(Model model) {
		return "register";
	}
	
	@GetMapping("/user-update-form")
	public String getUpdateForm(Model model, HttpSession session) {
		// 서비스를 통해 리파지터리로 부터 정보를 가져와야 하나 세션에 저장해두었으므로 정보를 활용
		User user = (User) session.getAttribute("user");
		//User sessionUser = userService.getUserById(user.getId());
		//model.addAttribute("user", sessionUser);
		model.addAttribute("user", user);
		return "info";
	}
	
	
	@PostMapping("/users")
	public String createUser(@Valid User user, Model model) {
		userService.saveUser(user);		
		return "redirect:/users"; //get 방식으로 해당  url로 재지정함
	}
	
	@GetMapping("/update")
	public String infoUser(HttpSession session) {
		
		return "/userinfo";
	}
	/*
	@GetMapping("/users/{id}")
	public String getUserById(@PathVariable(value = "id") Long userId, Model model)
			throws ResourceNotFoundException {
		UserEntity user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
		//위에는 우리가 원하는대로 예쁘게 출력
		//User user = userRepo.findById(userId).get();//에러 자바에서 출력 
		
		//model.addAttribute("id", user.getId());
		//model.addAttribute("name", user.getName());
		//model.addAttribute("company", user.getCompany());
		//↓
		model.addAttribute("user", user);
		return "user";
		//return ResponseEntity.ok().body(user);
	}
	@GetMapping("/users/fn")	//fn:url이 변경됨
	public String getUserByName(@Param(value="name") String name, Model model)
			throws ResourceNotFoundException {
		List<UserEntity> users = userRepo.findByName(name);
		model.addAttribute("users", users);
		return "userlist";
		//return ResponseEntity.ok().body(user);
	}
	*/
	@PutMapping("/users/{id}") //@PatchMapping 수정한 부분만 수정되는 것
	public String updateUser(@PathVariable(value = "id") Long id, @Valid User user, Model model, HttpSession session) {
		user.setId(userService.getUserById(id).getId());
		userService.updateUser(user);	
		session.setAttribute("user", user);
		return "redirect:/users";
	}
	/*
	@DeleteMapping("/users/{id}")
	public String deleteUser(@PathVariable(value = "id") Long userId, Model model) {
		UserEntity user = userRepo.findById(userId).get();//select
		userRepo.delete(user);//delete
		model.addAttribute("name", user.getName());
		return "user-deleted";
	}
	*/
}


