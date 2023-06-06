package com.bestbuy.bestbuytest;

import com.bestbuy.bestbuyinfo.StoreSteps;
import com.bestbuy.testbase.TestBase;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;

/**
 * Created By Kashyap patel
 */

/*@Concurrent(threads = "2x")
@UseTestDataFrom("src/test/java/resources/testdata/storeinfo.csv")
@RunWith(SerenityParameterizedRunner.class)*/
public class StoreDataDrivenTest extends TestBase {

    static String name;
    static String type;
    static String address;
    static String address2;
    static String city;
    static String state;
    static String zip;

    @Steps
    StoreSteps storeSteps;

    @Title("Data driven test for adding multiple stores to the application")
    @Test
    public void createMultipleStudents(){
        Response response = storeSteps.createStore(name, type, address, address2, city, state, zip);
        response.then().log().all().statusCode(201);
    }
}
