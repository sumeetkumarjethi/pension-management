package com.digitalhonorsproject.pensionmanagement.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.digitalhonorsproject.pensionmanagement.model.PMModel;
import com.digitalhonorsproject.pensionmanagement.service.JwtUtil;
import com.digitalhonorsproject.pensionmanagement.service.PMService;

@RestController
@CrossOrigin
public class PMController{

	@Autowired
	private PMService pmservice;
	
	@Autowired
	private JwtUtil jwtUtil;

	@GetMapping("/thepension/{aadhar_number}")
	public ResponseEntity<PMModel> getPensionByAadharNumber(@RequestHeader("Authorization") String token,
			@PathVariable(value = "aadhar_number") long aadhar_number) {
		if (token != null && jwtUtil.isTokenExpired(token.substring("Bearer ".length())))
			return new ResponseEntity<PMModel>(pmservice.findByAadharNumber(aadhar_number), HttpStatus.OK); //pmservice.findByAadharNumber(aadhar_number);
		else 
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			
			
	}

	
	@PostMapping("/ProcessPension/{aadhar_number}")
	public ResponseEntity<Map<String, String>> getProcessPension(@PathVariable(value = "aadhar_number") long aadhar_number) {
		Double amount = pmservice.findByProcessPension(aadhar_number);
		Map<String,String> map = new HashMap<String, String>();
		map.put("pension_amount",amount.toString());
		return ResponseEntity.ok(map);
	}
}
