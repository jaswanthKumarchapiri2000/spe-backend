package com.example.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AdminController {

	private  final AuthenticationService service;

	private final AdminRepository adminRepository ;
	
	// get admin detail by admin_name
	
	@GetMapping("/admin/{name}")
	public Optional<Admin> getAdminDetails(@PathVariable("name") String name){
		return adminRepository.findByName(name);
	}

	@PostMapping("/admin/")
	public ResponseEntity<AuthenticateResponce> authenticate(
			@RequestBody AuthenticationRequest request
	) {
		return ResponseEntity.ok((AuthenticateResponce) service.authenticate(request));
	}

}
