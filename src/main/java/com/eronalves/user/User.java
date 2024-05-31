package com.eronalves.user;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import com.eronalves.persistence.SecureEntity;
import com.eronalves.user.Role.RoleType;
import com.eronalves.user.UserResource.UserDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.ws.rs.core.UriInfo;

@Entity
@Table(name = "app_user")
public class User extends SecureEntity {

  public static User from(UserDTO user) {
    var entity = new User();
    entity.name = user.name;
    entity.email = user.email;
    entity.password = user.password;
    return entity;
  }

  public User() {
    super();
    this.roles = Arrays.asList(Role.from(RoleType.CUSTOMER));
  }

  @Column(nullable = false, unique = true)
  public String name;

  @Column(nullable = false, unique = true)
  public String email;

  @Column(nullable = false)
  String password;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
  public List<Role> roles;

  public void setPassword(String password) {
    this.password = password;
  }

  public URI informPath(UriInfo uriInfo) {
    return uriInfo.getAbsolutePathBuilder()
        .segment(this.id)
        .build();
  }

}
