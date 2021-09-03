package server.crud;

import java.io.IOException;

public interface CrudOperation<T> {
   public T perform() throws IOException, NoSuchFieldException, IllegalAccessException;
}
