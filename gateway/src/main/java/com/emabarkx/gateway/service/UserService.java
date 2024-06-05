package com.emabarkx.gateway.service;

import com.emabarkx.gateway.model.User;
import com.emabarkx.gateway.repo.RoleRepository;
import com.emabarkx.gateway.repo.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    UserRepository userRepository;
    RoleRepository roleRepository;

    public UserService(final UserRepository userRepository,final RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

//        List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
//                .map(role -> new SimpleGrantedAuthority(role.getName()))
//                .toList();

        return org.springframework.security.core.userdetails.User.withUsername(
                user.getUsername())
                .password(user.getPassword())
//                .authorities(authorities)
                .build();
    }
}
