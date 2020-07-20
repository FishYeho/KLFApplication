package com.yehoshuafish.klfapplication.services;

import com.yehoshuafish.klfapplication.entities.Activity;
import com.yehoshuafish.klfapplication.entities.User;
import java.util.List;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;
import javax.transaction.Transactional;

/**
 * This class is used to handle all CRUD operations of the database
 * It contains methods to create new users, edit existing users, delete
 * existing users and query information about the existing users
 * 
 * @author Shuey
 */
@ApplicationScoped
public class DataService {
    
    @PersistenceContext(unitName="klfApplication")
    EntityManager em;
    
    @Inject
    Pbkdf2PasswordHash passwordHasher;
    
    @Transactional
    public User createUser(String name, String username, String hash) {
        User newUser = new User(name, username, passwordHasher.generate(hash.toCharArray()));
        em.persist(newUser);
        em.flush();
        return newUser;
    }
    
    @Transactional
    public Activity createActivity(String name) {
        Activity newActivity = new Activity(name);
        em.persist(newActivity);
        em.flush();
        return newActivity;
    }
    
    public List<User> getAllUsers() {
        return em.createNamedQuery("User.all", User.class).getResultList();
    }
    
    public Optional<User> getUser(String username) {
        return em.createNamedQuery("User.byUsername", User.class)
                .setParameter("username", username)
                .getResultList()
                .stream()
                .findFirst();
    }
    
    public Optional<User> updateName(String name, String username) {
        return em.createNamedQuery("User.editNameByUsername", User.class)
                .setParameter("name", name)
                .setParameter("username", username)
                .getResultList()
                .stream()
                .findFirst();
    }
    
    public Optional<User> deleteUser(String username) {
        return em.createNamedQuery("User.deleteByUsername", User.class)
                .setParameter("username", username)
                .getResultList()
                .stream()
                .findFirst();
    }
    
    public List<Activity> getAllActivities() {
        return em.createNamedQuery("Activity.all", Activity.class).getResultList();
    }
    
    public List<Activity> getActivitiesByUser(User user) {
        return em.createNamedQuery("Activity.byUser", Activity.class)
                .setParameter("userId", user.getId())
                .getResultList();
    }
}
