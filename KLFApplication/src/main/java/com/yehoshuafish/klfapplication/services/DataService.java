package com.yehoshuafish.klfapplication.services;

import com.yehoshuafish.klfapplication.entities.Activity;
import com.yehoshuafish.klfapplication.entities.User;
import java.util.List;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 *
 * @author Shuey
 */
@ApplicationScoped
public class DataService {
    
    @PersistenceContext(unitName="klfApplication")
    EntityManager em;
    
    @Transactional
    public User createUser(String name, String username, byte[] hash, String salt) {
        User newUser = new User(name, username, hash, salt);
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
    
    public List<Activity> getAllActivities() {
        return em.createNamedQuery("Activity.all", Activity.class).getResultList();
    }
    
    public List<Activity> getActivitiesByUser(User user) {
        return em.createNamedQuery("Activity.byUser", Activity.class)
                .setParameter("userId", user.getId())
                .getResultList();
    }
}
