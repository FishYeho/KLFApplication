package com.yehoshuafish.klfapplication;

import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;

/**
 *
 * @author Shuey
 */
@DatabaseIdentityStoreDefinition(
        dataSourceLookup = "jdbc/klfAppDS",
        callerQuery = "select user_password from users where username = ?"
)
@CustomFormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(
                loginPage = "/login.xhtml",
                errorPage = "",
                //We don't want to forward, we want to redirect
                useForwardToLogin = false
        )
)
@ApplicationScoped
public class ApplicationConfiguration {
    
}
