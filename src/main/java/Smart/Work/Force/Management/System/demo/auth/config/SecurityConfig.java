package Smart.Work.Force.Management.System.demo.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
     public class SecurityConfig {
         @Bean
         public PasswordEncoder passwordEncoder() {
             return new BCryptPasswordEncoder();
         }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. Allow everyone to access every endpoint
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                )
                // 2. Disable CSRF (Crucial for POST/PUT/DELETE requests)
                .csrf(csrf -> csrf.disable())
                // 3. Disable Frame Options (To allow H2 Console if you use it)
                .headers(headers -> headers.frameOptions(frame -> frame.disable()));

        return http.build();
    }
}
