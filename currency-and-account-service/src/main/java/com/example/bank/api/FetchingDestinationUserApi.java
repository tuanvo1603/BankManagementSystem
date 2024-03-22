package com.example.bank.api;

import com.example.bank.dto.UserResponseDTO;
import com.example.bank.exception.AppException;
import com.example.bank.exception.ErrorCode;
import com.example.bank.model.Account;
import com.example.bank.repository.AccountRepository;
import com.example.bank.request.DestinationAccountRequest;
import com.example.bank.response.UserInfoResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static com.example.bank.utils.ApiUrl.AUTH_SERVICE_API_URL;

@Component
public class FetchingDestinationUserApi extends CommonApi<UserInfoResponse, DestinationAccountRequest>{

    @Autowired
    AccountRepository accountRepository;

    @Override
    public UserInfoResponse execute(DestinationAccountRequest request) throws JsonProcessingException {

        UserInfoResponse userInfoResponse = new UserInfoResponse();
        RestTemplate restTemplate = new RestTemplate();

        String destinationAccountNumber = request.getDestinationAccount();

        Account accountDestination = accountRepository.getUserIdByAccountNumber(destinationAccountNumber);
        if(accountDestination == null){
            return userInfoResponse;
        }
        String urlRequest = AUTH_SERVICE_API_URL + "user/"+accountDestination.getUserId();

        UserResponseDTO userResponse = restTemplate.getForObject(urlRequest, UserResponseDTO.class);
        userResponse.setAccountId(accountDestination.getAccountId());
        userInfoResponse.setUserResponseDTO(userResponse);
        return userInfoResponse;
    }
}
