package com.example.JWT;



//        import com.iiitb.healthcare_abha.DAO.Employeeinterface;
//        import com.iiitb.healthcare_abha.Service.DoctorService;
        import com.example.admin.AdminService;
//        import com.example.user.UserService;
        import lombok.RequiredArgsConstructor;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.security.authentication.AuthenticationManager;
        import org.springframework.security.authentication.AuthenticationProvider;
        import org.springframework.security.authentication.ProviderManager;
        import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
        import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
        import org.springframework.security.core.userdetails.UserDetails;
        import org.springframework.security.core.userdetails.UserDetailsService;
        import org.springframework.security.core.userdetails.UsernameNotFoundException;
        import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
        import org.springframework.security.crypto.password.PasswordEncoder;

        import java.util.Arrays;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

// @Autowired
//    private Employeeinterface repository;


    @Autowired
    private final AdminService adminService;
//
//    @Autowired
//    private final UserService userService;

/*@Bean
    public UserDetailsService userDetailsService() {
        return username -> repository.findbyemail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }*/


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(adminService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



}
