package com.bestbuy.bestbuyinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.StorePojo;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

/**
 * Created By Kashyap patel
 */
public class StoreSteps {

    @Step("Creating Store with name : {0}, type : {1}, address : {2}, address2 : {3}, city : {4}, state : {5}, zip : {6}")
    public Response createStore(String name, String type, String address, String address2,
                                String city, String state, String zip){
        StorePojo storePojo = StorePojo.getStorePojo(name, type, address, address2, city, state, zip);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .when()
                .body(storePojo)
                .post(EndPoints.CREATE_STORE);
    }

    @Step("Getting the store information with id : {0}")
    public ValidatableResponse getStoreInfo(int storeId){
        return SerenityRest.given().log().all()
                .pathParam("id", storeId)
                .when()
                .get(EndPoints.GET_SINGLE_STORE_BY_ID)
                .then().log().all();
    }

    @Step("Updating Store with name : {0}, type : {1}, address : {2}, address2 : {3}, city : {4}, state : {5}, zip : {6}")
    public ValidatableResponse updateStore(int storeId, String name, String type, String address, String address2,
                                String city, String state, String zip){
        StorePojo storePojo = StorePojo.getStorePojo(name, type, address, address2, city, state, zip);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .when()
                .body(storePojo)
                .put(EndPoints.UPDATE_STORE_BY_ID + storeId)
                .then().log().all();
    }

    @Step("Deleting student information with storeId : {0}")
    public ValidatableResponse deleteStore(int storeId){
        return SerenityRest.given().log().all()
                .when()
                .delete(EndPoints.DELETE_STORE_BY_ID + storeId)
                .then().log().all();
    }

    @Step("Getting student information with storeId : {0}")
    public ValidatableResponse getStoreById(int storeId){
        return SerenityRest.given().log().all()
                .pathParam("id", storeId)
                .when()
                .get(EndPoints.GET_SINGLE_STORE_BY_ID)
                .then().log().all();
    }
}
