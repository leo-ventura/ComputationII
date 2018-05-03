import java.util.Iterator;

public class RangeData implements Iterable<Data> {
    Data dataGeral1 = new Data();
    Data dataGeral2 = new Data();

    static int loop = 0;

    public RangeData(Data d1, Data d2) {
        this.dataGeral1 = d1;
        this.dataGeral2 = d2;
    }

    public Data mudaData() {
        int d = dataGeral1.getDia();
        int m = dataGeral1.getMes();
        int a = dataGeral1.getAno();

        if(ehMenor()) {
            switch(m) {
              case 1:
              case 3:
              case 5:
              case 7:
              case 8:
              case 10:
                if(d > 0 && d < 31) {
                    d++;
                } else if(d == 31) {
                    d=1;
                    m++;
                }
                break;
              case 12:
                if(d > 0 && d < 31) {
                    d++;
                } else if(d == 31) {
                    d = 1;
                    m = 1;
                    a++;
                }
                break;
              case 4:
              case 6:
              case 9:
              case 11:
                if(d > 0 && d < 30) {
                    d++;
                } else if(d == 30) {
                  d = 1;
                  m++;
                }
                break;
              case 2:
                if(a%400==0 || (a%100!=0 && a%4==0)) {
                  if(d > 0 && d < 29) {
                      d++;
                  } else if(d == 29) {
                      d = 1;
                      m = 3;
                  }
                } else {
                  if(d > 0 && d < 28) {
                    d++;
                  } else if(d == 28) {
                    d = 1;
                    m = 3;
                  }
                }
                break;
            }
        }
        else { // data1 maior que data2, entao tenho que ir de tras pra frente
            switch(m) {
              case 1:
                if(d > 1 && d <= 31) {
                    d--;
                } else if(d==1) {
                    d = 31;
                    m = 12;
                    a--;
                }
                break;
              case 8:
                if(d > 1 && d <= 31) {
                    d--;
                } else if(d == 1) {
                    d = 31;
                    m--;
                }
                break;
              case 5:
              case 7:
              case 10:
              case 12:
                if(d > 1 && d <= 31) {
                    d--;
                } else if(d == 1) {
                    d = 30;
                    m--;
                }
                break;
              case 3:
                if(d > 1 && d <= 31) {
                  d--;
                } else if(d == 1) {
                  m = 2;
                  if(a%400==0 || (a%100!=0 && a%4==0))
                    d = 29;
                  else d = 28;
                }
                break;
              case 2:
              case 4:
              case 6:
              case 9:
              case 11:
                if(d > 1 && d <= 30) {
                    d--;
                } else if(d == 1) {
                    d = 31;
                    m--;
                }
                break;
            }
        }
        Data data = new Data();
        data.setData(d,m,a);
        loop++;
        System.out.println(loop);
        return data;
    }

    public boolean ehMenor() {
        return (dataGeral1.total() < dataGeral2.total());
    }

    @Override
    public Iterator<Data> iterator() {
        return new Iterator<Data>() {

            @Override
            public boolean hasNext() {
                return !(dataGeral1.getAno() == dataGeral2.getAno() && dataGeral1.getMes() == dataGeral2.getMes() && dataGeral1.getDia() == dataGeral2.getDia())
            }

            @Override
            public Data next() {
                if(loop == 0) {
                    loop++;
                    return dataGeral1;
                }
                return dataGeral1 = mudaData();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
