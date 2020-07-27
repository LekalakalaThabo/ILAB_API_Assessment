package com.ilabquality.tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.thucydides.core.annotations.Step;

public class SearchForRandomBreeds implements Task {
    private String resourceUrl;

    public SearchForRandomBreeds (String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }



    public static SearchForRandomBreeds  withResource(String resourceUrl) {
        return Instrumented.instanceOf(SearchForRandomBreeds .class).withProperties(resourceUrl);
    }

    @Override
    @Step("{0} searches for for a random dog breed")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource(resourceUrl)
        );
        
    }
}
