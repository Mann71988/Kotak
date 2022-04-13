package productSecurity;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("user").password("{foo}password").roles("USER")
                .and()
                .withUser("admin").password("{foo}password").roles("USER", "ADMIN");

    }

    
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                //HTTP Basic authentication
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/getAllProducts/**").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/getProductById/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/addProduct").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/updateProduct/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/deleteProduct/**").hasRole("ADMIN")
                .and()
                .csrf().disable()
                .formLogin().disable();
    }

}
