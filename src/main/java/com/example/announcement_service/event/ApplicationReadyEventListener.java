//package com.example.announcement_service.event;
//
//import com.example.announcement_service.dictionary.UserRole;
//import com.example.announcement_service.entity.User;
//import com.example.announcement_service.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.context.event.ApplicationReadyEvent;
//import org.springframework.context.annotation.Profile;
//import org.springframework.context.event.EventListener;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//@Component
//@Profile("dev")
//@RequiredArgsConstructor
//public class ApplicationReadyEventListener {
//    private static final String ADMIN_PHONE_NUMBER = "79999999999";
//
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    @EventListener(ApplicationReadyEvent.class)
//    public void onApplicationReady() {
//        if (userRepository.findUserByPhoneNumber(ADMIN_PHONE_NUMBER).isEmpty()) {
//            User user = User.builder()
//                    .firstName("Name")
//                    .lastName("Surname")
//                    .phoneNumber(ADMIN_PHONE_NUMBER)
//                    .password(passwordEncoder.encode("123"))
//                    .role(UserRole.ADMIN)
//                    .build();
//
//            User user1 = User.builder()
//                    .firstName("Name2")
//                    .lastName("Surname2")
//                    .phoneNumber("79999999998")
//                    .password(passwordEncoder.encode("123"))
//                    .role(UserRole.USER)
//                    .build();
//
//            userRepository.save(user);
//            userRepository.save(user1);
//        }
//    }
//
//}