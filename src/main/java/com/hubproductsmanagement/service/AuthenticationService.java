package com.hubproductsmanagement.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthenticationService {


    public boolean authenticate(HttpHeaders headers) {

        if (headers.containsKey(HttpHeaders.AUTHORIZATION)) {
            String authorizationHeader = headers.getFirst(HttpHeaders.AUTHORIZATION);
            if (authorizationHeader!=null && authorizationHeader.startsWith("Basic ")) {
                return true;
            }
        }
        return false;
    }



}
