package com.eronalves.persistence;

import java.time.LocalDateTime;

import jakarta.persistence.AttributeConverter;

/**
 * SoftDeleteTimestampConverter
 */
public class SoftDeleteTimestampConverter
    implements AttributeConverter<Boolean, LocalDateTime> {

  @Override
  public LocalDateTime convertToDatabaseColumn(Boolean isDeleted) {
    if (isDeleted) {
      return LocalDateTime.now();
    }
    return null;
  }

  @Override
  public Boolean convertToEntityAttribute(LocalDateTime deletedAt) {
    return deletedAt != null;
  }

}
