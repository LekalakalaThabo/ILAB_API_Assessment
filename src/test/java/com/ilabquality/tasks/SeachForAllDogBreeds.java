package com.ilabquality.tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.thucydides.core.annotations.Step;

public class SeachForAllDogBreeds implements Task {

    private String resourceUrl;


    public SeachForAllDogBreeds(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public static SeachForAllDogBreeds withResource(String resourceUrl) {
        return Instrumented.instanceOf(SeachForAllDogBreeds.class).withProperties(resourceUrl);
    }

    @Override
    @Step("{0} searched for all dog breeds")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Get.resource(resourceUrl));

    }
}
