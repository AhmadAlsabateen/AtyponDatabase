package server.crud;


import dto.Filter;
import dto.Input;
import dto.Query;
import services.FileAccessService;
import services.FileAccessServiceFactory;

import java.io.IOException;

public class Update implements CrudOperation<Void>{
    Filter filter;
    Input input;
    String tableName;

    public Update(Query query){
        filter= query.filter;
        tableName=query.getTable();
        input=query.input;
        System.out.println("update");
    }
    @Override
    public Void perform() {

        FileAccessService fileAccessService= FileAccessServiceFactory.getFileAccessService(tableName);
        try {
            fileAccessService.update(input,filter);

        } catch (IOException |ClassNotFoundException |NoSuchFieldException e) {
            e.printStackTrace();
        }

        return null;

    }
}
