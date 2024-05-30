package com.eronalves.user;

import com.eronalves.persistence.DefaultEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

/**
 * Role
 */
@Entity
public class Role extends DefaultEntity {

  @Column(nullable = false, unique = true)
  public String name;

}
