package com.eronalves.persistence;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import org.hibernate.type.NullType;

import jakarta.persistence.AttributeConverter;

/**
 * SoftDeleteTimestampConverter
 */
public class SoftDeleteTimestampConverter
    implements AttributeConverter<Boolean, Object> {

  @Override
  public Object convertToDatabaseColumn(Boolean isDeleted) {
    if (isDeleted) {
      return Date
          .from(LocalDateTime.now().atZone(ZoneOffset.systemDefault())
              .toInstant());
    }
    return Date.from(Instant.ofEpochMilli(0));
  }

  @Override
  public Boolean convertToEntityAttribute(Object deletedAt) {
    return deletedAt != null;
  }

}
