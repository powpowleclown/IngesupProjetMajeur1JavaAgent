import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Computer {
	
	private String availableProcessor;
	private String freeMemory;
	private String maxMemory;
	private String totalMemory;
	//private String procCacheSize;
	//private String procFamily;
	//private String procSpeed;
	private List<Root> rootslist;
	
	public Computer(Runtime runtime)
	{
		this.setAvailableProcessor("Available processors (cores): " + runtime.availableProcessors());
		this.setFreeMemory("Free memory (bytes): " + runtime.freeMemory());
		this.setMaxMemory("Maximum memory (bytes): " + (runtime.maxMemory() == Long.MAX_VALUE ? "no limit" : runtime.maxMemory()));
		this.setTotalMemory("Total memory available to JVM (bytes): " + runtime.totalMemory());
		
		rootslist = new ArrayList<Root>();
		File[] roots = File.listRoots();
	   for (File rootfile : roots)
	   {
		   Root root = new Root(rootfile);
		   rootslist.add(root);
	    }
	}
	
	public String getAvailableProcessor() {
		return availableProcessor;
	}
	private void setAvailableProcessor(String availableProcessor) {
		this.availableProcessor = availableProcessor;
	}
	public String getFreeMemory() {
		return freeMemory;
	}
	private void setFreeMemory(String freeMemory) {
		this.freeMemory = freeMemory;
	}
	public String getMaxMemory() {
		return maxMemory;
	}
	private void setMaxMemory(String maxMemory) {
		this.maxMemory = maxMemory;
	}
	public String getTotalMemory() {
		return totalMemory;
	}
	private void setTotalMemory(String totalMemory) {
		this.totalMemory = totalMemory;
	}
	/*public String getProcCacheSize() {
		return procCacheSize;
	}
	private void setProcCacheSize(String procCacheSize) {
		this.procCacheSize = procCacheSize;
	}
	public String getProcFamily() {
		return procFamily;
	}
	private void setProcFamily(String procFamily) {
		this.procFamily = procFamily;
	}
	public String getProcSpeed() {
		return procSpeed;
	}
	public void setProcSpeed(String procSpeed) {
		this.procSpeed = procSpeed;
	}*/
	public List<Root> getRoots() {
		return rootslist;
	}
	public void setRoots(List<Root> roots) {
		this.rootslist = roots;
	}
	
	public void Affichage()
	{
		System.out.println(this.getAvailableProcessor());
		System.out.println(this.getFreeMemory());
		System.out.println(this.getMaxMemory());
		//System.out.println(this.getProcCacheSize());
		//System.out.println(this.getProcFamily());
		//System.out.println(this.getProcSpeed());
		System.out.println(this.getTotalMemory());
		for(Root root : this.getRoots())
		{
			root.Affichage();
		}
	}
}
