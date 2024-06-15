import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    System.out.println("Hello world!");
    splitExample();
  }

  //replaceAll(String regExp, String replacement), matches(String regExp), split(String regExp)
  private void matchesExample() {
    String user = "user";
    System.out.println(user.matches("[a-z]*"));; // true
    //a부터z사이 문자들 중 0개 이상
  }

  private static void splitExample() {
    String user = "a1b2c3d4e5f6g7h8i9j0";
    String[] split = user.split("[a-z]"); // [1, 2, 3, 4, 5, 6, 7, 8, 9, 0]
    String[] split2 = user.split("[0-9]"); // [a, b, c, d, e, f, g, h, i, j]
    for (String s : split) {
      System.out.println(s); // 1 2 3 4 5 6 7 8 9 0
    }
    //a부터z사이 문자들을 기준으로 나눔
  }
  private void replaceExample() {
    String user = "...!@BaT#*..y.abcdefghijklm";
    user = user.replaceAll("[^a-z0-9.-_]",""); //bat.y.abcdefghijklm
    //a부터z사이 문자들을 a로 바꿈
  }

  private void replaceAllExample() {
    String user = "...!@BaT#*..y.abcdefghijklm";
    user = user.replaceAll("[^a-z0-9\\.-_]", "");// 백 스페이스 두번인 이유는 모르겠음
    user = user.replaceAll("^\\.+ |\\.+$", ""); //bat.y.abcdefghijklm 시작과 끝에 있는 .을 제거
  }
}