package com.api.model;

public class BookingID {
	private String bookingid;

	public String getBookingid ()
	{
		return bookingid;
	}

	public void setBookingid (String bookingid)
	{
		this.bookingid = bookingid;
	}

	@Override
	public String toString()
	{
		return "ClassPojo [bookingid = "+bookingid+"]";
	}
}
