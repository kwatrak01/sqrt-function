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
		File file = new File(getPath());
		if (!file.exists())
			file.mkdir();
	}
	
	default void initDictionary(HashMap<String, String> translates) {
		dictionary.putAll(translates);
	}
	
	default String t(String key) {
		return dictionary.getOrDefault(key, "UNDEFINED");
	}
	
	default String getPath(String name) {
		return getPath() + File.separator + name + ".lang";
	}
	
	default String getPath() {
		return Paths.get("").toAbsolutePath() + File.separator + "lang";
	}
}
