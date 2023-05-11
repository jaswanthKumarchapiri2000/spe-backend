package com.example.admin;

import com.example.JWT.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private AdminRepository adminRepository ;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/admin/register")
	public Admin saveAdminDetails(@RequestBody Admin admin) {
		// Save the admin details to the database using the AdminRepository object
		System.out.println("++++++++++++");

		String encodedPassword = passwordEncoder.encode(admin.getPassword());
		admin.setPassword(encodedPassword);
		return this.adminRepository.save(admin);

		// Return a success response with a message
//		return "Admin details registered successfully";
	}
	
	// get admin detail by admin_name
	
	@GetMapping("/admin/{name}")
	@PreAuthorize("hasAnyAuthority('Admin')")
	public Admin getAdminDetails(@PathVariable("name") String name){
		System.out.println("++++++++++++ admin name called");
		return this.adminRepository.findByName(name);
	}

	@GetMapping("/admin/test")
	@PreAuthorize("hasAnyAuthority('Admin')")
	public String testAdminEndpoint() {
		System.out.println("++++++++++++ admin test called");
		return "Hello from the admin endpoint";
	}

	@PostMapping("admin/login")
	public String Admin_login(@RequestBody Admin  admin) throws Exception {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						admin.getName(),
						admin.getPassword()
				)
		);
		UserDetails userDetails=adminService.loadUserByUsername(admin.getName());
		System.out.println(userDetails.getAuthorities());

		String  jwtToken = jwtService.generateToken(userDetails);
		return jwtToken;

	}



}
