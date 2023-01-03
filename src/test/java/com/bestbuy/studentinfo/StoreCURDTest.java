package com.bestbuy.studentinfo;

import com.bestbuy.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.hasValue;
@RunWith(SerenityRunner.class)
public class StoreCURDTest extends TestBase {

   static String name = "ABC";
    static String type = "max";
    static String address = "cecil road";
    static String address2 = "wealdstone";
    static String city = "london";
    static String state = "Middex";
    static String zip = "tv10";

    static String services;

    static int id = 100;

    static int lat = 11;

    static int lng = 12;


    static String hours = "1";
    static int createdAt = 2;
    static int updatedAt = 3;
;
    StoreSteps storeSteps;

    @Title("This will create new store")
    @Test

    public void test001()throws IndexOutOfBoundsException {

        Map<String,Object>services= new HashMap<>();
        Map<String,Object>servicesData=new HashMap<String,Object>();
        servicesData.put("id",100);
        servicesData.put("name","ABC");
        servicesData.put("createdAt","2023-02-01");
        servicesData.put("updatedAt","2023-02-01");
        Map<String,Object>storeServices=new HashMap<String,Object>();
        storeServices.put("createdAt","2023-02-01");
        storeServices.put("updatedAt","2023-02-01");
        storeServices.put("stordId",101);
        storeServices.put("serviceId",11);
        servicesData.put("storeservices",storeServices);
        services.put("services",servicesData);
        ValidatableResponse response = storeSteps.createStore(name,address,city,state,zip,type,address2,lat,lng,hours,services);
        response.log().all().statusCode(201);
        id = response.log().all().extract().path("id");

    }
    @Title("Verify if the store was added to the application")
    @Test
    public void test002()throws IndexOutOfBoundsException{
        System.out.println("Find by lat "+lat);
        HashMap<String, Object> studentMap = storeSteps.getProductInfoByName(lat);
        Assert.assertThat(studentMap, hasValue(lat));
        id = (int) studentMap.get("id");
        System.out.println("newly added id :"+id);

    }
    @Title("Update the user information and verify the updated information")
    @Test
    public void test003() {
        name = name+"-updated-data";
        Map<String,Object>services= new HashMap<>();
        Map<String,Object>servicesData=new HashMap<String,Object>();
        servicesData.put("id",100);
        servicesData.put("name","viral");
        servicesData.put("createdAt","2023-02-01");
        servicesData.put("updatedAt","2023-02-01");
        Map<String,Object>storeServices=new HashMap<String,Object>();
        storeServices.put("createdAt","2023-02-01");
        storeServices.put("updatedAt","2023-02-01");
        storeServices.put("stordId",101);
        storeServices.put("serviceId",11);
        servicesData.put("storeservices",storeServices);

        services.put("services",servicesData);
        storeSteps.updateStore(id,name,address,city,state,zip,type,address2,lat,lng,hours,services)
                .log().all().statusCode(200);
        HashMap<String, Object> studentMap = storeSteps.getProductInfoByName(lat);
        Assert.assertThat(studentMap, hasValue(lat));
    }
    @Title("Delete the store and verify if the student is deleted!")
    @Test
    public void test004() {
        storeSteps.deleteProduct(id).statusCode(200);
        storeSteps.getProductId(id).statusCode(404);


    }}
