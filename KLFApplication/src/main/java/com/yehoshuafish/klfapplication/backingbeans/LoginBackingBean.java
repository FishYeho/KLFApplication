package com.yehoshuafish.klfapplication.backingbeans;

import java.io.IOException;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;

/**
 *
 * @author Shuey
 */
@RequestScoped
@Named("loginBackingBean")
public class LoginBackingBean {
    
    @NotEmpty
    private String username;
    
    @NotEmpty
    private String password;
    
    @Inject
    FacesContext facesContext;
    
    @Inject
    SecurityContext securityContext;

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
    
    public void login() throws IOException {
        switch(processAuthentication()) {
            case SEND_CONTINUE:
                facesContext.responseComplete();
                break;
            case SEND_FAILURE:
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Credentials", null));
                break;
            case SUCCESS:
                getExternalContext().redirect(getExternalContext().getRequestContextPath() + "/app/index.xhtml");
                break;
        }
    }
    
    public void logout() {
        
    }
    
    /**
     * Tiny helper method
     * @author https://www.youtube.com/watch?v=odJIoyFeAbk&list=PLFMhxiCgmMR-wxd1BAFiSGdDGROxRgDDX&index=14
     * @return 
     */
    private AuthenticationStatus processAuthentication() {
        ExternalContext externalContext = getExternalContext();
        return securityContext.authenticate((HttpServletRequest)externalContext.getRequest(), 
                                            (HttpServletResponse)externalContext.getResponse(), 
                                            AuthenticationParameters.withParams()
                                            .credential(new UsernamePasswordCredential(username, password)));
    }
    
    /**
     * Tiny helper method
     * @return 
     */
    private ExternalContext getExternalContext() {
        return facesContext.getExternalContext();
    }
}
