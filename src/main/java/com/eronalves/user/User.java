package com.eronalves.user;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SoftDeleteType;

import com.eronalves.persistence.SecureEntity;
import com.eronalves.persistence.TimestampFields;
import com.eronalves.user.Role.RoleType;
import com.eronalves.user.UserResource.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.smallrye.mutiny.Uni;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.ws.rs.core.UriInfo;

@Entity
@Table(name = "app_user")
@SoftDelete(strategy = SoftDeleteType.DELETED)
public class User extends SecureEntity {

  public static Uni<User> from(UserDTO user) {
    return User.create()
        .invoke(entity -> {
          entity.name = user.name();
          entity.email = user.email();
          entity.password = user.password();
        });
  }

  public static Uni<User> create() {
    return Role.from(RoleType.CUSTOMER)
        .map(role -> {
          var user = new User();
          user.roles = Arrays.asList(role);
          return user;
        });
  }

  protected User() {
  }

  @Column(nullable = false, unique = true)
  public String name;

  @Column(nullable = false, unique = true)
  public String email;

  @Column(nullable = false)
  String password;

  @ManyToMany(fetch = FetchType.LAZY)
  @JsonIgnore
  @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
  public List<Role> roles;

  @Embedded
  public TimestampFields timestamps;

  public void setPassword(String password) {
    this.password = password;
  }

  public URI informPath(UriInfo uriInfo) {
    return uriInfo.getAbsolutePathBuilder()
        .segment(this.id.toString())
        .build();
  }

}
