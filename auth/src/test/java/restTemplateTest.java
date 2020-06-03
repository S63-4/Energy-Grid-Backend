import com.energygrid.auth.common.models.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

public class restTemplateTest {

    @Before
    public void setUp(){
    }

    @Test
    public void testRestTemplateCallTest() throws URISyntaxException
    {
        RestTemplate restTemplate = new RestTemplate();

        // test one of our endpoints
        final String baseUrl =  "http://35.197.228.250/";
        URI uri = new URI(baseUrl);

        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class );

        //Verify request is ok
        Assert.assertEquals(200, result.getStatusCodeValue());
    }
}
