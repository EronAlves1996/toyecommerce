package com.eronalves.product;

import org.jboss.resteasy.reactive.RestResponse;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;

/**
 * ProductResource
 */
@Path("/product")
public class ProductResource {

  static class ProductDTO {
    public String name;
    public int price;

    Product toEntity() {
      var entity = new Product();
      entity.name = this.name;
      entity.price = this.price;
      return entity;
    }
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  public Uni<RestResponse<Void>> createProduct(ProductDTO product,
      UriInfo uriInfo) {
    return Panache.withTransaction(product.toEntity()::persist)
        .map(p -> (Product) p)
        .map(p -> p.informPath(uriInfo))
        .map(RestResponse::created);
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Uni<RestResponse<Product>> retrieve(@PathParam("id") long id) {
    return Product.findById(id)
        .map(p -> (Product) p)
        .map(p -> RestResponse.ok(p));
  }

}
