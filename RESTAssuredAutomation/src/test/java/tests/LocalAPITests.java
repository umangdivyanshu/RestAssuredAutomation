package tests;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class LocalAPITests {
	
	//@Test(priority=2)
	public void testGet() {
		baseURI = "http://localhost:3000";
		
		given().
			get("/users").
		then().
			statusCode(200).
			log().all();
	}
	
	//@Test(priority=1)
	public void testPost() {
		
		JSONObject request = new JSONObject();
		request.put("firstName", "Arthur");
		request.put("lastName", "Curry");
		request.put("subjectId", "2");
		
		
		baseURI = "http://localhost:3000";
		
		given().
			header("Content-Type","application/json").
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
			body(request.toJSONString()).
		when().
			post("/users").
		then().
			statusCode(201).
			log().all();
	}
	
	//@Test
	public void testPut() {
		JSONObject request = new JSONObject();
		request.put("firstName", "Barry");
		request.put("lastName", "Allen");
		request.put("subjectId", "2");
		
		RestAssured.baseURI = "http://localhost:3000";
		RestAssured.basePath = "/users/4";		//using in a different way here
		
		given().
			header("Content-Type", "application/json").
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
			body(request.toJSONString()).
		when().
			put().
		then().
			statusCode(200).log().all();		
	}
	
	//@Test
	public void testPatch() {
		JSONObject request = new JSONObject();
		request.put("firstName", RestUtils.getFirstName());
		request.put("lastName", RestUtils.getLastName());
		
		baseURI = "http://localhost:3000";
		
		Response res = given().
			header("Content-Type", "application/json").
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
			body(request.toJSONString()).
		when().
			patch("/users/4").
		then().
			statusCode(200).
			log().all().
			extract().response();
		
// we can use below mentioned code to assert if the response is correct or not
//		String jsonString = res.asString();
//		Assert.assertEquals(jsonString.contains("Record added successfully"), true);
		
	}
	
	@Test
	public void testDelete() {
		baseURI = "http://localhost:3000";
		
		when().
			delete("/users/4").
		then().
			statusCode(200).		//sometimes we get 200 for deletion, usually we get 204
			log().all().
			extract().response();
	}
}
