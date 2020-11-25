package com.studentapp.junit.studentinfo;

import static org.hamcrest.Matchers.hasValue;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import com.studentapp.cucumber.serenity.StudentSerenitySteps;
import com.studentapp.testbase.TestBase;
import com.studentapp.utils.ReuseableSpecification;
import com.studentapp.utils.TestUtils;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentsCRUDTest3 extends TestBase {
	
	//This is the final modifier test
	//

	@Steps
	StudentSerenitySteps steps;
	static String firstName = TestUtils.getRandomValue() + "Mir";
	static String lastName = "Mamun" + TestUtils.getRandomValue();
	static String email = TestUtils.getRandomValue() + "Mir@gmail.com";
	static String programme = "Computer Science";
	
	static int studetID;


	@Title("This test will create a new student")
	@Test
	public void test001() {
		List<String> courses = Arrays.asList("Java, Selenium");
		steps.createStudent(firstName, lastName, email, programme, courses)
		.statusCode(201).spec(ReuseableSpecification.getGenericResponseSpecification()); //Add new line 
	}
	
	

	@Title("Varify if the student was added to the application")
	@Test
	public void test002() {
		
		
        HashMap<String,Object> value = steps.getStudentInfoByEmail(email);
		     
		studetID= (int)value.get("id");
		
		assertThat(value, hasValue(email));

	}
	
	
	
	
	@Title("Update thie user information and verify the update information")
	@Test
	public void test003() {
		
		List<String> courses = Arrays.asList("SQL, Pearl");
		
		firstName = firstName+"_Update";
		
		steps.createStudent(firstName, lastName, email, programme, courses);

		steps.updateStudent(studetID, firstName, lastName, email, programme, courses);
		
        HashMap<String, Object> value = steps.getStudentInfoByFirstName(firstName);
       
		
		assertThat(value, hasValue(firstName));


	}

	
	
	@Title("Delete the student and varify the student is deleted")
	@Test
	public void test004()
	{
		steps.deleteStudent(studetID);
		
		steps.getStudentById(studetID).statusCode(404);
	}
	
	
	
	
}











