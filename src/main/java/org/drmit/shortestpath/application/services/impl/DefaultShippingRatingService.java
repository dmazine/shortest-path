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
package org.drmit.shortestpath.application.services.impl;

import org.drmit.shortestpath.application.services.ServiceException;
import org.drmit.shortestpath.application.services.ShippingRatingService;
import org.springframework.stereotype.Service;

/**
 * Default ShippingRatingService interface implementation.
 * 
 * @author Diego Rani Mazine
 */
@Service
public class DefaultShippingRatingService implements ShippingRatingService {

	/**
	 * Constructs a new DefaultShippingRateService object.
	 */
	public DefaultShippingRatingService() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.drmit.shortestpath.application.services.ShippingRatingService#
	 * getShippingRate(double, double, double)
	 */
	@Override
	public double getShippingRate(double distance, double vehicleMileage,
			double fuelPrice) throws ServiceException {
		if (distance < 0) {
			throw new IllegalArgumentException("distance is null");
		}
		if (vehicleMileage <= 0) {
			throw new IllegalArgumentException("vehicleMileage is invalid");
		}
		if (fuelPrice < 0) {
			throw new IllegalArgumentException("fuelPrice is null");
		}

		return ((distance * fuelPrice) / vehicleMileage);
	}

}
