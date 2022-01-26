package com.digitalhonorsproject.pensionmanagement.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.digitalhonorsproject.pensionmanagement.model.PMModel;

@Repository
public interface PMRepository extends CrudRepository<PMModel, Long>  // Crud to interact with DB & concept of Java Inheritence to inherit attributes and methods from one class to another (Superclass or Parent class--->CrudRepository & Subclass or Child class----->PMRepository)  
{
 PMModel findByAadharNumber(long aadharNumber);
}


// PMModel is type generic means we can pass any class name
// primary key in model class is Long