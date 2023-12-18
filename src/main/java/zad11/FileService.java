package main.java.zad11;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class FileService
{
    private final String fileName = "data.csv";

    List<Data> readAllFile() throws IOException
    {
        return Files.readAllLines(Paths.get(fileName))
                .stream()
                .map(CsvEntryConventer::parse)
                .collect(Collectors.toList());
    }

    void saveEntries(List<Data> entries) throws IOException
    {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

        for (Data data : entries) {
            writer.write(data.toString());
            writer.newLine();
        }
        writer.close();
    }

    private static class CsvEntryConventer
    {
        static Data parse(String text)
        {
            String[] split = text.split(";");
            return new Data(split[0], split[1]);
        }
    }
}
