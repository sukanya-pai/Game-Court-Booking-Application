package dev.sukanya.gamecourtbooking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration  //Says that this class is a Configuration class where we define our configs --> Here we do password encryption
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder encode(){
        //Bcrypt encoder is one way hashed
        return new BCryptPasswordEncoder(12);
        //Spring is a application context where there are loads of objects called Beans.
        // Using this bean, we can autowire Password encoder anywhere in the application
    }

    //once spring application is initialised
    //checks for all beans in app context
    //As soon as spring finds a bean of WebSecurityConfigAdapter
    //this tell spring that it needs spring security
    //AOP will inject code to authenticate the apis before running the code inside them
    //How does spring checks this:
    //Spring security checks config to inject the code

    //In runtime, spring receives a request
    //If that is a authenticated end point, Spring security takes charge
    //Possibilities of User state: logged out, logged in but not authorized, loggedin and authorized
    // if logged out, show login page
    //if loggedin but not authorized-- show auth error page
    // if logged in and authorized-- run the remaining code of controller


    //for  login page
    //You enter username,password
    //they are received by some endpoint internally provided by spring
    //spring security gets username and calls loadUserByUserName
    //if it finds user, it checks if there was a bean of password encoder --> here
    //there are two possibilities, present or not --> Bean or NoBean -->
    //if there are multiple beans of password encoder, we need to specify what bean id is to be used in securityConfig
    //if no bean, then password was not hashed, throw error
    //to define bean with name: @Bean(name="bcryptBean")


    //how to tell spring what to authorize and what not to
    //override configure method

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        //to authorize all requests
        //http.authorizeRequests().anyRequest();

        //disable csrf temporarily for testing
        //to match pattern matching of urls and disable csrf
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/user/register*").permitAll()
                .antMatchers("/mentee-dashboard/**").hasAnyRole("MENTEE","ADMIN")
                .antMatchers("/mentor-dashboard*").hasAnyRole("MENTOR", "ADMIN")
                .antMatchers("/admin-dashboard").hasRole("ADMIN")
                .antMatchers("/email/get*").hasAuthority("READ_EMAIL_ADDRESS")
        .and()
        .formLogin()
//        .loginPage("/onlineJudgeLogin")
        .loginProcessingUrl("/processLoginPage") //login request should go as POST Request
        .and()
        .logout();
        //.logoutSuccessHandler();
        //we can have authorities independent of roles. So that anyone can access the endpoint not linked to any role

        //formLogin -->
        //while telling

    }
}
