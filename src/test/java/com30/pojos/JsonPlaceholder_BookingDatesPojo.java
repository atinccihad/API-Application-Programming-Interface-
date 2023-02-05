package com30.pojos;

public class JsonPlaceholder_BookingDatesPojo {

    // 1- private degiskenler
    private String checkin;
    private String checkout;

    // 2- getter setter
    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    // 3- constructor(parametreli ve parametresiz)
    public JsonPlaceholder_BookingDatesPojo() {
    }

    public JsonPlaceholder_BookingDatesPojo(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

    // 4- toString()
    @Override
    public String toString() {
        return "JsonPlaceholder_BookingDatesPojo{" +
                "checkin='" + checkin + '\'' +
                ", checkout='" + checkout + '\'' +
                '}';
    }
}
