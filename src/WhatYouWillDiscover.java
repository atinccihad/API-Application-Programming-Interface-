public class WhatYouWillLearn {
    /*
     API DERSİNDE NELER ÖĞRENILECEK?
     * POSTMAN

     * Rest Assured: REST API'lerini test etmek ve doğrulamak için kullanılan
                     Open Source (Açık Kaynak) bir Java kütüphanesidir.

     * Matchers Class kullanarak doğrulama
       http://hamcrest.org/JavaHamcrest/javadoc/1.3/org/hamcrest/Matchers.html

       Matchers.equalto(): Key-Value şeklinde girilen datanın, eşit olduğunu doğrulamak için kullanılır.
       Matchers.hasSize(): Datanın size'ını doğrulamak için kullanılır.
       Matchers.hasItem(): Girilen tek bir data'yı doğrulamak için kullanılır.
       Matchers.hasItems(): Girilen birden fazla datayı doğrulamak için kullanılır.

     * JUnit ve TestNG Assert'leri kullanılarak doğrulama

     * Json Path kullanarak doğrulama
         JsonPath() json = response.JsonPath();

     * De-Serialization: JSON formatını Javaya dönüştürme
         HashMap<String, Object> actualData = response.as(HashMap.class);

     * Serialization: Java yapısında olan dataları JSON'a dönüştürme
         Gson gson = new Gson();
         String jsonFromJava = gson.toJson(actual);

     * JSON Object
         JsonPlaceHolderTestData testObje = new JsonPlaceHolderTestData();
         JSONObject expectedRequest = testObje.setUpPostData();

     * Pojo Class (Plain Old Java Object)

     * ObjectMapper: JSON ve POJO (Plain Old Java Objects) okuma ve yazma işlevlerinin yanı sıra
         dönüştürmeleri gerçekleştirmek için de kullanılır.

     * API den alınan response'ları bilgisayara text dosyası olarak kaydetme ve doğrulama yapma.
     * */
}