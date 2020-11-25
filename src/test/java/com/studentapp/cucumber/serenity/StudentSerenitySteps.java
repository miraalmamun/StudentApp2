package com.studentapp.cucumber.serenity;

import java.util.HashMap;
import java.util.List;

import com.studentapp.model.StudentClass;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class StudentSerenitySteps {
	
	
	//This class does not have any relation with ReuseableSpecification.java
	
	@Step("Create student with firstName2:{0}, LastName:{1}, Email:{2},Programme{3} ,Courses:{4}")
	public ValidatableResponse createStudent(String firstName, String lastName, String email, String programme, List<String> courses)
	{
		
		
		StudentClass student = new StudentClass();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmail(email);
		student.setProgramme(programme);
		student.setCourses(courses);
		
	  ValidatableResponse validate =  SerenityRest.rest().given()
		.contentType(ContentType.JSON)
		.when().body(student).post().then();
		
		return validate;
	}
	
	
	@Step("Getting student information with Email:{0}")
	public HashMap<String, Object> getStudentInfoByEmail(String email)
	{
		String p1 = "findAll{it.email=='";
		String p2 = "'}.get(0)";
	
       
		HashMap<String, Object> value = SerenityRest.rest()
				.given()
				.when().get("/list").then()
				.statusCode(200)
				.extract().path(p1+email+p2);
		return value;
	}
	
	@Step("Getting student information with FirstName:{0}")
	public HashMap<String, Object> getStudentInfoByFirstName(String firstName)
	{
		String p1 = "findAll{it.firstName=='";
		String p2 = "'}.get(0)";
	
       
		HashMap<String, Object> value = SerenityRest.rest()
				.given()
				.when().get("/list").then()
				.statusCode(200)
				.extract().path(p1+firstName+p2);
		return value;
	}
	
	@Step("Updating student information with studentId:{0},FirstName:{1},LastName:{2},Email:{3},Programme:{4},Courses:{5}")
	public ValidatableResponse updateStudent(int studetID, String firstName, String lastName, String email, String programme, List<String> courses)
	{
		StudentClass student = new StudentClass();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmail(email);
		student.setProgramme(programme);
		student.setCourses(courses);
		
		ValidatableResponse validate = SerenityRest.rest().given()
		.contentType(ContentType.JSON)
		.when().body(student).log()
		.all().put("/"+studetID).then().log()
				.all();
		
		return validate;
	}
	
	
	@Step("Getting student information with StudentId:{0}")
	public ValidatableResponse getStudentById(int studentId){
		return 
		SerenityRest
		.rest()
		.given()
		.when()
		.get("/" + studentId).then();}
	    
	@Step("Deleting the student with StudentId:{0}")
		public  void deleteStudent(int studentId) {
			SerenityRest.rest().given().when().delete("/" + studentId);
		}
		
	
	
	
	
	
	

}
