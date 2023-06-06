package com.bestbuy.bestbuytest;

import com.bestbuy.bestbuyinfo.ProductsSteps;
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
public class ProductCrudTest extends TestBase {

    static String name = "New Product" + TestUtils.getRandomValue();
    static String updateName = "New Product" + TestUtils.getRandomValue();
    static String type = "Hard Good" + TestUtils.getRandomValue();
    static String upc = "12" + TestUtils.getRandomValue();
    static double price = 99.99;
    static String description = "This is a placeholder request for creating a new product.";
    static String model = "NP" + TestUtils.getRandomValue();
    static int productId;

    @Steps
    ProductsSteps productsSteps;

    @Title("This will create a new product.")
    @Test
    public void test001(){
        Response response = productsSteps.createProduct(name, type, upc, price, description, model);
        response.then().log().all().statusCode(201);
        String responseBody = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);
        productId = jsonPath.getInt("id");
        System.out.println(productId);
    }

    @Title("Getting the product information with ID")
    @Test
    public void test002(){
        productsSteps.getProductInfo(productId).statusCode(200);
    }

    @Title("Update the product information")
    @Test
    public void test003() {
        name = name + "_updated";
        productsSteps.updateProduct(productId, name, type, upc, price, description, model).statusCode(200);
    }

    @Title("Delete the product and verify if the product is deleted.!")
    @Test
    public void test004() {
        productsSteps.deleteProduct(productId).statusCode(200);
        productsSteps.getProductById(productId).statusCode(404);
    }
}
