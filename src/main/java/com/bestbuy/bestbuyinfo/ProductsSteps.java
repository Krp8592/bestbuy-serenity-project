package com.bestbuy.bestbuyinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.ProjectPojo;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

/**
 * Created By Kashyap patel
 */
public class ProductsSteps {

    @Step("Creating a new Product with name : {0}, type : {1}, upc : {2}, price : {3}, description : {4}, model : {5}")
    public Response createProduct(String name, String type, String upc,
                                  double price, String description, String model){
        ProjectPojo projectPojo = ProjectPojo.getProductPojo(name, type, upc, price, description, model);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .when()
                .body(projectPojo)
                .post(EndPoints.CREATE_PRODUCT);
    }

    @Step("Getting the product information with id : {0}")
    public ValidatableResponse getProductInfo(int productId){
        return SerenityRest.given().log().all()
                .pathParam("id", productId)
                .when()
                .get(EndPoints.GET_SINGLE_PRODUCT_BY_ID)
                .then().log().all();
    }

    @Step("Updating Product with storeId : {0}, name : {1}, type : {2}, upc : {3}, price : {4}, description : {5}, state : {6}")
    public ValidatableResponse updateProduct(int productId, String name, String type, String upc, double price,
                                           String description, String model){
        ProjectPojo projectPojo = ProjectPojo.getProductPojo(name, type, upc, price, description, model);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .when()
                .body(projectPojo)
                .put(EndPoints.UPDATE_PRODUCT_BY_ID + productId)
                .then().log().all();
    }

    @Step("Deleting product information with storeId : {0}")
    public ValidatableResponse deleteProduct(int productId){
        return SerenityRest.given().log().all()
                .when()
                .delete(EndPoints.DELETE_PRODUCT_BY_ID + productId)
                .then().log().all();
    }

    @Step("Getting product information with studentId : {0}")
    public ValidatableResponse getProductById(int productId){
        return SerenityRest.given().log().all()
                .pathParam("id", productId)
                .when()
                .get(EndPoints.GET_SINGLE_PRODUCT_BY_ID)
                .then().log().all();
    }
}
