package com.starshop.services;

import com.starshop.entities.Address;

public interface GeoService {

	double getDistanceKm(Address address);

	double[] getLatLongFromAddress(Address address);

}
