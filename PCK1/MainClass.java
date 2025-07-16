package PCK1;

import java.util.ArrayList;
import java.util.Collections;

public class MainClass {
	
	
	public static void main(String[] args) {
		long programStartTime = System.currentTimeMillis();
		int totalNumberOfObjects = 10000;
		int rangeOfRandomNumbers = 1000000;
		int numberOfLoops = 0;
		double greatestNumber = 0;
		double randomNumber;
		long objectCreationTime;
		String objectCreationName;
		ArrayList<StorageObject> storageObjects = new ArrayList<>();
		ArrayList<StorageObject> storageObjectsSorted = new ArrayList<>();
		
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
				
				//Checks to see if the object's value/number is the current greatest number in the program
				greatestNumber = findGreatestValue(object, greatestNumber);
				
				//Removes the Package and Class name from the objects name/reference variable
				object.setObjectName(trimObjectName(object));

				//Covert the time the object was created to a human readable format (milliseconds) and displays it the console
				printObjectCreationTime(object, programStartTime, i);
			}

			//Objects are organized alphabetically based on string name
			//Object # (string name) was created # milliseconds after program started. After being organized Object became object (# based on organized position)
			storageObjectsSorted = sortObjects(storageObjects);
			printSortedObjects(storageObjectsSorted, programStartTime);
			
			//Final Print statements of the program
			System.out.println("The greatest number out of all the objects is: " + greatestNumber);
			System.out.println("The program completed");

			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	    }
	}
	
	public static void printObjectCreationTime(StorageObject object, long programStartTime, int objectNumber) {
		long objectCreationTime = object.getCreationTime();
		//Inefficiency
		String objectName = trimObjectName(object);
		System.out.println("Object " + (objectNumber + 1) + " (" + objectName + ")" + " was created " + 
		(objectCreationTime - programStartTime) + " miliseconds after the program started");

		
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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

		public static ArrayList<StorageObject>  sortObjects(ArrayList<StorageObject> storageObjects){
			ArrayList<String> objectNames = new ArrayList<>();
			ArrayList<StorageObject> sortedStorageObjects = new ArrayList<>();

			//Extract All object names and store them into an ArrayList
			for(StorageObject storageObject : storageObjects){
				objectNames.add(storageObject.getObjectName());
			}

			//Sort the list of object names
			Collections.sort(objectNames);

			//Loops through storageObjects to sort storageObjects based on how their object names are listed in objectNames
			for(String name : objectNames){
				for(int i = 0; i < storageObjects.size(); i++){
					StorageObject storageObject = storageObjects.get(i);
					if(name.equals(storageObject.getObjectName())){
							//This adds the time in which the object was sorted into the object's sortedTime variable
							storageObject.setSortedTime(System.currentTimeMillis());
						sortedStorageObjects.add(storageObject);
						break;
					}
				}
			}

			return sortedStorageObjects;
	}

	public static void printSortedObjects(ArrayList<StorageObject> storageObjects, long programStartTime){
				System.out.println("\nSorting Objects based on name ...");
		for(int i = 0; i < storageObjects.size(); i++){
			String sortedObjectName = storageObjects.get(i).getObjectName();
			System.out.println("Sorted Object " + (i + 1) + " (" + sortedObjectName + ")" + " was sorted " 
			+ (storageObjects.get(i).getSortedTime() - programStartTime) + " milliseconds after the program started");

			try {
			    Thread.sleep(1);
		    } catch (InterruptedException e) {
			    e.printStackTrace();
		    }
		}
	}
}