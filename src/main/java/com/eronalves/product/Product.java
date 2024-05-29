package com.eronalves.product;

import java.net.URI;

import com.eronalves.product.ProductResource.ProductDTO;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.ws.rs.core.UriInfo;

@Entity
public class Product extends PanacheEntity {

  public String name;
  public int price;

  public static Product of(ProductDTO product) {
    var entity = new Product();
    entity.name = product.name;
    entity.price = product.price;
    return entity;
  }

  public URI informPath(UriInfo uriInfo) {
    return uriInfo.getAbsolutePathBuilder()
        .segment(this.id.toString())
        .build();
  }

}
