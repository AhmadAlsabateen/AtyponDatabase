package server.crud;

import dto.Input;
import dto.Query;
import entities.EntityFactory;
import services.FileAccessService;
import services.FileAccessServiceFactory;

import java.io.IOException;
import java.io.Serializable;

public class Create implements CrudOperation<Void> {

    String tableName;
    Input input;

    public Create(Query query) throws IOException, NoSuchFieldException, IllegalAccessException {

        tableName = query.getTable();
        input = query.getInput();


    }

    @Override
    public Void perform() throws IOException, NoSuchFieldException, IllegalAccessException {
        Object record = EntityFactory.getEntity(tableName);
        FileAccessService fileAccessService = FileAccessServiceFactory.getFileAccessService(tableName);
        fileAccessService.create(input, (Serializable) record);

        return null;

    }
}
