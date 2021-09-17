package joiner;

import data.Data;
import data.JoinedData;
import file.Task2FileWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class LinkedListJoiner implements Joiner<LinkedList<Data>> {
    @Override
    public boolean join(LinkedList<Data> fromA, LinkedList<Data> fromB, FileWriter fw) {
        ListIterator<Data> iteratorA = fromA.listIterator();
        ListIterator<Data> iteratorB = fromB.listIterator();
        if (!iteratorA.hasNext() || !iteratorB.hasNext()) {
            try {
                fw.close();
            } catch (IOException e) {
                System.out.println("Ошибка в имени выходного файла");
            }
            return false;
        }
        Data dataFromA = iteratorA.next();
        Data dataFromB = iteratorB.next();
        while (iteratorA.hasNext() && iteratorB.hasNext()) {
            if (dataFromA.getId() < dataFromB.getId()) {
                dataFromA = iteratorA.next();
            } else if (dataFromA.getId() > dataFromB.getId()) {
                dataFromB = iteratorB.next();
            } else {
                int aId = dataFromA.getId();
                int counter = 0;
                while (dataFromB.getId().equals(dataFromA.getId())) {
                    Task2FileWriter.writeDataToFile(fw, new JoinedData(dataFromA.getId(), dataFromA.getValue(), dataFromB.getValue()));
                    if (!iteratorB.hasNext()) {
                        break;
                    }
                    dataFromB = iteratorB.next();
                    counter++;
                }
                dataFromA = iteratorA.next();
                if (dataFromA.getId() == aId) {
                    for (int i = 0; i < counter + 1; i++) {
                        iteratorB.previous();
                    }
                    dataFromB = iteratorB.next();
                }
                if (!iteratorA.hasNext() && dataFromA.getId() >= dataFromB.getId()) {
                    while (dataFromA.getId() > dataFromB.getId() && iteratorB.hasNext()) {
                        dataFromB = iteratorB.next();
                    }
                    while (dataFromA.getId().equals(dataFromB.getId())) {
                        Task2FileWriter.writeDataToFile(fw, new JoinedData(dataFromA.getId(), dataFromA.getValue(), dataFromB.getValue()));
                        if (!iteratorB.hasNext()) {
                            break;
                        }
                        dataFromB = iteratorB.next();
                    }
                }
                if (!iteratorB.hasNext() && dataFromB.getId().equals(dataFromA.getId())) {
                    while (dataFromB.getId().equals(dataFromA.getId())) {
                        Task2FileWriter.writeDataToFile(fw, new JoinedData(dataFromA.getId(), dataFromA.getValue(), dataFromB.getValue()));
                        if (!iteratorA.hasNext()) {
                            break;
                        }
                        dataFromA = iteratorA.next();
                    }
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
