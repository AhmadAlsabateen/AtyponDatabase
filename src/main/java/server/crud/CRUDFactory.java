package server.crud;

import dto.Query;
import server.AbstractFactory;
import server.crud.CrudOperation;

import java.io.IOException;

public class CRUDFactory implements AbstractFactory<CrudOperation, Query> {

    @Override
    public CrudOperation getInstance(Query query) throws IOException, NoSuchFieldException, IllegalAccessException {
            switch (query.getType()){
                case "create":
                    return new Create(query);
                case "update":
                    System.out.println("factory crud");
                    return new Update(query);

                case "retrieve":
                    return new Retrieve(query);
                case "delete":
                    return new Delete(query);
                default:
                    return null;
            }
    }
}
