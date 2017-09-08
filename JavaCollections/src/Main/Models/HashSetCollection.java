package Main.Models;

import java.util.HashSet;

import Main.Interfaces.SetCollection;

public class HashSetCollection implements SetCollection{
	
	private HashSet<Long> data = new HashSet<>();
	
	
	 public HashSetCollection() {
		
	}

	@Override
	public void addElement(Long size) {
		Long startTimeAddElement = System.nanoTime();
		for (Long i = 0L; i <= size; i++) {
			this.data.add(i);
		}
		Long endTimeAddElement = System.nanoTime();
		Long elapsedTimeAdd = endTimeAddElement - startTimeAddElement;
		System.out.printf("Elapsed time -> %s",elapsedTimeAdd);
		System.out.println();
	}
	@Override
	public void printElements() {
		Long startTimePrintElement = System.nanoTime();
		for (Long element : data) {
			 System.out.println(element);
		}
		Long endTimePrintElement = System.nanoTime();
		Long elapsedTimePrint = endTimePrintElement - startTimePrintElement;
		System.out.printf("Elapsed time -> %s",elapsedTimePrint);
		System.out.println();
	}

	@Override
	public void searchElement(Long element) {
		Long startTimeSeerchElement = System.nanoTime();
		if(this.data.contains(element)) {
			System.out.printf("Element founded - > %s",element);
			System.out.println();
		}else {
			System.out.printf("No such element - > %s",element);
			System.out.println();
		}
		Long endTimeSearchElement = System.nanoTime();
		Long elapsedTimeSearch = endTimeSearchElement - startTimeSeerchElement;
		System.out.printf("Elapsed time for searching -> %s",elapsedTimeSearch);
		System.out.println();
	}

	@Override
	public void removeElement(Long element) {
		Long startTimeRemovingElement = System.nanoTime();
		this.data.remove(element);
		System.out.printf("Removed element -> %s",element);
		System.out.println();
		Long endTimeRemovingElement = System.nanoTime();
		Long elapsedTimeRemoving = endTimeRemovingElement - startTimeRemovingElement;
		System.out.printf("Elaped time for removing -> %s", elapsedTimeRemoving);
		System.out.println();
	}
}

