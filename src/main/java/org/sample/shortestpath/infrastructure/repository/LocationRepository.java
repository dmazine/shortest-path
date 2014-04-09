/*
 *  @(#)LocationRepository.java
 * 
 *  Copyright 2014 Diego Rani Mazine. All rights reserved.
 */
package org.sample.shortestpath.infrastructure.repository;

import org.sample.shortestpath.domain.model.Location;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.core.EntityPath;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Location Repository.
 * 
 * @author Diego Rani Mazine
 */
public interface LocationRepository extends GraphRepository<Location> {
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public Location getLocationByName(String name);


	/**
	 * Finds the shortest path between the origin and the destination.
	 * 
	 * @param origin
	 *            the origin.
	 * @param destination
	 *            the destination.
	 * @return the shortest path between the origin and the destination.
	 */
	@Query("START from=node:Location(name={0}), to=node:Location(name={1}) "
			+ "MATCH p = shortestPath(n-[*]-x) "
			+ "RETURN p")
	public Iterable<EntityPath<Location, Location>> findShortestPath(String origin, String destination);

}
