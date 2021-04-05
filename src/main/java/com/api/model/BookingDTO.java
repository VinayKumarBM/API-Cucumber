package com.api.model;

public class BookingDTO
{
    private BookingDetailsDTO booking;

    private String bookingid;

    public BookingDetailsDTO getBooking ()
    {
        return booking;
    }

    public void setBooking (BookingDetailsDTO booking)
    {
        this.booking = booking;
    }

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
        return "ClassPojo [booking = "+booking+", bookingid = "+bookingid+"]";
    }
}