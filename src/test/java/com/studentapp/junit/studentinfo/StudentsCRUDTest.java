

package com.studentapp.junit.studentinfo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import com.studentapp.model.StudentClass;
import com.studentapp.testbase.TestBase;
import com.studentapp.utils.TestUtils;

import io.restassured.http.ContentType;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;


import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentsCRUDTest extends TestBase {
    
    //This class does not any relation StudentSerenitySteps.java
    
    
	StudentClass student;
	static String firstName = TestUtils.getRandomValue() + "Mir";
	static String lastName = "Mamun" + TestUtils.getRandomValue();
	static String email = TestUtils.getRandomValue() + "Mir@gmail.com";
	static String programme = "Computer Science";
	static List<String> courses;
	static int studetID;


	@Title("This test will create a new student")
	@Test
	public void test001() {
		
		
		
		courses = Arrays.asList("Java, Selenium");
		student = new StudentClass();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmail(email);
		student.setProgramme(programme);
		student.setCourses(courses);
		

		SerenityRest.rest().given()
		.contentType(ContentType.JSON)
		.when().body(student).log()
		.all().post().then().log()
				.all().statusCode(201);

	}

	@Title("Varify if the student was added to the application")
	@Test
	public void test002() {
		
		String p1 = "findAll{it.email=='";
		String p2 = "'}.get(0)";
	
       
		HashMap<String, Object> value = SerenityRest.rest()
				.given()
				.when().get("/list").then()
		        .statusCode(200)
				.extract().path(p1+email+p2);
		     
		studetID= (int)value.get("id");
		
		assertThat(value, hasValue(email));

	}
	
	
	

	@Title("Update thie user information and verify the update information")
	@Test
	public void test003() {
		
		String p1 = "findAll{it.firstName=='";
		String p2 = "'}.get(0)";
		
		firstName = firstName+"_Update";
		
		courses = Arrays.asList("Java, Selenium");
		student = new StudentClass();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmail(email);
		student.setProgramme(programme);
		student.setCourses(courses);

		
		SerenityRest.rest().given()
		.contentType(ContentType.JSON)
		.when().body(student).log()
		.all().put("/"+studetID).then().log()
				.all();
       
		HashMap<String, Object> value = SerenityRest.rest()
				.given()
				.when().get("/list").then()
		        .statusCode(200)
				.extract().path(p1+firstName+p2);
		
		assertThat(value, hasValue(firstName));


	}

	
	
	@Title("Delete the student and varify the student is deleted")
	@Test
	public void test004()
	{
		SerenityRest.rest().given()
		.when().delete("/"+studetID);
		
		SerenityRest.rest().given().when()
		.get("/"+studetID).then().log().all().statusCode(404);
	}
	
	
		

}






