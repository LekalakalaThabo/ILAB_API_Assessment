package com.ilabquality.models.petstore;

import java.math.BigInteger;
import java.util.Collection;


public class Pet {
    private BigInteger id;
    private Category category;
    private String name;
    private Collection<String> photoUrls;
    private Collection<Tag>  tags;
    public AvailabilityStatus status;

    public Pet( Category category, String name, Collection<String> photoUrls, Collection<Tag> tags, AvailabilityStatus status) {
        //this.id = id;
        this.category = category;
        this.name = name;
        this.photoUrls = photoUrls;
        this.tags = tags;
        this.status = status;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<String> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(Collection<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    public Collection<Tag> getTags() {
        return tags;
    }

    public void setTags(Collection<Tag> tags) {
        this.tags = tags;
    }
}
