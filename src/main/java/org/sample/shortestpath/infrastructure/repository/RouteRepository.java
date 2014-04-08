/*
 *  @(#)RouteRepository.java
 * 
 *  Copyright 2014 Diego Rani Mazine. All rights reserved.
 */
package org.sample.shortestpath.infrastructure.repository;

import org.sample.shortestpath.domain.model.Route;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Route Repository.
 * 
 * @author Diego Rani Mazine
 */
public interface RouteRepository extends GraphRepository<Route> {

}
