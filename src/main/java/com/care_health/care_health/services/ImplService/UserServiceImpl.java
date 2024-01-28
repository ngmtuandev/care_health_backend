package com.care_health.care_health.services.ImplService;

import com.care_health.care_health.configurations.CustomUserDetail;
import com.care_health.care_health.configurations.JwtProvider;
import com.care_health.care_health.dtos.UserDTO;
import com.care_health.care_health.dtos.request.user.LoginRequestDTO;
import com.care_health.care_health.dtos.request.user.RegisterRequestDTO;
import com.care_health.care_health.entity.Roles;
import com.care_health.care_health.entity.User;
import com.care_health.care_health.enums.ERole;
import com.care_health.care_health.repositories.IUsersRepo;
import com.care_health.care_health.services.IServices.IRoleService;
import com.care_health.care_health.services.IServices.IUserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    private final IUsersRepo usersRepo;

    private final IRoleService roleService;

    private final AuthenticationManager authenticationManager;

    private final JwtProvider jwtProvider;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(IUsersRepo usersRepo, IRoleService roleService, AuthenticationManager authenticationManager, JwtProvider jwtProvider, PasswordEncoder passwordEncoder) {
        this.usersRepo = usersRepo;
        this.roleService = roleService;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findByUserName(String userName) {
        return usersRepo.findByUserName(userName);
    }

    @Override
    public boolean existsByUserName(String userName) {
        return usersRepo.existsByUserName(userName);
    }

    @Override
    public boolean existsByEmail(String email) {
        return usersRepo.existsByEmail(email);
    }

    @Override
    public String register(RegisterRequestDTO request) {

        if (existsByUserName(request.getUserName())) {
            return "user da ton tai";
        }
        if (existsByEmail(request.getEmail())) {
            return "email da ton tai";
        }

        User newUser = new User();
        newUser.setUserName(request.getUserName());
        newUser.setEmail(request.getEmail());

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        newUser.setPassword(encodedPassword);

        newUser.setUserStatus(true);
        newUser.setLastModifiedBy(null);
        newUser.setCreatedBy(request.getUserName());

        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        Date dateCreated = new Date();
        String dateNowCreated = formatDate.format(dateCreated);
        try {
            newUser.setCreatedDate(formatDate.parse(dateNowCreated));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Set<String> roleStrRequest = request.getListRoles();
        Set<Roles> listRoles = new HashSet<>();

        if (roleStrRequest == null) {
            Roles userRoleInit = roleService.findByRoleName(ERole.ROLE_USER).orElseThrow(
                    () -> new RuntimeException("Error Role not found")
            );
            listRoles.add(userRoleInit);
        }
        else {
            roleStrRequest.forEach(roleItem -> {
                switch (roleItem) {
                    case "admin":
                        Roles roleAdmin = roleService.findByRoleName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Role not found"));
                        listRoles.add(roleAdmin);
                        break;
                    case "edit":
                        Roles roleEdit = roleService.findByRoleName(ERole.ROLE_EDIT)
                                .orElseThrow(() -> new RuntimeException("Role not found"));
                        listRoles.add(roleEdit);
                        break;
                    case "user":
                        Roles roleUser = roleService.findByRoleName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Role not found"));
                        listRoles.add(roleUser);
                        break;
                }
            });
        }

        newUser.setListRoles(listRoles);
        usersRepo.save(newUser);

        return "register successfully";
    }

    @Override
    public String login(LoginRequestDTO requestLogin) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestLogin.getUserName(), requestLogin.getPassword()));

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        SecurityContextHolder.getContext().setAuthentication(authentication);

        CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();

        /* generate token */
        String jwt = jwtProvider.generateToken(customUserDetail);



        List<String> listRoles = customUserDetail.getAuthorities().stream()
                .map(item -> item.getAuthority()).collect(Collectors.toList());

        return jwt;
    }
}
