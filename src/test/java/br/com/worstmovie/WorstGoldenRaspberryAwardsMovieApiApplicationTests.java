package br.com.worstmovie;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WorstGoldenRaspberryAwardsMovieApiApplicationTests {
	
	TestRestTemplate restTemplate = new TestRestTemplate();
	
	HttpHeaders headers = new HttpHeaders();
	
	@LocalServerPort
    private int port;

	@Test
	public void testRetrieveMinMaxIntervalBetweenTwoAwards() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/movies/min-max-interval-between-two-awards"), HttpMethod.GET, entity, String.class);
		String expected = "{\"min\":[{\"producer\":\"Joel Silver\",\"interval\":1,\"previousWin\":1990,\"followingWin\":1991}],\"max\":[{\"producer\":\"Matthew Vaughn\",\"interval\":49,\"previousWin\":2015,\"followingWin\":2064}]}";
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}
	
	@Test
    public void testStatusReturnMethod() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity(createURLWithPort("/movies/min-max-interval-between-two-awards"), 
        		String.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }    

	
	private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
