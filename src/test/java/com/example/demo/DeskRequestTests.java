package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.handlers.DeskRequestHandler;
import com.example.demo.models.DataResponse;
import com.example.demo.models.DataResponseStatus;
import com.example.demo.models.DeskRequest;
import com.example.demo.models.DeskRequestStatus;
import com.example.demo.repositories.DeskRequestRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class DeskRequestTests {
	// Repository
	@Mock
	DeskRequestRepository deskRequestRepository;

	// SetUp
	@BeforeEach
	void setUp(){
		MockitoAnnotations.openMocks(this);
	}

	// Mock Data Model Generators
	DeskRequest mockDeskRequest(){
		SimpleDateFormat dateFormater = new SimpleDateFormat("dd-MM-yyyy-HH:mm:ss");
		DeskRequest deskRequest = new DeskRequest(); 
		deskRequest.setId(1);
		deskRequest.setDeskId(1);
		deskRequest.setStatus(DeskRequestStatus.CURRENT);
		deskRequest.setUserId(1);
		try {
			deskRequest.setStartDate(dateFormater.parse("09-05-2023-00:22:23"));
			deskRequest.setEndDate(dateFormater.parse("09-05-2023-07:22:23"));
		} catch (Exception e) {}
		return deskRequest;
	}

	
	String mockDeskRequestJSON(){
		try{
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", 1);
			map.put("desk_id", 1);
			map.put("status", DeskRequestStatus.CURRENT);
			map.put("user_id", 1);
			map.put("start_date", "09-05-2023-00:22:23");
			map.put("end_date", "09-05-2023-07:22:23");
			return mapper.writeValueAsString(map);
		} catch (Exception e){
			return "";
		}
	}

	Exception mockException(){
		return new Exception("mockException");
	}

	// DeskRequest Tests

	@Test
	void deskRequestSaveSuccess() throws JsonMappingException, JsonProcessingException{
		DeskRequest mockDeskRequest = mockDeskRequest();
		when(deskRequestRepository.save(mockDeskRequest)).thenReturn(mockDeskRequest);
		DeskRequestHandler deskRequestHandler = DeskRequestHandler.instance(deskRequestRepository);
		DataResponse response = deskRequestHandler.save(mockDeskRequestJSON());
		DeskRequest testDeskRequest = (DeskRequest)response.getData();
		assertEquals(DataResponseStatus.SUCCESS, response.getStatus());
		assertEquals(mockDeskRequest, testDeskRequest);
	}

	@Test
	void deskRequestSaveError() throws JsonMappingException, JsonProcessingException{
		DeskRequest mockDeskRequest = mockDeskRequest();
		when(deskRequestRepository.save(mockDeskRequest)).thenThrow(new IllegalArgumentException("save fail"));
		DeskRequestHandler deskRequestHandler = DeskRequestHandler.instance(deskRequestRepository);
		DataResponse response = deskRequestHandler.save(mockDeskRequestJSON());
		assertEquals(DataResponseStatus.ERROR, response.getStatus());
		assertEquals("save fail", response.getMessage());
	}

	@Test
	void deskRequestFindAllSuccess(){
		List<DeskRequest> deskRequestList = new ArrayList<DeskRequest>();
		DeskRequest mockDeskRequest = mockDeskRequest();
		deskRequestList.add(mockDeskRequest);
		when(deskRequestRepository.findAll()).thenReturn(deskRequestList);
		DeskRequestHandler deskRequestHandler = DeskRequestHandler.instance(deskRequestRepository);
		DataResponse response = deskRequestHandler.findAll();
		ArrayList<DeskRequest> testDeskRequestList = (ArrayList)response.getData();
		DeskRequest testRequestDesk = testDeskRequestList.get(0).copy();
		assertEquals(mockDeskRequest, testRequestDesk);
	}

	@Test
	void deskRequestUpdateSuccess() throws JsonMappingException, JsonProcessingException{
		List<DeskRequest> deskRequestList = new ArrayList<DeskRequest>();
		DeskRequest mockDeskRequest = mockDeskRequest();
		DeskRequest mockDeskRequest2 = mockDeskRequest();
		mockDeskRequest2.setStatus(DeskRequestStatus.FUTURE);
		deskRequestList.add(mockDeskRequest2);
		when(deskRequestRepository.findAll()).thenReturn(deskRequestList);
		when(deskRequestRepository.save(mockDeskRequest)).thenReturn(mockDeskRequest);
		DeskRequestHandler deskRequestHandler = DeskRequestHandler.instance(deskRequestRepository);
		DataResponse response = deskRequestHandler.update(mockDeskRequestJSON());
		DeskRequest testDeskRequest = (DeskRequest)response.getData();
		verify(deskRequestRepository, times(1)).delete(mockDeskRequest2);
		assertEquals(DataResponseStatus.SUCCESS, response.getStatus());
		assertEquals(mockDeskRequest, testDeskRequest);
	}

	@Test
	void deskRequestUpdateError() throws JsonMappingException, JsonProcessingException{
		List<DeskRequest> deskRequestList = new ArrayList<DeskRequest>();
		DeskRequest mockDeskRequest = mockDeskRequest();
		DeskRequest mockDeskRequest2 = mockDeskRequest();
		mockDeskRequest2.setId(2);
		deskRequestList.add(mockDeskRequest2);
		when(deskRequestRepository.findAll()).thenReturn(deskRequestList);
		when(deskRequestRepository.save(mockDeskRequest)).thenReturn(mockDeskRequest);
		DeskRequestHandler deskRequestHandler = DeskRequestHandler.instance(deskRequestRepository);
		DataResponse response = deskRequestHandler.update(mockDeskRequestJSON());
		verify(deskRequestRepository, times(0)).delete(mockDeskRequest2);
		assertEquals(DataResponseStatus.ERROR, response.getStatus());
	}

	@Test
	void deskRequestDeleteSuccess() {
		List<DeskRequest> deskRequestList = new ArrayList<DeskRequest>();
		DeskRequest mockDeskRequest = mockDeskRequest();
		DeskRequest mockDeskRequest2 = mockDeskRequest();
		mockDeskRequest2.setId(2);
		deskRequestList.add(mockDeskRequest);
		deskRequestList.add(mockDeskRequest2);
		when(deskRequestRepository.findAll()).thenReturn(deskRequestList);
		DeskRequestHandler deskRequestHandler = DeskRequestHandler.instance(deskRequestRepository);
		DataResponse response = deskRequestHandler.delete(2);
		verify(deskRequestRepository, times(1)).delete(mockDeskRequest2);
		assertEquals(DataResponseStatus.SUCCESS, response.getStatus());
	}

	@Test
	void deskRequestDeleteError() {
		List<DeskRequest> deskRequestList = new ArrayList<DeskRequest>();
		DeskRequest mockDeskRequest = mockDeskRequest();
		DeskRequest mockDeskRequest2 = mockDeskRequest();
		mockDeskRequest2.setId(2);
		deskRequestList.add(mockDeskRequest);
		deskRequestList.add(mockDeskRequest2);
		when(deskRequestRepository.findAll()).thenReturn(deskRequestList);
		DeskRequestHandler deskRequestHandler = DeskRequestHandler.instance(deskRequestRepository);
		DataResponse response = deskRequestHandler.delete(3);
		verify(deskRequestRepository, times(0)).delete(mockDeskRequest2);
		assertEquals(DataResponseStatus.ERROR, response.getStatus());
	}
}
