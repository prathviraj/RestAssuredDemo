package Demos;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class EcommerceRestTesting {
	
	public static String baseURI = "https://ecommerceservice.herokuapp.com";
	public String TOKEN;
	public String id;	
	// signup is POST Method with Request Body
	@Test(enabled = false)
	public void signup()
	{
		System.out.println("\n E-Commerce signup Test Case is Executing");
		RestAssured.baseURI = baseURI;
		
		String requestbody = "{\r\n" + 
				"	\"email\": \"prathvi49107@gmail.com\",\r\n" + 
				"	\"password\": \"ashwini@91127\"\r\n" + 
				"}\r\n" + 
				"";
		Response response =given()
		.header("content-Type", "application/json")
		.body(requestbody)
		
		.when()
		.post("/user/signup")
		
		.then()
		.assertThat()
		//.statusCode(201)
		.and()
		.contentType(ContentType.JSON)
		.extract().response();
		System.out.println(response.asString());
	}
	
	@Test(priority=0)
	public void Login()
	{
		System.out.println("\n E-Commerce Login Test Case is Executing");
		RestAssured.baseURI = baseURI;
		
		String requestbody = "{\r\n" + 
				"	\"email\": \"prathvi49107@gmail.com\",\r\n" + 
				"	\"password\": \"ashwini@91127\"\r\n" + 
				"}\r\n" + 
				"";
		Response response =given()
		.header("content-Type", "application/json")
		.body(requestbody)
		
		.when()
		.post("/user/login")
		
		.then()
		.assertThat()
		.statusCode(200)
		.and()
		.contentType(ContentType.JSON)
		.extract().response();
		System.out.println(response.asString());
		
		//Need to convert response data from string to JSON format
		String jsonresponsebody = response.asString();
		
		//want to convert this into Json
		JsonPath responsebody = new JsonPath(jsonresponsebody);		
		TOKEN = responsebody.get("accessToken");
		System.out.println(TOKEN);
		
	}

	@Test(priority=1)
	public void getalluser()
	{
		System.out.println("\n E-Commerce GetAllUsers Test Case is Executing");
		RestAssured.baseURI = baseURI;
				
		Response response =given()
		.header("content-Type", "application/json")
		.header("Authorization", "bearer " + TOKEN )
		
		.when()
		.get("/user")
		
		.then()
		.assertThat()
		.statusCode(200)
		.and()
		.contentType(ContentType.JSON)
		
		.extract().response();
		System.out.println(response.asString());
		
		//Need to convert response data from string to JSON format
		String jsonresponsebody = response.asString();
		
		//want to convert this into Json
		JsonPath responsebody = new JsonPath(jsonresponsebody);		
		id =  responsebody.get("users[2]._id");
		System.out.println(id);			
	}
	
	@Test(priority=2)
	public void deleteuser()
	{
		System.out.println("\n E-Commerce DeleteUser Test Case is Executing");
		RestAssured.baseURI = baseURI;
				
		Response response =given()
		.header("content-Type", "application/json")
		.header("Authorization", "bearer " + TOKEN )
		
		.when()
		.delete("/user/" + id)
		
		.then()
		.assertThat()
		.statusCode(200)
		.and()
		.contentType(ContentType.JSON)
		
		.extract().response();
		System.out.println(response.asString());
		
		//Need to convert response data from string to JSON format
		String jsonresponsebody = response.asString();
		
		//want to convert this into Json
		JsonPath responsebody = new JsonPath(jsonresponsebody);		
		String message = responsebody.get("message");
		System.out.println(message);
	}
	
}