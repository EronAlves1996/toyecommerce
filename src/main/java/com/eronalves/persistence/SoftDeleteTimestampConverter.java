package com.eronalves.persistence;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import jakarta.persistence.AttributeConverter;

/**
 * SoftDeleteTimestampConverter
 */
public class SoftDeleteTimestampConverter
    implements AttributeConverter<Boolean, Date> {

  @Override
  public Date convertToDatabaseColumn(Boolean isDeleted) {
    if (isDeleted) {
      return Date
          .from(LocalDateTime.now().atZone(ZoneOffset.systemDefault())
              .toInstant());
    }
    return null;
  }

  @Override
  public Boolean convertToEntityAttribute(Date deletedAt) {
    return deletedAt != null;
  }

}
