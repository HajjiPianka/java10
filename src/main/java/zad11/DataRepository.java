package main.java.zad11;

import java.io.IOException;
import java.util.*;

public class DataRepository
{
    private List<Data> entries;

    DataRepository()
    {
        FileService fileService = new FileService();

        try
        {
            this.entries = fileService.readAllFile();
        }

        catch(IOException e)
        {
            entries = new ArrayList<>();
        }
    }

    List<Data> getAll()
    {
        return entries;
    }

    Set<Data> getRandomEntries(int number)
    {
        Random random = new Random();
        Set<Data> randomEntries = new HashSet<>();

        while(randomEntries.size() < number && randomEntries.size() < entries.size())
        {
            randomEntries.add(entries.get(random.nextInt(entries.size())));
        }
        return randomEntries;
    }

    void add(Data data)
    {
        entries.add(data);
    }

    int size()
    {
        return entries.size();
    }

    boolean isEmpty()
    {
        return entries.isEmpty();
    }
}
