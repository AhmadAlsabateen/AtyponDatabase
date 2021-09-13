package services;

import entities.Author;
import entities.Book;
import entities.Category;
import entities.User;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class FileAccessServiceFactory {

    private static Map<String, FileAccessService> serviceMap = new HashMap<>();

    public static FileAccessService getFileAccessService(String fileName) {
        if (serviceMap.containsKey(fileName)) {
            return serviceMap.get(fileName);
        } else {
            try {
                FileAccessService fileAccessService = null;
                switch (fileName) {
                    case "books":
                        fileAccessService = new FileAccessService<Book>(fileName + ".txt", 500);
                        break;
                    case "authors":
                        fileAccessService = new FileAccessService<Author>(fileName + ".txt", 300);
                    case "users":
                        fileAccessService = new FileAccessService<User>(fileName + ".txt", 300);
                    case "categories":
                        fileAccessService = new FileAccessService<Category>(fileName + ".txt", 300);

                }
                serviceMap.put(fileName, fileAccessService);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return serviceMap.get(fileName);
        }

    }

}
