package EcomercePlatformDemoApp.service;

import EcomercePlatformDemoApp.dao.RoleDao;
import EcomercePlatformDemoApp.dao.UserDao;
import EcomercePlatformDemoApp.entity.Role;
import EcomercePlatformDemoApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerNewUser (User user){
        Role role = roleDao.findById("user").get();
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRole(roles);

        user.setUserPassword(getEcodedPassword(user.getUserPassword()));
        return userDao.save(user);
    }
    public void initRolesAndUser() {
        Role adminRole = new Role();
        adminRole.setRoleName("admin");
        adminRole.setRoleDescription("Admin Role");
        roleDao.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("user");
        userRole.setRoleDescription("Default role for a newly created record");
        roleDao.save(userRole);

        User adminUser = new User();
        adminUser.setUserFirstName("Paul");
        adminUser.setUserLastName("Nadola");
        adminUser.setUserName("nadola");
        adminUser.setUserPassword(getEcodedPassword("nadola"));
        Set<Role> adminRoles =  new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userDao.save(adminUser);

//        User user = new User();
//        user.setUserFirstName("Naddy");
//        user.setUserLastName("Yings");
//        user.setUserName("naddy");
//        user.setUserPassword(getEcodedPassword("naddy"));
//        Set<Role> userRoles =  new HashSet<>();
//        userRoles.add(userRole);
//        user.setRole(userRoles);
//        userDao.save(user);
    }
    public String getEcodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
