package com.sm.shopmore.utils;

import com.sm.shopmore.dto.request.admin.AdminRegistrationRequest;
import com.sm.shopmore.dto.request.CustomerRequest.CustomerRegistrationRequest;
import com.sm.shopmore.dto.request.merchantRequest.MerchantRegisterRequest;
import com.sm.shopmore.entity.RoleEntity;
import com.sm.shopmore.entity.admin.AdminUser;
import com.sm.shopmore.entity.customer.CustomerUser;
import com.sm.shopmore.entity.merchant.MerchantUser;
import com.sm.shopmore.enums.City;
import com.sm.shopmore.enums.Country;
import com.sm.shopmore.enums.Gender;
import com.sm.shopmore.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
@RequiredArgsConstructor
public class MapperUtils {
    private final RoleRepository repository;
    public AdminUser adminUserDtoToAdminEntity(AdminRegistrationRequest request) {
        RoleEntity roleEntity = repository.findByRoleName("ADMIN");

        AdminUser adminUser = new AdminUser();

        adminUser.setFirstName(request.getFirstName());
        adminUser.setLastName(request.getLastName());
        adminUser.setPhoneNumber(request.getPhoneNumber());
        adminUser.setDepartment(request.getDepartment());
        adminUser.setEmail(request.getEmail());
        adminUser.setStatus(false);
        adminUser.setCity(request.getCity());
        adminUser.setRole(roleEntity);
        adminUser.setDepartment(request.getDepartment());
        adminUser.setCountry(request.getCountry());
        adminUser.setStreetAddress(request.getStreetAddress());
        adminUser.setDateOfBirth(Date.valueOf(request.getDateOfBirth()));
        adminUser.setImageURL(UserUtils.IMAGE_PLACEHOLDER_URL);
                return adminUser;
    }
    public CustomerUser CustomerRegistrationDtoToCustomerEntity(CustomerRegistrationRequest request){
        RoleEntity roleEntity = repository.findByRoleName("CUSTOMER");

        CustomerUser customerUser = new CustomerUser();

        customerUser.setFirstName(request.getFirstName());
        customerUser.setLastName(request.getLastName());
        customerUser.setEmail(request.getEmail());
        customerUser.setRole(roleEntity);
        customerUser.setGender(Gender.valueOf(request.getGender()));
        customerUser.setPhone(request.getPhone());
        customerUser.setStatus(false);
        customerUser.setDateOfBirth(Date.valueOf(request.getDateOfBirth()));
        return customerUser;

    }
    public MerchantUser merchantRegistrationDtoToMerchantEntity(MerchantRegisterRequest request){
        RoleEntity roleEntity = repository.findByRoleName("MERCHANT");

        MerchantUser merchantUser = new MerchantUser();
                merchantUser.setFirstName(request.getFirstName());
                merchantUser.setLastName(request.getLastName());
                merchantUser.setEmail(request.getEmail());
                merchantUser.setAddress1(request.getAddress1());
                merchantUser.setAddress2(request.getAddress2());
                merchantUser.setRole(roleEntity);
                merchantUser.setAccountManagersPhoneNumber(request.getAccountManagersPhoneNumber());
                merchantUser.setAreYouARegisteredVAT(request.getAreYouARegisteredVAT());
                merchantUser.setBusinessOwnerOrLegalRepresentativeDateOfBirth(request.getBusinessOwnerOrLegalRepresentativeDateOfBirth());
                merchantUser.setBusinessOwnerOrLegalRepresentativeFirstName(request.getBusinessOwnerOrLegalRepresentativeFirstName());
                merchantUser.setBusinessOwnerOrLegalRepresentativeLastName(request.getBusinessOwnerOrLegalRepresentativeLastName());
                merchantUser.setBusinessType(request.getBusinessType());
                merchantUser.setCACRegistrationNumber(request.getCACRegistrationNumber());
                merchantUser.setCompanyName(request.getCompanyName());
                merchantUser.setShopName(request.getShopName());
                merchantUser.setCity(City.valueOf(request.getCity()));
                merchantUser.setDoYouHaveAShop(request.getDoYouHaveAShop());
                merchantUser.setImageUrl(UserUtils.IMAGE_PLACEHOLDER_URL);
                merchantUser.setCountry(Country.valueOf(request.getCountry()));
                merchantUser.setPostalCode(request.getPostalCode());
                merchantUser.setPassportOrIdOfBusinessOwner(request.getPassportOrIdOfBusinessOwner());
                merchantUser.setSizeOfBusiness(request.getSizeOfBusiness());
                merchantUser.setTaxIdentificationNumber(request.getTaxIdentificationNumber());
                merchantUser.setUploadTIN(request.getUploadTIN());
                merchantUser.setUploadCAC(request.getUploadCAC());
                merchantUser.setStatus(false);
                merchantUser.setWhichCountryWillYouShipFrom(request.getWhichCountryWillYouShipFrom());
        return merchantUser;
    }

}
