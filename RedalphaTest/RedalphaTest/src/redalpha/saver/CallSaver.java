package redalpha.saver;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import redalpha.dataobject.Call;

/**
 * 
 * @author anastasia
 *
 */
public class CallSaver {
	/**
	 * Save file with call to disk.
	 * 
	 */
	public static void saveCall(Call call) {
		String firstName = call.getFirstName();
		String lastName = call.getLastName();
		
		List<String> lines = Arrays.asList(call.getPhone(), call.getTime());
		Path file = Paths.get("./" + lastName.toUpperCase() + "_" + firstName.toUpperCase() + ".txt");
		try {
			Files.write(file, lines, Charset.forName("UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
