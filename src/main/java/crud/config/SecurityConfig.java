package crud.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailService;
    private LoginSuccessHandler successUserHandler;
    private PasswordEncoder passwordEncoder;

    public SecurityConfig(UserDetailsService userDetailService, LoginSuccessHandler successUserHandler, PasswordEncoder passwordEncoder) {
        this.userDetailService = userDetailService;
        this.successUserHandler = successUserHandler;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("ADMIN").password("ADMIN").roles("ADMIN");
//        auth.inMemoryAuthentication().withUser("USER").password("USER").roles("USER");
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/login")
//                .loginPage("/")
                .successHandler(successUserHandler)
                .loginProcessingUrl("/login")
                .usernameParameter("j_username")
                .passwordParameter("j_password")
                .permitAll();
        http.logout()
                .permitAll()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .and().csrf().disable();
        http.authorizeRequests()
                .antMatchers("/login").anonymous()
                .antMatchers("/user").access("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
                .antMatchers("/admin").access("hasAnyRole('ROLE_ADMIN')")
                .anyRequest()
                .authenticated();
    }

}
