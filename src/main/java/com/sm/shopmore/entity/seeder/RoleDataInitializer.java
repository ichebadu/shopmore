package com.sm.shopmore.entity.seeder;

import com.sm.shopmore.entity.RoleEntity;
import com.sm.shopmore.repository.RoleRepository;
import com.sm.shopmore.utils.PermissionsUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class RoleDataInitializer implements CommandLineRunner {
    private final RoleRepository repository;
    @Override
    public void run(String... args) throws Exception {
        RoleEntity superAdminRole = new RoleEntity();
        superAdminRole.setRoleName("SUPER_ADMIN");
        superAdminRole.setPermissions(Arrays.asList(
                PermissionsUtils.SUPER_ADMIN_CREATE,
                PermissionsUtils.SUPER_ADMIN_READ,
                PermissionsUtils.SUPER_ADMIN_UPDATE,
                PermissionsUtils.SUPER_ADMIN_DELETE,
                PermissionsUtils.ADMIN_CREATE,
                PermissionsUtils.ADMIN_READ,
                PermissionsUtils.ADMIN_UPDATE,
                PermissionsUtils.ADMIN_DELETE,
                PermissionsUtils.MERCHANT_CREATE,
                PermissionsUtils.MERCHANT_READ,
                PermissionsUtils.MERCHANT_UPDATE,
                PermissionsUtils.MERCHANT_DELETE,
                PermissionsUtils.USER_CREATE,
                PermissionsUtils.USER_READ,
                PermissionsUtils.USER_UPDATE,
                PermissionsUtils.USER_DELETE
        ));


        RoleEntity adminRole = new RoleEntity();
        adminRole.setRoleName("ADMIN");
        adminRole.setPermissions(Arrays.asList(
                PermissionsUtils.ADMIN_CREATE,
                PermissionsUtils.ADMIN_READ,
                PermissionsUtils.ADMIN_UPDATE,
                PermissionsUtils.ADMIN_DELETE,
                PermissionsUtils.MERCHANT_CREATE,
                PermissionsUtils.MERCHANT_READ,
                PermissionsUtils.MERCHANT_UPDATE,
                PermissionsUtils.MERCHANT_DELETE,
                PermissionsUtils.USER_CREATE,
                PermissionsUtils.USER_READ,
                PermissionsUtils.USER_UPDATE,
                PermissionsUtils.USER_DELETE
        ));

        RoleEntity merchantRole = new RoleEntity();
        merchantRole.setRoleName("MERCHANT");
        merchantRole.setPermissions(Arrays.asList(
                PermissionsUtils.MERCHANT_CREATE,
                PermissionsUtils.MERCHANT_READ,
                PermissionsUtils.MERCHANT_UPDATE,
                PermissionsUtils.MERCHANT_DELETE
        ));

        RoleEntity customerRole = new RoleEntity();
        customerRole.setRoleName("CUSTOMER");
        customerRole.setPermissions(Arrays.asList(
                PermissionsUtils.USER_CREATE,
                PermissionsUtils.USER_READ,
                PermissionsUtils.USER_UPDATE,
                PermissionsUtils.USER_DELETE
        ));
        repository.save(adminRole);
        repository.save(customerRole);
        repository.save(merchantRole);
    }
}
