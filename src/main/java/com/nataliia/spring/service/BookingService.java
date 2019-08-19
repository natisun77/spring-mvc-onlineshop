package com.nataliia.spring.service;

import com.nataliia.spring.model.Booking;

public interface BookingService {

    boolean addBooking(Booking booking, long userId);
}


