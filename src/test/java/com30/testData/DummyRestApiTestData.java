package com30.testData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DummyRestApiTestData {
    public HashMap<String, Object> setUpTestData() {

        List<Integer> arananYaslar = new ArrayList<>();
        arananYaslar.add(40);
        arananYaslar.add(21);
        arananYaslar.add(19);

        HashMap<String,Object> birinci =new HashMap<>();
        birinci.put("id",1);
        birinci.put("employee_name","Tiger Nixon");
        birinci.put("employee_salary",320800);
        birinci.put("employee_age",61);
        birinci.put("profile_image","");

        HashMap<String,Object> ikinci =new HashMap<>();
        ikinci.put("id",2);
        ikinci.put("employee_name","Garrett Winters");
        ikinci.put("employee_salary",170750);
        ikinci.put("employee_age",63);
        ikinci.put("profile_image","");

        HashMap<String,Object> ucuncu =new HashMap<>();
        ucuncu.put("id",3);
        ucuncu.put("employee_name","Ashton Cox");
        ucuncu.put("employee_salary",86000);
        ucuncu.put("employee_age",66);
        ucuncu.put("profile_image","");

        HashMap<String,Object> dorduncu =new HashMap<>();
        dorduncu.put("id",4);
        dorduncu.put("employee_name","Cedric Kelly");
        dorduncu.put("employee_salary",433060);
        dorduncu.put("employee_age",33);
        dorduncu.put("profile_image","");

        HashMap<String,Object> besinci =new HashMap<>();
        besinci.put("id",5);
        besinci.put("employee_name","Airi Satou");
        besinci.put("employee_salary",162700);
        besinci.put("employee_age",33);
        besinci.put("profile_image","");

        HashMap<String,Object> altinci =new HashMap<>();
        besinci.put("id",6);
        besinci.put("employee_name","Brielle Williamson");
        besinci.put("employee_salary",372000);
        besinci.put("employee_age",61);
        besinci.put("profile_image","");

        HashMap<String, Object> onbirinci = new HashMap<>();
        onbirinci.put("id", 11);
        onbirinci.put("employee_name", "Jena Gaines");
        onbirinci.put("employee_salary", 90560);
        onbirinci.put("employee_age", 30);
        onbirinci.put("profile_image", "");

        HashMap<String, Object> expectedData = new HashMap<>();

        expectedData.put("statusCode", 200);
        expectedData.put("besinciCalisan", "Airi Satou");
        expectedData.put("birinciCalisan", "Tiger Nixon");
        expectedData.put("ikinciCalisan", "Garrett Winters");
        expectedData.put("calisanSayisi", 24);
        expectedData.put("sondanIkinciCalisanMaasi", 106450);
        expectedData.put("birinciCalisanMaasi", 320800);
        expectedData.put("ikinciCalisanMaasi", 170750);
        expectedData.put("birinciCalisanYasi", 61);
        expectedData.put("birinciCalisanBilgileri", "{id=1, employee_name=Tiger Nixon, employee_salary=320800, employee_age=61, profile_image=}");
        expectedData.put("ikinciCalisanBilgileri", "{id=2, employee_name=Garrett Winters, employee_salary=170750, employee_age=63, profile_image=}");
        expectedData.put("arananYaslar", arananYaslar);
        expectedData.put("onbirinciCalisan", onbirinci);

        return expectedData;
    }
    public HashMap<String, Integer> setUpTestData02(){
        HashMap<String,Integer> expectedData = new HashMap<>();
        expectedData.put("statusCode",200);
        expectedData.put("enYuksekMaas",725000);
        expectedData.put("enKucukYas",19);
        expectedData.put("ikinciEnYuksekMaas",675000);

        HashMap<String,Object> maasListesi =new HashMap<>();
        maasListesi.put("1. KisiMaasi",320800);
        maasListesi.put("2. KisiMaasi",170750);
        maasListesi.put("3. KisiMaasi",86000);
        maasListesi.put("4. KisiMaasi",433060);
        maasListesi.put("5. KisiMaasi",162700);
        maasListesi.put("6. KisiMaasi",372000);
        maasListesi.put("7. KisiMaasi",137500);
        maasListesi.put("8. KisiMaasi",327900);
        maasListesi.put("9. KisiMaasi",205500);
        maasListesi.put("10. KisiMaasi",103600);
        maasListesi.put("11. KisiMaasi",90560);
        maasListesi.put("12. KisiMaasi",342000);
        maasListesi.put("13. KisiMaasi",470600);
        maasListesi.put("14. KisiMaasi",313500);
        maasListesi.put("15. KisiMaasi",385750);
        maasListesi.put("16. KisiMaasi",198500);
        maasListesi.put("17. KisiMaasi",725000);
        maasListesi.put("18. KisiMaasi",237500);
        maasListesi.put("19. KisiMaasi",132000);
        maasListesi.put("20. KisiMaasi",217500);
        maasListesi.put("21. KisiMaasi",345000);
        maasListesi.put("22. KisiMaasi",675000);
        maasListesi.put("23. KisiMaasi",106450);
        maasListesi.put("24. KisiMaasi",85600);

        return expectedData;
    }
}
