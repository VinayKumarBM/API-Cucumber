package com.api.model;

public class BookingDates {
    private String checkin;

    private String checkout;

    public String getCheckin ()
    {
        return checkin;
    }

    public void setCheckin (String checkin)
    {
        this.checkin = checkin;
    }

    public String getCheckout ()
    {
        return checkout;
    }

    public void setCheckout (String checkout)
    {
        this.checkout = checkout;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [checkin = "+checkin+", checkout = "+checkout+"]";
    }
}