package com.sm.shopmore.service.implementation;


import com.sm.shopmore.dto.request.LoginRequest;
import com.sm.shopmore.dto.response.LoginResponse;
import com.sm.shopmore.entity.User;
import com.sm.shopmore.exception.InvalidCredentialsException;
import com.sm.shopmore.exception.UserNotFoundException;
import com.sm.shopmore.repository.AdminRepository;
import com.sm.shopmore.repository.CustomerRepository;
import com.sm.shopmore.repository.MerchantRepository;
import com.sm.shopmore.security.JwtService;
import com.sm.shopmore.service.LoginAuthenticationService;
import com.sm.shopmore.utils.UserUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginAuthenticationServiceImpl implements LoginAuthenticationService {
    private final AdminRepository adminRepository;
    private final CustomerRepository customerRepository;
    private final MerchantRepository merchantRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @SneakyThrows
    @Override
    public LoginResponse loginAuthentication(LoginRequest loginRequest) {
        User user = findEmail(loginRequest.getEmail());

//
//        if(user.getStatus().equals(false)){
//            throw new UserDisabledException("Account is Disabled");
//        }



        if(user.getStatus()){
            if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                throw new InvalidCredentialsException("Invalid Password");
            }
            String jwtAccessToken = jwtService.generateToken(user);
            String jwtRefreshToken = jwtService.generateRefreshToken(user);
            log.info("PRINT OUT THE VALUE OF jwtrefreshetoken : " + jwtRefreshToken);
            log.info("PRINT OUT THE VALUE OF jwtAccesstoken: "  + jwtAccessToken);
            System.out.println(jwtRefreshToken);
            System.out.println("****************");
            System.out.println(jwtAccessToken);


            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    user.getEmail(), user.getPassword());

            SecurityContextHolder.getContext().setAuthentication(authentication);
            return LoginResponse.builder()
                    .email(user.getEmail())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .accessToken(jwtAccessToken)
                    .refreshToken(jwtRefreshToken)
                    .imageUrl(UserUtils.IMAGE_PLACEHOLDER_URL)
                    .message("Login Successful")
                    .build();

        }else{
            throw new InvalidCredentialsException("Invalid Details");
        }
    }

    private User findEmail(String email){
        var admin = adminRepository.findByEmail(email);
        var customer = customerRepository.findByEmail(email);
        var merchant = merchantRepository.findByEmail(email);
        if(admin.isPresent()){
            return admin.get();
        }
        if(customer.isPresent()){
            return customer.get();
        }
        if(merchant.isPresent()){
            return merchant.get();
        }
        else {
            throw new UserNotFoundException("Email does not exist");
        }
    }


}
