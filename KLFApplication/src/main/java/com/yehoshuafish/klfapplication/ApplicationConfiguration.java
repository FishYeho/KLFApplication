package com.yehoshuafish.klfapplication;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;

/**
 * Class that handles the initial configuration of the application
 * Specifies that the application should use the jdbc/klfAppDS data source
 * 
 * @author Shuey + The official @Payara Youtube channel
 */
@DatabaseIdentityStoreDefinition(
        dataSourceLookup = "jdbc/klfAppDS",
        callerQuery = "select hash from users where username = ?"
)
@CustomFormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(
                loginPage = "/login.xhtml",
                errorPage = "",
                //We don't want to forward, we want to redirect
                useForwardToLogin = false
        )
)
//I use the @FacesConfig annotation so that I don't have to create my own
//faces-config file
@FacesConfig
@ApplicationScoped
public class ApplicationConfiguration {
    
}
