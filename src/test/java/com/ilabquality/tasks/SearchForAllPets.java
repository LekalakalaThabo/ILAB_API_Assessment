package com.ilabquality.tasks;

import com.ilabquality.models.petstore.Pet;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

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

        AtomicBoolean found = new AtomicBoolean(false);
        List<Pet> petObjects  = SerenityRest.lastResponse().jsonPath().getList("",Pet.class);
        Iterator<Pet> petListIterator =  petObjects.iterator();

        while(petListIterator.hasNext() && found.get() == false){
            Pet pet = petListIterator.next();

            Optional.of(pet).map(Pet::getCategory).ifPresent(category -> {

                if(category.getId().equals(BigInteger.valueOf(12)) && pet.getName().equalsIgnoreCase("doggie")){
                    found.set(true);
                   }
            } );

        }

       if(found.get() == true){
            Assert.assertTrue("Pet named doggy with category ID is on the list", found.get());
       }else
            Assert.assertTrue("Pet named doggy with category ID is not on the list", found.get());

    }
}
