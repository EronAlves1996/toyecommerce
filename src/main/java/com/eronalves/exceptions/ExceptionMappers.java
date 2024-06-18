package com.eronalves.exceptions;

import org.hibernate.exception.ConstraintViolationException;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.RestResponse.ResponseBuilder;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import io.smallrye.mutiny.Uni;

/**
 * ExceptionMappers
 */
public class ExceptionMappers {

  @ServerExceptionMapper
  public Uni<RestResponse<String>> mapException(
      ConstraintViolationException cve) {
    return Uni.createFrom().item(cve)
        .map(e -> e.getMessage())
        .map(
            e -> ResponseBuilder.<String>create(RestResponse.Status.CONFLICT)
                .entity("Conflict")
                .build());
  }
}
