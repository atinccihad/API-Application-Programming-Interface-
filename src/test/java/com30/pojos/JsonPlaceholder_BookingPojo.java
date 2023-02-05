package com30.pojos;

public class JsonPlaceholder_BookingPojo {
    /*
    {
     "firstname":"Selim",
     "lastname":"Ak",
     "totalprice":15000,
     "depositpaid":true,
     "bookingdates":{
       "checkin":"2020-09-09",
       "checkout":"2020-09-21"
         }
       }
     */
    // 1- private degiskenler
    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private JsonPlaceholder_BookingDatesPojo jsonPlaceholderBookingDatesPojo;
    // daha once olusturdugumuz pojo kalibini datatype olarak belirterek yeni pojonun icerisine aktarmis oldum.

    // 2- getter setter
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    public boolean isDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public JsonPlaceholder_BookingDatesPojo getBookingDatesPojo() {
        return jsonPlaceholderBookingDatesPojo;
    }

    public void setBookingDatesPojo(JsonPlaceholder_BookingDatesPojo jsonPlaceholderBookingDatesPojo) {
        this.jsonPlaceholderBookingDatesPojo = jsonPlaceholderBookingDatesPojo;
    }

    // 3- constructor(parametreli ve parametresiz)
    public JsonPlaceholder_BookingPojo() {
    }

    public JsonPlaceholder_BookingPojo(String firstname, String lastname, int totalprice, boolean depositpaid, JsonPlaceholder_BookingDatesPojo jsonPlaceholderBookingDatesPojo) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        this.jsonPlaceholderBookingDatesPojo = jsonPlaceholderBookingDatesPojo;
    }

    // 4- toString()
    @Override
    public String toString() {
        return "JsonPlaceholder_BookingPojo{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", totalprice=" + totalprice +
                ", depositpaid=" + depositpaid +
                ", bookingDatesPojo=" + jsonPlaceholderBookingDatesPojo +
                '}';
    }
}
