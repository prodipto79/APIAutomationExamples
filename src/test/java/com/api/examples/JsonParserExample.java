package com.api.examples;

import com.json.payload.Payloads;
import io.restassured.path.json.JsonPath;

public class JsonParserExample {

    public static void main(String[] args) {

        JsonPath jspath = new JsonPath(Payloads.mockJsonResponse());
       // 1. Print No of courses returned by API
        System.out.println(" Number of courses " + jspath.getInt("courses.size()"));
        //2.Print Purchase Amount
        Integer purchaseAmt = jspath.getInt("dashboard.purchaseAmount");
        System.out.println(" Purchase Amount " + purchaseAmt);
        //3. Print Title of the first course
        String titleofFirstCourse = jspath.getString("courses[0].title");
        System.out.println(" Title of First Course  " + titleofFirstCourse);
        //4. Print All course titles and their respective Prices
        int count = jspath.getInt("courses.size()");
        for(int i=0;i<count;i++){

            String title = jspath.getString("courses["+ i +"].title");
            int price = jspath.getInt("courses["+ i +"].price");

            System.out.println(" The title of " + i + "courses " + title);
            System.out.println(" The price of " + i + "courses " + price);

        }

    }
}
