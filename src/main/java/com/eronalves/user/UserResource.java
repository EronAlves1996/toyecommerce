package com.eronalves.user;

import java.util.NoSuchElementException;

import org.jboss.resteasy.reactive.RestResponse;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.UriInfo;

/**
 * UserResource
 */
@Path("/user")
public class UserResource {

  static record UserDTO(String email, String name, String password) {
  }

  private UserService userService;

  @Inject
  public UserResource(UserService userService) {
    this.userService = userService;
  }

  @GET
  @Path("/{id}")
  public Uni<User> retrieve(@PathParam("id") String id) {
    return this.userService.retrieve(id);
  }

  @POST
  public Uni<RestResponse<Void>> create(UserDTO user, UriInfo uriInfo) {
    return User.from(user)
        .chain(this.userService::create)
        .map(u -> u.informPath(uriInfo))
        .map(RestResponse::created);
  }

  @PUT
  @Path("/{id}")
  public Uni<RestResponse<Void>> updateOrCreate(UserDTO user,
      @PathParam("id") String id, UriInfo uriInfo) {
    return this.userService.tryUpdate(user, id)
        .onItem()
        .<RestResponse<Void>>transform(entity -> RestResponse.ok())
        .onFailure(NoSuchElementException.class)
        .recoverWithUni(t -> User.from(user)
            .invoke(entity -> {
              entity.id = id;
            })
            .chain(this.userService::create)
            .map(u -> u.informPath(uriInfo))
            .map(RestResponse::created));
  }

  @DELETE
  @Path("/{id}")
  public Uni<Void> softDelete(@PathParam("id") String id) {
    return this.userService.softDelete(id);
  }

}
