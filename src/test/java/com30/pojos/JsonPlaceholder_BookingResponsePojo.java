package com30.pojos;

public class JsonPlaceholder_BookingResponsePojo {

    // 1- private degiskenler
    private int bookingId;
    private JsonPlaceholder_BookingPojo booking;

    // 2- getter setter
    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public JsonPlaceholder_BookingPojo getBooking() {
        return booking;
    }

    public void setBooking(JsonPlaceholder_BookingPojo booking) {
        this.booking = booking;
    }

    // 3- constructor(parametreli ve parametresiz)
    public JsonPlaceholder_BookingResponsePojo() {
    }

    public JsonPlaceholder_BookingResponsePojo(int bookingId, JsonPlaceholder_BookingPojo booking) {
        this.bookingId = bookingId;
        this.booking = booking;
    }

    // 4- toString()
    @Override
    public String toString() {
        return "JsonPlaceholder_BookingResponsePojo{" +
                "bookingId=" + bookingId +
                ", booking=" + booking +
                '}';
    }
}
