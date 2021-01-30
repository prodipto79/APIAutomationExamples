package com.api.examples;

import com.json.payload.Payloads;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
//import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class LibraryAPIExample {

    //@Test
    public void addBook(){

        RestAssured.baseURI = "http://216.10.245.166";
        String response = given().header("Content-Type","application/json")
                .body(Payloads.addBookJson("abcd","1231")).
        when().post("/Library/Addbook.php").
        then().assertThat().statusCode(200).extract().response().asString();

        String id;
        String msg;
        JsonPath jpath = new JsonPath(response);
        id = jpath.get("ID");
        msg = jpath.getString("Msg");

        System.out.println("id is :"+ id);
        System.out.println("Message  is :"+ msg);


    }

    public static void main(String[] args) {
        LibraryAPIExample l = new LibraryAPIExample();
        l.addBook();
    }
}
