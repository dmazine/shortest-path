/*
 *  @(#)Route.java
 * 
 *  Copyright 2014 Diego Rani Mazine. All rights reserved.
 */
package org.sample.shortestpath.domain.model;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * This class represents a route between two locations.
 * 
 * @author Diego Rani Mazine
 */
public class Route {

	/** Origin. */
	private String origin;

	/** Destination. */
	private String destination;

	/** The list of legs associated with this route. */
	private List<RouteLeg> legs = null;

	/**
	 * Creates a new Route object.
	 * 
	 * @param origin
	 *            the route origin.
	 * @param destination
	 *            the route destination.
	 * @param legs
	 *            the list of legs associated with this route.
	 */
	public Route(String origin, String destination, List<RouteLeg> legs) {
		setOrigin(origin);
		setDestination(destination);
		setLegs(legs);
	}

	/**
	 * Gets the route origin.
	 * 
	 * @return the route origin.
	 */
	public String getOrigin() {
		return origin;
	}

	/**
	 * Sets the route origin.
	 * 
	 * @param origin
	 *            the origin to set.
	 * @throws IllegalArgumentException
	 *             if origin is null.
	 */
	private void setOrigin(String origin) {
		if (origin == null) {
			throw new IllegalArgumentException("origin is null");
		}
		this.origin = origin;
	}

	/**
	 * Gets the route destination.
	 * 
	 * @return the route destination.
	 */
	public String getDestination() {
		return destination;
	}

	/**
	 * Sets the route destination.
	 * 
	 * @param destination
	 *            the destination to set.
	 * @throws IllegalArgumentException
	 *             if destination is null.
	 */
	private void setDestination(String destination) {
		if (destination == null) {
			throw new IllegalArgumentException("destination is null");
		}
		this.destination = destination;
	}

	/**
	 * Gets the list of legs associated with this route.
	 * 
	 * @return the list of legs associated with this route.
	 */
	public List<RouteLeg> getLegs() {
		return Collections.unmodifiableList(legs);
	}

	/**
	 * Sets the list of legs associated with this route.
	 * 
	 * @param distance
	 *            the list of legs associated with this route.
	 * @throws IllegalArgumentException
	 *             if legs is null.
	 */
	private void setLegs(List<RouteLeg> legs) {
		if (legs == null) {
			throw new IllegalArgumentException("legs is null");
		}
		this.legs = legs;
	}

	/**
	 * Gets the length of the route.
	 * 
	 * @return the length of the route.
	 */
	public double getLength() {
		double length = 0;
		for (RouteLeg leg : legs) {
			length += leg.getDistance();
		}
		return length;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(origin).append(destination)
				.append(legs).toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}
		final Route rhs = (Route) obj;
		return new EqualsBuilder().append(origin, rhs.origin)
				.append(destination, rhs.destination).append(legs, rhs.legs)
				.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
