package PCK1;

public class StorageObject {
	private long creationTime;
	private double value;
	private String objectName;
	private long sortedTime;
	
	public StorageObject() {
		
	}
	
	public void storeValues(long creationTime, double value, String objectName) {
		this.creationTime = creationTime;
		this.value = value;
		this.objectName = objectName;
	}

	public long getCreationTime() {
		return creationTime;
	}

	public double getValue() {
		return value;
	}

	public String getObjectName() {
		return objectName;
	}
	
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public long getSortedTime(){
		return sortedTime;
	}

	public void setSortedTime(long sortedTime){
		this.sortedTime = sortedTime;
	}
}
