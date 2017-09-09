package Main;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) {
		int[] data = { 5, 5, 34, 22, 10,104, 60, 30, 22, 100, 54 };
		hashSet(data);
		treeSet(data);
		linkedHashSet(data);
	}

	public static void hashSet(int[] arr) {
		Set<Integer> hashSet = new HashSet<>();
		for (int i = 0; i < arr.length; i++) {
			hashSet.add(arr[i]);
		}
		System.out.println(hashSet);
	}

	public static void treeSet(int[] arr) {
		Set<Integer> treeSet = new TreeSet<>();
		for (int i = 0; i < arr.length; i++) {
			treeSet.add(arr[i]);
		}
		System.out.println(treeSet);
	}

	public static void linkedHashSet(int[] arr) {
		Set<Integer> linkedHashSet = new LinkedHashSet<>();
		for (int i = 0; i < arr.length; i++) {
			linkedHashSet.add(arr[i]);
		}
		System.out.println(linkedHashSet);
	}
}
