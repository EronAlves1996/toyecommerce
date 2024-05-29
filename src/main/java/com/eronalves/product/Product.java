package com.eronalves.product;

import java.net.URI;

import com.eronalves.persistence.DefaultEntity;

import jakarta.persistence.Entity;
import jakarta.ws.rs.core.UriInfo;

@Entity
public class Product extends DefaultEntity {

  public String name;
  public int price;

  public URI informPath(UriInfo uriInfo) {
    return uriInfo.getAbsolutePathBuilder()
        .segment(this.id.toString())
        .build();
  }

}
