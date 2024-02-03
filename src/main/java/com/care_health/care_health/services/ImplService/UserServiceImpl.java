package com.care_health.care_health.services.ImplService;

import com.care_health.care_health.configurations.CustomUserDetail;
import com.care_health.care_health.configurations.JwtProvider;
import com.care_health.care_health.dtos.request.role.RoleRequestDTO;
import com.care_health.care_health.dtos.request.user.EmailRequestDTO;
import com.care_health.care_health.dtos.request.user.LoginRequestDTO;
import com.care_health.care_health.dtos.request.user.RegisterRequestDTO;
import com.care_health.care_health.dtos.response.user.UserProfileDTO;
import com.care_health.care_health.entity.Roles;
import com.care_health.care_health.entity.User;
import com.care_health.care_health.enums.ERole;
import com.care_health.care_health.helper.GenerateRandomPassword;
import com.care_health.care_health.repositories.IUsersRepo;
import com.care_health.care_health.services.IServices.IRoleService;
import com.care_health.care_health.services.IServices.IUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
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

    private final EmailServiceImpl emailService;

    public UserServiceImpl(IUsersRepo usersRepo, IRoleService roleService, AuthenticationManager authenticationManager, JwtProvider jwtProvider, PasswordEncoder passwordEncoder, EmailServiceImpl emailService) {
        this.usersRepo = usersRepo;
        this.roleService = roleService;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
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
    public User findByEmail(String email) {
        return usersRepo.findByEmail(email);
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
        System.out.println("authorities" + authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();
        System.out.println("customUserDetail" + customUserDetail);
        /* generate token */
        String jwt = jwtProvider.generateToken(customUserDetail);

        System.out.println("jwt" + jwt);

        List<String> listRoles = customUserDetail.getAuthorities().stream()
                .map(item -> item.getAuthority()).collect(Collectors.toList());

        return jwt;
    }

    @Override
    public String addRoleForUser(UUID idUser, RoleRequestDTO roleName) {

        Optional<User> findUser = usersRepo.findById(idUser);

        if (findUser.isEmpty()) {
            return "Khong thay user";
        }


        User user = findUser.get();

        ERole role = roleName.getRoleName();

        if (role == null) {
            return "Role khong hop le";
        }

        Optional<Roles> checkRole = roleService.findByRoleName(role);
        if (!checkRole.isPresent()) {
            return "Role not found";
        }

        user.addRole(checkRole.get());
        usersRepo.save(user);

        return "add role successfully";
    }

    @Override
    public String resetPassword(EmailRequestDTO email) {
        User user = findByEmail(email.getEmail());
        if (user != null) {
            String newPassword = GenerateRandomPassword.generateRandomPassword(8);
            user.setPassword(passwordEncoder.encode(newPassword));
            usersRepo.save(user);
            System.out.println("save user with new password");
            String subject = "Reset Password";
            String text = "Your new password is: " + newPassword;
            System.out.println("test ->>>>>" + text);
            emailService.sendEmail(email.getEmail(), subject, text);
            return "reset success";
        }

        return "reset failure";
    }

    @Override
    public String deleteRoleOfUser(UUID idUser, RoleRequestDTO roleName) {
        Optional<User> userOpt = usersRepo.findById(idUser);
        User user = userOpt.get();
        if (userOpt.isPresent()) {
            ERole role = roleName.getRoleName();
            Roles roleDeleteOfUser = roleService.findByRoleName(role).get();
            boolean checkDeleteRole = user.removeRole(roleDeleteOfUser);
            if (checkDeleteRole) {
                usersRepo.save(user);
                return "Delete role of user successfully";
            }
            return "Delete role failure";
        }
        return "Not found user";
    }

    @Override
    public UserProfileDTO getUserProfile(String token) {
        System.out.println("token : " + token);
        String userName = jwtProvider.getUserNameFromJWT(token);
        if (userName != null) {
            User userProfile = findByUserName(userName);
            if (userProfile != null) {
                UserProfileDTO userProfileDetail = UserProfileDTO.builder()
                        .userName(userProfile.getUserName())
                        .isUserStatus(true)
                        .email(userProfile.getEmail())
                        .listRoles(userProfile.getListRoles())
                        .build();
                return userProfileDetail;
            }
        }
        return null;
    }

    @Override
    public String deleteUser(String username) {
        User user = findByUserName(username);

        if (user != null) {
            System.out.println("find user successfully");
            usersRepo.deleteById(user.getId());
            return "delete user successfully";
        }

        return "delete user failure";
    }

}
