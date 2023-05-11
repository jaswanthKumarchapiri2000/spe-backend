//package com.example.user;
//
//import com.example.admin.Admin;
//import com.example.admin.AdminRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class UserService implements UserDetailsService {
//
//    private final UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user= userRepository.findByEmail(username);
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found");
//        }
//        return user;
//    }
//
//
//
//
//
//
//}
//
