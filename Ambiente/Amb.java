public class Amb {
    int a;
    String s;

    public String toString() {
        return "{ " + s + " | " + a + " }";
    }

    public Amb() {
        this.a = 1;
        this.s = "'void'";
    }
    public Amb(int t) {
        this.a = 2;
        this.s = "'int'";
    }
    public Amb(double t) {
        this.a = 3;
        this.s = "'double'";
    }
    public Amb(char t) {
        this.a = 4;
        this.s = "'char'";
    }
    public Amb(String t) {
        this.a = 5;
        this.s = "'String'";
    }
    public Amb(int i, int j) {
        this.a = 6;
        this.s = "'int,int'";
    }
    public Amb(String i, int j) {
        this.a = 7;
        this.s = "'String,int'";
    }
    public Amb(String i, String j) {
        this.a = 8;
        this.s = "'String,String'";
    }
}