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
package org.drmit.shortestpath.presentation.controller;

import java.util.List;

import org.drmit.shortestpath.application.services.NoShippingRouteServiceException;
import org.drmit.shortestpath.application.services.ServiceException;
import org.drmit.shortestpath.application.services.ShippingService;
import org.drmit.shortestpath.domain.model.Leg;
import org.drmit.shortestpath.domain.model.LogisticsNetwork;
import org.drmit.shortestpath.domain.model.ShippingDetails;
import org.drmit.shortestpath.presentation.converter.LogisticsNetworkConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Shipping controller.
 * 
 * @author Diego Rani Mazine
 */
@Controller
@RequestMapping("/shipping")
public class ShippingController {

	/** Shipping service. */
	@Autowired
	private ShippingService shippingService = null;

	/** Logistics network converter. */
	@Autowired
	private LogisticsNetworkConverter logisticsNetworkConverter = null;

	/**
	 * Adds a new logistics network used for shipping route selection.
	 * 
	 * @param name
	 *            network name.
	 * @param legs
	 *            the shipping legs contained in this network.
	 * @throws ServiceException
	 *             if a service access error occurs.
	 */
	@RequestMapping(value = "/logisticsNetwork/{name}", method = RequestMethod.PUT)
	public void addLogisticsNetwork(@PathVariable String name,
			@RequestBody String legs) throws ServiceException {

		System.out.println("--->>>> network name: [" + name + "]");
		System.out.println("--->>>> legs: [" + legs + "]");

		final List<Leg> legs2 = logisticsNetworkConverter.parseLegs(legs);

		System.out.println("--->>>> legs2: [" + legs2 + "]");

		// Adds a new logistics network used for shipping route selection
		shippingService.addLogisticsNetwork(new LogisticsNetwork(name,
				logisticsNetworkConverter.parseLegs(legs)));
	}

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
	 * @throws ServiceException
	 *             if a service access error occurs.
	 */
	@RequestMapping(value = "/shippingDetails", method = RequestMethod.GET)
	public @ResponseBody
	ShippingDetails getShippingDetails(@RequestParam String origin,
			@RequestParam String destination,
			@RequestParam double vehicleMileage, @RequestParam double fuelPrice)
			throws NoShippingRouteServiceException, ServiceException {
		// Gets an order shipping details
		return shippingService.getShippingDetails(origin, destination,
				vehicleMileage, fuelPrice);
	}

	/**
	 * NoShippingRouteServiceException custom exception handler.
	 */
	@ExceptionHandler(NoShippingRouteServiceException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No route could be found between the origin and destination")
	public void notFound() {
	}

}
