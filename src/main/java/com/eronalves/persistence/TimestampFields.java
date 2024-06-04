package com.eronalves.persistence;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 * TimestampFields
 */
@Embeddable
public class TimestampFields {

  @Column(name = "created_at")
  public LocalDateTime createdAt;

  @Column(name = "updated_at")
  public LocalDateTime updatedAt;

}
