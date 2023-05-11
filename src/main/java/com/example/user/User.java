package com.example.user;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class User  {
    
	   @Column(name="user_name")
	   private String name;
	   
	   @Id
	   @Column(name="user_email")
	   private String email;
	   
	   @Column(name="user_password")
	   private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		List<String> roles= new ArrayList<>();
//		roles.add("User");
//
//		System.out.println(roles);
//
//
//		List<SimpleGrantedAuthority> simpleGrantedAuthorityList=roles.stream().map(role->new SimpleGrantedAuthority(role)).collect(Collectors.toList());
//
//		System.out.println(simpleGrantedAuthorityList);
//		return simpleGrantedAuthorityList;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	@Override
//	public String getUsername() {
//		return null;
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		return true;
//	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	   
	   
	   
	   
}
