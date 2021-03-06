package com.yehoshuafish.klfapplication.backingbeans;

import com.yehoshuafish.klfapplication.entities.Activity;
import com.yehoshuafish.klfapplication.entities.User;
import com.yehoshuafish.klfapplication.services.DataService;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.SecurityContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * This class is the controller of the index.xhtml page
 * Contains a method to retrieve the currently logged-in user, a method to 
 * retrieve all of the activities from the DB and the logout method
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
    
    private String contactEmail;
    private String contactName;
    private String contactText;

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactText() {
        return contactText;
    }

    public void setContactText(String contactText) {
        this.contactText = contactText;
    }
    
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
    
    public void onSubmitContactForm() {
        FacesMessage msg = new FacesMessage("Thank you! We received your submission", null);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public String logout() throws ServletException {
        ExternalContext externalContext = facesContext.getExternalContext();
        ((HttpServletRequest)externalContext.getRequest()).logout();
        return "/login.xhtml?faces-redirect=true";
    }
}
