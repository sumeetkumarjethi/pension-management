package com.digitalhonorsproject.pensionmanagement;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.digitalhonorsproject.pensionmanagement.model.PMModel;
import com.digitalhonorsproject.pensionmanagement.repository.PMRepository;
import com.digitalhonorsproject.pensionmanagement.service.PMService;

@SpringBootTest
class PensionManagementApplicationTests {


	
	@Autowired
	private PMService pmservice;
	
	@MockBean  // @MockBean creates a Mock for the Repository, which can be used to call the actual Repository
	private PMRepository pensionrepo;   

	
	@Test
	void  findByAadharNumber()
	{
		PMModel obj= new PMModel();
		
	obj.setAadharNumber(9060421389253850L);
	obj.setBankName("General Financial Inc");
	
	Mockito.when(pensionrepo.findByAadharNumber(9060421389253850L)).thenReturn(obj);
	
	PMModel obj2=pmservice.findByAadharNumber(9060421389253850L);
	
	assertThat(obj.getBankName()).isEqualTo(obj2.getBankName());
	
	}
	
	@Test
	void findByProcessPension()
	{
		PMModel pmModel = new PMModel();
		pmModel.setAadharNumber(6849072063696130L);
		pmModel.setAllowances(555.0);
		pmModel.setBankAccountNumber(998877665585L);
		pmModel.setBankName("Better Life Holdings Inc");
		pmModel.setDate_of_birth("21-08-2021");
		pmModel.setFamilyPension(0.0);
		pmModel.setName("Jacqui Vanover");
		pmModel.setPan("YSNCH0058E");
		pmModel.setPensionType( "Family Pension");
		pmModel.setSalaryEarned(50044.0);
		pmModel.setSelfPension(0.0);
		
		
		
		Mockito.when(pensionrepo.findByAadharNumber(9060421389253850L)).thenReturn(pmModel);
		Double obj2=pmservice.findByProcessPension(9060421389253850L);
	}
	
	
	@Test
	void findByProcessPension_else()
	{
		PMModel pmModel = new PMModel();
		pmModel.setAadharNumber(6849072063696130L);
		pmModel.setAllowances(555.0);
		pmModel.setBankAccountNumber(998877665585L);
		pmModel.setBankName("Better Life Holdings Inc");
		pmModel.setDate_of_birth("21-08-2021");
		pmModel.setFamilyPension(0.0);
		pmModel.setName("Jacqui Vanover");
		pmModel.setPan("YSNCH0058E");
		pmModel.setPensionType( "Self Pension");
		pmModel.setSalaryEarned(50044.0);
		pmModel.setSelfPension(0.0);
		
		
		
		Mockito.when(pensionrepo.findByAadharNumber(9060421389253850L)).thenReturn(pmModel);
		Double obj2=pmservice.findByProcessPension(9060421389253850L);
	}
}


