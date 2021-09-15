import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        if (args.length < 5) {
            System.out.println("Неверное количество аргументов командной строки (ожидается 5, найдено " + args.length + ")");
        }
        List<Data> listA = Task2FileReader.readFile(args[0]);
        List<Data> listB = Task2FileReader.readFile(args[1]);

        List<JoinedData> joinedDataArrayList = InnerJoiner.joinArrays(listA, listB);

        LinkedList<Data> linkedListA = listA.stream()
                .sorted(Comparator.comparing(Data::getId).thenComparing(Data::getValue))
                .collect(Collectors.toCollection(LinkedList::new));

        LinkedList<Data> linkedListB = listB.stream()
                .sorted(Comparator.comparing(Data::getId).thenComparing(Data::getValue))
                .collect(Collectors.toCollection(LinkedList::new));

        List<JoinedData> joinedDataLinkedList = InnerJoiner.joinLinkedList(linkedListA, linkedListB);

        List<JoinedData> joinedDataHashMap = InnerJoiner.joinHashMap(listA, listB);

        Task2FileWriter.writeToFile(args[2], joinedDataArrayList);
        Task2FileWriter.writeToFile(args[3], joinedDataLinkedList);
        Task2FileWriter.writeToFile(args[4], joinedDataHashMap);
    }
}
