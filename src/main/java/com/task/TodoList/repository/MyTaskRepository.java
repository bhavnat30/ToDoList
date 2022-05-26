package com.task.TodoList.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task.TodoList.Task;

@Repository
public  interface MyTaskRepository extends JpaRepository<Task, Integer>{

}
