package com.example.announcement_service.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String role;
}
