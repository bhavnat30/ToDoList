package com.task.TodoList;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.TodoList.repository.MyTaskRepository;
import com.task.TodoList.repository.MyToDoRepository;

@RestController
public class ToDoListController {
	
	@Autowired
	MyTaskRepository myTaskRepository;
	@Autowired
	MyToDoRepository doRepository;
	
	@RequestMapping(value = "/tasks/{id}")
	public ResponseEntity<ToDoList> getAllTasks(@PathVariable int id) {
		Optional<ToDoList> list =  doRepository.findById(id);
		if(list.isPresent()) {
			return new ResponseEntity<ToDoList>(list.get(), HttpStatus.OK);
		}
		ToDoList doto = new ToDoList();
		Task task = new Task(); task.setTaskDescription("first");
		doto.setTasks(Arrays.asList(task));
		doto.setUserId(111);
		return new ResponseEntity<ToDoList>(doto, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/addTask")
	public ResponseEntity<ToDoList> addTask(@RequestBody ToDoList toDoList) {
		Optional<ToDoList> list =  doRepository.findById(toDoList.getUserId());
		if(!list.isPresent()) {
			myTaskRepository.saveAll(toDoList.getTasks());
			doRepository.save(toDoList);
			return new ResponseEntity<ToDoList>(toDoList, HttpStatus.OK);
		}
		List<Task> existingTasks = list.get().getTasks();
		toDoList.getTasks().addAll(existingTasks);
		myTaskRepository.saveAll(toDoList.getTasks());
		doRepository.save(toDoList);
		return new ResponseEntity<ToDoList>(toDoList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/deleteTask")
	public void deleteTask(@PathVariable int id ) {
		Optional<ToDoList> list =  doRepository.findById(id);
		if(list.isPresent()) {
			doRepository.delete(list.get());
		}
	}
	
	@RequestMapping(value = "/allUserTasks")
	public List<ToDoList> getAllUsersTask() {
		List<ToDoList> list = doRepository.findAll();
		return list;
	}
}
