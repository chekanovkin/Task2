package file;

import data.JoinedData;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Task2FileWriter {
    public static FileWriter writeHeader(String file) {
        try {
            FileWriter fw = new FileWriter(file, true);
            fw.write("ID\t\tA.VALUE\t\tB.VALUE\n");
            fw.flush();
            return fw;
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        } catch (IOException e) {
            System.out.println("Ошибка в имени выходного файла");
        }
        return null;
    }

    public static void writeDataToFile(FileWriter fw, JoinedData data) {
        try {
            fw.write(data.getId() + "\t\t\t" + data.getValueA() + "\t\t\t" + data.getValueB() + "\n");
            fw.flush();
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        } catch (IOException e) {
            System.out.println("Ошибка в имени выходного файла");
        }
    }
}
