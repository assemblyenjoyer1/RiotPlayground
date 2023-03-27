package org.example.utils.models;

import java.util.ArrayList;

public class Root {

    public String id;
    public String object;
    public int created;
    public String model;
    public Usage usage;
    public ArrayList<Choice> choices;

    public Root(String id, String object, int created, String model, Usage usage, ArrayList<Choice> choices) {
        this.id = id;
        this.object = object;
        this.created = created;
        this.model = model;
        this.usage = usage;
        this.choices = choices;
    }

    public String getId() {
        return id;
    }

    public String getObject() {
        return object;
    }

    public int getCreated() {
        return created;
    }

    public String getModel() {
        return model;
    }

    public Usage getUsage() {
        return usage;
    }

    public ArrayList<Choice> getChoices() {
        return choices;
    }
}
