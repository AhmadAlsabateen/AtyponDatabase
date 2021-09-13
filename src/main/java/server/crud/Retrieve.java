package server.crud;

import dto.Filter;
import dto.Query;
import services.FileAccessService;
import services.FileAccessServiceFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Retrieve implements CrudOperation<List>  {
    String tableName;
    Filter filter;

    public Retrieve(Query query){
        tableName=query.getTable();
        filter=query.getFilter();
    }
    @Override
    public List perform() {
        ArrayList result = null;

        try {
            FileAccessService fileAccessService= FileAccessServiceFactory.getFileAccessService(tableName);
            result= (ArrayList) fileAccessService.get(filter);

        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }
        return result; }}
