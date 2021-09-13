package services;

import dto.Filter;
import dto.Input;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class FileAccessService<T extends Serializable> {
    private ReentrantReadWriteLock lock;
    private int recordSize;
    private RandomAccessFile file ;


     FileAccessService(String fileName, int recordSize) throws FileNotFoundException {
         file = new RandomAccessFile(fileName, "rw");
         lock = new ReentrantReadWriteLock();
         this.recordSize = recordSize;
    }



    public void create(Input input,T record) throws IOException, NoSuchFieldException, IllegalAccessException {
         lock.writeLock().lock();

        long id = getNumOfRecords()+1;
        Field field = record.getClass().getDeclaredField("id");
        field.set(record,id);
        for (String key : input.fields.keySet()) {
            field = record.getClass().getDeclaredField(key);
            try {
                field.set(record, input.fields.get(key));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        file.seek(file.length());
        file.write(serializeToBytes(record));
        lock.writeLock().unlock();
    }

    public List<T> get(Filter filter) throws IOException, ClassNotFoundException, InterruptedException {

        lock.readLock().lock();
        file.seek(0);

        List<T> result = new ArrayList<>();
        file.seek(0);

        for (int i = 0; i < file.length() / recordSize; i++) {
            byte[] data = new byte[recordSize];
            file.read(data, 0, recordSize);
            boolean isEmpty = Arrays.equals(data, new byte[recordSize]);
            if (isEmpty) {
                continue;
            }
            ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(data));
            T record = (T) inputStream.readObject();
            inputStream.close();
            if (isApplicable(filter, record)) {
                result.add(record);
            }
        }
        lock.readLock().unlock();
        return result;
    }

    public void update(Input input, Filter filter) throws IOException, ClassNotFoundException, NoSuchFieldException {
        lock.writeLock().lock();
        file.seek(0);
        for (int i = 0; i < file.length() / recordSize; i++) {
            byte[] data = new byte[recordSize];
            file.read(data, 0, recordSize);
            boolean isEmpty = Arrays.equals(data, new byte[recordSize]);
            if (isEmpty) {
                System.out.println("empty");
                continue;
            }
            ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(data));
            T record = (T) inputStream.readObject();
            System.out.println(record.getClass().getTypeName());
            inputStream.close();
            if (isApplicable(filter, record)) {
                for (String key : input.fields.keySet()) {
                    Field field = record.getClass().getDeclaredField(key);
                    try {
                        field.set(record, input.fields.get(key));
                        System.out.println("updated");
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                file.seek(file.getFilePointer()-recordSize);
                file.write(serializeToBytes(record));
            }
        }
        lock.writeLock().unlock();
    }

    public void delete(Filter filter) throws IOException, ClassNotFoundException {
        lock.writeLock().lock();
        file.seek(0);
        for (int i = 0; i < file.length() / recordSize; i++) {
            byte[] data = new byte[recordSize];
            file.read(data, 0, recordSize);
            boolean isEmpty = Arrays.equals(data, new byte[recordSize]);
            if (isEmpty) {
                continue;
            }
            ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(data));
            T record = (T) inputStream.readObject();
            inputStream.close();
            if (isApplicable(filter, record)) {
                file.seek(file.getFilePointer()-recordSize);
                file.write(new byte[recordSize]);
            }
        }
        lock.writeLock().unlock();
    }

    private byte[] serializeToBytes(T record) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(record);
        byte[] data = new byte[recordSize];
        System.arraycopy(bos.toByteArray(), 0, data, 0, bos.toByteArray().length);
        return data;
    }

    private boolean isApplicable(Filter filter, T record)  {
                for (String key : filter.fields.keySet()){
                    try {
                        Field field = record.getClass().getDeclaredField(key);
                        String recordVlaue = field.get(record).toString();
                        if (!recordVlaue.equalsIgnoreCase(filter.fields.get(key).toString())){

                            System.out.println("not apll");
                            return false;
                        }
                    } catch (NoSuchFieldException |IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
        return true;
    }
    public long getNumOfRecords() throws IOException {
         return file.length()/recordSize;
    }

}
