package com.care_health.care_health.configurations;

import com.care_health.care_health.dtos.UserDTO;
import com.care_health.care_health.entity.User;
import com.care_health.care_health.repositories.IUsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final IUsersRepo usersRepo;

    public CustomUserDetailService(IUsersRepo usersRepo) {
        this.usersRepo = usersRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("find start user" + username);
        User user = usersRepo.findByUserName(username);
        System.out.println("find user" + user.getUserName());
        if (user == null) {
            throw new UsernameNotFoundException("User not found !!!");
        }

        return CustomUserDetail.mapUserToDetail(user);
    }
}
