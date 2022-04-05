package mx.com.percont.hotelling.bookings;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class BookingServiceIntegrationTest {

    private final static String NAME_PARAMETER = "name";

    @BeforeClass
    public static void doClassSetup() {
        // TODO proveer la url base a partir de parámetros de ambiente.
        RestAssured.baseURI = "https://nodejs-rest-sample.herokuapp.com";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @Test
    public void getAllBookingsTest() {
        get("/bookings").then().statusCode(200).contentType(JSON);
    }

    @Test
    public void getAllBookingsFilteredByNameTest(){
        String name = "e";
        given()
                .queryParam(NAME_PARAMETER, name)
                .basePath("/bookings")
        .when()
                .get()
        .then().
                assertThat()
                .statusCode(200).and()
                .contentType(JSON);
    }
}
