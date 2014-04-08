/*
 *  @(#)SampleTest.java
 * 
 *  Copyright 2014 Diego Rani Mazine. All rights reserved.
 */
package org.sample.shortestpath.infrastructure.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sample.shortestpath.domain.model.Location;
import org.sample.shortestpath.domain.model.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * LocationRepository test case.
 * 
 * @author Diego Rani Mazine
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SampleTest {

	/** Location repository. */
	@Autowired
	private LocationRepository locationRepository;

	/** Route repository. */
	@Autowired
	private RouteRepository routeRepository;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		//
		final Location a = new Location("a");
		final Location b = new Location("b");
		final Location c = new Location("c");
		final Location d = new Location("d");
		final Location e = new Location("e");

		locationRepository.save(a);
		locationRepository.save(b);
		locationRepository.save(c);
		locationRepository.save(d);
		locationRepository.save(e);
		
		final Route route1 = a.connectedTo(b, new Double(10));
		final Route route2 = b.connectedTo(d, new Double(15));
		final Route route3 = a.connectedTo(c, new Double(20));
		final Route route4 = c.connectedTo(d, new Double(30));
		final Route route5 = b.connectedTo(e, new Double(50));
		final Route route6 = d.connectedTo(e, new Double(30));


		routeRepository.save(route1);
		routeRepository.save(route2);
		routeRepository.save(route3);
		routeRepository.save(route4);
		routeRepository.save(route5);
		routeRepository.save(route6);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		//
		routeRepository.deleteAll();
		
		//
		locationRepository.deleteAll();
	}

	@Test
	public void test() {

		final Location a = locationRepository.getLocationByName("a");
		System.out.println("------------>>>>>>>>>>> a: " + a);
	}

//	/**
//	 * 
//	 * @param name
//	 * @return
//	 */
//	private Location createLocation(String name) {
//		return locationRepository.save(new Location(name));
//	}
//
//	/**
//	 * 
//	 * @param origin
//	 * @param destination
//	 * @param distance
//	 * @return
//	 */
//	private Route createRoute(Location origin, Location destination,
//			Double distance) {
//		return routeRepository.save(new Route(origin, destination, distance));
//	}
//
//    private void userClickedProduct(User user, Product product) {
//
//        user.addClickedProduct(product);
//
//        userRepository.save(user);
//        productRepository.save(product);
//    }	
}
