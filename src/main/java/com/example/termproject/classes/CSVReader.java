package com.example.termproject.classes;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CSVReader {
    private final File file;


    public CSVReader(String filePath) {
        this.file = new File(filePath);
    }

    // get file data dimensions
    public int[] getFileRowLength() throws FileNotFoundException {
        int rowLength = 1;
        Scanner reader = new Scanner(file);
        if(!reader.hasNext()) { return new int[]{0, 0};}
        int columnLength = reader.nextLine().split(",").length;
        while (reader.hasNext()) {reader.nextLine(); rowLength++;}
        reader.close();
        return new int[]{rowLength, columnLength};
     
    }
    // convert file data to 2d array and return it
    public String[][] readTo2DArray() throws FileNotFoundException{
        int[] Lengths = getFileRowLength();
        int rowLength = Lengths[0];
        int columnLength = Lengths[1];
        String[][] data = new String[rowLength][columnLength];
        int index = 0;

        Scanner reader = new Scanner(file);
        while (reader.hasNext()) {
            data[index] = reader.nextLine().split(","); 
            index++;
            }

        reader.close();

        return data;
     
    }

}
