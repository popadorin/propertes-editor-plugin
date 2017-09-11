package helpers;

import java.util.LinkedHashMap;

public class PropertiesGetter {
	
	public LinkedHashMap<String, String> getProperties(String content) {
		if (content == null) {
			System.out.println("content is null");
			return null;
		}
		LinkedHashMap<String, String> properties = new LinkedHashMap<>();
		String[] lines = content.split("\\R");
		for (String line : lines) {
			if (line.isEmpty()) {
				continue;
			}
			String key = line.split("\\=")[0];
			String value = line.split("\\=")[1];
			properties.put(key, value);
		}
		
		return properties;
	}

}
