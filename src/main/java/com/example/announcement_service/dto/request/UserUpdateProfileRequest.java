package com.example.announcement_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateProfileRequest {

    @NotBlank(message = "Имя обязательно")
    @Size(max = 50, message = "Имя не должно быть пустым и более 50 символов")
    private String firstName;

    @NotBlank(message = "Фамилия обязательна")
    @Size(max = 50, message = "Фамилия не должна быть пустой и более 50 символов")
    private String lastName;

}
