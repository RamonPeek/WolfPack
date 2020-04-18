package nl.ramonpeek.models;

import java.util.List;

public class Pack {

    private int id;
    private String name;
    private List<Wolf> wolves;

    public Pack() {}

    public Pack(int id, String name, List<Wolf> wolves) {
        this.id = id;
        this.name = name;
        this.wolves = wolves;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Wolf> getWolves() {
        return wolves;
    }

    public void setWolves(List<Wolf> wolves) {
        this.wolves = wolves;
    }
}
