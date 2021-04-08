package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*; //If we dont use static here then we need to use 
											//the get method by className i.e. RestAssured.get();
											//To use the methods directly we are using static import of library
											//https://github.com/rest-assured/rest-assured/wiki/Usage
import io.restassured.response.Response;

public class TestExample {
	
	@Test
	public void test_1() {
		
		Response response = get("https://reqres.in/api/users?page=2");
		System.out.println("Response code - " + response.getStatusCode());
		System.out.println("Time taken - " + response.getTime() + "ms");
		System.out.println(response.getBody().asString());
		System.out.println(response.getStatusLine());
		System.out.println(response.getHeader("content-type"));
		
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	public void test_2() {
		
		baseURI = "https://reqres.in/api";
		
		given().
			get("/users?page=2").
		then().
			statusCode(200).
			body("data[1].id", equalTo(8)).	//data[1].id - find json path using JSONPathFinder
			log().all();
			
	}

}
