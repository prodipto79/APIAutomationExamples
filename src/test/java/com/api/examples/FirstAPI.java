package com.api.examples;

import com.json.payload.Payloads;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class FirstAPI {

    // given for all input details
    // when for http method for submit the request
    // then to validate the response

    void addPlaceAPI() {
        RestAssured.baseURI = "https://rahulshettyacademy.com/";
        String response = given().log().all().queryParam("key","qaclick123")
                .header("Content-Type","application/json")

                .body(Payloads.addPlaceJson())
                .when().post("maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200).body("scope",equalTo("APP"))
                .header("server","Apache/2.4.18 (Ubuntu)").extract().response().asString();
        System.out.println("the response is" + response );
        JsonPath jsonpath = new JsonPath(response);  // for parsing the json body/payload
        String OriginalpalaceId = jsonpath.getString("place_id");
        System.out.println("the place id is" + OriginalpalaceId );

        // update the address using original place id

        String updateResponse = given().log().all().queryParam("key","qaclick123")
                .header("Content-Type","application/json")
                .body("{\n" +
                        "\"place_id\":\""+ OriginalpalaceId + "\",\n" +
                        "\"address\":\"70 Summer walk, USA\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}")
                .when().put("maps/api/place/update/json")
                .then().log().all().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated"))
                .extract().response().asString();
                jsonpath = null;
                jsonpath = new JsonPath(updateResponse);
                String msg = jsonpath.getString("msg");
                System.out.println("the successful msg is " + msg );

                // now getPlace API call to get the updated Place Id

        String getResponse = given().log().all().queryParam("key","qaclick123")
                .queryParam("place_id",OriginalpalaceId)
                .when().get("maps/api/place/get/json")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();

        jsonpath = null;
        jsonpath = new JsonPath(getResponse);
        System.out.println(jsonpath.getString("address"));
        System.out.println("the response of the get API method "+ getResponse);

        Assert.assertEquals(jsonpath.getString("address"),"70 Summer walk, USA","Address not matched" );

    }

    public static void main(String[] args) {

        FirstAPI firstapi = new FirstAPI();
        firstapi.addPlaceAPI();
    }
}
