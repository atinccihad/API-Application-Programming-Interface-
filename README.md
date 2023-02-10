<h3 align="center"><img src="https://www.eventur.com/wp-content/uploads/2019/02/api-icon.png" /></h3>
API’ ı bir restoranda garson olarak düşünelim.
Bir garson olarak, API bir istemciden bir istek alır (Request), bu isteği veri tabanına alır, 
istenen verileri veri tabanından alır ve istemciye bir yanıt (Response) döndürür.

<h3>API Nedir?</h3>
- API ==> Application Programming Interface.
<br>- API’ nin UI (User Interface - Kullanıcı Arayüzü) yoktur.

<h3>API Testi Nedir?</h3> 
API testi, API‘ ın bir uygulamanın işlevselliği, güvenilirliği, performansı ve güvenliği açısından beklentileri karşılayıp karşılamadığını kontrol etmek için yapılır. <br>API testi uygulamanın görünümü ile ilgilenmez.

<h3>End to End (E2E) Senaryosu:</h3><br>
1) Create/Update/Delete kullanıcı arayüzü kullanarak verileri oluşturabilir, silebilir veya güncelleyebiliriz.
3) Create/Update/Delete SQL komutlarını kullanarak verileri oluşturabilir, silebilir veya güncelleyebiliriz.
2) Create/Update/Delete API kullanarak verileri oluşturabilir, silebilir veya güncelleyebiliriz.

Not: Her şirket kendi API'larını oluşturur ve bu hizmetleri satarak para kazanabilirler.

<h3>API ve Web Service Arasındaki Fark?</h3>
API ve Web Service uygulamalar arasında iletişim oluştururlar.
<br>Web Service Aralarındaki tek fark Web Service iki uygulama arasındaki iletişimi internet aracılığı ile yapar. 
<br>API Microsoft Word gibi masaüstü uygulamaları, internete ihtiyaç duymayan kendi API‘ larını kullanır. 
<br>Bu yüzden Microsoft Word kullandığımızda Microsoft Word API kullanıyoruz.
<br>Not: Bütün Web Service’ler API’dır. Ancak bütün API’lar Web Service değildir.

<h3>HTTP Nedir?</h3> 
<br> http: Hyper Text Transfer Protocol İstemci (Client) bilgisayarlar ve web sunucuları arasındaki iletişim, 
HTTP Request ve HTTP Response göndererek yapılır.<br> İstemciler (Client) genellikle tarayıcılardır (Chrome, Edge, Safari), 
ancak herhangi bir program veya cihaz türü olabilirler.<br> Sunucular (Server) genellikle buluttaki bilgisayarlardır. 
Client ve Server arasındaki iletişim, request ve response lar ile yapılır::<br> 1. Bir Client (bir tarayıcı) web'e bir HTTP Request gönderir.
<br>2. Bir Web Server Request’i alır.
<br>3. Server, Request’i işlemek için bir uygulama çalıştırır.
<br>4. Server tarayıcıya bir HTTP Response (çıktı) gönderir.
<br>5. Client (Browser) Response’u alır.

<h3>API Testinde Tam Olarak Neyin Doğrulanması Gerekir?</h3>
<br>API testinde, bizler API’a bilinen verilerle bir istek göndeririz ve cevabı test ederiz.
<br>1) HTTP Status Kodları
<br>2) Data Doğruluğu 

<h3>Ortak HTTP status Kodları</h3>
<br>1) 200 (OK) ==> Talep karşılandı. Request gerçekleşti.
<br>2) 201 (CREATED) ==> Database’ e gönderilen datanın oluşturulduğunu söyler. (200 olarak da gelebilir)
<br>3) 204 (NO CONTENT) ==> Talep yerine getirildi ancak size cevap (response) dönemiyoruz.
<br>4) 400 (BAD REQUEST) ==> Syntax yanlış, yüksek boyutlu data ya da diğer kullanıcı hatası...
<br>5) 403 (FORBIDDEN) ==> Bu dataya erişim izniniz yok. (400 olarak da gelebilir)
<br>6) 404 (NOT FOUND) ==> Aranılan data bulunamadı.
<br>7) 500 (INTERNAL SERVER ERROR) ==> Server kaynaklı bir hatadır, server’ın düzelmesini bekleriz.

