package dev.sukanya.gamecourtbooking.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO {

    private Long id;

    private String email;

    private String fullName;

    private boolean isActive;

    public UserResponseDTO(Long id, String email, String fullName, boolean isActive) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.isActive = isActive;
    }

    /**While sending response from Controller, we follow by building a ResponseDTO object so that we can only
     * send necessary details and not all.
     * For user, its not necessary that we send the stored, password and roles, hence we dont use it here
     * */

}
