import java.util.Map;
import java.util.TreeMap;

public class SomEquivalente {
    Map<String, String> map = new TreeMap<>();

    public SomEquivalente(Map<String, String> m) {
        this.map = m;
    }

    public boolean equivalente(String str1, String str2) {
        if((str1 == null && str2 == null) || (str1 == null && str2 == "") || (str1 == "" && str2 == null)) return true;
        if(str1 == null || str2 == null) return false;
        for(Map.Entry<String, String> entry : map.entrySet())
            if(str1.replace(entry.getKey(), entry.getValue()).equalsIgnoreCase(str2.replace(entry.getKey(), entry.getValue()))) return true;
        return false;
    }
}
