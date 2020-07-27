package com.ilabquality.tasks;

import com.ilabquality.models.petstore.Pet;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.thucydides.core.annotations.Step;

public class AddNewPetToTheStore implements Task {
    private Pet pet;

    public AddNewPetToTheStore(Pet pet) {
        this.pet = pet;
    }

    public static AddNewPetToTheStore pet(Pet pet) {
        return Instrumented.instanceOf(AddNewPetToTheStore.class).withProperties(pet);
    }

    @Override
    @Step("{0} adds a new pet in a petstore")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to("pet")
                        .with(request -> request.header("Content-Type", "application/json")
                                .body(pet)
                        )

        );

        Pet petObject  = SerenityRest.lastResponse().jsonPath().getObject("",Pet.class);
        long autoGeneratedID= petObject.getId();

        pet.setId(autoGeneratedID);

    }
}
