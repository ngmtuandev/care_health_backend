package com.care_health.care_health.services.ImplService;

import com.care_health.care_health.configurations.CustomUserDetail;
import com.care_health.care_health.configurations.JwtProvider;
import com.care_health.care_health.constant.ResourceBundleConstant;
import com.care_health.care_health.constant.SystemConstant;
import com.care_health.care_health.dtos.request.role.RoleRequestDTO;
import com.care_health.care_health.dtos.request.user.EmailRequestDTO;
import com.care_health.care_health.dtos.request.user.LoginRequestDTO;
import com.care_health.care_health.dtos.request.user.RegisterRequestDTO;
import com.care_health.care_health.dtos.response.user.UserProfileDTO;
import com.care_health.care_health.dtos.response.user.UserResponse;
import com.care_health.care_health.entity.Roles;
import com.care_health.care_health.entity.User;
import com.care_health.care_health.enums.ERole;
import com.care_health.care_health.helper.GenerateRandomPassword;
import com.care_health.care_health.repositories.IUsersRepo;
import com.care_health.care_health.services.IServices.IRoleService;
import com.care_health.care_health.services.IServices.IUserService;
import com.care_health.care_health.utils.BaseAmenityUtil;
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

    private final BaseAmenityUtil baseAmenityUtil;

    public UserServiceImpl(IUsersRepo usersRepo, IRoleService roleService, AuthenticationManager authenticationManager, JwtProvider jwtProvider, PasswordEncoder passwordEncoder, EmailServiceImpl emailService, BaseAmenityUtil baseAmenityUtil) {
        this.usersRepo = usersRepo;
        this.roleService = roleService;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
        this.baseAmenityUtil = baseAmenityUtil;
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


    private String getMessageBundle(String key) {
        return baseAmenityUtil.getMessageBundle(key);
    }

    @Override
    public UserResponse register(RegisterRequestDTO request) {

        if (existsByUserName(request.getUserName())) {
            return UserResponse.builder()
                    .code(ResourceBundleConstant.USR_001)
                    .status(SystemConstant.STATUS_CODE_BAD_REQUEST)
                    .message(getMessageBundle(ResourceBundleConstant.USR_001))
                    .responseTime(baseAmenityUtil.currentTimeSeconds())
                    .build();
        }
        if (existsByEmail(request.getEmail())) {
            return UserResponse.builder()
                    .code(ResourceBundleConstant.USR_001)
                    .status(SystemConstant.STATUS_CODE_BAD_REQUEST)
                    .message(getMessageBundle(ResourceBundleConstant.USR_001))
                    .responseTime(baseAmenityUtil.currentTimeSeconds())
                    .build();
        }

        User newUser = new User();
        newUser.setUserName(request.getUserName());
        newUser.setEmail(request.getEmail());

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        newUser.setPassword(encodedPassword);

        newUser.setUserStatus(true);

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

        return UserResponse.builder()
                .code(ResourceBundleConstant.USR_002)
                .status(SystemConstant.STATUS_CODE_SUCCESS)
                .message(getMessageBundle(ResourceBundleConstant.USR_002))
                .data(newUser)
                .responseTime(baseAmenityUtil.currentTimeSeconds())
                .build();
    }

    @Override
    public UserResponse login(LoginRequestDTO requestLogin) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestLogin.getUserName(), requestLogin.getPassword()));

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        SecurityContextHolder.getContext().setAuthentication(authentication);

        CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();

        /* generate token */
        String jwt = jwtProvider.generateToken(customUserDetail);

        List<String> listRoles = customUserDetail.getAuthorities().stream()
                .map(item -> item.getAuthority()).collect(Collectors.toList());

        return UserResponse.builder()
                .code(ResourceBundleConstant.USR_003)
                .status(SystemConstant.STATUS_CODE_SUCCESS)
                .message(getMessageBundle(ResourceBundleConstant.USR_003))
                .data(jwt)
                .responseTime(baseAmenityUtil.currentTimeSeconds())
                .build();
    }

    @Override
    public UserResponse addRoleForUser(UUID idUser, RoleRequestDTO roleName) {

        Optional<User> findUser = usersRepo.findById(idUser);

        if (findUser.isEmpty()) {
            return UserResponse.builder()
                    .code(ResourceBundleConstant.USR_004)
                    .status(SystemConstant.STATUS_CODE_BAD_REQUEST)
                    .message(getMessageBundle(ResourceBundleConstant.USR_004))
                    .responseTime(baseAmenityUtil.currentTimeSeconds())
                    .build();
        }


        User user = findUser.get();

        ERole role = roleName.getRoleName();

        if (role == null) {
            return UserResponse.builder()
                    .code(ResourceBundleConstant.ROL_001)
                    .status(SystemConstant.STATUS_CODE_BAD_REQUEST)
                    .message(getMessageBundle(ResourceBundleConstant.ROL_001))
                    .responseTime(baseAmenityUtil.currentTimeSeconds())
                    .build();
        }

        Optional<Roles> checkRole = roleService.findByRoleName(role);
        if (!checkRole.isPresent()) {
            return UserResponse.builder()
                    .code(ResourceBundleConstant.ROL_001)
                    .status(SystemConstant.STATUS_CODE_BAD_REQUEST)
                    .message(getMessageBundle(ResourceBundleConstant.ROL_001))
                    .responseTime(baseAmenityUtil.currentTimeSeconds())
                    .build();
        }

        user.addRole(checkRole.get());
        usersRepo.save(user);

        return UserResponse.builder()
                .code(ResourceBundleConstant.ROL_002)
                .status(SystemConstant.STATUS_CODE_SUCCESS)
                .message(getMessageBundle(ResourceBundleConstant.ROL_002))
                .responseTime(baseAmenityUtil.currentTimeSeconds())
                .build();
    }

    @Override
    public UserResponse resetPassword(EmailRequestDTO email) {
        User user = findByEmail(email.getEmail());
        if (user != null) {
            String newPassword = GenerateRandomPassword.generateRandomPassword(8);
            user.setPassword(passwordEncoder.encode(newPassword));
            usersRepo.save(user);
            System.out.println("save user with new password");
            String subject = "Reset Password";
            String text = "Your new password is: " + newPassword;
            emailService.sendEmail(email.getEmail(), subject, text);
            return UserResponse.builder()
                    .code(ResourceBundleConstant.USR_006)
                    .status(SystemConstant.STATUS_CODE_SUCCESS)
                    .message(getMessageBundle(ResourceBundleConstant.USR_006))
                    .responseTime(baseAmenityUtil.currentTimeSeconds())
                    .build();
        }

        return UserResponse.builder()
                .code(ResourceBundleConstant.USR_007)
                .status(SystemConstant.STATUS_CODE_BAD_REQUEST)
                .message(getMessageBundle(ResourceBundleConstant.USR_007))
                .responseTime(baseAmenityUtil.currentTimeSeconds())
                .build();
    }

    @Override
    public UserResponse deleteRoleOfUser(UUID idUser, RoleRequestDTO roleName) {
        Optional<User> userOpt = usersRepo.findById(idUser);
        User user = userOpt.get();
        if (userOpt.isPresent()) {
            ERole role = roleName.getRoleName();
            Roles roleDeleteOfUser = roleService.findByRoleName(role).get();
            boolean checkDeleteRole = user.removeRole(roleDeleteOfUser);
            if (checkDeleteRole) {
                usersRepo.save(user);
                return UserResponse.builder()
                        .code(ResourceBundleConstant.USR_011)
                        .status(SystemConstant.STATUS_CODE_SUCCESS)
                        .message(getMessageBundle(ResourceBundleConstant.USR_011))
                        .responseTime(baseAmenityUtil.currentTimeSeconds())
                        .build();
            }
            return UserResponse.builder()
                    .code(ResourceBundleConstant.USR_012)
                    .status(SystemConstant.STATUS_CODE_BAD_REQUEST)
                    .message(getMessageBundle(ResourceBundleConstant.USR_012))
                    .responseTime(baseAmenityUtil.currentTimeSeconds())
                    .build();
        }
        return UserResponse.builder()
                .code(ResourceBundleConstant.USR_010)
                .status(SystemConstant.STATUS_CODE_BAD_REQUEST)
                .message(getMessageBundle(ResourceBundleConstant.USR_010))
                .responseTime(baseAmenityUtil.currentTimeSeconds())
                .build();
    }

    @Override
    public UserResponse getUserProfile(String token) {
        String userName = jwtProvider.getUserNameFromJWT(token);
        if (userName != null) {
            User userProfile = findByUserName(userName);
            if (userProfile != null) {
                UserProfileDTO userProfileDetail = UserProfileDTO.builder()
                        .id(userProfile.getId())
                        .userName(userProfile.getUserName())
                        .isUserStatus(true)
                        .email(userProfile.getEmail())
                        .listRoles(userProfile.getListRoles())
                        .build();
                return UserResponse.builder()
                        .code(ResourceBundleConstant.USR_009)
                        .status(SystemConstant.STATUS_CODE_SUCCESS)
                        .message(getMessageBundle(ResourceBundleConstant.USR_009))
                        .data(userProfileDetail)
                        .responseTime(baseAmenityUtil.currentTimeSeconds())
                        .build();
            }
        }
        return UserResponse.builder()
                .code(ResourceBundleConstant.USR_010)
                .status(SystemConstant.STATUS_CODE_BAD_REQUEST)
                .message(getMessageBundle(ResourceBundleConstant.USR_010))
                .responseTime(baseAmenityUtil.currentTimeSeconds())
                .build();
    }

    @Override
    public UserResponse deleteUser(String username) {
        User user = findByUserName(username);

        if (user != null) {
            System.out.println("find user successfully");
            if (user.isUserStatus()) {
                user.setUserStatus(false);
                usersRepo.save(user);
            }
            else {
                user.setUserStatus(true);
                usersRepo.save(user);
            }
            return UserResponse.builder()
                    .code(ResourceBundleConstant.USR_013)
                    .status(SystemConstant.STATUS_CODE_SUCCESS)
                    .message(getMessageBundle(ResourceBundleConstant.USR_013))
                    .responseTime(baseAmenityUtil.currentTimeSeconds())
                    .build();
        }

        return UserResponse.builder()
                .code(ResourceBundleConstant.USR_004)
                .status(SystemConstant.STATUS_CODE_BAD_REQUEST)
                .message(getMessageBundle(ResourceBundleConstant.USR_010))
                .responseTime(baseAmenityUtil.currentTimeSeconds())
                .build();
    }

    @Override
    public UserResponse getAllUser() {
        List<User> allUsers = usersRepo.findAll();
        return UserResponse.builder()
                .code(ResourceBundleConstant.USR_014)
                .status(SystemConstant.STATUS_CODE_SUCCESS)
                .message(getMessageBundle(ResourceBundleConstant.USR_014))
                .data(allUsers)
                .responseTime(baseAmenityUtil.currentTimeSeconds())
                .build();
    }

}
