package com.example.admin;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
public class Admin implements UserDetails {
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private int id;
	  
      @Column(name="admin_name")
	  private String name ;
      
      @Column(name="admin_password")
	  private String  password ;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<String> roles= new ArrayList<>();
		roles.add("Admin");

		System.out.println(roles);


		List<SimpleGrantedAuthority> simpleGrantedAuthorityList=roles.stream().map(role->new SimpleGrantedAuthority(role)).collect(Collectors.toList());

		System.out.println(simpleGrantedAuthorityList);
		return simpleGrantedAuthorityList;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return this.name;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	    
	
	  
	  
	
	  
	  
}
