package com.tsystems.nazukin.logiweb.model.entity;

import com.tsystems.nazukin.logiweb.model.enums.EmployeeType;

import javax.persistence.*;

/**
 * Created by 1 on 11.02.2016.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "EmployeeEntity.findAll", query = "SELECT c FROM EmployeeEntity c"),
        @NamedQuery(name = "EmployeeEntity.findByLoginAndPassword", query = "SELECT c FROM EmployeeEntity c " +
                "WHERE c.login=:login AND c.password=:password"),
        @NamedQuery(name = "EmployeeEntity.findByLogin", query = "SELECT c FROM EmployeeEntity c " +
                "WHERE c.login=:login"),
        @NamedQuery(name = "EmployeeEntity.findAllNew", query = "SELECT c FROM EmployeeEntity c " +
                "WHERE c.employeeType=:type")
})

@Table(name = "employees", schema = "logiwebdb", catalog = "")
public class EmployeeEntity {
    private int id;
    private String firstName;
    private String secondName;
    private String login;
    private String password;
    private EmployeeType employeeType;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "second_name")
    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    @Basic
    @Column(name = "login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "employee_type")
    @Enumerated(EnumType.STRING)
    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployeeEntity that = (EmployeeEntity) o;

        if (id != that.id) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (secondName != null ? !secondName.equals(that.secondName) : that.secondName != null) return false;
        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (employeeType != null ? !employeeType.equals(that.employeeType) : that.employeeType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (secondName != null ? secondName.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (employeeType != null ? employeeType.hashCode() : 0);
        return result;
    }
}
