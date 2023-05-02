package com.hung.albumphoto.service;

import com.hung.albumphoto.dto.UserDTO;
import com.hung.albumphoto.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {
    UserEntity save(UserDTO user);
}
