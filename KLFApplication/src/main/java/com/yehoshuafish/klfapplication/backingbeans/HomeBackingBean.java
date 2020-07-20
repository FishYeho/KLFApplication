package com.yehoshuafish.klfapplication.backingbeans;

import com.yehoshuafish.klfapplication.entities.Activity;
import com.yehoshuafish.klfapplication.entities.User;
import com.yehoshuafish.klfapplication.services.DataService;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.SecurityContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Shuey
 */
@RequestScoped
@Named("homeBackingBean")
public class HomeBackingBean {
    
    @Inject
    DataService dataService;
    
    @Inject
    SecurityContext securityContext;
    
    @Inject
    FacesContext facesContext;
    
    private Optional<User> currentUser;
    private List<Activity> currentActivities;
    
    @PostConstruct
    public void initialize() {
        String username = "bob";
        //String username = securityContext.getCallerPrincipal().getName();
        this.currentUser = dataService.getUser(username);
        this.currentActivities = dataService.getAllActivities();
    }
    
    public User getCurrentUser() {
        return this.currentUser.orElse(null);
    }

    public List<Activity> getCurrentActivities() {
        return currentActivities;
    }
    
    public String logout() throws ServletException {
        ExternalContext externalContext = facesContext.getExternalContext();
        ((HttpServletRequest)externalContext.getRequest()).logout();
        return "/login.xhtml?faces-redirect=true";
    }
}
