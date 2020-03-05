package com.mah.springbootjwteg;

import com.mah.springbootjwteg.domain.User;
import com.mah.springbootjwteg.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringbootjwtegApplication {

    private final UserRepository userRepository;

    @Autowired
    public SpringbootjwtegApplication(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //Approach 1 for inserting records in database during application startup
    @PostConstruct
    public void initDbWithUsersRecord() {
        System.err.println("SpringbootjwtegApplication Bean Loaded");
        List<User> userList = new ArrayList<>();
        userList.add(new User(null, "arif_hussain", "qwerty", "arif_hussain@gmail.com"));
        userList.add(new User(null, "rohan_nevatia", "123456", "rohan_nevatia@gmail.com"));
        userList.add(new User(null, "venga_nathan", "asdfgh", "venga_nathan@gmail.com"));
        userRepository.saveAll(userList);
    }

    //Approach 2 for inserting records in database just after application is started
    @EventListener({ApplicationReadyEvent.class})
    public void applicationReadyEvent() {
        System.err.println("SpringbootjwtegApplication Application Started");
/*
        List<User> userList = new ArrayList<>();
        userList.add(new User(null, "arif_hussain", "qwerty", "arif_hussain@gmail.com"));
        userList.add(new User(null, "rohan_nevatia", "123456", "rohan_nevatia@gmail.com"));
        userList.add(new User(null, "venga_nathan", "asdfgh", "venga_nathan@gmail.com"));
        userRepository.saveAll(userList);
*/
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootjwtegApplication.class, args);
    }

}
