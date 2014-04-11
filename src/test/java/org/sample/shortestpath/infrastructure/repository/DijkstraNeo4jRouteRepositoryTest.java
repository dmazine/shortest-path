/*
 *  @(#)DijkstraNeo4jRouteRepositoryTest.java.java
 * 
 *  Copyright 2014 Diego Rani Mazine. All rights reserved.
 */
package org.sample.shortestpath.infrastructure.repository;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sample.shortestpath.domain.model.Route;
import org.sample.shortestpath.domain.model.RouteLeg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Diego Rani Mazine
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:DijkstraNeo4jRouteRepositoryTest.xml")
public class DijkstraNeo4jRouteRepositoryTest {

	/** Location repository. */
	@Autowired
	private RouteRepository routeRepository;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Populates the database
		final List<RouteLeg> legs = new LinkedList<RouteLeg>();
		legs.add(new RouteLeg("A", "B", 10));
		legs.add(new RouteLeg("B", "D", 15));
		legs.add(new RouteLeg("A", "C", 20));
		legs.add(new RouteLeg("C", "D", 20));
		legs.add(new RouteLeg("B", "E", 50));
		legs.add(new RouteLeg("D", "E", 50));

		// Saves all given Legs into the repository
		routeRepository.saveLegs(legs);
	}

	/**
	 * Test method for
	 * {@link org.sample.poc.dijkstra.infrastructure.repository.neo4j.AbstractNeo4jRouteRepository#findShortestRoute(java.lang.String, java.lang.String)}
	 * .
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidOrigin() throws RepositoryExeption {
		// Finds the shortest route between the origin and the destination
		routeRepository.findShortestRoute(null, "D");
	}

	/**
	 * Test method for
	 * {@link org.sample.poc.dijkstra.infrastructure.repository.neo4j.AbstractNeo4jRouteRepository#findShortestRoute(java.lang.String, java.lang.String)}
	 * .
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidDestination() throws RepositoryExeption {
		// Finds the shortest route between the origin and the destination
		routeRepository.findShortestRoute("A", null);
	}

	/**
	 * Test method for
	 * {@link org.sample.poc.dijkstra.infrastructure.repository.neo4j.AbstractNeo4jRouteRepository#findShortestRoute(java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testSameLocation() throws RepositoryExeption {
		// Finds the shortest route between the origin and the destination
		final Route actualRoute = routeRepository.findShortestRoute("A", "A");

		// Expected legs
		final List<RouteLeg> expectedLegs = Collections.emptyList();

		// Expected route
		final Route expectedRoute = new Route("A", "A", expectedLegs);

		// Asserts that the expected and the actual route are equals
		assertEquals(expectedRoute, actualRoute);
		assertEquals(expectedRoute.getLength(), 0, 0.001);
	}
	
	/**
	 * Test method for
	 * {@link org.sample.poc.dijkstra.infrastructure.repository.neo4j.AbstractNeo4jRouteRepository#findShortestRoute(java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testFindShortestRoute() throws RepositoryExeption {
		// Finds the shortest route between the origin and the destination
		final Route actualRoute = routeRepository.findShortestRoute("A", "D");

		// Expected legs
		final List<RouteLeg> expectedLegs = new LinkedList<RouteLeg>();
		expectedLegs.add(new RouteLeg("A", "B", 10));
		expectedLegs.add(new RouteLeg("B", "D", 15));

		// Expected route
		final Route expectedRoute = new Route("A", "D", expectedLegs);

		// Asserts that the expected and the actual route are equals
		assertEquals(expectedRoute, actualRoute);
		assertEquals(expectedRoute.getLength(), 25, 0.001);
	}

	/**
	 * Test method for
	 * {@link org.sample.poc.dijkstra.infrastructure.repository.neo4j.AbstractNeo4jRouteRepository#findShortestRoute(java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testRoundTrip() throws RepositoryExeption {
		// Finds the shortest route between the origin and the destination
		final Route route = routeRepository.findShortestRoute("A", "D");

		// Finds the shortest route between the origin and the destination
		final Route returnRoute = routeRepository.findShortestRoute("D", "A");
		
		// Asserts that the expected and the actual route are equals
		assertEquals(route.getLength(), returnRoute.getLength(), 0.001);
	}

}
