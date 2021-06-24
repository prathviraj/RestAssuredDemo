package Demos;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;

import static org.hamcrest.CoreMatchers.equalTo;

public class restAssuredDemo {
	
	public static String baseURI = "https://api.trello.com";
	
	@Test(enabled = false)
	public void testcase1()
	{
		//base uri gets loaded
		RestAssured.baseURI = baseURI;
		//given, when and Then
		given()
		.param("key", "a67f1dfbf58365629a34c88adeb960cf")
		.param("token", "18604d58d75b56bb7130323f8885005fac96354107e0578c9f2054be2589f521")
		.when()
		.get("/1/boards/60cacc98f1adc85fbe803003")
		.then()
		.assertThat()
		.statusCode(200)
		.contentType(ContentType.JSON)
		.and()
		.body("name", equalTo("Prathvi Board"));
		System.out.println("Executed Successfully");
	}
	
	@Test(enabled = false)
	public void testcase2()
	{
		//base uri gets loaded
		RestAssured.baseURI = baseURI;
		//given, when and Then
		given()
		.param("key", "a67f1dfbf58365629a34c88adeb960cf")
		.param("token", "18604d58d75b56bb7130323f8885005fac96354107e0578c9f2054be2589f521")
		.when()
		.get("/1/boards/60cacc98f1adc85fbe803003")
		.then()
		.assertThat()
		.statusCode(200)
		.contentType(ContentType.JSON)
		.and()
		.header("content-Type", "application/json; charset=utf-8");		
		System.out.println("Executed Successfully");
		
	}
	
	//POST Method without body // Create Board -> key, token, name query parameters need to pass
	@Test
	public void testcase3()
	{
		String BoardName = "Creation of Board from Eclipse" + (int) (Math.random()*100);
		RestAssured.baseURI = baseURI;
		given()
		.queryParam("key", "a67f1dfbf58365629a34c88adeb960cf")
		.queryParam("token", "18604d58d75b56bb7130323f8885005fac96354107e0578c9f2054be2589f521")
		.queryParam("name", BoardName)
		.header("content-Type", "application/JSON")
		.when()
		.post("/1/boards/")
		.then()
		.assertThat()
		.statusCode(200);
		
	}
	

}
