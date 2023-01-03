package com.bestbuy.studentinfo;
import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.ProductPojo;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.lang.Math.log;
import static org.hamcrest.Matchers.hasValue;

/**
 * Created by Jay
 */
@RunWith(SerenityRunner.class)
public class ProductCURDTest extends TestBase {
    static String name = "Duracel11";
    static String type = "max";
    static String upc = "ABC";
    static String model = "AAA";
    static int price = 10;
    static int shipping = 20;
    static String description = "asdf";
    static String manufacturer = "asdfsd";

    static int productId= 456;
    @Steps
    ProductSteps productSteps;

    @Title("This will create new product")
    @Test
    public void test001() {
        ValidatableResponse response = productSteps.createProduct(name, type, upc, description, model,price, shipping, manufacturer);
        response.log().all().statusCode(201);
    }

    @Title("Verify if the product  was added to the application")
    @Test
    public void test002() {
        HashMap<String, Object> studentMap = ProductSteps.getProductInfoByFirstName(price);
        Assert.assertThat(studentMap, hasValue(price));
        productId = (int) studentMap.get("id");
    }

   @Title("Update the user information and verify the updated information")
    @Test
    public void test003() {
       productSteps.updateProduct(productId,name,type,upc,description,model,price,shipping,manufacturer)
               .log().all().statusCode(200);
       HashMap<String, Object> studentMap = productSteps.getProductInfoByFirstName(price);
       Assert.assertThat(studentMap, hasValue(price));
   }
    @Title("Delete the product and verify if the product is deleted!")
    @Test
    public void test004() {
        productSteps.deleteproduct(productId).statusCode(200);
        productSteps.getproductbyPrice(productId).statusCode(404);



    }






    }



