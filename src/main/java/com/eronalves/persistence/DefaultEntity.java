package com.eronalves.persistence;

import org.hibernate.annotations.SoftDelete;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.Embedded;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

/**
 * CommerceEntity
 */
@MappedSuperclass
@SoftDelete(columnName = "deleted_at", converter = SoftDeleteTimestampConverter.class)
public class DefaultEntity extends PanacheEntityBase {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Integer id;

  @Embedded
  public TimestampFields timestampFields;
}
