package br.com.smtech.controlegastos.config;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.smtech.controlegastos.api.enums.PermissionEnum;
import br.com.smtech.controlegastos.api.model.Permission;
import br.com.smtech.controlegastos.api.model.Person;
import br.com.smtech.controlegastos.api.model.User;
import br.com.smtech.controlegastos.api.repository.PermissionRepository;
import br.com.smtech.controlegastos.api.service.UserService;
import br.com.smtech.controlegastos.library.util.DateUtil;

@Profile({ "dev" })
@Configuration
public class Initialize {

    @Bean
    CommandLineRunner init(PermissionRepository permissionRepository, UserService userService) {
        return args -> {
            initRegisters(permissionRepository, userService);
        };
    }

    private void initRegisters(PermissionRepository permissionRepository, UserService userService) {
        List<Permission> permissions = new LinkedList<>();
        List<Permission> permissionsAdm = new LinkedList<>();
        for (PermissionEnum permissionEnum : PermissionEnum.values()) {
            final Permission permission = savePermission(permissionRepository, permissionEnum.toString());
            permissions.add(permission);
            if (!PermissionEnum.ROLE_ROOT.equals(permissionEnum)) {
                permissionsAdm.add(permission);
            }
        }

        userService.create(User.builder().username("root").password("123456").authorities(permissions)
                .person(Person.builder().name("Root").birth(DateUtil.getDateNowAdd(Calendar.YEAR, -25))
                        .cpf("13023092001").city("Goiânia").state("Goiás").countrie("Brasil")
                        .email("suleimanmoraes@gmail.com").build())
                .build());

        userService.create(User.builder().username("adm").password("123456")
                .person(Person.builder().name("Administrador").birth(DateUtil.getDateNowAdd(Calendar.YEAR, -25))
                        .cpf("13023092002").city("Goiânia").state("Goiás").countrie("Brasil")
                        .email("suleimanmoraes@gmail.com").build())
                .build());
    }

    private Permission savePermission(PermissionRepository permissionRepository, String permissionName) {
        Permission permission = null;
        Permission findPermission = permissionRepository.findTopByAuthority(permissionName);
        if (findPermission == null) {
            permission = new Permission();
            permission.setAuthority(permissionName);
            permission = permissionRepository.save(permission);
        } else {
            permission = findPermission;
        }
        return permission;
    }
}
