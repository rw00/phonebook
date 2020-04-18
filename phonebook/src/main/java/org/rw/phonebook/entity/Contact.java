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
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity(name = "Contact")
@Table(name = "Contact")
@Access(value = AccessType.FIELD)
public class Contact implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contactId")
    private Integer contactId;

    @Column(name = "phoneNumber", nullable = false)
    private String phoneNumber;
    @Column(name = "email")
    private String email = "";
    @Column(name = "firstName", nullable = false)
    private String firstName;
    @Column(name = "lastName")
    private String lastName = "";
    @Column(name = "address")
    private String address = "";

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user", referencedColumnName = "userId", nullable = false)
    private User user;


    public Contact() {
        super();
    }

    public Contact(String firstName, String lastName, String phoneNumber, String email, String address, User u) {
        super();
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setPhoneNumber(phoneNumber);
        this.setEmail(email);
        this.setAddress(address);
        this.user = u;
    }


    public int getContactId() {
        return contactId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
        Contact other = (Contact) obj;
        if (contactId == null) {
            if (other.contactId != null) {
                return false;
            }
        } else if (!contactId.equals(other.contactId)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((contactId == null) ? 0 : contactId.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Contact [contactId=" + contactId + "]";
    }
}
