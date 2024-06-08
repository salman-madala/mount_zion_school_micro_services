package com.emabarkx.gateway.controller;


import com.emabarkx.gateway.core.BaseController;
import com.emabarkx.gateway.jwt.JwtUtils;
import com.emabarkx.gateway.model.security.request.LoginRequest;
import com.emabarkx.gateway.model.security.response.JwtResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController extends BaseController {
    @Autowired
    private AuthenticationManager authenticationManager;

//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    private RoleRepository roleRepository;

//    @Autowired
//    PasswordEncoder encoder;

//    @Autowired
//    private EmailHelper emailHelper;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody @Valid LoginRequest loginRequest) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        } catch (AuthenticationException e) {
            Map<String, Object> map = new HashMap<>();
            map.put("message", "Bad credentials");
            map.put("status", false);
            return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String jwt = jwtUtils.generateJwtTokenFromUserName(userDetails);


        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt, 1111L,
                userDetails.getUsername(),
                "",
                roles));
    }

//    @PostMapping("/signup")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public ResponseEntity<?> registerUser(@RequestBody @Valid SignupRequest signUpRequest) {
//        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
//            return ResponseEntity
//                    .badRequest()
//                    .body(new MessageResponse("Error: Username is already taken!"));
//        }
//
//        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
//            return ResponseEntity
//                    .badRequest()
//                    .body(new MessageResponse("Error: Email is already in use!"));
//        }
//
//        // Create new user's account
//        User user = new User();
//        user.setFirstName(signUpRequest.getFirstName());
//        user.setLastName(signUpRequest.getLastName());
//        user.setPhoneNumber(signUpRequest.getPhoneNumber());
//        user.setUsername(signUpRequest.getUsername());
//        user.setPassword(encoder.encode(signUpRequest.getPassword()));
//        user.setEmail(signUpRequest.getEmail());
//        user.setImage(signUpRequest.getImage());
//
//        Set<String> strRoles = signUpRequest.getRole();
//        Set<Role> roles = new HashSet<>();
//
//        if (strRoles == null) {
//            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//            roles.add(userRole);
//        } else {
//            strRoles.forEach(role -> {
//                switch (role) {
//                    case "admin":
//                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(adminRole);
//
//                        break;
//                    case "mod":
//                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(modRole);
//
//                        break;
//                    default:
//                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(userRole);
//                }
//            });
//        }
//
//        user.setRoles(roles);
//        emailHelper.sendmail();
//        userRepository.save(user);
//
//        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
//    }


}
