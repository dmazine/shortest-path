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
package org.drmit.shortestpath.application.services;

import org.drmit.shortestpath.domain.model.LogisticsNetwork;
import org.drmit.shortestpath.domain.model.ShippingDetails;

/**
 * Shipping service.
 * 
 * @author Diego Rani Mazine
 */
public interface ShippingService {

	/**
	 * Adds a new logistics network used for shipping route selection.
	 * 
	 * @param logisticsNetwork
	 *            the logistics network.
	 * @throws ServiceException
	 *             if a service access error occurs.
	 */
	public void addLogisticsNetwork(LogisticsNetwork logisticsNetwork)
			throws ServiceException;

	/**
	 * Gets an order shipping details.
	 * 
	 * @param origin
	 *            the origin of shipment.
	 * @param destination
	 *            the destination of shipment.
	 * @param vehicleMileage
	 *            vehicle mileage (in Kilometers per liter or KMPL).
	 * @param fuelPrice
	 *            fuel price per liter.
	 * @return the order shipping details.
	 * @throws NoShippingRouteServiceException
	 *             if no shipping route could be found between the origin and
	 *             destination.
	 * @throws ServiceException
	 *             if a service access error occurs.
	 */
	public ShippingDetails getShippingDetails(String origin,
			String destination, double vehicleMileage, double fuelPrice)
			throws NoShippingRouteServiceException, ServiceException;

}
