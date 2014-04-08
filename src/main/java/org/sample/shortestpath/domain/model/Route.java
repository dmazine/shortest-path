/*
 *  @(#)Route.java
 * 
 *  Copyright 2014 Diego Rani Mazine. All rights reserved.
 */
package org.sample.shortestpath.domain.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

/**
 * This class represents a route between two locations.
 * 
 * @author Diego Rani Mazine
 */
@RelationshipEntity(type = "CONNECTED_TO")
public class Route {

	/** ID */
	@GraphId
	private Long id;

	/** Origin. */
	@StartNode
	@Fetch
	private Location origin;

	/** Destination. */
	@EndNode
	@Fetch
	private Location destination;

	/** The distance between the locations. */
	private Double distance;

	/**
	 * Creates a new Route object.
	 */
	public Route() {
	}

	/**
	 * Creates a new Route object.
	 * 
	 * @param origin
	 *            the route origin.
	 * @param destination
	 *            the route destination.
	 * @param distance
	 *            the distance between the two location.s
	 */
	public Route(Location origin, Location destination, double distance) {
		setOrigin(origin);
		setDestination(destination);
		setDistance(distance);
	}

	/**
	 * Gets the route id.
	 * 
	 * @return the route id.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the route id.
	 * 
	 * @param id
	 *            the id to set.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the route origin.
	 * 
	 * @return the route origin.
	 */
	public Location getOrigin() {
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
	public void setOrigin(Location origin) {
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
	public Location getDestination() {
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
	public void setDestination(Location destination) {
		if (destination == null) {
			throw new IllegalArgumentException("destination is null");
		}
		this.destination = destination;
	}

	/**
	 * Gets the distance between the locations.
	 * 
	 * @return the distance between the locations.
	 */
	public Double getDistance() {
		return distance;
	}

	/**
	 * Sets the distance between the locations.
	 * 
	 * @param distance
	 *            the distance to set.
	 * @throws IllegalArgumentException
	 *             if distance is null or negative.
	 */
	public void setDistance(Double distance) {
		if (distance == null) {
			throw new IllegalArgumentException("distance is null");
		}
		if (distance < 0) {
			throw new IllegalArgumentException("distance is negative");
		}
		this.distance = distance;
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
		return new EqualsBuilder().appendSuper(super.equals(obj))
				.append(id, rhs.id).isEquals();
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
