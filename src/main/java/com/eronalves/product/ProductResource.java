package com.eronalves.product;

import org.jboss.resteasy.reactive.PartType;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.multipart.FileUpload;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.Consumes;
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
    @RestForm
    public String name;

    @RestForm
    public float price;

    @RestForm
    public FileUpload image;

    Product toEntity() {
      var entity = new Product();
      entity.name = this.name;
      entity.price = (int) this.price * 100;
      return entity;
    }
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.MULTIPART_FORM_DATA)
  public Uni<RestResponse<Void>> createProduct(@BeanParam ProductDTO product,
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