<h3>API Testlerinde Hangi Programları Kullanırız?</h3> 
<br>1) Postman, geliştiricilerin API’ları oluşturmasını, paylaşmasını ve test etmesini ve belgelemesini sağlayan popüler bir uygulamadır.
<br>2) SOAP UI SOAP: Simple Object Access Protocol(Basit Nesne Erişim Protokolü) Web uygulamalarının internet üzerinden haberleşebilmesi önemlidir. 
<br>HTTP, tüm internet tarayıcıları ve sunucuları tarafından desteklendiğinden, uygulamalar arasında iletişim kurmanın en iyi yolu HTTP üzerindendir.SOAP bunu başarmak için oluşturulmuştur. 
<br>SOAP, farklı teknolojiler ve programlama dilleri ile farklı işletim sistemlerinde çalışan uygulamalar arasında iletişim kurmak için bir yol veya biçim sağlar. 
<br>SOAP, platformlardan bağımsızdır.
<h3>3) REST / RestFul</h3>
<br>REST:  REpresentational State Transfer
<br>-	REST sistemlerin birbirleri ile iletişim kurmalarını kolaylaştırır.
<br>-	REST mimarisinde, client’ lar dataları almak (retrive) veya değiştirmek (modify) için request gönderir ve server’lar bu request’lere response gönderir.
<br>-	REST , XML ve JSON (Java Script Object Notation) formatlarının ikisini de kullanabilir. Bu sebeple SOAP dan daha yaygın bir kullanımı vardır.
<h3>Bir REST Request’i Nelerden Oluşur?</h3>
<br>1) Bir HTTP yöntemi, ne tür bir işlemin gerçekleştirileceğini tanımlar.
<br>   A) GET, dataları okumak içindir. ==> Bu sitede hotel arayalım www.hotels.com
<br>   B) POST, yeni data oluşturmak içindir. ==> Sitede hotel rezervasyonu yapalım www.hotels.com
<br>   C) PUT, dataları tamamen güncellemek içindir. ==>Tüm rezervasyon datasını değiştirelim    www.hotels.com
<br>   D) PATCH, dataları kısmen güncellemek içindir. ==> Sadece rezervasyon tarihini değiştirelim www.hotels.com
<br>   E) DELETE, dataları silmek içindir. ==> Rezervasyonu iptal edelim. www.hotels.com
<br>2) HEADER(Başlık)‘da, istemci(Client) sunucudan alınacak içeriğin türünü gönderir. 
<br>Header(Başlık), sunucunun istemci tarafından anlaşılamayan veya işlenemeyen verileri göndermemesini sağlar. 
<h3>Bir kaynağa PATH(Yol) (Endpoint/URL/URI) oluşturma:</h3> 
İstekler(Request), işlemin gerçekleştirileceği bir kaynağa giden yolu içermelidir.
<h3>API Endpoint Nedir?</h3> 
<br>API’ın istek gönderdiği ve kaynağın yaşadığı yer endpoint’tir. 
<br>API developer’lar bir API oluşturduğunda genel endpoint’ler(URL) oluştururlar ve hangi endpoint’in hangi HTTP Request yöntemleriyle çalışacağına karar verirler.
<h3>Swagger Nedir?</h3>
<br>Swagger, API’ nızın, zayıf kaynak kodu bilgisine sahip olsalar bile geliştiriciler ve testerlar tarafından okunması ve anlaşılması kolay ortak bir dil kullanarak tanımlamak için bir framework’tür.
<br>Bunu bir ev planı gibi düşünebilirsiniz.İstediğiniz yapı malzemesini kullanabilirsiniz ancak evin planının dışına çıkamazsınız.
<br>API belgeleri için SWAGGER kullandım. SWAGGER bana API endpoint’leri veriyor ve bunların nasıl kullanılacağı hakkında beni bilgilendiriyor.
<h3>GET Request Nasıl Çalışır?</h3>
<br>Postman, end-point URL’ini girdiğimiz backend testi için kullanılır. Request’i sunucuya gönderir ve sunucudan yanıtı alır.
<br> A) Parameters(Yol Parametresi) (Optional)
<br> B) REST API URL/Endpoint(Mandatory), Query Parameters (Sorgu Parametresi) ya da Path 
<br> C) Headers‘da kullanılacak cevap formatını seçmek. XML ya da JSON (Optional) olabilir. 
<h3>POST Request Nasıl Çalışır?</h3>
<br>Note: Yanıt aldıktan sonra öncelikle status code kontrol ederiz.
<br>Note: POST Request yapabilmeniz için yetkilendirilmiş olmanız gerekmektedir.
<h3>PUT Request Nasıl Çalışır?</h3>
<br>Note: Yanıt aldıktan sonra öncelikle status code kontrol ederiz.
<br>Note: PUT Request yapabilmeniz için yetkilendirilmiş olmanız gerekmektedir.
<h3>PATCH Request Nasıl Çalışır?</h3>
<br>Note: Yanıt aldıktan sonra öncelikle status code kontrol ederiz. 
<br>Note: PATCH Request yapabilmeniz için yetkilendirilmiş olmanız gerekmektedir.
<h3>DELETE Request Nasıl Çalışır?</h3>
<br>Note: Yanıt aldıktan sonra öncelikle status code kontrol ederiz.
<br>Note: DELETE Request yapabilmeniz için yetkilendirilmiş olmanız gerekmektedir.
<h3>Postman‘de Testler Nasıl Yapılır ?</h3>
<br>1) Tests’ e tıklanır 
<br>2) Seçim
<br>3) Güncellemeye ihtiyacınız varsa
<br>4) Gönder
<br>5) İsterseniz kayıt edebilirsiniz
<h3>API Test Case‘leri Nasıl Yapılır ?</h3>
<br>Aşağıdaki testleri Postman Requestlerini kullanarak yapalım (Manually)
<br>1)	When REST API ye GET sorgusu gönderdiğimizde 
       <br>URL: https://restful-booker.herokuapp.com/booking 
       <br>And Accept type: “application/JSON” olmalıdır 
      <br>Then HTTP Status Code: 200 olmalıdır 
      <br>And Response formatı: "application/JSON"
<br>2)	When REST API ye GET sorgusu gönderdiğimizde 
      <br>URL https://restful-booker.herokuapp.com/booking/5 
      <br>And Accept formatı “application/JSON” olmalıdır 
     <br>Then HTTP Status Code 200 olmalıdır 
     <br>And first name “Mary” olmalıdır 
     <br>And total price 814 olmalıdır
<br>3)	When REST API ye GET sorgusu gönderdiğimizde 
     <br>URL http://dummy.restapiexample.com/api/v1/employees And Accept formatı “application/JSON” olmalıdır 
     <br>Then HTTP Status Code 200 olmalıdır And “Gerret Winters” data da görünmelidir.
<br>4)	When REST API ye GET sorgusu gönderdiğimizde URL http://dummy.restapiexample.com/api/v1/employee/1 
     <br>And Accept formatı “application/JSON” olmalıdır Then HTTP Status Code 200 olmalıdır 
     <br>And employee name “Tiger Nixon” olmalıdır And employee salary “320800” olmalıdır And employee age “61” olmalıdır
