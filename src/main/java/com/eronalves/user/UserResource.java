package com.eronalves.user;

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

  static class UserDTO {
    public String email;
    public String name;
    public String password;
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
    return this.userService.create(User.from(user))
        .map(u -> u.informPath(uriInfo))
        .map(RestResponse::created);
  }

  @PUT
  @Path("/{id}")
  public Uni<RestResponse<Void>> updateOrCreate(UserDTO user,
      @PathParam("id") String id, UriInfo uriInfo) {
    return this.userService.tryUpdate(user, id);
    // TODO: implement subsequent logic for create when user doesn't exists
  }

  @DELETE
  @Path("/{id}")
  public Uni<Void> softDelete(@PathParam("id") String id) {
    this.userService.softDelete(id);
  }

}
