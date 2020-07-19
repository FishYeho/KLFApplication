package com.yehoshuafish.klfapplication.services;

import com.yehoshuafish.klfapplication.entities.User;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

/**
 *
 * @author Shuey
 */
@ApplicationScoped
public class DataInitializer {
    
    @Inject
    DataService dataService;
    
    public void setup(@Observes @Initialized(ApplicationScoped.class) Object event) {
        if(dataService.getAllUsers().isEmpty()) {
            User bob = dataService.createUser("bob", "itsmrbob", new byte[4], "123456789");
            User jim = dataService.createUser("jim", "jimbo", new byte[4], "123456789");
            
            dataService.createActivity("Login");
            dataService.createActivity("Logout");
            dataService.createActivity("View");
        }
    }
}
