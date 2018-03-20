import java.util.*;
import java.lang.*;
import java.io.*;

public class StringVector {
  String str[];

  public StringVector(int tam) {
    str = new String[tam];
    Arrays.fill(str, "");
  }

  public String at(int i) {
    return str[i];
  }

  public void put(int i, String st) {
    str[i] = st;
  }

  public int find(String st) {
    for(int i = 0; i < str.length; i++) {
      if(str[i].equals(st))
        return i;
    }
    return -1;
  }

  public void newSize(int size) throws VectorSizeException {
    int podeTirar = 0;
    for(int i = 0; i < str.length; i++) {
      if(!str[i].equals(""))
        podeTirar++;
    }

    if(size < podeTirar) {
      throw new VectorSizeException(podeTirar, size);
    } else {

      String oldStr[] = str;
      int j = 0;

      for(int i = 0; i < str.length; i++) {
        if(!str[i].equals("")) {
          oldStr[j] = str[i];
          j++;
        }
      }

      str = new String[size];

      for(int i = 0; i < j; i++) {
        str[i] = oldStr[i];
      }

      for(; j < size; j++) {
        if(str[j] == null)
          str[j] = "";
      }
    }
  }
}
