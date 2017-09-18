package Main.TreeImpl;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

import Main.Interfaces.ISimpleMode;

public class Node<T extends ISimpleMode> {

	public T value;
	public List<Node<T>> children;

	public Node(T value, int childrenCount) {
		this.value = value;
		this.children = new ArrayList<>(childrenCount);
	}

	public void print() {
		StringBuilder builder = new StringBuilder();
		builder.append(' ');
		System.out.println(this.value);
		for (Node<T> child : this.children) {
			System.out.printf("%s", builder);
			child.print();
		}
	}

	public Iterable<T> orderDFS() {
		List<T> result = new ArrayList<>();
		this.DFS(this, result);
		return result;
	}

	public void DFS(Node<T> tree, List<T> resultList) {
		for (Node<T> child : tree.children) {
			this.DFS(child, resultList);
		}
		resultList.add(tree.value);
	}

	public Iterable<T> orderBFS() {
		List<T> result = new ArrayList<>();
		Queue<Node<T>> queue = new ArrayDeque<>();
		queue.add(this);
		while (!queue.isEmpty()) {
			Node<T> current = queue.poll();
			result.add(current.value);
			for (Node<T> child : current.children) {
				queue.add(child);
			}
		}
		return result;
	}
}
