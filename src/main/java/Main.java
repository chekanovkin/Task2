import data.Data;
import data.JoinedData;
import file.Task2FileReader;
import file.Task2FileWriter;
import joiner.ArrayListJoiner;
import joiner.HashMapJoiner;
import joiner.LinkedListJoiner;

import java.io.FileWriter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static ArrayListJoiner arrayListJoiner = new ArrayListJoiner();
    static LinkedListJoiner linkedListJoiner = new LinkedListJoiner();
    static HashMapJoiner hashMapJoiner = new HashMapJoiner();

    public static void main(String[] args) {
        if (args.length < 5) {
            System.out.println("Неверное количество аргументов командной строки (ожидается 5, найдено " + args.length + ")");
        } else {
            ArrayList<Data> listA = Task2FileReader.readFile(args[0]);
            ArrayList<Data> listB = Task2FileReader.readFile(args[1]);

            FileWriter arrayFW = Task2FileWriter.writeHeader(args[2]);
            FileWriter listFW = Task2FileWriter.writeHeader(args[3]);
            FileWriter mapFW = Task2FileWriter.writeHeader(args[4]);

            arrayListJoiner.join(listA, listB, arrayFW);

            LinkedList<Data> linkedListA = listA.stream()
                    .sorted(Comparator.comparing(Data::getId).thenComparing(Data::getValue))
                    .collect(Collectors.toCollection(LinkedList::new));

            LinkedList<Data> linkedListB = listB.stream()
                    .sorted(Comparator.comparing(Data::getId).thenComparing(Data::getValue))
                    .collect(Collectors.toCollection(LinkedList::new));

            linkedListJoiner.join(linkedListA, linkedListB, listFW);

            HashMap<Integer, List<String>> hashMapA = (HashMap<Integer, List<String>>) listA.stream()
                    .collect(Collectors.groupingBy(Data::getId, Collectors.mapping(Data::getValue, Collectors.toList())));

            HashMap<Integer, List<String>> hashMapB = (HashMap<Integer, List<String>>) listB.stream()
                    .collect(Collectors.groupingBy(Data::getId, Collectors.mapping(Data::getValue, Collectors.toList())));

            hashMapJoiner.join(hashMapA, hashMapB, mapFW);
        }
    }
}
