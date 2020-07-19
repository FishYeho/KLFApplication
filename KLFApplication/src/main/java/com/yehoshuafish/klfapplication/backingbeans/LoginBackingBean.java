package com.yehoshuafish.klfapplication.backingbeans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Shuey
 */
@RequestScoped
@Named("loginBackingBean")
public class LoginBackingBean {
    
    private String username;
    private String password;
}
