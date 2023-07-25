package com.sm.shopmore.security;

import com.sm.shopmore.entity.admin.AdminUser;
import com.sm.shopmore.entity.buyer.BuyerUser;
import com.sm.shopmore.entity.merchant.MerchantUser;
import com.sm.shopmore.repository.AdminRepository;
import com.sm.shopmore.repository.BuyerRepository;
import com.sm.shopmore.repository.MerchantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final MerchantRepository merchantRepository;
    private final BuyerRepository buyerRepository;
    private final AdminRepository adminRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<MerchantUser> merchantUser = merchantRepository.findMerchantUserByEmail(email);
        if(merchantUser.isPresent()){
            return new User(merchantUser.get().getUsername(),merchantUser.get().getPassword(), new ArrayList<>());
        }

        Optional<BuyerUser> buyerUser = buyerRepository.findBuyerUserByEmail(email);
        if(buyerUser.isPresent()){
            return new User(buyerUser.get().getUsername(), buyerUser.get().getPassword(), new ArrayList<>());
        }

        Optional<AdminUser> adminUser = adminRepository.findAdminUserByEmail(email);
        if(adminUser.isPresent()){
            return new User(adminUser.get().getUsername(), adminUser.get().getPassword(), new ArrayList<>());
        }
        throw new UsernameNotFoundException("Username not found");
    }
}
