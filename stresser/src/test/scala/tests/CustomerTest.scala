package tests

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class CustomerTest extends Simulation {

  val httpConf = http
    .baseURL("http://localhost:8080/service/customers")
    .acceptHeader("text/html,application/json;q=0.9,*/*;q=0.8") // Here are the common headers
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")

  val headers_10 = Map("Content-Type" -> """application/x-www-form-urlencoded""") // Note the headers specific to a given request

  val scn = scenario("Customer REST-API tests") // A scenario is a chain of requests and pauses
    .exec(http("request_1")
      .get("/"))
    .pause(1) // Note that Gatling has recorder real time pauses
    .exec(http("request_2")
      .post("/")
      .body(StringBody("""{"name":"Testi-Tero"}"""))
      .asJSON
      .check(regex(""""link.*"""")
        .find(0)
        .transform(s => s.split("\"")(3).split("/")(2))
        .saveAs("newCustomer")))
    .exec(http("request_3_" + "${newCustomer}")
      .get("/" + "${newCustomer}"))

  setUp(scn.inject(atOnceUsers(50)).protocols(httpConf))
}