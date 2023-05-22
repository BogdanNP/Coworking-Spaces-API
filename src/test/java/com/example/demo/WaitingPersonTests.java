package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.example.demo.handlers.WaitingListHandler;
import com.example.demo.models.DataResponse;
import com.example.demo.models.DataResponseStatus;
import com.example.demo.models.Desk;
import com.example.demo.models.DeskRequest;
import com.example.demo.models.DeskRequestStatus;
import com.example.demo.models.OrderP;
import com.example.demo.models.OrderPStatus;
import com.example.demo.models.Room;
import com.example.demo.models.TariffTypes;
import com.example.demo.models.UserP;
import com.example.demo.models.UserPTypes;
import com.example.demo.models.WaitingPerson;
import com.example.demo.repositories.DeskRepository;
import com.example.demo.repositories.DeskRequestRepository;
import com.example.demo.repositories.OrderPRepository;
import com.example.demo.repositories.RoomRepository;
import com.example.demo.repositories.UserPRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class DemoApplicationTests {
	// Repositories
	@Mock
	DeskRepository deskRepository;
	@Mock 
	DeskRequestRepository deskRequestRepository;
	@Mock
	OrderPRepository orderPRepository;
	@Mock 
	RoomRepository roomRepository;
	@Mock
	UserPRepository userPRepository;

	// SetUp
	@BeforeEach
	void setUp(){
		MockitoAnnotations.openMocks(this);
	}

	// Mock Data Model Generators
	Desk mockDesk(){
		Desk desk = new Desk(); 
		desk.setId(1);
		desk.setHeight(100);
		desk.setWidth(50);
		desk.setLength(120);
		desk.setTariff(5.0);
		desk.setTariffType(TariffTypes.HOUR);
		return desk;
	}

	String mockDeskJSON(){
		try{
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", 1);
			map.put("height", 100);
			map.put("width", 50);
			map.put("length", 120);
			map.put("tariff", 5.0);
			map.put("tariff_type", TariffTypes.HOUR);
			return mapper.writeValueAsString(map);
		} catch (Exception e){
			return "";
		}
	}

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

	OrderP mockOrderP(){
		OrderP orderP = new OrderP();
		orderP.setId(1);
		orderP.setDeskId(1);
		orderP.setUserId(1);
		orderP.setStatus(OrderPStatus.NEW);
		orderP.setTotal(300.0);
		return orderP;
	}

	String mockOrderPJSON(){
		try{
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", 1);
			map.put("desk_id", 1);
			map.put("user_id", 1);
			map.put("status", OrderPStatus.NEW);
			map.put("total", 300.0);
			return mapper.writeValueAsString(map);
		} catch (Exception e){
			return "";
		}
	}

	Room mockRoom(){
		Room room = new Room();
		room.setId(1);
		room.setDetails("details");
		room.setLength(200);
		room.setWidth(20);
		return room;
	}

	String mockRoomJSON(){
		try{
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", 1);
			map.put("details", "details");
			map.put("width", 20);
			map.put("length", 200);
			return mapper.writeValueAsString(map);
		} catch (Exception e){
			return "";
		}
	}

	UserP mockUserP(){
		UserP userP = new UserP();
		userP.setId(1);
		userP.setType(UserPTypes.LOGGED_IN);
		userP.setPassword("1234");
		userP.setUsername("TestUsername");
		return userP;
	}

	String mockUserPJSON(){
		try{
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", 1);
			map.put("type", UserPTypes.LOGGED_IN);
			map.put("password", "1234");
			map.put("username", "TestUsername");
			return mapper.writeValueAsString(map);
		} catch (Exception e){
			return "";
		}
	}

	WaitingPerson mockWaitingPerson(){
		WaitingPerson waitingPerson = new WaitingPerson();
		waitingPerson.setUserId(1);
		waitingPerson.setDeskId(1);
		waitingPerson.setDeskAvailable(false);
		return waitingPerson;
	}

	String mockWaitingPersonJSON(){
		try{
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("user_id", 1);
			map.put("desk_id", 1);
			return mapper.writeValueAsString(map);
		} catch (Exception e){
			return "";
		}
	}

	Exception mockException(){
		return new Exception("mockException");
	}

	// WaitingList Tests

	@Test
	void addWaitingPersonSuccessAvailable(){
		WaitingPerson waitingPerson = mockWaitingPerson();
		waitingPerson.setDeskAvailable(true);
		List<DeskRequest> deskRequestList = new ArrayList<DeskRequest>();
		DeskRequest mockDeskRequest = mockDeskRequest();
		DeskRequest mockDeskRequest2 = mockDeskRequest();
		mockDeskRequest2.setId(2);
		deskRequestList.add(mockDeskRequest);
		deskRequestList.add(mockDeskRequest2);
		when(deskRequestRepository.findAll()).thenReturn(deskRequestList);
		WaitingListHandler waitingListHandler = WaitingListHandler.instance(
			deskRequestRepository, deskRepository);
		DataResponse response = waitingListHandler.add(mockWaitingPersonJSON());
		assertEquals(DataResponseStatus.SUCCESS, response.getStatus());
		assertEquals(waitingPerson, response.getData());
	}
	
	@Test
	void addWaitingPersonSuccessUnavailable(){
		WaitingPerson waitingPerson = mockWaitingPerson();
		List<DeskRequest> deskRequestList = new ArrayList<DeskRequest>();
		DeskRequest mockDeskRequest = mockDeskRequest();
		DeskRequest mockDeskRequest2 = mockDeskRequest();
		mockDeskRequest.setStatus(DeskRequestStatus.RESERVED);
		mockDeskRequest2.setId(2);
		deskRequestList.add(mockDeskRequest);
		deskRequestList.add(mockDeskRequest2);
		when(deskRequestRepository.findAll()).thenReturn(deskRequestList);
		WaitingListHandler waitingListHandler = WaitingListHandler.instance(
			deskRequestRepository, deskRepository);
		DataResponse response = waitingListHandler.add(mockWaitingPersonJSON());
		assertEquals(DataResponseStatus.SUCCESS, response.getStatus());
		assertEquals(waitingPerson, response.getData());
	}
}
