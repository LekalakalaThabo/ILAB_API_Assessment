package com.ilabquality.tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.thucydides.core.annotations.Step;

public class SearchNewlyCreatedPet implements Task {
    private long petID;

    public SearchNewlyCreatedPet(Long petID) {
        this.petID = petID;
    }


    public static SearchNewlyCreatedPet byID(long petID) {
        return Instrumented.instanceOf(SearchNewlyCreatedPet.class).withProperties(petID);
    }

    @Override
    @Step("{0} searches for a pet using the id: #petID")
    public <T extends Actor> void performAs(T actor) {
         actor.attemptsTo(
               Get.resource("pet/{petId}").with(request -> request.pathParam("petId",petID))
        );

    }
}
