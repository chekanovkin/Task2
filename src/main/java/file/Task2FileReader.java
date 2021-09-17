package file;

import data.Data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Task2FileReader {

    public static ArrayList<Data> readFile(String file) {
        String[] data;
        ArrayList<Data> dataList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            int strNum = 1;
            while (line != null) {
                data = line.split(", ");
                try {
                    dataList.add(new Data(Integer.parseInt(data[0]), data[1]));
                } catch (IndexOutOfBoundsException ex) {
                    System.out.println("Неверное количество данных в строке "
                            + strNum + " (ожидается 2, найдено " + data.length + ")");
                }
                line = br.readLine();
                strNum++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Входной файл " + file + " не найден");
            return new ArrayList<>(0);
        } catch (IOException e) {
            System.out.println("Ошибка при чтении данных из файла " + file);
            return new ArrayList<>(0);
        }
        return dataList;
    }
}
