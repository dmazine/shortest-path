/*
 *  @(#)Location.java
 * 
 *  Copyright 2014 Diego Rani Mazine. All rights reserved.
 */
package org.sample.shortestpath.domain.model;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedToVia;

/**
 * Location.
 * 
 * @author Diego Rani Mazine
 */
@NodeEntity
public class Location {

	/** ID */
	@GraphId
	private Long id;

	/** Location name. */
	@Indexed(unique = true)
	private String name;

	/** Routes. */
	@RelatedToVia(type = "CONNECTED_TO")
	private Set<Route> routes = new HashSet<Route>();

	/**
	 * Creates a Location object.
	 */
	public Location() {
	}

	/**
	 * Creates a Location object.
	 * 
	 * @param name
	 *            the name to set.
	 */
	public Location(String name) {
		setName(name);
	}

	/**
	 * Gets the location id.
	 * 
	 * @return the location id.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the location id.
	 * 
	 * @param id
	 *            the id to set.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the location name.
	 * 
	 * @return the location name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the location name.
	 * 
	 * @param name
	 *            the name to set.
	 * @throws IllegalArgumentException
	 *             if name is null.
	 */
	public void setName(String name) {
		if (name == null) {
			throw new IllegalArgumentException("name is null");
		}
		this.name = name;
	}

	/**
	 * TODO:
	 * 
	 * @param location
	 * @param distance
	 * @return
	 */
	public Route connectedTo(Location location, Double distance) {
		final Route route = new Route(this, location, distance);
		routes.add(route);
		return route;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(id).toHashCode();
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
		final Location rhs = (Location) obj;
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
