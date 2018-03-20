public class Bubble {
  public static void main(String[] args) {
    int v[] = {4, 7, 1, 8, 23, 3, 9, 6};
    int t = v.length;

    for(int j = 0; j < t-1; j++) {
      for(int i = 0; i < t-1-j; i++) {
        if(v[i] > v[i+1]) {
          int temp = v[i+1];
          v[i+1] = v[i];
          v[i] = temp;
        }
      }
    }
    for(int i = 0; i < t-1; i++) {
      System.out.print(v[i] + " ");
    }
    System.out.println(v[t-1]);
  }
}
