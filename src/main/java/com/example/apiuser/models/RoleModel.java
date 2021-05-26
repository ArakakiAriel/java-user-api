package com.example.apiuser.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity(name="roles")
@Table(name="roles") //It creates the table with this name or it will go to update it
public class RoleModel {

    @Id //to create primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", unique = true, nullable = false, updatable = false) //Column details
    int roleId;
    @NotNull(message="(Role name was not provided)")
    @Column(name = "role_name", unique = true, nullable = false)
    String roleName;
    @Column(name = "role_description")
    String roleDescription;
    @NotNull(message="(Role permission was not provided)")
    @Column(name = "role_permissions", nullable = false)
    String rolePermissions;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<UserModel> users = new HashSet<>();

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public String getRolePermissions() {
        return rolePermissions;
    }

    public void setRolePermissions(String rolePermissions) {
        this.rolePermissions = rolePermissions;
    }

    public Set<UserModel> getUsers() {
        return users;
    }

    public void setUsers(Set<UserModel> users) {
        this.users = users;
    }
}
