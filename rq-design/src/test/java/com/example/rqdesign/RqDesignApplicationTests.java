package com.example.rqdesign;

import com.example.rqdesign.dto.response.CreateEmpRespDto;
import com.example.rqdesign.dto.response.EmpRespDto;
import com.example.rqdesign.dto.response.EmployeesRespDto;
import com.example.rqdesign.dto.response.HighestQueryDataRespDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT)
class RqDesignApplicationTests {

	@LocalServerPort
	int serverPort;

	private static RestTemplate restTemplate;
	private static ObjectMapper objectMapper;

	@BeforeAll
	public static void setup() {
		restTemplate = new RestTemplate();
		objectMapper = new ObjectMapper();
	}

	@Test
	void contextLoads() {
	}

	/*@Test
	public void testInvalidUrl() throws URISyntaxException {
		URI uri = new URI(createBaseUrl().append("/dd").toString());

		assertThrows(HttpClientErrorException.class,
				()-> restTemplate.getForEntity(uri, String.class));
	}*/

	@Test
	public void testGetAllEmployeesA() throws URISyntaxException, JsonProcessingException {
		URI uri = new URI(createBaseUrl().toString());
		ObjectReader objectReader = objectMapper.reader().forType(new TypeReference<EmployeesRespDto>(){});

		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

		Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
		Assertions.assertNotNull(result.getBody());
		EmployeesRespDto employeesRespDto = objectReader.readValue(result.getBody());
		Assertions.assertNotNull(employeesRespDto);
		Assertions.assertNotNull(employeesRespDto.getData());
		Assertions.assertEquals(20, employeesRespDto.getData().size());
	}

	@Test
	public void testSearchEmployeeBySearchString() throws URISyntaxException, JsonProcessingException {
		URI uri = new URI(createBaseUrl().append("/search?name=Tom").toString());
		ObjectReader objectReader = objectMapper.reader().forType(new TypeReference<EmployeesRespDto>(){});

		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

		Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
		Assertions.assertNotNull(result.getBody());
		EmployeesRespDto employeesRespDto = objectReader.readValue(result.getBody());
		Assertions.assertNotNull(employeesRespDto);
		Assertions.assertNotNull(employeesRespDto.getData());
		Assertions.assertEquals(3, employeesRespDto.getData().size());
		employeesRespDto.getData().forEach(employee -> Assertions.assertTrue(employee.getEmployeeName().contains("Tom")));
	}

	@Test
	public void testGetEmployeesByValidEmpId() throws URISyntaxException, JsonProcessingException {
		URI uri = new URI(createBaseUrl().append("/2").toString());
		ObjectReader objectReader = objectMapper.reader().forType(new TypeReference<CreateEmpRespDto>(){});
		EmpRespDto expectedEmployee = new EmpRespDto("2", "Tom Cruise", "400000", "40", "");
		CreateEmpRespDto createEmpRespDto = new CreateEmpRespDto(Constants.SUCCESS, expectedEmployee);

		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

		Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
		Assertions.assertNotNull(result.getBody());
		CreateEmpRespDto actualEmployee = objectReader.readValue(result.getBody());
		Assertions.assertNotNull(actualEmployee);
		Assertions.assertEquals(createEmpRespDto, actualEmployee);
	}

	@Test
	public void testGetEmployeeDetailsByIdEmployeeDontExist() throws URISyntaxException, JsonProcessingException {
		URI uri = new URI(createBaseUrl().append("/240").toString());
		ObjectReader objectReader = objectMapper.reader().forType(new TypeReference<CreateEmpRespDto>(){});
		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

		Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
		Assertions.assertNotNull(result.getBody());
		CreateEmpRespDto actualEmployee = objectReader.readValue(result.getBody());
		Assertions.assertNotNull(actualEmployee);
		Assertions.assertEquals(null, actualEmployee.getData());
	}

	@Test
	public void testHighestSalary() throws URISyntaxException {
		URI uri = new URI(createBaseUrl().append(Constants.URL_EMP_TOP_SALARY).toString());

		ResponseEntity<HighestQueryDataRespDto> result = restTemplate.getForEntity(uri, HighestQueryDataRespDto.class);

		Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
		Assertions.assertNotNull(result.getBody());
		Assertions.assertEquals(900800, result.getBody().getHighestSalary());
	}

	@Test
	public void testTopKHighestEarningEmployeeNames() throws URISyntaxException, JsonProcessingException {
		URI uri = new URI(createBaseUrl().append(Constants.URL_EMP_TOP_EARNING_EMPS + "?topKCount=5").toString());
		ObjectReader objectReader = objectMapper.reader().forType(new TypeReference<HighestQueryDataRespDto>(){});
		List<String> expectedNameList = Arrays.asList("Tom salaey Nixon","Samsher Saxena","R R","Bahdur JK","Sofi Don");

		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

		Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
		Assertions.assertNotNull(result.getBody());
		HighestQueryDataRespDto  respDto = objectReader.readValue(result.getBody());
		List<String> actualNameList = respDto.getHighestEarningEmployees();
		Assertions.assertNotNull(actualNameList);
		Assertions.assertEquals(5, actualNameList.size());
		Assertions.assertEquals(expectedNameList, actualNameList);
	}

	@Test
	public void testCreateEmployee() throws URISyntaxException {
		URI uri = new URI(createBaseUrl().toString());

		Map<String, Object> inputRequestPayload = new HashMap<>();
		inputRequestPayload.put("name", "Harry Potter");
		inputRequestPayload.put("salary", 204500);
		inputRequestPayload.put("age", 26);

		ResponseEntity<CreateEmpRespDto> result = restTemplate.postForEntity(uri, inputRequestPayload, CreateEmpRespDto.class);

		Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
		Assertions.assertNotNull(result.getBody());
		CreateEmpRespDto actualEmployee = result.getBody();
		Assertions.assertNotNull(actualEmployee);
		Assertions.assertNotNull(actualEmployee.getData().getId());
	}

	@Test
	public void testCreateEmployeeNameMissing() throws URISyntaxException {
		URI uri = new URI(createBaseUrl().toString());

		Map<String, Object> payload = new HashMap<>();
		payload.put("age", "26");
		payload.put("salary", "45600");

		HttpClientErrorException ex = assertThrows(HttpClientErrorException.class,
				()-> restTemplate.postForEntity(uri, payload, CreateEmpRespDto.class));

		Assertions.assertTrue(ex.getMessage().contains("Name is blank."));
	}

	@Test
	public void testCreateEmployeeAgeMissing() throws URISyntaxException {
		URI uri = new URI(createBaseUrl().toString());

		Map<String, Object> payload = new HashMap<>();
		payload.put("name", "Harry Potter");
		payload.put("salary", 204500);

		HttpClientErrorException ex = assertThrows(HttpClientErrorException.class,
				()-> restTemplate.postForEntity(uri, payload, CreateEmpRespDto.class));

		Assertions.assertTrue(ex.getMessage().contains("Age is missing"));
	}


	@Test
	public void testDeleteEmployeeById() throws URISyntaxException {
		URI uri = new URI(createBaseUrl().append("/60").toString());

		restTemplate.delete(uri);
	}

	private StringBuilder createBaseUrl() {
		String BASE_URL = "http://localhost:";
		return new StringBuilder().append(BASE_URL).append(serverPort).append("/").append(Constants.URL_EMPLOYEES);
	}

}
