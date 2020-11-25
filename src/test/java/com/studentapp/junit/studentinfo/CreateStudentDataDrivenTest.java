package com.studentapp.junit.studentinfo;

import java.util.ArrayList;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.studentapp.cucumber.serenity.StudentSerenitySteps2;
import com.studentapp.testbase.TestBase;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.UseTestDataFrom;

@UseTestDataFrom("testdata/studentinfo.csv")
@RunWith(SerenityParameterizedRunner.class)
public class CreateStudentDataDrivenTest extends TestBase {
	
	//Data-driven testing using CSV files
	//https://www.mockaroo.com/--->Create mock data
	
	@Steps 
	StudentSerenitySteps2 steps;
	
	private String firstName;
	private String lastName;
	private String email;
	private String programme;
	private String courses1;
	

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


    public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProgramme() {
		return programme;
	}

	public void setProgramme(String programme) {
		this.programme = programme;
	}

	public String getCourses1() {
		return courses1;
	}


	public void setCourses1(String courses1) {
		this.courses1 = courses1;
	}



	
	@Test
	@Title("Datan Driven test for adding multiple students to the Student App")
	public void createMultipleStudents()
	{    
		System.out.println(firstName+" "+lastName+email+" "+programme+" "+courses1);
		ArrayList<String> courses2 = new ArrayList<String>();
		courses2.add(courses1);
		steps.createStudent(firstName, lastName, email, programme, courses2).statusCode(201);
		
	}

}
