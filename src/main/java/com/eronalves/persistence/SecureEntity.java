package com.eronalves.persistence;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

/**
 * SecureEntity
 */
@MappedSuperclass
public class SecureEntity extends PanacheEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  public String id;

}
