package pl.konradwatrak.languages;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import pl.konradwatrak.Language;

public class English implements Language {

	@Override
	public HashMap<String, String> load() throws IOException {
		File file = new File(getPath(English.class.getCanonicalName().toLowerCase()));
		if (!file.exists())
			throw new IOException("Language file is not exist!");
		
		byte[] bytes;
		try (FileInputStream fis = new FileInputStream(file)) {
			bytes = fis.readAllBytes();
		}
		
		HashMap<String, String> output = null;
		ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
		try {
			output = (HashMap<String, String>) ois.readObject();
		} catch (ClassNotFoundException | IOException e) {
			output = new HashMap<>();
			e.printStackTrace();
		}
		
		return output;
	}

	@Override
	public void save(Object data) throws IOException {
		byte[] bytes = null;
		try (ByteArrayOutputStream stream = new ByteArrayOutputStream(); ObjectOutputStream ostream = new ObjectOutputStream(stream)) {
			ostream.writeObject(data);
			ostream.flush();
			bytes = stream.toByteArray();
		}
		
		File file = new File(getPath(English.class.getCanonicalName().toLowerCase()));
		
		try (FileOutputStream output = new FileOutputStream(file)) {
			output.write(bytes);
		}
	}

}