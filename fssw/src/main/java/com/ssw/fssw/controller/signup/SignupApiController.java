package com.ssw.fssw.controller.signup;

import com.ssw.fssw.domain.User;
import com.ssw.fssw.repository.UserApiRepository;
import com.ssw.fssw.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
public class SignupApiController {

    private final UserApiRepository userApiRepository;

    @GetMapping("/api/{EmailInput}/signup")
    public ResponseEntity<Boolean> checkIdDuplication(@PathVariable String EmailInput){
        ResponseEntity<Boolean> result = ResponseEntity.ok(userApiRepository.existsByEmailId(EmailInput));
        return result;
    }

}
