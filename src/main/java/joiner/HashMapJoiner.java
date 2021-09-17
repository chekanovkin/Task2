package joiner;

import data.JoinedData;
import file.Task2FileWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HashMapJoiner implements Joiner<HashMap<Integer, List<String>>> {

    @Override
    public boolean join(HashMap<Integer, List<String>> fromA, HashMap<Integer, List<String>> fromB, FileWriter fw) {
        if (fromA.isEmpty() || fromB.isEmpty()) {
            try {
                fw.close();
            } catch (IOException e) {
                System.out.println("Ошибка в имени выходного файла");
            }
            return false;
        }
        fromA.forEach((k, v) -> {
            if (fromB.containsKey(k)) {
                joinStrings(k, v, fromB.get(k), fw);
            }
        });
        try {
            fw.close();
        } catch (IOException e) {
            System.out.println("Ошибка в имени выходного файла");
        }
        return true;
    }

    public static List<JoinedData> joinStrings(Integer id, List<String> arr1, List<String> arr2, FileWriter fw) {
        List<JoinedData> joinedDataList = new ArrayList<>();
        for (String fromArr1 : arr1) {
            for (String fromArr2 : arr2) {
                Task2FileWriter.writeDataToFile(fw, new JoinedData(id, fromArr1, fromArr2));
            }
        }
        return joinedDataList;
    }
}
