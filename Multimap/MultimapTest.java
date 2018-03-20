import java.util.*;

public class MultimapTest {
    public static void main(String[] args) throws Exception {

        /*Multimap<String, Integer> m1 = new HashMultimap<>();
        System.out.println("--- .put | .get ---");
        m1.put("Jan", 31);
        System.out.print((m1.get("Jan").size()+"").equals("1") + " - ");
        System.out.println("1 == " + m1.get("Jan").size());
        System.out.println();
        
        System.out.println("--- .put | .get ---");
        m1.put("Fev", 28, 29);
        System.out.print((m1.get("Fev").size()+"").equals("2") + " - ");
        System.out.println("2 == " + m1.get("Fev").size());
        System.out.println();
        
        System.out.println("--- .put | .get ---");
        m1.put("Fev", 28, 29);
        System.out.print((m1.get("Fev").size()+"").equals("4") + " - ");
        System.out.println("4 == " + m1.get("Fev").size());
        System.out.println();
        
        System.out.println("--- .put | .get ---");
        System.out.print((m1.get( "Fev")+"").equals("[28, 29, 28, 29]") + " - ");
        System.out.println("[28, 29, 28, 29] == " + m1.get( "Fev"));
        System.out.println();
        
        System.out.println("--- .put | .get ---");
        System.out.print((m1.get("Abr")+"").equals("null") + " - ");
        System.out.println("null == " + m1.get("Abr"));
        System.out.println();
        
        
        Multimap<String, Integer> m2 = new HashMultimap<>();
        System.out.println("--- .put | .get ---");
        m2.put("Jan", 31, (Integer[]) null);
        System.out.print((m2.get( "Jan" )+"").equals("[31]") + " - ");
        System.out.println("[31] == " + m2.get( "Jan" ));
        System.out.println();
        
        
        Multimap<String, Integer> m3 = new HashMultimap<>();
        System.out.println("--- .replace ---");
        m3.put("Jan", 29, 31);
        m3.replace("Jan", 1, 2);
        System.out.print((m3.get("Jan")+"").equals("[1, 2]") + " - ");
        System.out.println("[1, 2] == " + m3.get("Jan"));
        System.out.println();
        
        
        Multimap<String, Integer> m4 = new HashMultimap<>();
        System.out.println("--- .replace ---");
        m4.put("Jan", 29, 31);
        m4.replace("Jan");
        System.out.print((m4.get("Jan")+"").equals("null") + " - ");
        System.out.println("null == " + m4.get("Jan"));
        System.out.println();*/

        /*Multimap<String, Integer> m5 = new HashMultimap<>();
        System.out.println("--- .values ---");
        m5.put("Jan", 29, 31);
        System.out.print((m5.values() + "").equals("[29, 31]") + " - ");
        System.out.println("[29, 31] == " + m5.values());
        System.out.println();*/

        /*Multimap<String, Integer> m10 = new HashMultimap<>();
        System.out.println("--- .removeAll ---");
        m10.put("test", 1, 2, 3);
        m10.removeAll("test", 1, 2);
        System.out.print((m10.get("test") + "").equals("[3]") + " - ");
        System.out.println("[3] == " + m10.get("test"));
        System.out.println();*/

        /*Multimap<String, Integer> m11 = new HashMultimap<>();
        System.out.println("--- .removeAll Exception ---");
        m11.put("test", 1, 2, 3);
        try {
            m11.removeAll("test", 1, 4);
            System.out.println("false - No exception");
        } catch (Exception e) {
            System.out.print(e.getMessage().equals("4") + " - ");
            System.out.println("4 == " + e.getMessage());
        }
        System.out.println();*/

        /*Multimap<String, Integer> m12 = new HashMultimap<>();
        System.out.println("--- .removeAny ---");
        m12.put("test", 1, 2, 3);
        m12.removeAny("test", 1, 4);
        System.out.print((m12.get("test") + "").equals("[2, 3]") + " - ");
        System.out.println("[2, 3] == " + m12.get("test"));
        System.out.println();*/

        /*Multimap<String, Integer> m13 = new HashMultimap<>();
        System.out.println("--- .removeAny Exception ---");
        m13.put("test", 1, 2, 3);
        try {
            m13.removeAny("test", 5, 4);
            System.out.println("false - No exception");
        } catch (Exception e) {
            System.out.print(e.getMessage().equals("5, 4") + " - ");
            System.out.println("5, 4 == " + e.getMessage());
        }
        System.out.println();*/

        /*Multimap<String, Integer> m14 = new HashMultimap<>();
        System.out.println("--- .contains ---");
        m14.put("test", 1, 2, 3);
        System.out.print((m14.contains("test")+"").equals("true") + " - ");
        System.out.println("true == " + m14.contains("test"));
        System.out.println();
        
        
        Multimap<String, Integer> m15 = new HashMultimap<>();
        System.out.println("--- .contains ---");
        m15.put("test", 1, 2, 3);
        System.out.print((m15.contains("t")+"").equals("false") + " - ");
        System.out.println("false == " + m15.contains("t"));
        System.out.println();

        Multimap<String, Integer> m16 = new HashMultimap<>();
        System.out.println("--- .containsAny ---");
        m16.put("test", 1, 2, 3);
        System.out.print((m16.containsAny("test", 5, 4) + "").equals("false") + " - ");
        System.out.println("false == " + m16.containsAny("test", 5, 4));
        System.out.println();

        Multimap<String, Integer> m17 = new HashMultimap<>();
        System.out.println("--- .containsAny ---");
        m17.put("test", 1, 2, 3);
        System.out.print((m17.containsAny("test", 1, 2, 3) + "").equals("true") + " - ");
        System.out.println("true == " + m17.containsAny("test", 1, 2, 3));
        System.out.println();

        Multimap<String, Integer> m18 = new HashMultimap<>();
        System.out.println("--- .containsAll ---");
        m18.put("test", 1, 2, 3);
        System.out.print((m18.containsAll("test", 1, 2, 3) + "").equals("true") + " - ");
        System.out.println("true == " + m18.containsAll("test", 1, 2, 3));
        System.out.println();

        Multimap<String, Integer> m19 = new HashMultimap<>();
        System.out.println("--- .containsAll ---");
        m19.put("test", 1, 2, 3);
        System.out.print((m19.containsAll("test", 5, 4) + "").equals("false") + " - ");
        System.out.println("false == " + m19.containsAll("test", 5, 4));
        System.out.println();*/

        /*Multimap<String, Integer> m20 = new HashMultimap<>();
        System.out.println("--- .entrySet ---");
        m20.put("test", 1, 2, 3);
        m20.put("teste2", 5, 3, 2);
        System.out.print((m20.entrySet()+"").equals("[test=[1, 2, 3], teste2=[5, 3, 2]]") + " - ");
        System.out.println("[test=[1, 2, 3], teste2=[5, 3, 2]] == " + m20.entrySet());
        System.out.println();
        
        Multimap<String, Integer> m21 = new HashMultimap<>();
        System.out.println("--- .keySet ---");
        m21.put("test", 1, 2, 3);
        m21.put("teste2", 5, 3, 2);
        System.out.print((m21.keySet()+"").equals("[test, teste2]") + " - ");
        System.out.println("[test, teste2] == " + m21.keySet());
        System.out.println();*/

        /*Multimap<String, Integer> m22 = new HashMultimap<>();
        System.out.println("--- .values ---");
        m22.put("test", 1, 2, 3);
        m22.put("teste2", 5, 3, 2);
        System.out.print((m22.values() + "").equals("[1, 2, 3, 5, 3, 2]") + " - ");
        System.out.println("[1, 2, 3, 5, 3, 2] == " + m22.values());
        System.out.println();*/

        /*Multimap<String, Integer> m23 = new TreeMultimap<>();
        m23.put("Queso1", 13, 14, 15, 16, 17);
        m23.put("Queso2d", 113, 114, 115, 116, 117);
        List<String> expected = new ArrayList<>();
        List<String> test = new ArrayList<>();
        expected.addAll(Arrays.asList("Queso1l13, Queso1l14, Queso1l15, Queso1l16, Queso1l17, "
                + "Queso2dl113, Queso2dl114, Queso2dl115, Queso2dl116, Queso2dl117"));
        m23.forEach((k, v) -> test.add(k + "l" + v));
        System.out.print((expected + "").equals(test + "") + " - ");
        System.out.println(expected + " == " + test);
        System.out.println();*/

    	Multimap<String, Integer> m = new TreeMultimap<>();
    	
    	m.put("Jan", 31, 32, 33, 34, 35);
    	
    	m.removeAny("Jan", 31, 32, 33, 34, 35);
        
        System.out.println(m.get("Jan"));
    }
}