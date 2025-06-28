package com.example.announcement_service.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class UserExistsResponse {
    private boolean exists;
}