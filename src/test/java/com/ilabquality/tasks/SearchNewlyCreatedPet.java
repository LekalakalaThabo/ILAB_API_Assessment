package com.ilabquality.tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.thucydides.core.annotations.Step;

import java.math.BigInteger;

public class SearchNewlyCreatedPet implements Task {
    private BigInteger petID;

    public SearchNewlyCreatedPet(BigInteger petID) {
        this.petID = petID;
    }


    public static SearchNewlyCreatedPet byID(BigInteger petID) {
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
