package com.eronalves.product;

import java.net.URI;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.ws.rs.core.UriInfo;

@Entity
public class Product extends PanacheEntity {

  public String name;
  public int price;

  public URI informPath(UriInfo uriInfo) {
    return uriInfo.getAbsolutePathBuilder()
        .segment(this.id.toString())
        .build();
  }

}
