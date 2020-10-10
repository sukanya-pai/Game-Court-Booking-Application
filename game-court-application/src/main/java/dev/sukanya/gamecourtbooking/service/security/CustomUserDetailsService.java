package dev.sukanya.gamecourtbooking.service.security;

import dev.sukanya.gamecourtbooking.model.user.Permission;
import dev.sukanya.gamecourtbooking.model.user.Role;
import dev.sukanya.gamecourtbooking.model.user.User;
import dev.sukanya.gamecourtbooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
//this class is to map security features given by SpringSecurity by default
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    /**
     *  as soon as we give user name and password, Spring will call this class's method loadUserByUserName
     *  it will get password, email and isActive and
     * @param s
     * @return User
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(s);
        if(user==null) throw new UsernameNotFoundException("No user found with that email");


        //org.springframework.security.core.userdetails.User implements UserDetails, hence we can pass this as result
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.isActive(),
                false,
                true,
                true,
                getAuthorities(user.getRoles())
        );

    }

    /**
     * Get Authorities from the role the user has
     * @param userRoles
     * @return list of granted authorities
     */
    private List<GrantedAuthority> getAuthorities(List<Role> userRoles){
        //fetch role for that particular User -> List<String>
        //fetch permission for that Role-> List<String>
        //map the roles and permissions to authorities of User object

        List<GrantedAuthority> authorities = new ArrayList<>();
        for(Role role:userRoles){
            for(Permission permission:role.getPermissions() ){
                authorities.add(new SimpleGrantedAuthority(permission.getName()));
            }
        }

        return authorities;
    }

}
