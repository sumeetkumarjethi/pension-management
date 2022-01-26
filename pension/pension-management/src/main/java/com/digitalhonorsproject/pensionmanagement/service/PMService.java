package com.digitalhonorsproject.pensionmanagement.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalhonorsproject.pensionmanagement.model.PMModel;
import com.digitalhonorsproject.pensionmanagement.repository.PMRepository;

@Service
public class PMService{
	
	@Autowired   //  inject the value of the class PMRepository
	private PMRepository pensionrepo;  
	
	public void pmService(PMModel pension)
	{
		pensionrepo.save(pension);
	}
public PMModel findByAadharNumber(long aadharNumber) {
		
		PMModel model = pensionrepo.findByAadharNumber(aadharNumber);
		return model;
	}
	

public Double findByProcessPension(long aadharNumber) {
	
	PMModel model = pensionrepo.findByAadharNumber(aadharNumber);
	Double pensionAmount=null;
	if (model != null)
	{
		if(model.getPensionType().equalsIgnoreCase("Self Pension"))
		{
			pensionAmount = (0.8*model.getSalaryEarned()) + model.getAllowances();
		}
		else
		{
			pensionAmount =  (0.5*model.getSalaryEarned()) + model.getAllowances();
		}
	}
	return pensionAmount;
	}
	
}

