package joiner;

import data.Data;
import data.JoinedData;
import file.Task2FileWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ArrayListJoiner implements Joiner<ArrayList<Data>> {

    @Override
    public boolean join(ArrayList<Data> fromA, ArrayList<Data> fromB, FileWriter fw) {
        if (fromA.isEmpty() || fromB.isEmpty()) {
            try {
                fw.close();
            } catch (IOException e) {
                System.out.println("Ошибка в имени выходного файла");
            }
            return false;
        }
        for (Data dataFromA : fromA) {
            for (Data dataFromB : fromB) {
                if (dataFromA.getId().equals(dataFromB.getId())) {
                    Task2FileWriter.writeDataToFile(fw, new JoinedData(dataFromA.getId(), dataFromA.getValue(), dataFromB.getValue()));
                }
            }
        }
        try {
            fw.close();
        } catch (IOException e) {
            System.out.println("Ошибка в имени выходного файла");
        }
        return true;
    }
}
