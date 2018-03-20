import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class GeraForm {

    public static String incluir( Class<?> formClass ) throws Exception{
        String out = "";
        String label = null, maxLength = null, required = null, name = null;
        boolean already = false;
        int i = 1, annCount = 0;
        for (Method me : formClass.getMethods()) {
            for (Annotation ai : me.getAnnotations())
                annCount++;
        }
        for(Method m : formClass.getMethods()) {
            for(Annotation a : m.getAnnotations()) {
                name = "name=\"" + m.getName().substring(3).toLowerCase() + "\"";
                Class<? extends Annotation> type = a.annotationType();
                for(Method method : type.getDeclaredMethods()) {
                    Object value = method.invoke(a, (Object[]) null);
                    if (method.getName().equals((Object) "label")) {
                        label = (String) value;
                    }
                    if (method.getName().equals((Object) "maxLength")) {
                        if(!method.getDefaultValue().equals(value))
                            maxLength = "maxlength=\"" + value.toString() + "\"";
                    }
                    if (method.getName().equals((Object) "required")) {
                        if (!method.getDefaultValue().equals(value))
                            required = "required=\"" + value.toString() + "\"";
                    }
                }
                if (maxLength != null && required != null) {
                    out += label + "<input type=\"text\"" + " " + name + " " + maxLength + " " + required + ">";
                }
                if (maxLength == null && required == null) {
                    out += label + "<input type=\"text\"" + " " + name + ">";
                    already = true;
                }
                if (maxLength == null && !already) {
                    out += label + "<input type=\"text\"" + " " + name + " " + required + ">";
                }
                if (required == null && !already)
                    out += label + "<input type=\"text\"" + " " + name + " " + maxLength + ">";
                if(i<annCount) {
                    out += "\n";i++;
                }
                label = null;
                maxLength = null;
                required = null;
                name = null;
            }
        }
        return out;
    }

    public static String editar( Object form ) throws Exception{
        String out = "";
        String label = null, maxLength = null, required = null, name = null, val = null, nameClass = null;
        boolean already = false;
        Class<?> formClass = form.getClass();
        int i=1, annCount = 0;
        for(Method me : formClass.getMethods()) {
            for(Annotation ai : me.getAnnotations()) annCount++;
        }
        for(Method m : formClass.getMethods()) {
            for(Annotation a : m.getAnnotations()) {
                name = "name=\"" + m.getName().substring(3).toLowerCase() + "\"";
                nameClass = m.getName().substring(3).toLowerCase();
                val = "value=\"" + formClass.getMethod("get" + capitalize(nameClass)).invoke(form) + "\"";
                Class<? extends Annotation> type = a.annotationType();
                for(Method method : type.getDeclaredMethods()) {
                    Object value = method.invoke(a, (Object[]) null);
                    if (method.getName().equals((Object) "label")) {
                        label = (String) value;
                    }
                    if (method.getName().equals((Object) "maxLength")) {
                        if(!method.getDefaultValue().equals(value))
                            maxLength = "maxlength=\"" + value.toString() + "\"";
                    }
                    if (method.getName().equals((Object) "required")) {
                        if (!method.getDefaultValue().equals(value))
                            required = "required=\"" + value.toString() + "\"";
                    }
                }
                if (maxLength != null && required != null) {
                    out += label + "<input type=\"text\"" + " " + name + " " + val + " " + maxLength + " " + required + ">";
                }
                if (maxLength == null && required == null) {
                    out += label + "<input type=\"text\"" + " " + name + " " + val + ">";
                    already = true;
                }
                if (maxLength == null && !already) {
                    out += label + "<input type=\"text\"" + " " + name + " " + val + " " + required + ">";
                }
                if (required == null && !already) {
                    out += label + "<input type=\"text\"" + " " + name + " " + val + " " + maxLength + ">";
                }
                if(i < annCount) {
                    out += "\n";i++;
                }
                label = null;
                maxLength = null;
                required = null;
                name = null;
                val = null;
                nameClass = null;
            }
        }
        return out;
    }

    public static void main(String[] args) throws Exception{
        Form a = new Form();
        try {System.out.println(editar(a));
        /*System.out.println(incluir(a.getClass()));*/}
        catch(Exception e) {}
    }
    
    public static String capitalize(String x) {
        return Character.toUpperCase(x.charAt(0)) + x.substring(1);
    }
}