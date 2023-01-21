package com81.pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingDatesPojo {

    // 1- Tum keyler icin private variable'lar olusturuyoruz.
    private String checkin;
    private String checkout;
    // 2- Tum parametrelerle ve parametresiz constructorlar olusturuyoruz.
    public BookingDatesPojo() {
    }

    public BookingDatesPojo(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }
    // 3- public Getter ve Setter methotlarini olusturuyoruz.
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
    // 4- toString() olusturuyoruz.
    @Override
    public String toString() {
        return "BookingDatesPojo{" +
                "checkin='" + checkin + '\'' +
                ", checkout='" + checkout + '\'' +
                '}';
    }
}
