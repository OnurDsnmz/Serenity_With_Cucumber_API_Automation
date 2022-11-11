package steps;

import api.VotesAPI;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.*;


import net.serenitybdd.screenplay.rest.interactions.Get;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.thucydides.core.annotations.Steps;


public class VotesSteps {
    Actor onur = Actor.named("Onur");
    String key;

    @Steps
    VotesAPI votesAPI;

    @Given("x-api-key and baseURI are already acquired.")
    public void x_api_key_and_base_uri_are_already_acquired() {
        key = "YOUR_API_KEY";
    }

    @When("Onur POST a create {string} with image_id : {string} for sub_id : {string}")
    public void actor_makes_post_request(String endpoint, String image_id ,String sub_id){
        onur.whoCan(CallAnApi.at(votesAPI.invoke_my_webservice()));

         onur.attemptsTo(Post.to(endpoint).with(request -> request.headers("x-api-key",key).headers("Content-Type","application/json")
                .body(String.format("{\"image_id\": \"%s\",\"sub_id\": \"%s\",\"value\": \"1\" }",image_id,sub_id))));

    }

    @Then("Onur see response has {int} status code")
    public void actor_see_response_status_code(int code){
        onur.should(
                seeThatResponse("response for votes should return",
                        response -> response.statusCode(code).body("message",is("SUCCESS")))
        );
    }

    @When("Onur see listing {string} for {string}")
    public void actor_makes_get_request(String endpoint, String sub_id){
        onur.whoCan(CallAnApi.at(votesAPI.invoke_my_webservice()));

        onur.attemptsTo(Get.resource(endpoint).with(
                request -> request.header("x-api-key",key).param("sub_id",sub_id)));
    }

    @Then("Onur see {string} for sub_id has {int} status code")
    public void actor_see_sub_id(String sub_id, int code) {
        onur.should(
                seeThatResponse("all the votes should be returned for user",
                        response -> response.statusCode(code).body("sub_id",hasItem(sub_id))
                        )
        );
    }
    @When("Onur see all {string} list")
    public void actor_see_all_vote_list(String endpoint) {
        onur.whoCan(CallAnApi.at(votesAPI.invoke_my_webservice()));

        onur.attemptsTo(Get.resource(endpoint).with(request -> request.header("x-api-key",key)));
    }

    @Then("Onur see all list has {int} status code")
        public void actor_see_response(int code){
        onur.should(
                seeThatResponse("all the votes should be returned",
                        response -> response.statusCode(code).body("id",notNullValue())
                        )
        );
        }
}
