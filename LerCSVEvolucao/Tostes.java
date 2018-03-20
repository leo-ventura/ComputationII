import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.IOException;

public class Tostes {

    public static Map<String, Map<Integer, ?>> leArquivo( Path path, Charset charset, String separador ) throws Exception {
        
    	Map<String, Map<Integer, ?>> outerMap = new TreeMap<>();
    	Map<Integer, Object> innerMap = new TreeMap<>();
    	List<String> all_Lines = Files.readAllLines( path, charset );
    	
    	String nomes[] = all_Lines.get(0).split(separador);
    	String elementos[] = null;
    	
    	Class<?> classe = null;
    	int classIndex = 0;
    	int idIndex = 0;
    	boolean vazia = true;
    	
    	try {
	    	for( String line : all_Lines) {
	    		
	    		if( line.equals("") || line.isEmpty() )  vazia = true;    		
	    		else {
	    			
	    			
	    			if( vazia ) {
                        nomes = line.split(separador);
                        innerMap = new TreeMap<>();
	    			} else {
	    				
	    				classIndex = Arrays.asList(nomes).indexOf("class");
	    				idIndex = Arrays.asList(nomes).indexOf("id");
	    				elementos = line.split(separador);
	    				classe = Class.forName( elementos[classIndex] );
	    				
	    				Integer id = Integer.valueOf(elementos[idIndex]);
	    				Object obj = classe.newInstance();    				
	    				Method metodos[] = classe.getMethods();
	    				
	    				int elementIndex = 0;    				
	    				for( String elemento : elementos ) {  					
	    					if( elemento != elementos[classIndex] && elemento != elementos[idIndex] ) {
	    						
	    						for( Method metodo : metodos ) {
	                    			if( metodo.getName().equalsIgnoreCase("set" + nomes[elementIndex] ) && elemento.getClass() == String.class ) 
	                    				try {
                                            metodo.invoke(obj, elemento);
                                        } catch(Exception e) {}
	    						}
	    						
	    					} else if( elemento == elementos[idIndex] ) {
	    						
	    						for( Method metodo : metodos ) {
	                    			if( metodo.getName().equalsIgnoreCase("set" + nomes[elementIndex]) )
                                        try {
                                            metodo.invoke(obj, id);
                                        } catch (Exception e) {}
	    						}  						
	    					}
	    					elementIndex++;
	    				}
	    				innerMap.put(id, obj);
	    				outerMap = verifyMap(outerMap, innerMap, classe);
	    			}
	    			
	    			vazia = false;
	    		}
	    	}
    	} catch (Exception e) {}

        return outerMap;
    }
    
    public static Map<String, Map<Integer, ?>> verifyMap(Map<String,Map<Integer,?>> outerMap, Map<Integer,Object> innerMap, Class<?> classe) {
        if(!outerMap.containsKey(classe.getName()))
            outerMap.put(classe.getName(), innerMap);
        else {
            Map<Integer, Object> aux = new TreeMap<>();
            for (Map.Entry<Integer, ?> map : outerMap.get(classe.getName()).entrySet()) {
                aux.put(map.getKey(), map.getValue());
            }
            aux.putAll(innerMap);
            outerMap.put(classe.getName(), aux);
        }
        return outerMap;
    }
}