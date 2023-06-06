package com.bestbuy.bestbuytest;

import com.bestbuy.bestbuyinfo.StoreSteps;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;

/**
 * Created By Kashyap patel
 */

//@RunWith(SerenityRunner.class)
public class StoreCrudTest extends TestBase {

    static int storeid;
    static String name = "Prime" + TestUtils.getRandomValue();
    static String UpdatedName = "UpdatedName" + TestUtils.getRandomValue();
    static String type = "Testing";
    static String address = "Xyz";

    static String address2 = "Abc";
    static String city = "Anand";

    static String state = "Gujarat";
    static String zip = "388320";

    @Steps
    StoreSteps storeSteps;

    @Title("This will create a new store.")
    @Test
    public void test001() {

        Response response = storeSteps.createStore(name, type, address, address2, city, state, zip);
        response.then().log().all().statusCode(201);
        String responseBody = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);
        storeid = jsonPath.getInt("id");
        System.out.println(storeid);

    }

    @Title("Getting the store information with ID")
    @Test
    public void test002(){
        storeSteps.getStoreInfo(storeid).statusCode(200);
    }

    @Title("Update the store information")
    @Test
    public void test003() {
        name = name + "_updated";
        storeSteps.updateStore(storeid, name, type, address, address2, city, state, zip).statusCode(200);
    }


    @Title("Delete the store and verify if the store is deleted.!")
    @Test
    public void test004() {

        storeSteps.deleteStore(storeid).statusCode(200);

        storeSteps.getStoreById(storeid).statusCode(404);

    }


}
