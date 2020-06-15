import com.energygrid.status_service.common.events.RegionalEvent;
import com.energygrid.status_service.repositories.RegionalEventRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
public class StatusRepositoryIntegrationTest {

    @Before
    public void setUp(){
    }


    @After
    public void tearDown() {
    }

    @Test
    public void shouldSaveEventTest() {
        RegionalEvent testEvent = new RegionalEvent(LocalDateTime.now(),"Eindhoven");
        assertThat(testEvent.getRegion(),  equalTo("Eindhoven"));
    }

}
