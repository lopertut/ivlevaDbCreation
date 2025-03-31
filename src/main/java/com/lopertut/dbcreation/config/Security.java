package com.lopertut.dbcreation.config;

import com.lopertut.dbcreation.entity.Roles;
import com.lopertut.dbcreation.entity.User;
import com.lopertut.dbcreation.repositories.UserRepository;
import com.lopertut.dbcreation.services.CustomUserDetailsService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
@Configuration
public class Security {

    private final UserRepository userRepository;

    public Security(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public CommandLineRunner createSuperUser(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setEmail("admin@example.com");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole(Roles.ROLE_ADMIN);
                userRepository.save(admin);
                System.out.println("Admin created: admin / admin123");
            }
        };
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService(userRepository);
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailsService());
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authenticationProvider(daoAuthenticationProvider());

        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/articles", "/articles", "/articles/**").permitAll()
                        .requestMatchers("/registration", "/login").permitAll()
                        .requestMatchers("/static/**", "/styles.css").permitAll()

                        .requestMatchers("/users/delete/**", "/tags/delete/**").hasRole("ADMIN")

                        .anyRequest().authenticated()

                )
                .formLogin(login ->
                        login.usernameParameter("email")
//                                .loginPage("/login")
                                .defaultSuccessUrl("/")
                                .permitAll()
                )
                .logout(logout -> logout.logoutSuccessUrl("/").permitAll()
                );

        return http.build();
    }
}
