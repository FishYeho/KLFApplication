package com.yehoshuafish.klfapplication.backingbeans;

import com.yehoshuafish.klfapplication.entities.Activity;
import com.yehoshuafish.klfapplication.entities.User;
import com.yehoshuafish.klfapplication.services.DataService;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Shuey
 */
@RequestScoped
@Named("homeBackingBean")
public class HomeBackingBean {
    
    @Inject
    DataService dataService;
    
    private Optional<User> currentUser;
    private List<Activity> currentActivities;
    
    @PostConstruct
    public void initialize() {
        String username = "bob";
        this.currentUser = dataService.getUser(username);
        this.currentActivities = dataService.getAllActivities();
//        this.currentUser.ifPresent(user -> {
//            this.currentActivities = dataService.getActivitiesByUser(user);
//        });
    }
    
    public User getCurrentUser() {
        return this.currentUser.orElse(null);
    }

    public List<Activity> getCurrentActivities() {
        return currentActivities;
    }
}
