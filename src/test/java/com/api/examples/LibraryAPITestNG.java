package com.api.examples;
import com.json.payload.Payloads;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class LibraryAPITestNG {
    @Test(dataProvider ="bookTestData")
    public void addBook(String isbn,String aisle){

        RestAssured.baseURI = "http://216.10.245.166";
        String response = given().header("Content-Type","application/json")
                .body(Payloads.addBookJson(isbn,aisle)).
                        when().post("/Library/Addbook.php").
                        then().assertThat().statusCode(200).extract().response().asString();

        String id;
        String msg;
        JsonPath jpath = new JsonPath(response);
        id = jpath.get("ID");
        msg = jpath.getString("Msg");

        System.out.println("id is : "+ id);
        System.out.println("Message  is :"+ msg);


    }

    @DataProvider(name="bookTestData")
    public Object[][] getBookData(){

        return new Object[][]{{"acxx","1511"},{"adxx","1512"},{"aexx","1513"}};

    }

}
