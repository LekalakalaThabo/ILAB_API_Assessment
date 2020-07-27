package com.ilabquality.tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.thucydides.core.annotations.Step;

public class SearchForSubBreeds implements Task {
    private String resourceUrl;
    private String breedName;

    public SearchForSubBreeds(String resourceUrl, String breedName) {
        this.resourceUrl = resourceUrl;
        this.breedName = breedName;
    }

    public static SearchForSubBreeds withResource(String resourceUrl, String breedName) {
        return Instrumented.instanceOf(SearchForSubBreeds.class).withProperties(resourceUrl,breedName);

    }

    @Override
    @Step("{0} searches for sub breeds of #breedName")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource(resourceUrl).with(request -> request.pathParam("breedName",breedName))
        );

    }
}
