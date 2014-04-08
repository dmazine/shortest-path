/*
 *  @(#)LocationRepository.java
 * 
 *  Copyright 2014 Diego Rani Mazine. All rights reserved.
 */
package org.sample.shortestpath.infrastructure.repository;

import org.sample.shortestpath.domain.model.Location;
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


//	/**
//	 * Gets the shortest path between the origin and the destination.
//	 * 
//	 * @param origin
//	 *            the origin.
//	 * @param destination
//	 *            the destination.
//	 * @return the shortest path between the origin and the destination.
//	 */
//	
//	MATCH
//		(from:Location { name:"Location A" }),
//		(to:Location { name: "Location I"}),
//		path = (from)-[:CONNECTED_TO*]->(to)
//		RETURN path AS shortestPath,
//		reduce(distance = 0, r in relationships(path) | distance+r.distance) AS totalDistance
//		ORDER BY totalDistance ASC
//		LIMIT 1
//	@Query("START root=node:User(login='root') MATCH root-[:knows]->friends RETURN friends")
//	public List<Route> getShortestPath(Location origin, Location destination);

}
