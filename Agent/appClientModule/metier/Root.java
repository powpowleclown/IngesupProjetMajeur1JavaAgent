package metier;
import java.io.File;

public class Root {

	private String absolutePath;
	private String totalSpace;
	private String freeSpace;
	private String usableSpace;
	
	public Root(File root)
	{
		this.setAbsolutePath("File system root: " + root.getAbsolutePath());
		this.setTotalSpace("Total space (bytes): " + root.getTotalSpace());
		this.setFreeSpace("Free space (bytes): " + root.getFreeSpace());
		this.setUsableSpace("Usable space (bytes): " + root.getUsableSpace());
	}
	
	public String getAbsolutePath() {
		return absolutePath;
	}
	private void setAbsolutePath(String absolutePath) {
		this.absolutePath = absolutePath;
	}
	public String getTotalSpace() {
		return totalSpace;
	}
	private void setTotalSpace(String totalSpace) {
		this.totalSpace = totalSpace;
	}
	public String getFreeSpace() {
		return freeSpace;
	}
	private void setFreeSpace(String freeSpace) {
		this.freeSpace = freeSpace;
	}
	public String getUsableSpace() {
		return usableSpace;
	}
	private void setUsableSpace(String usableSpace) {
		this.usableSpace = usableSpace;
	}
	
	public void Affichage()
	{
		System.out.println(this.getAbsolutePath());
		System.out.println(this.getFreeSpace());
		System.out.println(this.getTotalSpace());
		System.out.println(this.getUsableSpace());
	}
}
