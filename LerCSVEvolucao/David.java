//package lab11;

import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.Map;
import java.util.TreeMap;
import java.io.BufferedReader;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.IOException;
import java.util.List;

public class David {

	@SuppressWarnings("unchecked")

	public static Map<String, Map<Integer, ?>> leArquivo(Path p, Charset c, String separador) throws Exception {

		Object object;

		Map<Integer, Object> auxmap = new TreeMap<>();

		Map<String, Map<Integer, ?>> finalmap = new TreeMap<>();

		try (BufferedReader reading = Files.newBufferedReader(p.toAbsolutePath(), c)) {

			String line1, line2 = "";

			while ((line1 = reading.readLine()) != null && line2 != null) {

				String classe = null;

				String[] keys = line1.split(separador);

				while ((line2 = reading.readLine()) != null) {

					int ID = 0;

					if (line2.isEmpty())
						break;

					String[] value = line2.split(separador);

					Map<String, String> startmap = new TreeMap<>();

					for (int j = 0; j < value.length; j++) {

						if (keys[j].equalsIgnoreCase("id"))
							ID = j;

						if (keys[j].equalsIgnoreCase("class"))
							classe = value[j];

						startmap.put(keys[j], value[j]);

					}

					object = Class.forName(classe).newInstance();

					if (finalmap.containsKey(classe)) {

						auxmap = (Map<Integer, Object>) finalmap.get(classe);

					}

					else {

						auxmap = new TreeMap<>();

					}

					auxmap.put(Integer.parseInt(value[ID]), object);

					finalmap.put(classe, auxmap);

					for (String key : keys) {

						if (key.equalsIgnoreCase("class"))
							continue;

						boolean verify = false;

						for (Method metodos : Class.forName(classe).getMethods()) {

							if (metodos.getName().toLowerCase().equals("set" + key) && metodos.getParameterCount() == 1
									&& metodos.getParameterTypes()[0].getSimpleName()
											.equals(String.class.getSimpleName())) {

								verify = true;

								metodos.invoke(object, startmap.get(key));

								break;
							}

							else if (metodos.getName().toLowerCase().equals("set" + key)
									&& metodos.getParameterCount() == 1
									&& metodos.getParameterTypes()[0].equals(Integer.class)) {

								verify = true;

								metodos.invoke(object, Integer.valueOf(startmap.get(key)));

							}
						}

						if (!verify)
							throw new Exception("Campo " + key + " não encontrado na classe " + classe);

					}

				}

			}
		}
		System.out.println(finalmap);
		return finalmap;
	}

	public static void main(String[] args) throws Exception {
		Path p = Paths.get("", "entrada.txt");
		Charset c = Charset.forName("UTF-8");
		leArquivo(p, c, ";");
	}
}