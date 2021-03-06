package com.example.demo;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.demo.entity.Work;
import com.google.gson.Gson;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;


@SpringBootTest
class DemoApplicationTests {
	@Autowired
	WebApplicationContext webApplicationContext;

	@Test
	public void whenResourcesAvaiable(){
	    Response response = RestAssured.get("http://localhost:8080/get" + "?pageNo=0&pageSize=2");
	    assertEquals(200, response.getStatusCode());
	}
	
	@Test
	public void whenResourcesNull(){
	    Response response = RestAssured.get("http://localhost:8080/get" + "?pageNo=3&pageSize=4");
	    assertTrue(response.body().as(List.class).isEmpty());
	}
	
	@Test
	public void testInsert() throws Exception{
		MockMvc mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		Work work = new Work();
		work.setWorkName("PM");
		work.setStartingDate("2021-07-14 00:00:00");
		work.setEndingDate("2021-07-14 00:00:00");
		work.setStatus("ok");
		Gson gson = new Gson();
		String json = gson.toJson(work);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/create")
			      .contentType(MediaType.APPLICATION_JSON).content(json)).andReturn(); 
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	@Test
	public void testUpdate() throws Exception{
		MockMvc mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		Work work = new Work();
		work.setWorkName("PM");
		work.setStartingDate("2021-07-14 00:00:00");
		work.setEndingDate("2021-07-14 00:00:00");
		work.setStatus("ok");
		Gson gson = new Gson();
		String json = gson.toJson(work);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put("http://localhost:8080//update/1")
			      .contentType(MediaType.APPLICATION_JSON).content(json)).andReturn(); 
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	@Test
	public void testDelete() throws Exception{
		Response response = RestAssured.delete("http://localhost:8080/delete/5");
		assertEquals(200, response.getStatusCode());
	}

}
