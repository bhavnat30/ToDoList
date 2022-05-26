package com.task.TodoList;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.TodoList.repository.MyToDoRepository;

public class ToDoListControllerTest {
	protected MockMvc mvc;
	@Autowired
	protected MyToDoRepository myToDoRepository;
	
	@Test
	public void testAdd() throws JsonProcessingException, Exception {
		Task task = new Task();
		task.setTaskDescription("first task");
		ObjectMapper objectMapper = new ObjectMapper();
		
		
		
	    MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/addTask").content(objectMapper.writeValueAsString(task))
			.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andReturn();
	
	assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
		
	}
	
	@Test
	public void testDelete() {
		
	}

}
