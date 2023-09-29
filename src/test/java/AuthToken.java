import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static io.restassured.specification.ProxySpecification.auth;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthToken {

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

    @Test
    public void getBookingId(){
        Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking/1629");
        System.out.println(response.asString());
        System.out.println(response.getBody().asString());
        System.out.println((response.getStatusCode()));
        System.out.println(response.getStatusLine());
        System.out.println(response.getHeader("content-type"));
        System.out.println((response.getTime()));
    }

    @Test
    public void getBookingIds(){
        //Arrange
       Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking");
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

    <Put> void updateBooking(){

        Map<String, Object > bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2018-01-01");
        bookingDates.put("checkout","2019-01-01");

        Map<String,Object> map = new HashMap<>();

        map.put("firstname", "Janusz");
        map.put("lastname", "Budowlaniec");
        map.put("totalprice", "5");
        map.put("depositpaid", true);
        map.put("bookingdates",bookingDates);
        JSONObject body = new JSONObject(map);

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("cookie", "token=c8e54375a8bd7fb")
                .body(body.toJSONString())
                .put("https://restful-booker.herokuapp.com/booking/1629");

        System.out.println(response.getBody().asString());



    }

    @Test

    <Patch> void partialUpdateBooking(){
        Map<String, Object> bookingDates = new HashMap<>();
        bookingDates.put()

    }





}
