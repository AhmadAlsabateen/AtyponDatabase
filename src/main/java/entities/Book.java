package entities;

import java.io.Serializable;

public class Book implements Serializable {
    private static final long serialVersionUID=3906930078076630745L;
    public long id;
    public String name;
    public String description;
    public String fileName;
    public long authorId;
    public long categeryId;

    public Book() {
    }

    public Book(long id, String name, String description, String url, long authorId, long categeryId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.fileName = url;
        this.authorId = authorId;
        this.categeryId = categeryId;
    }

    public Book(long id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFileName() {
        return fileName;
    }

    public void getFileName(String url) {
        this.fileName = url;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public long getCategeryId() {
        return categeryId;
    }

    public void setCategeryId(long categeryId) {
        this.categeryId = categeryId;
    }
}
