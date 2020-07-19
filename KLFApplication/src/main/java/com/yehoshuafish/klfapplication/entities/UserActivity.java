package com.yehoshuafish.klfapplication.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Shuey
 */
@Entity
@Table(name = "user_activity")
public class UserActivity implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
    
    @ManyToOne
    @JoinColumn(name = "activity_id")
    Activity activity;
    
    LocalDateTime occurence;

    public UserActivity() {
    }

    public UserActivity(User user, Activity activity, LocalDateTime occurence) {
        this.user = user;
        this.activity = activity;
        this.occurence = occurence;
    }

    public Integer getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Activity getActivity() {
        return activity;
    }

    public LocalDateTime getOccurence() {
        return occurence;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserActivity other = (UserActivity) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
