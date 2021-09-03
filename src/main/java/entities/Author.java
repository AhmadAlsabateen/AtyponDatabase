package entities;

import java.io.Serializable;

public class Author implements Serializable {
    public long id;
    public String name;

    public Author(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Author() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
