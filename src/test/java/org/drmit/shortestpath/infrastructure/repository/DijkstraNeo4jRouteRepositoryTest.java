/*
 * The MIT License (MIT)
 * 
 * Copyright (c) 2014 Diego Rani Mazine
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.drmit.shortestpath.infrastructure.repository;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.drmit.shortestpath.domain.model.Route;
import org.drmit.shortestpath.domain.model.RouteLeg;
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
	 * {@link org.drmit.poc.dijkstra.infrastructure.repository.neo4j.AbstractNeo4jRouteRepository#findShortestRoute(java.lang.String, java.lang.String)}
	 * .
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidOrigin() throws RepositoryExeption {
		// Finds the shortest route between the origin and the destination
		routeRepository.findShortestRoute(null, "D");
	}

	/**
	 * Test method for
	 * {@link org.drmit.poc.dijkstra.infrastructure.repository.neo4j.AbstractNeo4jRouteRepository#findShortestRoute(java.lang.String, java.lang.String)}
	 * .
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidDestination() throws RepositoryExeption {
		// Finds the shortest route between the origin and the destination
		routeRepository.findShortestRoute("A", null);
	}

	/**
	 * Test method for
	 * {@link org.drmit.poc.dijkstra.infrastructure.repository.neo4j.AbstractNeo4jRouteRepository#findShortestRoute(java.lang.String, java.lang.String)}
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
	 * {@link org.drmit.poc.dijkstra.infrastructure.repository.neo4j.AbstractNeo4jRouteRepository#findShortestRoute(java.lang.String, java.lang.String)}
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
	 * {@link org.drmit.poc.dijkstra.infrastructure.repository.neo4j.AbstractNeo4jRouteRepository#findShortestRoute(java.lang.String, java.lang.String)}
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
