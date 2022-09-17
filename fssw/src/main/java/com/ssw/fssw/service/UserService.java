package com.ssw.fssw.service;

import com.ssw.fssw.domain.User;
import com.ssw.fssw.repository.UserApiRepository;
import com.ssw.fssw.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final UserApiRepository userApiRepository;

    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);
    }

}
