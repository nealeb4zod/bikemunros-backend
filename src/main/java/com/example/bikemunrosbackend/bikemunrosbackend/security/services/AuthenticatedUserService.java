//package com.example.bikemunrosbackend.bikemunrosbackend.security.services;
//
//import com.example.bikemunrosbackend.bikemunrosbackend.models.User;
//import com.example.bikemunrosbackend.bikemunrosbackend.repositories.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;
//
//import java.util.UUID;
//
//@Service
//public class AuthenticatedUserService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    public boolean hasId(UUID id){
//        String username =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User user = userRepository.findByEmail(username);
//        return user.getId().equals(id);
//
//    }
//
//}
