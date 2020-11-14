package entities;

public class OberserverFile {
	private static String nameFile;
	private static boolean modified;
	

	public static boolean check() {
		return modified;
	}
	
	public static void setModified(boolean bool) {
		modified = bool;
	}
	
	public static void setName(String name) {
		nameFile = name;
	}
	
	public static String getName() {
		return nameFile;
	}
	
}
