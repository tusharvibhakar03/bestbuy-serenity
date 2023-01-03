package com.bestbuy.studentinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.ProductPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

/**
 * Created by Jay
 */
public class ProductSteps {
    @Step("Creating student with name : {0}, type: {1}, price {2}, manufacturer: {3} and url {4}")
    public static ValidatableResponse createProduct(String name, String type, String upc, String description, String model,int price, int shipping, String manufacturer){
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setUpc(upc);
        productPojo.setDescription(description);
        productPojo.setModel(model);
       productPojo.setPrice(price);
        productPojo.setShipping(shipping);
        productPojo.setManufacturer(manufacturer);
        // productPojo.setUrl(url);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .when()
                .body(productPojo)
                .post().then();

    }


    @Step("Getting the product information with name: {0}")
    public static HashMap<String, Object> getProductInfoByFirstName(int price) {
        String p1 = "data.findAll{it.price = ";
        String p2 = "}.get(0)";
        return SerenityRest.given().log().all()
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .path(p1 + price + p2);
    }

    @Step("Updating student information with studentId: {0}, firstName: {1}, lastName: {2}, email: {3}, programme: {4} and courses: {5}")
    public  ValidatableResponse updateProduct(int productId, String name, String type, String upc, String description, String model, int price, int shipping, String manufacturer){
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setUpc(upc);
        productPojo.setDescription(description);
        productPojo.setModel(model);
        productPojo.setPrice(price);
        productPojo.setShipping(shipping);
        return SerenityRest.given().log().all()
                .header("Accept", "application/json")
                .pathParam("id", productId)
                .body(productPojo)
                .when()
                .patch(EndPoints.UPDATE_PRODUCT_BY_PRODUCT_ID)
                .then();
    }
    @Step("Deleting product information with price: {0}")
    public  ValidatableResponse deleteproduct(int productId) {
        return SerenityRest.given().log().all()
                .pathParam("id", productId)
                .when()
                .delete(EndPoints.DELETE_PRODUCT_BY_PRODUCT_ID)
                .then();
    }

    @Step("Getting product information with price: {0}")
    public  ValidatableResponse getproductbyPrice(int productId){
        return SerenityRest.given().log().all()
                .pathParam("id", productId)
                .when()
                .get(EndPoints.GET_SINGLE_PRODUCT_BY_PRODUCT_ID)
                .then();
    }

}
