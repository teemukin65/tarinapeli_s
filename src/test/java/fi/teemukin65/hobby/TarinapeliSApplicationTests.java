package fi.teemukin65.hobby;

import fi.teemukin65.hobby.tarinapeli.TarinapeliSApplication;
import fi.teemukin65.hobby.tarinapeli.rest.dto.PlayerDto;
import fi.teemukin65.hobby.tarinapeli.rest.dto.PlayerRegistrationDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {
                TarinapeliSApplication.class

        }
)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
public class TarinapeliSApplicationTests {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TestRestTemplate restTemplate;

    @Value("${random.int}")
    Integer testPersonId;


	@Test
    public void contextLoads() {
        logger.debug("contextLoads");
    }


    @Test
    public void playerRegistrationOK() {
        PlayerRegistrationDto registrationDto = new PlayerRegistrationDto();
        String email = "test" + testPersonId.toString() + "@" + "example.com";
        registrationDto.setEmail(email);
        registrationDto.setPassword("secret4test!");
        HttpEntity<PlayerRegistrationDto> request = new HttpEntity<>(registrationDto);

        PlayerDto body = this.restTemplate.postForObject("/game/player/sign-up", request, PlayerDto.class);
        assertThat(body.getEmail()).isEqualTo(email);
//		this.restTemplate.getForEntity("/api/crud/player/"+body.get)

    }

}
