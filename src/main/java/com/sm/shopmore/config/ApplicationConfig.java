package com.sm.shopmore.config;

import com.sm.shopmore.entity.admin.AdminUser;
import com.sm.shopmore.entity.buyer.BuyerUser;
import com.sm.shopmore.entity.merchant.MerchantUser;
import com.sm.shopmore.repository.AdminRepository;
import com.sm.shopmore.repository.BuyerRepository;
import com.sm.shopmore.repository.MerchantRepository;
import com.sm.shopmore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final MerchantRepository merchantRepository;
    private final BuyerRepository buyerRepository;


    @Bean
    public UserDetailsService userDetailsService(){
        return email -> {
            Optional<AdminUser> adminUser = adminRepository.findAdminUserByEmail(email);
            if (adminUser.isPresent()) {
                return adminUser.get();
            }

            Optional<MerchantUser> merchantUser = merchantRepository.findMerchantUserByEmail(email);
            if(merchantUser.isPresent()){
                return merchantUser.get();
            }

                return buyerRepository.findBuyerUserByEmail(email)
                        .orElseThrow( ()-> new UsernameNotFoundException("Buyer not found"));
            };
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

}
