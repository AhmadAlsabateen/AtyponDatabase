package server;

import java.io.IOException;

public interface AbstractFactory<T,Q> {
    T getInstance(Q query) throws IOException, NoSuchFieldException, IllegalAccessException;
}
