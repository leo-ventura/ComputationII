public class Data {
  private int d, m, a;
  private String mes[]={"Janeiro","Fevereiro","Março","Abril","Maio","Junho","Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"};

  public String toString() {return this.d + "/" + this.m + "/" + this.a;}

  public int getDia() {
      return this.d;
  }

  public int getMes() {
      return this.m;
  }

  public int getAno() {
      return this.a;
  }

  public int total() {
      return this.a*365 + this.m*30 + this.d;
  }

  public void setDia(int d) {
      this.d = d;
  }

  public void setMes(int m) {
      this.m = m;
  }

  public void setAno(int a) {
      this.a = a;
  }

  public void setData(int d, int m, int a) {
    //isInvalidDate(d, m, a);
    switch(m) {
      case 1:
      case 3:
      case 5:
      case 7:
      case 8:
      case 10:
      case 12:
        if(d < 0 || d > 31) {
          System.out.println("Para " + mes[m-1] + ", o d deve estar entre 1 e 31, mas foi passado " + d);
          System.exit(0);
        }
        break;
      case 4:
      case 6:
      case 9:
      case 11:
        if(d < 0 || d > 30) {
          System.out.println("Para " + mes[m-1] + ", o d deve estar entre 1 e 30, mas foi passado " + d);
          System.exit(0);
        }
        break;
      case 2:
        if(a%400==0 || (a%100!=0 && a%4==0)) {
          if(d < 0 || d > 29) {
            System.out.println(a + " é um a bissexto. Para Fevereiro, o d deve estar entre 1 e 29, mas foi passado " + d);
            System.exit(0);
          }
        } else {
          if(d < 0 || d > 28) {
            System.out.println(a + " não é a bissexto. Para Fevereiro, o d deve estar entre 1 e 28, mas foi passado " + d);
            System.exit(0);
          }
        }
        break;
      default:
        System.out.println("Mês inválido: " + m);
        System.exit(0);
    }
    this.d = d;
    this.m = m;
    this.a = a;
  }
}
