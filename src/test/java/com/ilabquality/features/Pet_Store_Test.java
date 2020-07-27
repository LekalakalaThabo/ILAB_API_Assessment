package com.ilabquality.features;

import com.ilabquality.models.petstore.AvailabilityStatus;
import com.ilabquality.models.petstore.Category;
import com.ilabquality.models.petstore.Pet;
import com.ilabquality.models.petstore.Tag;
import com.ilabquality.tasks.*;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.SimpleDateFormat;
import java.util.*;

import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.*;


@RunWith(SerenityRunner.class)
public class Pet_Store_Test {
    private Actor apiTester;

    private Pet pet;

    private String name = "Doggie_No_"+ new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
    private String categoryName = "category" + new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
    private int categoryID = 12345;

    private String status = "available";
    private String breedName = "bulldog";

    String baseUrl = "https://petstore.swagger.io/v2/";

    @Before
    public void user_can_call_an_API(){
        Collection<String> photoUrls = new ArrayList<>();
        photoUrls.add("https://images.dog.ceo/breeds/bulldog-boston/n02096585_10452.jpg");
        photoUrls.add("https://images.dog.ceo/breeds/bulldog-boston/n02096585_10734.jpg");

        Collection<Tag> tags = new ArrayList<>();

        tags.add(new Tag(1,"tag1"));
        tags.add(new Tag(2,"tag2"));

        pet = new Pet(new Category(categoryID,categoryName),name,photoUrls,tags, AvailabilityStatus.available);

        apiTester = Actor.named("Thabo").whoCan(net.serenitybdd.screenplay.rest.abilities.CallAnApi.at(baseUrl));
    }

    @Test
    @WithTags({@WithTag("petStore"), @WithTag("getPetByStatus")})
    public void should_retrieve_all_available_pets_and_confirm_that_the_name_doggie_with_category_id_12_is_on_the_list(){
        givenThat(apiTester).wasAbleTo(CallAnApi.withBaseUrl(baseUrl));

        when(apiTester).attemptsTo(SearchForAllPets.withResource("pet/findByStatus",status));

        then(apiTester).should(seeThatResponse("List of Available pets should be returned with a dog names \"Doggy\" with catefory id 12",
                response -> response.statusCode(200)
                        //.body(hasItem("doggie"),"catefory.id",hasItem(12))
                        .body("name", hasItems("doggie") ,"category.id", hasItem(12))
        ));
    }

    @Test
    @WithTags({@WithTag("petStore"), @WithTag("createNewPet")})
    public void should_add_a_new_pet_with_an_auto_generated_name_and_status_available_Confirm_the_new_pet_has_been_added(){

        givenThat(apiTester).wasAbleTo(CallAnApi.withBaseUrl(baseUrl));

        when(apiTester).attemptsTo(AddNewPetToTheStore.pet(pet));

        then(apiTester).should(seeThatResponse("New pet with name "+ pet.getName() +" and ID " + pet.getId() +
                        " created", response -> response.statusCode(200).body("name",
                containsString(pet.getName()))
        ));
    }

    @Test
    @WithTags({@WithTag("petStore"), @WithTag("getCreatePetByID")})
    public void should_retrieve_the_created_pet_using_the_ID(){

        givenThat(apiTester).wasAbleTo(AddNewPetToTheStore.pet(pet));

        when(apiTester).attemptsTo(SearchNewlyCreatedPet.byID(pet.getId()));

        then(apiTester).should(seeThatResponse("New pet with name "+ pet.getName() +" and ID " + pet.getId() +
                " retrieved",
                response -> response.statusCode(200).body("name", containsString(pet.getName()))
        ));
    }



}
