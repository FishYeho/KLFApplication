package com.yehoshuafish.klfapplication.backingbeans;

import com.yehoshuafish.klfapplication.entities.User;
import com.yehoshuafish.klfapplication.services.DataService;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;

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
    private String name;
    private String username;
    private String password;
    
    @PostConstruct
    public void initialize() {
        this.allUsers = dataService.getAllUsers();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public List<User> getAllUsers() {
        return allUsers;
    }
    
    public void createUser() {
        dataService.createUser(name, username, password);
    }
    
    public void onRowEdit(RowEditEvent<User> event) {
        FacesMessage msg = new FacesMessage("User Edited", event.getObject().getId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void onRowCancel(RowEditEvent<User> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", event.getObject().getId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    
}
