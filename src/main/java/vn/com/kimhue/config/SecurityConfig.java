package vn.com.kimhue.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import vn.com.kimhue.CustomEmpDetailsService;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses = CustomEmpDetailsService.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    @Qualifier(value = "customEmpDetailsService")
//    private UserDetailsService userDetailsService;
//
//    @Autowired
//    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService);
//    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
httpSecurity.csrf().disable().authorizeRequests()
.antMatchers("/").permitAll();
//        httpSecurity.authorizeRequests()
//                .antMatchers("/").access("hasAnyRole('-','ROLE_ADMIN','ROLE_SUPER_ADMIN')")
//                .antMatchers("/my-request/**").access("hasAnyRole('ROLE_USER','ROLE_ADMIN','ROLE_SUPER_ADMIN')")
//                .antMatchers("/leader/**").access("hasAnyRole('ROLE_ADMIN','ROLE_SUPER_ADMIN')")
//                .antMatchers("/superadmin/**").access("hasRole('ROLE_SUPER_ADMIN')")
//                .and().formLogin().loginPage("/login").failureUrl("/login-error")
//                .usernameParameter("email")
//                .passwordParameter("password")
//                .and().logout().logoutSuccessUrl("/logout")
//                .and().csrf()
//                .and().exceptionHandling().accessDeniedPage("/403")
//                .and().rememberMe().key("rem-me-key")
//                .rememberMeParameter("remember-me-param")
//                .rememberMeCookieName("my-remember-me")
//                .tokenValiditySeconds(86400);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

}
