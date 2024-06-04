package com.eronalves.persistence;

import java.time.LocalDateTime;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 * TimestampFields
 */
@Embeddable
public class TimestampFields {

  @Temporal(TemporalType.TIMESTAMP)
  public LocalDateTime createdAt;

  @Temporal(TemporalType.TIMESTAMP)
  public LocalDateTime updatedAt;

  @Temporal(TemporalType.TIMESTAMP)
  public LocalDateTime deletedAt;

}
