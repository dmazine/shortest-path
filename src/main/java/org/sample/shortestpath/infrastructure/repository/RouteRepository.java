/*
 *  @(#)RouteRepository.java
 * 
 *  Copyright 2014 Diego Rani Mazine. All rights reserved.
 */
package org.sample.shortestpath.infrastructure.repository;

import java.util.List;

import org.sample.shortestpath.domain.model.Route;
import org.sample.shortestpath.domain.model.RouteLeg;

/**
 * This interface defines a Route repository.
 * 
 * @author Diego Rani Mazine
 */
public interface RouteRepository {

	/**
	 * Saves a Leg into the repository. If the Leg already exists in the
	 * Repository, it will be updated.
	 * 
	 * @param leg
	 *            the leg to be saved.
	 * @throws RepositoryExeption
	 *             if a repository access error occurs.
	 */
	public void saveLeg(RouteLeg leg) throws RepositoryExeption;

	/**
	 * Saves all given Legs into the repository. If a Leg already exists in the
	 * Repository, it will be updated.
	 * 
	 * @param legs
	 *            the list of legs to be saved.
	 * @throws RepositoryExeption
	 *             if a repository access error occurs.
	 */
	public void saveLegs(List<RouteLeg> legs) throws RepositoryExeption;

	/**
	 * Tries to find the shortest route between the origin and the destination.
	 * 
	 * @param origin
	 *            the route origin.
	 * @param destination
	 *            the route destination.
	 * @return the shortest route between the origin and the destination, or
	 *         null if no path was found.
	 * @throws RepositoryExeption
	 *             if a repository access error occurs.
	 */
	public Route findShortestRoute(String origin, String destination)
			throws RepositoryExeption;

}
