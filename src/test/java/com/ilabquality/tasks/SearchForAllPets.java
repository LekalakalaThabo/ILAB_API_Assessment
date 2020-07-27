package com.ilabquality.tasks;

import com.ilabquality.models.petstore.Pet;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.rest.Ensure;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class SearchForAllPets implements Task {
    private String resourceUrl;
    private String status;

    public SearchForAllPets(String resourceUrl, String status) {
        this.resourceUrl = resourceUrl;
        this.status = status;
    }

    public static SearchForAllPets withResource(String resourceUrl, String status) {
        return Instrumented.instanceOf(SearchForAllPets.class).withProperties(resourceUrl,status);
    }

    @Override
    @Step("{0} searched for all #status pets")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource(resourceUrl).with(request -> request.queryParam("status",status) )
        );
/*
        boolean found = false;
        List<Pet> petObjects  = SerenityRest.lastResponse().jsonPath().getList("",Pet.class);

        Iterator<Pet> petListIterator =  petObjects.iterator();

        while(petListIterator.hasNext() && found == false){
            System.out.println("Object: "+petListIterator.next().toString());

            if(petListIterator.next().getCategory().getId()==12
                    && petListIterator.next().getName().equalsIgnoreCase("doggie")){
                found = true;
            }
            //System.out.println("Found: "+ found+" DogName: " + petListIterator.next().getName() +" CatID :" +petListIterator.next().getId());
        }

        if(found){
            Assert.assertTrue("Pet named doggy with category ID is on the list",found);
        }else
            Assert.assertTrue("Pet named doggy with category ID is not on the list",found);

*/



    }
}
