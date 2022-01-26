package com.digitalhonorsproject.pensionmanagement;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.digitalhonorsproject.pensionmanagement.controller.PMController;
import com.digitalhonorsproject.pensionmanagement.model.PMModel;
import com.digitalhonorsproject.pensionmanagement.service.JwtUtil;
import com.digitalhonorsproject.pensionmanagement.service.PMService;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class ControllerTest{

	@Autowired
	private PMController pmController;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PMService pmservice;
	
	@MockBean
	private JwtUtil jwtUtil;
	
	@Test
	void getPensionByAadharNumber() throws Exception
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
		
		
		when(jwtUtil.isTokenExpired("abc")).thenReturn(true);
		when(pmservice.findByAadharNumber(6849072063696130L)).thenReturn(pmModel);
		this.mockMvc.perform(
		MockMvcRequestBuilders.get( "/thepension/6849072063696130").header("Authorization","Bearer abc"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().json("{\r\n"
				+ "    \"aadharNumber\": 6849072063696130,\r\n"
				+ "    \"name\": \"Jacqui Vanover\",\r\n"
				+ "    \"date_of_birth\": \"21-08-2021\",\r\n"
				+ "    \"pan\": \"YSNCH0058E\",\r\n"
				+ "    \"salaryEarned\": 50044.0,\r\n"
				+ "    \"allowances\": 555.0,\r\n"
				+ "    \"selfPension\": 0.0,\r\n"
				+ "    \"familyPension\": 0.0,\r\n"
				+ "    \"pensionType\": \"Family Pension\",\r\n"
				+ "    \"bankName\": \"Better Life Holdings Inc\",\r\n"
				+ "    \"bankAccountNumber\": 998877665585\r\n"
				+ "}")).andReturn();
	}
	
	@Test
	void getPensionByAadharNumber2() throws Exception
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
		
		
		when(jwtUtil.isTokenExpired("abc")).thenReturn(false);
		when(pmservice.findByAadharNumber(6849072063696130L)).thenReturn(pmModel);
		this.mockMvc.perform(
		MockMvcRequestBuilders.get( "/thepension/6849072063696130").header("Authorization","Bearer abc"))
		.andDo(print()).andExpect(status().isUnauthorized()).andReturn();

				
	}
	
	@Test
	void getProcessPension() throws Exception
	{
		when(pmservice.findByProcessPension(6849072063696130L)).thenReturn(12.0);
		RequestBuilder requestBuilder=MockMvcRequestBuilders.post("/ProcessPension/6849072063696130").content("acv")
        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		//RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v1/giftcard/", giftCardPaymentRequest).content(new ObjectMapper().writeValueAsString(giftCardPaymentRequest)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);when(giftCardService.postGCPayment(Mockito.any(),Mockito.anyString())).thenReturn(giftCardPaymentResponseEnvelope);MvcResult result = mockMvc.perform(requestBuilder).andReturn();


		//MockMvcRequestBuilders.post( "/ProcessPension/{aadhar_number}").contentType(MediaType.APPLICATION_JSON)
	}
}
