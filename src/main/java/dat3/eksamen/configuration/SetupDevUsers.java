package dat3.eksamen.configuration;

import dat3.security.entity.Role;
import dat3.security.entity.UserWithRoles;
import dat3.security.repository.RoleRepository;
import dat3.security.repository.UserWithRolesRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class SetupDevUsers implements ApplicationRunner {

    UserWithRolesRepository userWithRolesRepository;
    RoleRepository roleRepository;
    PasswordEncoder pwEncoder;
    String passwordUsedByAll;

    public SetupDevUsers(UserWithRolesRepository userWithRolesRepository,RoleRepository roleRepository,PasswordEncoder passwordEncoder) {
        this.userWithRolesRepository = userWithRolesRepository;
        this.roleRepository = roleRepository;
        this.pwEncoder = passwordEncoder;

        passwordUsedByAll = "test12";
    }

    public void run(ApplicationArguments args) {
        setupAllowedRoles();
        setupUserWithRoleUsers();
    }

    private void setupAllowedRoles(){
        roleRepository.save(new Role("USER"));
        roleRepository.save(new Role("ADMIN"));
    }

     /*****************************************************************************************
     IMPORTANT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL
     iT'S ONE OF THE TOP SECURITY FLAWS YOU CAN DO
     If you see the lines below in log-outputs on Azure, forget whatever had your attention on, AND FIX THIS PROBLEM
     *****************************************************************************************/
    private void setupUserWithRoleUsers() {
        Role roleUser = roleRepository.findById("USER").orElseThrow(()-> new NoSuchElementException("Role 'user' not found"));
        Role roleAdmin = roleRepository.findById("ADMIN").orElseThrow(()-> new NoSuchElementException("Role 'admin' not found"));
        System.out.println("******************************************************************************");
        System.out.println("********** IMPORTANT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ************");
        System.out.println();
        System.out.println("******* NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL ************");
        System.out.println("******* REMOVE THIS BEFORE DEPLOYMENT, AND SETUP DEFAULT USERS DIRECTLY  *****");
        System.out.println("**** ** ON YOUR REMOTE DATABASE                 ******************************");
        System.out.println();
        System.out.println("******************************************************************************");
        UserWithRoles user1 = new UserWithRoles("admin", pwEncoder.encode(passwordUsedByAll), "admin1@a.dk");


        userWithRolesRepository.save(user1);

    }
}
