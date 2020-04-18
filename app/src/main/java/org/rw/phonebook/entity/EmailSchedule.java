package org.rw.phonebook.entity;

import java.io.Serializable;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity(name = "Email_Schedule")
@Table(name = "Email_Schedule")
@Access(value = AccessType.FIELD)
public class EmailSchedule implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "scheduleId") private Long scheduleId;

    @OneToOne(targetEntity = User.class) @JoinColumn(name = "ADMIN_userId", referencedColumnName = "userId") private User admin;

    @OneToOne(targetEntity = User.class) @JoinColumn(name = "USER_userId", referencedColumnName = "userId") private User user;

    public EmailSchedule() {
        super();
    }

    public EmailSchedule(User admin, User user) {
        super();
        this.admin = admin;
        this.user = user;
    }

    public long getScheduleId() {
        return scheduleId;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((scheduleId == null) ? 0 : scheduleId.hashCode());
        return result;
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
        EmailSchedule other = (EmailSchedule) obj;
        if (scheduleId == null) {
            return other.scheduleId == null;
        } else {
            return scheduleId.equals(other.scheduleId);
        }
    }

    @Override
    public String toString() {
        return "Contact [contactId=" + scheduleId + "]";
    }
}
