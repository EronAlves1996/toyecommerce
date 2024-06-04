package com.eronalves.user;

import com.eronalves.user.UserResource.UserDTO;

import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@WithTransaction
public class UserService {

  public Uni<User> retrieve(String id) {
    return User.<User>findById(id)
        .onItem()
        .ifNull()
        .fail();
  }

  public Uni<User> create(User user) {
    return user.<User>persist();
  }

  public Uni<User> tryUpdate(UserDTO user, String id) {
    return this.retrieve(id)
        .invoke(entity -> {
          entity.password = user.password();
          entity.name = user.name();
        });
  }

  public Uni<Void> softDelete(String id) {
    return Uni.createFrom().voidItem()
        .call(() -> User.deleteById(id));
  }
}
