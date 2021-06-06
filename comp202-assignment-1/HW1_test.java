import java.util.Random;

public class HW1_test {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    int n_tests = 10;
    Random rand = new Random();
    rand.setSeed(12);
    for (int n = 5; n < 5+n_tests; n++) {
      char[][] input = new char[n][n];
      for (int i = 0; i < n; i++) {
        int a_num = rand.nextInt(n);
        for (int j = 0; j < a_num; j++) {
          input[i][j] = 'a';
        }
        for (int j = a_num; j < n; j++) {
          input[i][j] = 'b';
        }
      }
      System.out.println(HW1.acount(input));
    }
  }
}
