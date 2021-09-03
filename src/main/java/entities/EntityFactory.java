package entities;

public class EntityFactory {

    public static Object getEntity(String type){

        switch (type){

            case "authors":
                return new Author();
            case "books":
                return new Book();
            case "categories":
                return new Category();
            case "users":
                return new User();

            default:
                return null;
        }

    }
}
