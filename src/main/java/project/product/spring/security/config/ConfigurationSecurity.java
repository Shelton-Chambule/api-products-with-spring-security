package project.product.spring.security.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class ConfigurationSecurity {

    private final SecurityFilter securityFilter;

    public ConfigurationSecurity(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {
        return httpSecurity.csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.
                        sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST,"/authentication/create/user").permitAll()
                        .requestMatchers(HttpMethod.POST,"/authentication/login").permitAll()
                        .requestMatchers(HttpMethod.POST,"/product/save").hasAnyRole("MANAGER","ADMIN")
                        .requestMatchers(HttpMethod.GET,"/product/find/all").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT,"/product/{productId}").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE,"/product/{productId}").hasAnyRole("MANAGER","ADMIN")
                        .requestMatchers(HttpMethod.POST,"/category/save").hasAnyRole("MANAGER", "ADMIN")
                        .requestMatchers(HttpMethod.GET,"/category/find/all").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT,"/category/{categoryId}").hasRole("MANAGER")
                        .anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class).build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration){
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
