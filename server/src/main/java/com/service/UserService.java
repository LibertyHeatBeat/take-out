package com.service;

import com.dto.UserLoginDTO;
import com.entity.User;

public interface UserService {
    User wxLogin(UserLoginDTO userLoginDTO);
}
