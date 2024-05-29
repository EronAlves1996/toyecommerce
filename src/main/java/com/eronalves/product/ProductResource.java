package com.eronalves.product;

import org.jboss.resteasy.reactive.RestResponse;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;

/**
 * ProductResource
 */
@Path("/product")
public class ProductResource {

  static class ProductDTO {
    String name;
    int price;
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  public Uni<RestResponse<Void>> createProduct(ProductDTO product,
      UriInfo uriInfo) {
    return Product.of(product)
        .persist()
        .map(p -> (Product) p)
        .map(p -> p.informPath(uriInfo))
        .map(RestResponse::created);
  }

}
