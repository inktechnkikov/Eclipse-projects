package starter.treeimpl;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import starter.interfaces.ISimpleNode;

public class Node<T extends ISimpleNode> {

	public T value;
	public List<Node<T>> childrenList;
	
	public Node(T value, int childrenCount) {
		this.value = value;
		this.childrenList = new ArrayList<>(childrenCount);
	}
	public void print() {
		StringBuilder builder = new StringBuilder();
		builder.append(' ');
		System.out.println(this.value);
		for (Node<T> child : this.childrenList) {
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
		for (Node<T> child : tree.childrenList) {
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
			for (Node<T> child : current.childrenList) {
				queue.add(child);
			}
		}
		return result;
	}
}
