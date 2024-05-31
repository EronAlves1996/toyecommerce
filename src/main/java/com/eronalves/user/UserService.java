package com.eronalves.user;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserService {

  public Uni<User> retrieve(String id) {
    return User.<User>findById(id)
        .onItem()
        .ifNull()
        .fail();
  }

  public Uni<User> create(User user) {
    return Panache.withTransaction(() -> user.<User>persist());
  }

}
