import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamBasicExamples {
  /**
   * Stream은 크게 3가지로 나뉜다.
   * 1. Stream 생성 부분
   * 2. Stream 가공 부분
   * 3. Stream에 대해서 결과 값 내는 부분
   * 이번 클래스에서는 생성 가공 결과값 내는 부분에 대해서 많은 정보들에 대해서 확인해볼 예정이다.
   */
  public static void main(String[] args) {

    sortExample2();
  }

  private static void charsSequenceExample() {
    String string = "hello java";
    IntStream stringIntStream = string.chars();
    Stream<Integer> integerStream = stringIntStream.boxed();
    Stream<Integer> sortedIntegerStream = integerStream.sorted((v1, v2) -> v2 - v1);
    Stream<String> mappedIntegerStream = sortedIntegerStream.map(ch -> String.valueOf((char) ch.intValue()));
    mappedIntegerStream.collect(Collectors.joining());


//    StringBuilder sb  = sortedIntegerStream.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append); //supplier
//    System.out.println(sb.toString());
  }
  private static <T> Stream<T> createArrayStreamInstance(T[] list) {
    return Arrays.stream(list);
  }
  private static <T> Stream<T> createCollectionStreamInstance(List<T> list) {
    return list.stream();
  }

  private static Stream<String> createStreamBuilderUsingStringList(String[] strings) {
    Stream<String> stringBuilderStream = Stream.<String>builder().add("Eric").add("Erina").build();
    return stringBuilderStream;
  }
  private void sortedExample1() {
    Stream<Integer> integerStream = IntStream.of(14, 11, 20, 39, 23)
        .sorted()
        .boxed();
    List<Integer> list = integerStream.collect(Collectors.toList());

  }

  private static void sortExample2() {
    List<String> lang =
        Arrays.asList("Java", "Scala", "Groovy", "Python", "Go", "Swift");

    Stream<String> stringStream = buildStringStream(lang);
    Stream<String> sortedStringStream = stringStream.sorted();
    System.out.println(sortedStringStream.collect(Collectors.joining(" ")));

    Stream<String> stringStream2 = buildStringStream(lang);
    Stream<String> sortedStringStream2 = stringStream2.sorted(Collections.reverseOrder()); //Comparator.ReverseOrder도 가능
    System.out.println(sortedStringStream2.collect(Collectors.toList()));

    Stream<String> stringStream3 = buildStringStream(lang);
    Stream<String> sortedStringStream3 = stringStream3.sorted((s1,s2) -> {
      return s1.length() - s2.length();
    });
    System.out.println(sortedStringStream3.collect(Collectors.joining(" ")));
  }

  private static Stream<String> buildStringStream(List<String> lang) {
    Stream<String> stringStream = lang.stream();
    return stringStream;
  }
}

public class DataProcessor {
  public static void main(String[] args) {
    List<String> rawData = Arrays.asList("1,John,25", "2,Alice,30", "3,Bob,28");

    Supplier<Stream<String>> dataSupplier = () -> rawData.stream();

    Consumer<Person> dataConsumer = person -> {
      System.out.println("Processing: " + person);
      // 여기서 데이터베이스 저장 등의 작업을 수행할 수 있습니다.
    };

    List<Person> processedData = dataSupplier.get()
        .map(DataProcessor::parsePerson)
        .peek(dataConsumer)
        .filter(person -> person.getAge() > 25)
        .collect(Collectors.toList());

    System.out.println("Processed data: " + processedData);
  }

  private static Person parsePerson(String data) {
    String[] parts = data.split(",");
    return new Person(Integer.parseInt(parts[0]), parts[1], Integer.parseInt(parts[2]));
  }
}

class Person {
  private int id;
  private String name;
  private int age;

  public Person(int id, String name, int age) {
    this.id = id;
    this.name = name;
    this.age = age;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  // 생성자, getter, setter, toString 메서드 생략
}