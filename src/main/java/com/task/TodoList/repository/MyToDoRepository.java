package com.task.TodoList.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.TodoList.ToDoList;

public interface MyToDoRepository extends JpaRepository<ToDoList, Integer> {

}
