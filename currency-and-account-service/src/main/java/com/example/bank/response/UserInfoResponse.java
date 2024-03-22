package com.example.bank.response;

import com.example.bank.dto.UserResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserInfoResponse extends ApiResponse{

    UserResponseDTO userResponseDTO;

}
