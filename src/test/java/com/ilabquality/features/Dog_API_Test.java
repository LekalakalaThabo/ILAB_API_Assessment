package com.ilabquality.features;

import com.ilabquality.tasks.*;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.*;

@RunWith(SerenityRunner.class)
public class Dog_API_Test {
    private Actor apiTester;
    private String breedName = "bulldog";

    String baseUrl = "https://dog.ceo/api/";

    @Before
    public void user_can_call_an_API(){
        apiTester = Actor.named("Thabo").whoCan(net.serenitybdd.screenplay.rest.abilities.CallAnApi.at(baseUrl));
    }

    @Test
    @WithTags({@WithTag("dogAPI"), @WithTag("randomBreed")})
    public void Should_Verify_that_a_successful_message_is_returned_when_a_user_searches_for_random_breeds(){

        givenThat(apiTester).wasAbleTo(CallAnApi.withBaseUrl(baseUrl));

        when(apiTester).attemptsTo(SearchForRandomBreeds.withResource("breeds/list/random"));

        then(apiTester).should(seeThatResponse("Random dog breed returned",
                response -> response.statusCode(200)));
    }

    @Test
    @WithTags({@WithTag("dogAPI"), @WithTag("allBreeds")})
    public void should_verify_that_bulldog_is_on_the_list_of_all_breeds(){

        givenThat(apiTester).wasAbleTo(CallAnApi.withBaseUrl(baseUrl));

        when(apiTester).attemptsTo(SeachForAllDogBreeds.withResource("breeds/list/all"));

        then(apiTester).should(seeThatResponse("List of all breeds containing bulldog retruned",
                response -> response.statusCode(200).body(containsString("bulldog"))));
    }

    @Test
    @WithTags({@WithTag("dogAPI"), @WithTag("subBreed")})
    public void should_retrieve_all_subBreeds_for_bulldogs_and_their_respective_images(){

        givenThat(apiTester).wasAbleTo(CallAnApi.withBaseUrl(baseUrl));

        when(apiTester).attemptsTo(SearchForSubBreeds.withResource("breed/{breedName}/images", breedName));

        then(apiTester).should(seeThatResponse("SubBreeds of "+breedName+ " returned with respective images",
                response -> response.statusCode(200).body(containsString("bulldog-boston"),
                        containsString("bulldog-french"),containsString("bulldog-englis"))));
    }



}
