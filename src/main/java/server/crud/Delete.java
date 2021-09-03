package server.crud;

import dto.Filter;
import dto.Query;
import services.FileAccessService;
import services.FileAccessServiceFactory;

import java.io.IOException;

public class Delete implements CrudOperation<Void>{
    Filter filter;
    String tableName;
    public Delete(Query query){
        filter= query.filter;
        tableName=query.getTable();
    }

    @Override
    public Void perform() {
        FileAccessService fileAccessService= FileAccessServiceFactory.getFileAccessService(tableName);
        try {
            fileAccessService.delete(filter);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}