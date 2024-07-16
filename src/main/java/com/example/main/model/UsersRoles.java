package com.example.main.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users_roles")
public class UsersRoles implements Serializable{

    public static class Users_Roles_ID implements Serializable{
        private Long user_id;
        private Long role_id;
        Users_Roles_ID(){

        }
        public Users_Roles_ID(Long user_id, Long role_id){
            this.user_id = user_id;
            this.role_id = role_id;
        }
    }

    @EmbeddedId
    Users_Roles_ID users_roles_id;


    public UsersRoles(Users_Roles_ID users_roles_id) {
        this.users_roles_id = users_roles_id;
    }

    UsersRoles(){}
}
