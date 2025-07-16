package PCK1;

import java.util.ArrayList;

public class MainClass {
	
	
	public static void main(String[] args) {
		long programStartTime = System.currentTimeMillis();
		int totalNumberOfObjects = 100000;
		int rangeOfRandomNumbers = 1000000;
		int numberOfLoops = 0;
		double greatestNumber = 0;
		double randomNumber;
		long objectCreationTime;
		String objectCreationName;
		ArrayList<StorageObject> storageObjects = new ArrayList<>();
		
		while(true) {
		//Creates Storage Objects and stores them into the storageObjects ArrayList
		for(int i = 0; i < totalNumberOfObjects; i++) {
			//The Current time in which the object is created
			objectCreationTime = System.currentTimeMillis();
			//Gets a random number from a range of specified numbers
			randomNumber = (double) (Math.random() * rangeOfRandomNumbers);
			//Creates a StorageObject that will be used to store the values above
			StorageObject object = new StorageObject();
			//Gets the value that is used to reference the object in memory
			objectCreationName = object.toString();
			
			//Stores the values created above into a StorageObject and adds that object into the storageObjects ArrayList
			object.storeValues(objectCreationTime, randomNumber, objectCreationName); 
			storageObjects.add(object);
		}
		
		int listStartingPoint = (totalNumberOfObjects - 1) * numberOfLoops;
		
		//Loops through the objects stored within the storageObjects ArrayList and does operations with the values in each object
		for(int i = listStartingPoint; i < storageObjects.size(); i++) {
			StorageObject object = storageObjects.get(i);
			
			//Covert the time the object was created to a human readable format (milliseconds) and displays it the console
			printObjectCreationTime(object, programStartTime, i);
			
			//Checks to see if the object's value/number is the current greatest number in the program
			greatestNumber = findGreatestValue(object, greatestNumber);
			
			//Removes the Package and Class name from the objects name/reference variable
			object.setObjectName(trimObjectName(object));
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		//Final Print statements of the program
		System.out.println();
		System.out.println("The greatest number out of all the objects is: " + greatestNumber);
		System.out.println("The first object created was " + storageObjects.get(0).getObjectName() + " and the last object created was " + 
		storageObjects.get(storageObjects.size() - 1).getObjectName());
		System.out.println("The program completed");
		}

	}
	
	public static void printObjectCreationTime(StorageObject object, long programStartTime, int objectNumber) {
		long objectCreationTime = object.getCreationTime();
		//Inefficiency
		String objectName = trimObjectName(object);
		System.out.println("Object " + (objectNumber + 1) + " (" + objectName + ")" + " was created " + 
		(objectCreationTime - programStartTime) + " miliseconds after the program started");
	}
	
	public static double findGreatestValue(StorageObject object, double currentGreatestNumber) {
		if(object.getValue() > currentGreatestNumber) {
			return object.getValue();
		}
		
		return currentGreatestNumber;
	}
	
	public static String trimObjectName(StorageObject object) {
		String trimmedObjectName = "";
		String objectCurrentName = object.getObjectName();
		
		if(objectCurrentName.contains("@")) {
			String[] objectNameSplit = objectCurrentName.split("@");
			trimmedObjectName = objectNameSplit[1];
			
			return trimmedObjectName;
		}
		
		return objectCurrentName;
	}
}