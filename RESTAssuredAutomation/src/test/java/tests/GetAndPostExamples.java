package tests;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class GetAndPostExamples {

	//@Test
	public void testGet() {
		baseURI = "https://reqres.in/api";

		given().
		get("/users?page=2").
		then().
		statusCode(200).
		body("data[4].first_name", equalTo("George")).
		body("data.first_name", hasItems("George", "Rachel"));
	}

	@Test
	public void testPost() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "Umang");
		map.put("job", "Engineer");

		//JSONObject request = new JSONObject(map);	//To convert the map into JSON		
		//If we are using above JSON library then we can directly use it and map is not required

		JSONObject request = new JSONObject();
		request.put("name", "Umang");
		request.put("job", "Engineer");
		System.out.println(request.toJSONString());


		baseURI = "https://reqres.in/api";;

		given().
			header("Content-Type", "application/json").
			contentType(ContentType.JSON).	//Here we are telling that the content we are sending is of type JSON
			accept(ContentType.JSON).		// and the response we are expecting is also of type JSON
			body(request.toJSONString()).
		when().
			post("/users").
		then().
			statusCode(201).
			log().all();


	}

}
