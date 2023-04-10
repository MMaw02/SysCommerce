package pe.mmaw.projectventafactura.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import pe.mmaw.projectventafactura.model.service.LoginHandler;
import pe.mmaw.projectventafactura.model.service.UserDetailServiceImpl;

@Configuration
public class SecurityConfig {

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Autowired
    private LoginHandler loginHandler;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable().authorizeHttpRequests()
                .requestMatchers("/",
                        "/bootstrap/**",
                        "/css/**",
                        "/img/**",
                        "/jquery/**",
                        "/js/**",
                        "/list**",
                        "/login**",
                        "/access**",
                        "/register**",
                        "/images**").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login").successHandler(loginHandler)
                .and().logout().logoutSuccessUrl("/close");

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
