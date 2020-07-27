package com.ilabquality.tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.thucydides.core.annotations.Step;

public class CallAnApi implements Task {

    private String baseUrl;

    public CallAnApi(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public static CallAnApi withBaseUrl(String baseUrl) {
        return Instrumented.instanceOf(CallAnApi.class).withProperties(baseUrl);
    }

    @Override
    @Step("{0} was able to call Api with base url #baseUrl")
    public <T extends Actor> void performAs(T t) {

    }
}
