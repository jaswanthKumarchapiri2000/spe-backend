package com.example.user;

import java.util.List;

import com.example.JWT.JwtService;
import com.example.admin.Admin;
import com.example.admin.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	   @Autowired
	   private UserRepository userRepository;

//	   @Autowired
//	   private UserService userService;


		@Autowired
		private JwtService jwtService;

		@Autowired
		private AuthenticationManager authenticationManager;


		@Autowired
		private PasswordEncoder passwordEncoder;

	     // get all user
		   @GetMapping("/user")
		   @PreAuthorize("hasAnyAuthority('Admin')")
		   public List<User> getAllUser() {
			   return (List<User>)this.userRepository.findAll();
		   }


	   // get user detail by its email
	   @GetMapping("/user/{email}")
	   @PreAuthorize("hasAnyAuthority('Admin')")
	   public User getUserDetails(@PathVariable("email") String email) {
			   System.out.println("+++++++ get user details called");
			   return this.userRepository.findByEmail(email);
	   }


	   // to add a new user
	   @PostMapping("/user")
	   public User addNewUser(@RequestBody User user) {
//		   String encodedPassword = passwordEncoder.encode(user.getPassword());
//		   user.setPassword(encodedPassword);
		   return this.userRepository.save(user);
	   }

//		@PostMapping("user/login")
//		public String Admin_login(@RequestBody User user) throws Exception {
//			System.out.println("++++++++++++ user login called");
//			authenticationManager.authenticate(
//					new UsernamePasswordAuthenticationToken(
//							user.getEmail(),
//							user.getPassword()
//					)
//			);
//			UserDetails userDetails=userService.loadUserByUsername(user.getEmail());
//			System.out.println(userDetails.getAuthorities());
//
//			String  jwtToken = jwtService.generateToken(userDetails);
//			return jwtToken;
//
//		}



}
