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

import com.example.demo.handlers.DeskHandler;
import com.example.demo.handlers.DeskRequestHandler;
import com.example.demo.handlers.OrderPHandler;
import com.example.demo.handlers.RoomHandler;
import com.example.demo.handlers.UserPHandler;
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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
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

	// Desk Tests

	@Test
	void deskSaveSuccess() throws JsonMappingException, JsonProcessingException{
		Desk mockDesk = mockDesk();
		when(deskRepository.save(mockDesk)).thenReturn(mockDesk);
		DeskHandler deskHandler = DeskHandler.instance(deskRepository);
		DataResponse response = deskHandler.save(mockDeskJSON());
		Desk testDesk = (Desk)response.getData();
		assertEquals(DataResponseStatus.SUCCESS, response.getStatus());
		assertEquals(mockDesk, testDesk);
	}

	@Test
	void deskSaveError() throws JsonMappingException, JsonProcessingException{
		Desk mockDesk = mockDesk();
		when(deskRepository.save(mockDesk)).thenThrow(new IllegalArgumentException("save fail"));
		DeskHandler deskHandler = DeskHandler.instance(deskRepository);
		DataResponse response = deskHandler.save(mockDeskJSON());
		assertEquals(DataResponseStatus.ERROR, response.getStatus());
		assertEquals("save fail", response.getMessage());
	}

	@Test
	void deskFindAllSuccess(){
		List<Desk> deskList = new ArrayList<Desk>();
		Desk mockDesk = mockDesk();
		deskList.add(mockDesk);
		when(deskRepository.findAll()).thenReturn(deskList);
		DeskHandler deskHandler = DeskHandler.instance(deskRepository);
		DataResponse response = deskHandler.findAll();
		ArrayList<Desk> testDeskList = (ArrayList)response.getData();
		Desk testDesk = testDeskList.get(0).copy();
		assertEquals(mockDesk, testDesk);
	}

	@Test
	void deskUpdateSuccess() throws JsonMappingException, JsonProcessingException{
		List<Desk> deskList = new ArrayList<Desk>();
		Desk mockDesk = mockDesk();
		Desk mockDesk2 = mockDesk();
		mockDesk2.setTariffType("DAY");
		deskList.add(mockDesk2);
		when(deskRepository.findAll()).thenReturn(deskList);
		when(deskRepository.save(mockDesk)).thenReturn(mockDesk);
		DeskHandler deskHandler = DeskHandler.instance(deskRepository);
		DataResponse response = deskHandler.update(mockDeskJSON());
		Desk testDesk = (Desk)response.getData();
		verify(deskRepository, times(1)).delete(mockDesk2);
		assertEquals(DataResponseStatus.SUCCESS, response.getStatus());
		assertEquals(mockDesk, testDesk);
	}

	@Test
	void deskUpdateError() throws JsonMappingException, JsonProcessingException{
		List<Desk> deskList = new ArrayList<Desk>();
		Desk mockDesk = mockDesk();
		Desk mockDesk2 = mockDesk();
		mockDesk2.setId(2);
		deskList.add(mockDesk2);
		when(deskRepository.findAll()).thenReturn(deskList);
		when(deskRepository.save(mockDesk)).thenReturn(mockDesk);
		DeskHandler deskHandler = DeskHandler.instance(deskRepository);
		DataResponse response = deskHandler.update(mockDeskJSON());
		verify(deskRepository, times(0)).delete(mockDesk2);
		assertEquals(DataResponseStatus.ERROR, response.getStatus());
	}

	@Test
	void deskDeleteSuccess(){
		List<Desk> deskList = new ArrayList<Desk>();
		Desk mockDesk = mockDesk();
		Desk mockDesk2 = mockDesk();
		mockDesk2.setId(2);
		deskList.add(mockDesk);
		deskList.add(mockDesk2);
		when(deskRepository.findAll()).thenReturn(deskList);
		DeskHandler deskHandler = DeskHandler.instance(deskRepository);
		DataResponse response = deskHandler.delete(2);
		verify(deskRepository, times(1)).delete(mockDesk2);
		assertEquals(DataResponseStatus.SUCCESS, response.getStatus());
	}

	@Test
	void deskDeleteError(){
		List<Desk> deskList = new ArrayList<Desk>();
		Desk mockDesk = mockDesk();
		Desk mockDesk2 = mockDesk();
		mockDesk2.setId(2);
		deskList.add(mockDesk);
		deskList.add(mockDesk2);
		when(deskRepository.findAll()).thenReturn(deskList);
		DeskHandler deskHandler = DeskHandler.instance(deskRepository);
		DataResponse response = deskHandler.delete(3);
		verify(deskRepository, times(0)).delete(mockDesk2);
		assertEquals(DataResponseStatus.ERROR, response.getStatus());
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
		mockDeskRequest2.setStatus(DeskRequestStatus.RESERVED);
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

	// OrderP Tests

	@Test
	void orderPSaveSuccess() throws JsonMappingException, JsonProcessingException{
		OrderP orderP = mockOrderP();
		when(orderPRepository.save(orderP)).thenReturn(orderP);
		OrderPHandler orderPHandler = OrderPHandler.instance(orderPRepository);
		DataResponse response = orderPHandler.save(mockOrderPJSON());
		OrderP testOrderP = (OrderP)response.getData();
		assertEquals(DataResponseStatus.SUCCESS, response.getStatus());
		assertEquals(orderP, testOrderP);
	}

	@Test
	void orderPSaveError() throws JsonMappingException, JsonProcessingException{
		OrderP orderP = mockOrderP();
		when(orderPRepository.save(orderP)).thenThrow(new IllegalArgumentException("save fail"));
		OrderPHandler orderPHandler = OrderPHandler.instance(orderPRepository);
		DataResponse response = orderPHandler.save(mockOrderPJSON());
		assertEquals(DataResponseStatus.ERROR, response.getStatus());
		assertEquals("save fail", response.getMessage());
	}

	@Test
	void orderPFindAllSuccess(){
		List<OrderP> orderPList = new ArrayList<OrderP>();
		OrderP mockOrderP = mockOrderP();
		orderPList.add(mockOrderP);
		when(orderPRepository.findAll()).thenReturn(orderPList);
		OrderPHandler orderPHandler = OrderPHandler.instance(orderPRepository);
		DataResponse response = orderPHandler.findAll();
		ArrayList<OrderP> testOrderPList = (ArrayList)response.getData();
		OrderP testOrderP = testOrderPList.get(0).copy();
		assertEquals(mockOrderP, testOrderP);
	}

	@Test
	void orderPUpdateSucces() throws JsonMappingException, JsonProcessingException{
		List<OrderP> orderPList = new ArrayList<OrderP>();
		OrderP mockOrderP = mockOrderP();
		OrderP mockOrderP2 = mockOrderP();
		mockOrderP2.setStatus(OrderPStatus.PAID);
		orderPList.add(mockOrderP2);
		when(orderPRepository.findAll()).thenReturn(orderPList);
		when(orderPRepository.save(mockOrderP)).thenReturn(mockOrderP);
		OrderPHandler orderPHandler = OrderPHandler.instance(orderPRepository);
		DataResponse response = orderPHandler.update(mockOrderPJSON());
		OrderP testOrderP = (OrderP)response.getData();
		verify(orderPRepository, times(1)).delete(mockOrderP2);
		assertEquals(DataResponseStatus.SUCCESS, response.getStatus());
		assertEquals(mockOrderP, testOrderP);
	}

	@Test
	void orderPUpdateError() throws JsonMappingException, JsonProcessingException{
		List<OrderP> orderPList = new ArrayList<OrderP>();
		OrderP mockOrderP = mockOrderP();
		OrderP mockOrderP2 = mockOrderP();
		mockOrderP2.setId(2);
		orderPList.add(mockOrderP2);
		when(orderPRepository.findAll()).thenReturn(orderPList);
		when(orderPRepository.save(mockOrderP)).thenReturn(mockOrderP);
		OrderPHandler orderPHandler = OrderPHandler.instance(orderPRepository);
		DataResponse response = orderPHandler.update(mockOrderPJSON());
		verify(orderPRepository, times(0)).delete(mockOrderP2);
		assertEquals(DataResponseStatus.ERROR, response.getStatus());
	}

	@Test
	void orderPDeleteSuccess() {
		List<OrderP> orderPList = new ArrayList<OrderP>();
		OrderP mockOrderP = mockOrderP();
		OrderP mockOrderP2 = mockOrderP();
		mockOrderP2.setId(2);
		orderPList.add(mockOrderP);
		orderPList.add(mockOrderP2);
		when(orderPRepository.findAll()).thenReturn(orderPList);
		OrderPHandler orderPHandler = OrderPHandler.instance(orderPRepository);
		DataResponse response = orderPHandler.delete(2);
		verify(orderPRepository, times(1)).delete(mockOrderP2);
		assertEquals(DataResponseStatus.SUCCESS, response.getStatus());
	}

	@Test
	void orderPDeleteError() {
		List<OrderP> orderPList = new ArrayList<OrderP>();
		OrderP mockOrderP = mockOrderP();
		OrderP mockOrderP2 = mockOrderP();
		mockOrderP2.setId(2);
		orderPList.add(mockOrderP);
		orderPList.add(mockOrderP2);
		when(orderPRepository.findAll()).thenReturn(orderPList);
		OrderPHandler orderPHandler = OrderPHandler.instance(orderPRepository);
		DataResponse response = orderPHandler.delete(3);
		verify(orderPRepository, times(0)).delete(mockOrderP2);
		assertEquals(DataResponseStatus.ERROR, response.getStatus());
	}

	// Room Tests
	// @Test
	// void roomSaveSuccess() throws JsonMappingException, JsonProcessingException{
	// 	Room mockRoom = mockRoom();
	// 	when(roomRepository.save(mockRoom)).thenReturn(mockRoom);
	// 	RoomHandler roomHandler = RoomHandler.instance(roomRepository);
	// 	DataResponse response = roomHandler.save(mockRoomJSON());
	// 	Room testRoom = (Room)response.getData();
	// 	assertEquals(DataResponseStatus.SUCCESS, response.getStatus());
	// 	assertEquals(mockRoom, testRoom);
	// }

	// UserP Tests
	
	@Test
	void userPSaveSuccess() throws JsonMappingException, JsonProcessingException{
		UserP mockUserP = mockUserP();
		when(userPRepository.save(mockUserP)).thenReturn(mockUserP);
		UserPHandler userPHandler = UserPHandler.instance(userPRepository);
		DataResponse response = userPHandler.save(mockUserPJSON());
		UserP testUserP = (UserP)response.getData();
		assertEquals(DataResponseStatus.SUCCESS, response.getStatus());
		assertEquals(mockUserP, testUserP);
	}

	@Test
	void userPSaveError() throws JsonMappingException, JsonProcessingException{
		UserP mockUserP = mockUserP();
		when(userPRepository.save(mockUserP)).thenThrow(new IllegalArgumentException("save fail"));
		UserPHandler userPHandler = UserPHandler.instance(userPRepository);
		DataResponse response = userPHandler.save(mockUserPJSON());
		assertEquals(DataResponseStatus.ERROR, response.getStatus());
		assertEquals("save fail", response.getMessage());
	}

	@Test
	void userPFindAllSuccess(){
		List<UserP> deskList = new ArrayList<UserP>();
		UserP mockUserP = mockUserP();
		deskList.add(mockUserP);
		when(userPRepository.findAll()).thenReturn(deskList);
		UserPHandler userPHandler = UserPHandler.instance(userPRepository);
		DataResponse response = userPHandler.findAll();
		ArrayList<UserP> testUserPList = (ArrayList)response.getData();
		UserP testUserP = testUserPList.get(0).copy();
		assertEquals(mockUserP, testUserP);
	}

	@Test
	void userPUpdateSuccess() throws JsonMappingException, JsonProcessingException{
		List<UserP> userPList = new ArrayList<UserP>();
		UserP mockUserP = mockUserP();
		UserP mockUserP2 = mockUserP();
		mockUserP2.setType(UserPTypes.ADMIN);
		userPList.add(mockUserP2);
		when(userPRepository.findAll()).thenReturn(userPList);
		when(userPRepository.save(mockUserP)).thenReturn(mockUserP);
		UserPHandler userPHandler = UserPHandler.instance(userPRepository);
		DataResponse response = userPHandler.update(mockUserPJSON());
		UserP testUserP = (UserP)response.getData();
		verify(userPRepository, times(1)).delete(mockUserP2);
		assertEquals(DataResponseStatus.SUCCESS, response.getStatus());
		assertEquals(mockUserP, testUserP);
	}

	@Test
	void userPUpdateError() throws JsonMappingException, JsonProcessingException{
		List<UserP> userPList = new ArrayList<UserP>();
		UserP mockUserP = mockUserP();
		UserP mockUserP2 = mockUserP();
		mockUserP2.setId(2);
		userPList.add(mockUserP2);
		when(userPRepository.findAll()).thenReturn(userPList);
		when(userPRepository.save(mockUserP)).thenReturn(mockUserP);
		UserPHandler userPHandler = UserPHandler.instance(userPRepository);
		DataResponse response = userPHandler.update(mockUserPJSON());
		verify(userPRepository, times(0)).delete(mockUserP2);
		assertEquals(DataResponseStatus.ERROR, response.getStatus());
	}

	@Test
	void userPDeleteSuccess(){
		List<UserP> userPList = new ArrayList<UserP>();
		UserP mockUserP = mockUserP();
		UserP mockUserP2 = mockUserP();
		mockUserP2.setId(2);
		userPList.add(mockUserP);
		userPList.add(mockUserP2);
		when(userPRepository.findAll()).thenReturn(userPList);
		UserPHandler userPHandler = UserPHandler.instance(userPRepository);
		DataResponse response = userPHandler.delete(2);
		verify(userPRepository, times(1)).delete(mockUserP2);
		assertEquals(DataResponseStatus.SUCCESS, response.getStatus());
	}

	@Test
	void userPDeleteError(){
		List<UserP> userPList = new ArrayList<UserP>();
		UserP mockUserP = mockUserP();
		UserP mockUserP2 = mockUserP();
		mockUserP2.setId(2);
		userPList.add(mockUserP);
		userPList.add(mockUserP2);
		when(userPRepository.findAll()).thenReturn(userPList);
		UserPHandler userPHandler = UserPHandler.instance(userPRepository);
		DataResponse response = userPHandler.delete(3);
		verify(userPRepository, times(0)).delete(mockUserP2);
		assertEquals(DataResponseStatus.ERROR, response.getStatus());
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
