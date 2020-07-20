package com.yehoshuafish.klfapplication.services;

import com.yehoshuafish.klfapplication.entities.User;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

/**
 * This class is used to generate mock data in order to test the application
 * It's method is used to create User and Activity objects
 * 
 * @author Shuey
 */
@ApplicationScoped
public class DataInitializer {
    
    @Inject
    DataService dataService;
    
    public void setup(@Observes @Initialized(ApplicationScoped.class) Object event) {
        if(dataService.getAllUsers().isEmpty()) {
            User bob = dataService.createUser("bob", "itsmrbob", "itsmrbob");
            User jim = dataService.createUser("jim", "jimbo", "jimbo");
            
            dataService.createActivity("Login");
            dataService.createActivity("Logout");
            dataService.createActivity("View");
        }
    }
}
