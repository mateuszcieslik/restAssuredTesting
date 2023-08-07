import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static io.restassured.specification.ProxySpecification.auth;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthToken {


    @Test
    public void getBookingIds(){
        //Arrange
       Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking?checkin=2023-08-07&checkout=2023-08-10");
        System.out.println(response.asString());
        System.out.println(response.getBody().asString());
        System.out.println((response.getStatusCode()));
        System.out.println(response.getStatusLine());
        System.out.println(response.getHeader("content-type"));
        System.out.println((response.getTime()));
        //Act
        //Assert

    }

    @Test
    public void getBookingId(){
        Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking/1366");
        System.out.println(response.asString());
        System.out.println(response.getBody().asString());
        System.out.println((response.getStatusCode()));
        System.out.println(response.getStatusLine());
        System.out.println(response.getHeader("content-type"));
        System.out.println((response.getTime()));
    }

    @Test
    public <Post> void PostAuth(){


        Map<String,String> map = new HashMap<String,String>();
        map.put("username","admin");
        map.put("password","password123");
        JSONObject body = new JSONObject(map);


        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(body.toJSONString())
                .post("https://restful-booker.herokuapp.com/auth");

        System.out.println(response.getBody().asString());

    }

   @Test
    public <Post> void CreateBooking(){

        Map<String, Object > bookingDates = new HashMap<>();
        bookingDates.put("checkin","2023-08-07");
        bookingDates.put("checkout","2023-08-10");

        Map<String,Object> map = new HashMap<>();
        map.put("firstname", "Andrzej");
        map.put("lastname", "Gołota");
        map.put("totalprice", "111");
        map.put("depositpaid", true);
        map.put("bookingdates",bookingDates);

        JSONObject body = new JSONObject(map);

        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(body.toJSONString())
                .post("https://restful-booker.herokuapp.com/booking");


        System.out.println(response.getBody().asString());
    }




}
