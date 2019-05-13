package com.expleo.project4;

import com.google.gson.JsonObject;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;

import static io.restassured.RestAssured.given;



public class BlogDA
{

    private static JsonObject jsonObject;
    private static RequestSpecification request;
    private static String url;
    private static Response response;

    //Setting up the HEADER just like in POSTMAN
    public static void setupRESTFullServices(String _url)
    {
        jsonObject = new JsonObject();
        request = given().contentType("application/json");
        url = _url;
    }

    //Using POST to send new comment to the json server
    public static void submitNewComment(String comment)//Write to Json is correct
    {
        jsonObject.addProperty("body",comment);
        jsonObject.addProperty("postId","1");
        request = request.body(jsonObject).when();
        // request.post(url + "/comments");
        response = request.post(url + "/comments");
    }

    //Used to return http return
    public static int getReturnCode(int returnCode)
    {
         request.then().statusCode(returnCode);
         return response.getStatusCode();
    }


}
