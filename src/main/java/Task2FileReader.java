import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Task2FileReader {

    public static List<Data> readFile(String file) {
        String[] data;
        List<Data> dataList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            while (line != null) {
                data = line.split(",");
                dataList.add(new Data(Integer.parseInt(data[0]), data[1]));
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Входной файл " + file + " не найден");
            return Collections.emptyList();
        } catch (IOException e) {
            System.out.println("Ошибка при чтении данных из файла " + file);
            return Collections.emptyList();
        }
        return dataList;
    }
}
