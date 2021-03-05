package com.api.jira;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class SampleJiraAPI {

    @Test
    public void createIssue(){

        RestAssured.baseURI="https://prodipto.atlassian.net";
       Response response =  given().header("Content-Type","application/json")
                .auth().preemptive().basic("prodiptodutta4@gmail.com","BDKVSv0ACgm4boYvAqfC9106")
               //.auth().oauth2("BDKVSv0ACgm4boYvAqfC9106")- not working through access token
                .body("{\n" +
                        "    \"fields\": {\n" +
                        "       \"project\":\n" +
                        "       {\n" +
                        "          \"key\": \"TES\"\n" +
                        "       },\n" +
                        "       \"summary\": \"Test Issue through Rest API - Defect 5\",\n" +
                        "       \"description\": \"Creating of an issue using project keys and Rest API-Defect 5 \",\n" +
                        "       \"issuetype\": {\n" +
                        "          \"name\": \"Bug\"\n" +
                        "       }\n" +
                        "   }\n" +
                        "}")
                .when().post("/rest/api/2/issue/")
                .then().assertThat().statusCode(201).extract().response();




    }
}
