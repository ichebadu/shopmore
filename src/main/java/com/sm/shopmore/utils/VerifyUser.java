package com.sm.shopmore.utils;

import com.sm.shopmore.entity.User;
import com.sm.shopmore.exception.AdminNotFoundException;
import com.sm.shopmore.exception.CustomerNotFoundException;
import com.sm.shopmore.exception.MerchantNotFoundException;
import com.sm.shopmore.exception.UserNotFoundException;
import com.sm.shopmore.repository.AdminRepository;
import com.sm.shopmore.repository.CustomerRepository;
import com.sm.shopmore.repository.MerchantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VerifyUser {
    private final CustomerRepository customerRepository;
    private final AdminRepository adminRepository;
    private final MerchantRepository merchantRepository;

    public User verifyingUser(String email){
        User user;
        if(customerRepository.existsByEmail(email)){
            user = customerRepository.findByEmail(email)
                    .orElseThrow(()->new CustomerNotFoundException("Customers not found"));
        }else if(adminRepository.existsByEmail(email)){
            user = adminRepository.findByEmail(email)
                    .orElseThrow(()->new AdminNotFoundException("Admin not found"));
        }else if(merchantRepository.existsByEmail(email)){
            user = merchantRepository.findByEmail(email)
                    .orElseThrow(()->new MerchantNotFoundException("merchant not found"));
        } else {
            throw new UserNotFoundException("User not found");
        }
        return user;
    }
}
