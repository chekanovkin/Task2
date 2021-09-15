import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Task2FileWriter {
    public static void writeToFile(String file, List<JoinedData> data) {
        try (FileWriter fw = new FileWriter(file, true)) {
            fw.write("ID\t\tA.VALUE\t\tB.VALUE\n");
            for (JoinedData joinedData : data) {
                fw.write(joinedData.getId() + "\t\t\t" + joinedData.getValueA() + "\t\t\t"
                        + joinedData.getValueB() + "\n");
            }
            fw.flush();
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        } catch (IOException e) {
            System.out.println("Ошибка в имени выходного файла");
        }
    }
}
