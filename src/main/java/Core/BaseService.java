package Core;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.*;
import com.mashape.unirest.request.HttpRequestWithBody;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Map;



@Data
@NonNull
@NoArgsConstructor
public class BaseService {

    @BeforeSuite
    public void beforeSuite(){
        System.out.printf("%nThis is BEFORE_SUITE! --> create reporting instance%n");
    }

    @BeforeTest
    public void beforeTest(){
        System.out.printf("%nThis is BEFORE_TEST! --> build parent-test%n");
    }

    @BeforeClass
    public void beforeClass(){
        System.out.printf("%nThis is BEFORE_Class! --> hmmmm%n");
    }

    @BeforeMethod
    public void beforeMethod(){
        System.out.printf("%nThis is BEFORE_METHOD! --> build child-step");
    }

    @AfterMethod
    public void afterMethod(){
        System.out.printf("%nThis is AFTER_METHOD! --> assess result %n");
    }

    @AfterClass
    public void afterClass(){
        System.out.printf("%nThis is AFTER_Class! --> hmmmm%n");
    }

    @AfterTest
    public void afterTest(){
        System.out.printf("%nThis is AFTER_TEST! --> flush results!%n");
    }

    @AfterSuite
    public void afterSuite(){
        System.out.printf("%nThis is AFTER_SUITE! --> cleanup!%n");
    }


    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected JsonNode postAnonymous(String url, String requestType, Map<String, Object> parameters) throws Exception {

        Unirest.setObjectMapper(new ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper = new com.fasterxml.jackson.databind.ObjectMapper();


            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }


            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        //from this WARNING BELOW use simple switch statement and Unirest.post.. Unirest.options
        logger.debug("Calling {} - Parameters {}", url, parameters);
        HttpResponse<JsonNode> response = Unirest.post(url).header("accept", "application/json").header("Content-Type", "application/json").body(parameters).asJson();

        if (response.getStatus() != 200 && response.getStatus() != 201 && response.getStatus() != 202) {
            String message = MessageFormat.format("Error calling end point {0}, response status was {1}, body was {2}", url, response.getStatus(), response.getBody());
            throw new Exception(message);
        }
        logger.debug("Call to {} was ok!", url);

        return response.getBody();
    }

    protected JsonNode postWithAUTH(String url, String requestType, String token, Map<String, Object> parameters) throws Exception {
        Unirest.setObjectMapper(new ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper = new com.fasterxml.jackson.databind.ObjectMapper();

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        logger.debug("Calling {} - Parameters {}", url, parameters);
        HttpResponse<JsonNode> response = Unirest.post(url).header("accept", "application/json").header("Content-Type", "application/json").header("Authorization", "Bearer " + token).body(parameters).asJson();

        if (response.getStatus() != 200 && response.getStatus() != 201 && response.getStatus() != 202) {
            String message = MessageFormat.format("Error calling end point {0}, response status was {1}, body was {2}", url, response.getStatus(), response.getBody());
            throw new Exception(message);
        } else {
            logger.debug("Call to {} was ok!", url);
        }

        return response.getBody();
    }



    private String testLombok = "Hi there!";


}