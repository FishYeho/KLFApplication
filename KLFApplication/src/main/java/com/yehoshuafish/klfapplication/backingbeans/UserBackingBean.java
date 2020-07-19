package com.yehoshuafish.klfapplication.backingbeans;

import com.yehoshuafish.klfapplication.entities.User;
import com.yehoshuafish.klfapplication.services.DataService;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Shuey
 */
@RequestScoped
@Named("usersBackingBean")
public class UserBackingBean {
    
    @Inject
    DataService dataService;
    
    private List<User> allUsers;
    
    @PostConstruct
    public void initialize() {
        this.allUsers = dataService.getAllUsers();
    }

    public List<User> getAllUsers() {
        return allUsers;
    }
    
    
}
