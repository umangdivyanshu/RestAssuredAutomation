package tests;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;

import javax.annotation.meta.When;

import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class DataDrivenExample extends DataForTests{
	
	
 	
	//@Test(dataProvider = "DataForPost")
	public void postTest(String firstName, String lastName, int subjectId) {
		JSONObject request = new JSONObject();
		request.put("firstName", firstName);
		request.put("lastName", lastName);
		request.put("subjectId", subjectId);
		
		baseURI = "http://localhost:3000";
		
		given().
			header("Content-Type", "application/json").
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
			body(request.toJSONString()).
		when().
			post("/users").
		then().
			statusCode(201).
			log().all();
	}
	
	//@Test(dataProvider = "DeleteUsers")	//if we do not have lot of data then we can use parametrization
	
	@Parameters({"userId"})
	@Test
	public void deleteTest(int userId) {
		System.out.println("Value for user id is - " + userId);
		baseURI = "http://localhost:3000";
		
		when().
			delete("/users/" + userId).
		then().
			statusCode(200).
			log().all();	
	}
	
	

}

