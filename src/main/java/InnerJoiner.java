import java.util.*;
import java.util.stream.Collectors;

public class InnerJoiner {

     public static List<JoinedData> joinArrays(List<Data> listA, List<Data> listB) {
          List<JoinedData> joinedDataList = new ArrayList<>();
          for (Data dataFromA : listA) {
               for (Data dataFromB : listB) {
                    if (dataFromA.getId().equals(dataFromB.getId())) {
                         joinedDataList.add(new JoinedData(dataFromA.getId(), dataFromA.getValue(), dataFromB.getValue()));
                    }
               }
          }
          return joinedDataList;
     }

     public static List<JoinedData> joinLinkedList(LinkedList<Data> listA, LinkedList<Data> listB) {
          List<JoinedData> joinedDataLinkedList = new LinkedList<>();
          ListIterator<Data> iteratorA = listA.listIterator();
          ListIterator<Data> iteratorB = listB.listIterator();
          if (!iteratorA.hasNext() || !iteratorB.hasNext()) {
               return Collections.emptyList();
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
                         joinedDataLinkedList.add(new JoinedData(dataFromA.getId(), dataFromA.getValue(), dataFromB.getValue()));
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

               }
          }
          while (dataFromB.getId().equals(dataFromA.getId())) {
               joinedDataLinkedList.add(new JoinedData(dataFromA.getId(), dataFromA.getValue(), dataFromB.getValue()));
               if (!iteratorB.hasNext()) {
                    break;
               }
               dataFromB = iteratorB.next();
          }
          return joinedDataLinkedList;
     }

     public static List<JoinedData> joinHashMap(List<Data> listA, List<Data> listB) {
          List<JoinedData> joinedDataList = new ArrayList<>();
          Map<Integer, String> mapA = listA.stream()
                  .collect(Collectors.toMap(Data::getId, Data::getValue, (v1, v2) -> v1 + ", " + v2));
          Map<Integer, String> mapB = listB.stream()
                  .collect(Collectors.toMap(Data::getId, Data::getValue, (v1, v2) -> v1 + ", " + v2));
          mapA.forEach((k, v) -> {
               if (mapB.containsKey(k)) {
                    joinedDataList.addAll(joinStrings(k, v, mapB.get(k)));
               }
          });
          return joinedDataList;
     }

     public static List<JoinedData> joinStrings(Integer id, String str1, String str2) {
          List<JoinedData> joinedDataList = new ArrayList<>();
          String[] arr1 = str1.split(", ");
          String[] arr2 = str2.split(", ");
          for (String fromArr1 : arr1) {
               for (String fromArr2 : arr2) {
                    joinedDataList.add(new JoinedData(id, fromArr1, fromArr2));
               }
          }
          return joinedDataList;
     }
}
