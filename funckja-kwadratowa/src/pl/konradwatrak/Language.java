package pl.konradwatrak;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;

public interface Language {

	HashMap<String, String> dictionary = new HashMap<>();
	
	HashMap<String, String> load() throws IOException;
	void save(Object data) throws IOException;
	
	default void init() throws IOException {
		File file = new File(getRootPath());
		if (!file.exists())
			file.mkdir();
	}
	
	default void initDictionary(HashMap<String, String> translates) {
		dictionary.putAll(translates);
	}
	
	default String t(String key) {
		return dictionary.getOrDefault(key, "UNDEFINED");
	}
	
	default String getPath() {
		return getRootPath() + File.separator + getClassName() + ".lang";
	}
	
	default String getRootPath() {
		return Paths.get("").toAbsolutePath() + File.separator + "lang";
	}
	
	default void saveFromTemplate(HashMap<String, String> tempDictionary) throws IOException {
		File file = new File(getPath());
		if (file.exists())
			return;
		
		if (!file.createNewFile())
			throw new IOException("Cannot create language file from template!");
		
		save(tempDictionary);
	}
	
	default void saveFromTemplate() throws IOException {}
	
	default String getClassName() {
		return getClass().getName().substring(getClass().getPackageName().length() + 1);
	}
}
