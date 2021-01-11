package com.DemoPet.Scenarios;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.testng.annotations.BeforeClass;
import com.DemoPet.GetterSetters.PostsRequests;
import com.DemoPet.appconfig.Config;
import com.DemoPet.liabraries.Genericfunctions;
import com.DemoPet.liabraries.Log;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.*;

public class DemoPetsScenarioTesting {
String responseID;

@BeforeClass
public void init() {
	Genericfunctions.setup();
}

@Test(priority=1)
	public void getAvailablePets() {
		System.out.println(Config.vUrl+"/findByStatus");

		Response resp = given().
				param("status", "available").
				when().
				get(Config.vUrl+"/findByStatus");

		
		Log.logInfo(DemoPetsScenarioTesting.class, "Checks the Response Code for getAvailablePets method" + resp.getStatusCode());
		Log.logInfo(DemoPetsScenarioTesting.class, "Checks the Response String for getAvailablePets method" + resp.toString());
		
		Assert.assertEquals(resp.getStatusCode(), 200);
	}
	
	@Test(priority=2)
//	@Test
	public void postNewAvailablePet() throws Exception {
		Response resp = given().
						body(Genericfunctions.parsingjsonToString()).
						when().
						contentType(ContentType.JSON).
						post(Config.vUrl);
				Log.logInfo(DemoPetsScenarioTesting.class, "Checks the Response Code  for postNewAvailablePet method " + resp.getStatusCode());
				Log.logInfo(DemoPetsScenarioTesting.class, "Checks the Response Code for postNewAvailablePet method" + resp.toString());
				
				responseID = getpath(resp,"id");
				Assert.assertTrue(resp.asString().contains(responseID));
				Assert.assertEquals(resp.getStatusCode(), 200);
				
						
	}
	
	@Test(priority=3)
			public void updatePetStatus() {
					PostsRequests pr = new PostsRequests();
					pr.setStatus("sold");
					pr.setId(responseID);
					
				Response resp = given().
								body(pr).
								when().
								contentType(ContentType.JSON).
								put(Config.vUrl);
				Log.logInfo(DemoPetsScenarioTesting.class, "Checks the Response Code  for updatePetStatus method " + resp.getStatusCode());
				Log.logInfo(DemoPetsScenarioTesting.class, "Checks the Response Code for updatePetStatus method" + resp.toString());
				String updatedStatus = getpath(resp,"status");
				Assert.assertTrue(resp.asString().contains(updatedStatus));
				Assert.assertEquals(resp.getStatusCode(), 200);
				
			}
	
		
		
	@Test(priority=4)
	public void deletePetID() {
		Response resp= given().
						when().
						delete(Config.vUrl+"/"+responseID);
		Log.logInfo(DemoPetsScenarioTesting.class, "Checks the Response Code  for deletePetID method " + resp.getStatusCode());
		Log.logInfo(DemoPetsScenarioTesting.class, "Checks the Response Code for deletePetID method" + resp.toString());
		
		System.out.println(resp.asString());
		
		Assert.assertTrue(resp.asString().contains(responseID));
		Assert.assertEquals(resp.getStatusCode(), 200);
	}
	
	/*
	 * @param response,pathId
	 * @author Gundeep 
	 */
	
	public String getpath(Response response, String pathId) {
		String xpathValue = response.then().contentType(ContentType.JSON).extract().path(pathId).toString();
		return xpathValue;

	}
	

	
}
