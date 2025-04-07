package com.example.JournalApplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // ✅ CSRF Disable for testing
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login.html", "/css/**", "/js/**").permitAll()  // ✅ Static files allow
                        .requestMatchers("/hello/**").permitAll()  // ✅ `/hello/**` ke sare API bina login ke accessible honge
                        .requestMatchers("/users/**").permitAll()   // ✅ User API without authentication
                        .requestMatchers("/weather").permitAll()
                        .requestMatchers("/cron/**").permitAll()
                        .anyRequest().authenticated()  // ✅ Baki sab authenticated requests rahengi
                )
                .formLogin(form -> form
                        .loginPage("/login.html")   // ✅ Custom login page
                        .loginProcessingUrl("/login")  // ✅ Form POST request handle
                        .permitAll()

                        .defaultSuccessUrl("/hello", true)
                )
                .logout(logout -> logout.logoutUrl("/logout"))

                .cors(cors -> {}); // ✅ ✅ Latest method for enabling CORS
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails admin = User.withUsername("ravi")
                .password(passwordEncoder().encode("12345"))  // ✅ Secure password
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // ✅ Password Encoding Enabled
    }
}










//
//
//package com.example.JournalApplication.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())  // ✅ CSRF Disable for testing
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/users/**").permitAll()   // ✅ User API without authentication
//                        .requestMatchers("/hello/**").permitAll()
//                        .requestMatchers("/weather").permitAll()
//                        .requestMatchers("/cron/**").permitAll()
//                        .anyRequest().permitAll()  // ✅ Sab API bina authentication accessible honge
//                )
//                .formLogin(form -> form.disable()) // ✅ Login page hata diya
//                .logout(logout -> logout.disable()); // ✅ Logout bhi disable
//
//        return http.build();
//    }
//}
