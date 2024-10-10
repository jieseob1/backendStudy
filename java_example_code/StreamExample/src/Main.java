import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
  static String skill = "CBD";
  static String[] skill_trees = {"BACDE", "CBADF", "AECB", "BDA"};

  public static void main(String[] args) {
//    List<String> list = Arrays.asList("a", "b", "c");
//    System.out.println(list);
//
//    Stream<String> streamFromList = list.stream();
//    System.out.println("streamFromList => " + streamFromList);
//
//    // 배열에서 스트림 생성
//    String[] array = {"x", "y", "z"};
//    Stream<String> streamFromArray = Arrays.stream(array); // Arrays.stream으로 만듬
//
//    Stream<String> directStream = Stream.of("one", "two", "three");
//
//    directStream.forEach(System.out::println);
//
////    middleCalculation();
//    catStreamExample();
    solutionExample();
  }

  private static void solutionExample() {
    int arr = (int) Arrays.stream(skill_trees).map(s -> s.replaceAll("[^ " + skill + "]", "")).filter(skill::startsWith).count();

  }
  private static void middleCalculation() {
    //중간 연산을 위한 메소드
    //sorted, filtered, map 등이 있다.
    filteredExample();
  }

  private static void sortedExample() {
  }

  private static void filteredExample() {
    List<String> list = Arrays.asList("apple", "banana", "orange", "pineApple");
    List<String> result = list.stream().filter(s -> s.startsWith("p")) //필터링
        .map(String::toUpperCase) // 대문자로 변경
        .sorted()
        .collect(Collectors.toList()); // 결과 수집
    System.out.println("필터 결과 => " + result);
  }

  private static void catStreamExample() {
    List<Cat> cats = Arrays.asList(
        new Cat(2, "sana"),
        new Cat(1, "momo"),
        new Cat(4, "mina"),
        new Cat(3, "jihyo")
    );

    //1.getNumber로 정순
    cats.stream()
        .sorted(Comparator.comparing(Cat::getNumber)) // 정렬
        .forEach(cat -> System.out.println(cat.toString()));
  }
}