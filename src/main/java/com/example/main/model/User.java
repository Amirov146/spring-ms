package com.example.main.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@Table(name = "users")
public class User {
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;

      @Column(name = "username")
      private String username;
      @Column(name = "password")
      private String password;
      @Column(name = "email")
      private String email;
      @Column(name = "first_name")
      private String first_name;
      @Column(name = "last_name")
      private String last_name;

      @ManyToMany
      @JoinTable(name = "users_roles",
              joinColumns = @JoinColumn(name = "user_id"),
              inverseJoinColumns = @JoinColumn(name = "role_id"))
      private Collection<Role> roles;

      public Long getId() {
            return id;
      }

      public void setId(Long id) {
            this.id = id;
      }

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

      public String getEmail() {
            return email;
      }

      public void setEmail(String email) {
            this.email = email;
      }

      public String getFirst_name() {
            return first_name;
      }

      public void setFirst_name(String first_name) {
            this.first_name = first_name;
      }

      public String getLast_name() {
            return last_name;
      }

      public void setLast_name(String last_name) {
            this.last_name = last_name;
      }

      public Collection<Role> getRoles() {
            return roles;
      }

      public void setRoles(Collection<Role> roles) {
            this.roles = roles;
      }

      public User(){}

      public User(Long id, String username, String password, String email, String first_name, String last_name, Collection<Role> roles) {
            this.id = id;
            this.username = username;
            this.password = password;
            this.email = email;
            this.first_name = first_name;
            this.last_name = last_name;
            this.roles = roles;
      }
}
