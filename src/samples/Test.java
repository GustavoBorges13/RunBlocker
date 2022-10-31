package samples;

import com.sun.jna.platform.win32.Advapi32Util;
import static com.sun.jna.platform.win32.WinReg.HKEY_LOCAL_MACHINE;
import java.util.ArrayList;
import java.util.TreeMap;

public class Test {
	@SuppressWarnings("rawtypes")
	public ArrayList<String> getlist() {
	
		@SuppressWarnings("unchecked")
		ArrayList<String> arr = new ArrayList<String>();
		String[] keys = Advapi32Util.registryGetKeys(HKEY_LOCAL_MACHINE,
				"Software\\Microsoft\\Windows\\CurrentVersion\\Uninstall");
		String temp;
		for (String key : keys) {
			temp = "Software\\Microsoft\\Windows\\CurrentVersion\\Uninstall" + "\\" + key;
			TreeMap<String, Object> tr = Advapi32Util.registryGetValues(HKEY_LOCAL_MACHINE, temp);
			if (tr.isEmpty()) {
				if (!key.contains("Update"))// all the instances of update are not actually installed applications
				{
					arr.add(key);
				}
			} else {
				if (tr.containsKey("DisplayName")) {
					String str = (String) tr.get("DisplayName");
					if (!str.contains("Update")) {
						arr.add(str);
					}
				}
			}
		}
		return arr;
	}
}