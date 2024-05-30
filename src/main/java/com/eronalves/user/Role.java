package com.eronalves.user;

import org.hibernate.Session;

import com.eronalves.persistence.DefaultEntity;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import io.smallrye.mutiny.Uni;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

/**
 * Role
 */
@Entity
public class Role extends DefaultEntity {

  enum RoleType {
    ADMIN(1), CUSTOMER(2);

    public int id;

    private RoleType(int id) {
      this.id = id;
    }
  }

  public static Uni<Role> from(RoleType role) {
    return PanacheEntityBase.getSession()
        .map(s -> s.getReference(Role.class, role.id));
  }

  @Column(nullable = false, unique = true)
  public String name;

}
