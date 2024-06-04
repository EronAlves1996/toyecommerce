package com.eronalves.persistence;

import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SoftDeleteType;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.Embedded;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

/**
 * SecureEntity
 */
@MappedSuperclass
@SoftDelete(columnName = "deleted_at", converter = SoftDeleteTimestampConverter.class)
public class SecureEntity extends PanacheEntityBase {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  public String id;

  @Embedded
  public TimestampFields timestampFields;

}
