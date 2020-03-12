package com.mah.springbootjwteg;

import com.mah.springbootjwteg.domain.Role;
import com.mah.springbootjwteg.domain.User;
import com.mah.springbootjwteg.repository.RoleRepository;
import com.mah.springbootjwteg.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class SpringbootjwtegApplication {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public SpringbootjwtegApplication(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    //Approach 1 for inserting records in database during application startup
    @PostConstruct
    public void initDbWithUsersRecord() {
        System.err.println("SpringbootjwtegApplication Bean Loaded");

        Role role1 = new Role(null, "ROLE_ADMIN", null);
        Role role2 = new Role(null, "ROLE_USER", null);
        Role role3 = new Role(null, "ROLE_MANAGER", null);
        Role role4 = new Role(null, "ROLE_SUPERVISOR", null);

        Set<Role> setOfRoles1 = new HashSet<>();
        setOfRoles1.add(role1);
        setOfRoles1.add(role3);

        Set<Role> setOfRoles2 = new HashSet<>();
        setOfRoles2.add(role1);
        setOfRoles2.add(role2);
        setOfRoles2.add(role4);

        Set<Role> setOfRoles3 = new HashSet<>();
        setOfRoles3.add(role3);
        setOfRoles3.add(role4);

        List<Role> roleArrayList = new ArrayList<>();
        roleArrayList.add(role1);
        roleArrayList.add(role2);
        roleArrayList.add(role3);
        roleArrayList.add(role4);
        roleRepository.saveAll(roleArrayList);

        List<User> userList = new ArrayList<>();
        userList.add(new User(null, "arif_hussain", "qwerty", "arif_hussain@gmail.com", setOfRoles1));
        userList.add(new User(null, "rohan_nevatia", "123456", "rohan_nevatia@gmail.com", setOfRoles2));
        userList.add(new User(null, "venga_nathan", "asdfgh", "venga_nathan@gmail.com", setOfRoles3));
        userRepository.saveAll(userList);
    }

    //Approach 2 for inserting records in database just after application is started
    @EventListener({ApplicationReadyEvent.class})
    public void applicationReadyEvent() {
        System.err.println("SpringbootjwtegApplication Application Started");
/*
        Role role1 = new Role(null, "ROLE_ADMIN", null);
        Role role2 = new Role(null, "ROLE_USER", null);
        Role role3 = new Role(null, "ROLE_MANAGER", null);
        Role role4 = new Role(null, "ROLE_SUPERVISOR", null);

        Set<Role> setOfRoles1 = new HashSet<>();
        setOfRoles1.add(role1);
        setOfRoles1.add(role3);

        Set<Role> setOfRoles2 = new HashSet<>();
        setOfRoles2.add(role1);
        setOfRoles2.add(role2);
        setOfRoles2.add(role4);

        Set<Role> setOfRoles3 = new HashSet<>();
        setOfRoles3.add(role3);
        setOfRoles3.add(role4);

        List<Role> roleArrayList = new ArrayList<>();
        roleArrayList.add(role1);
        roleArrayList.add(role2);
        roleArrayList.add(role3);
        roleArrayList.add(role4);
        roleRepository.saveAll(roleArrayList);

        List<User> userList = new ArrayList<>();
        userList.add(new User(null, "arif_hussain", "qwerty", "arif_hussain@gmail.com", setOfRoles1));
        userList.add(new User(null, "rohan_nevatia", "123456", "rohan_nevatia@gmail.com", setOfRoles2));
        userList.add(new User(null, "venga_nathan", "asdfgh", "venga_nathan@gmail.com", setOfRoles3));
        userRepository.saveAll(userList);
*/
    }

    public static void main(String[] args) {
        //incomplete because roles based not working properly
        SpringApplication.run(SpringbootjwtegApplication.class, args);
    }

}
