package com.studentapp.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.Matchers.*;

import java.util.concurrent.TimeUnit;

public class ReuseableSpecification {

	public static RequestSpecification rspec;
	public static ResponseSpecification respec;

	public static RequestSpecBuilder rspecb;
	public static ResponseSpecBuilder respecb;
	
	
	public static RequestSpecification getGenericRequestSpecification()
	{  
		rspecb = new RequestSpecBuilder();
		rspecb.setContentType(ContentType.JSON);
		rspec = rspecb.build();
		return rspec;
    }
	
	public static ResponseSpecification getGenericResponseSpecification()
	{  
		respecb = new ResponseSpecBuilder();
		respecb.expectHeader("Content-Type", "application/json;charset=UTF-8");
		respecb.expectHeader("Transfer-Encoding", "chunked");
		respecb.expectResponseTime(lessThan(5L),TimeUnit.SECONDS);
		respec = respecb.build();
		return respec;
    }

}
