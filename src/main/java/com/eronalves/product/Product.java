package com.eronalves.product;

import java.net.URI;

import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SoftDeleteType;

import com.eronalves.persistence.DefaultEntity;
import com.eronalves.persistence.TimestampFields;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.ws.rs.core.UriInfo;

@Entity
@SoftDelete(strategy = SoftDeleteType.DELETED)
public class Product extends DefaultEntity {

  public String name;
  public int price;

  @Column(name = "image_name")
  public String imageName;

  @Embedded
  public TimestampFields timestamps;

  public URI informPath(UriInfo uriInfo) {
    return uriInfo.getAbsolutePathBuilder()
        .segment(this.id.toString())
        .build();
  }

}
