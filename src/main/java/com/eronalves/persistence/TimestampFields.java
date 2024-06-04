package com.eronalves.persistence;

import java.time.LocalDateTime;

import jakarta.persistence.Embeddable;

/**
 * TimestampFields
 */
@Embeddable
public class TimestampFields {

  public LocalDateTime createdAt;

  public LocalDateTime updatedAt;

  public LocalDateTime deletedAt;

}
