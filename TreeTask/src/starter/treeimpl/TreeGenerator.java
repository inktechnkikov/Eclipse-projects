package starter.treeimpl;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import starter.interfaces.ISimpleNode;

public class TreeGenerator<T> {

	public T value;
	public List<TreeGenerator<T>> childrenList;
	
	public TreeGenerator() {
		
	}
	public void print() {
		StringBuilder builder = new StringBuilder();
		builder.append(' ');
		System.out.println(this.value);
		for (TreeGenerator<T> child : this.childrenList) {
			System.out.printf("%s", builder);
			child.print();
		}
	}

	public Iterable<T> orderDFS() {
		List<T> result = new ArrayList<>();
		this.DFS(this, result);
		return result;
	}

	public void DFS(TreeGenerator<T> tree, List<T> resultList) {
		for (TreeGenerator<T> child : tree.childrenList) {
			this.DFS(child, resultList);
		}
		resultList.add(tree.value);
	}

	public Iterable<T> orderBFS() {
		List<T> result = new ArrayList<>();
		Queue<TreeGenerator<T>> queue = new ArrayDeque<>();
		queue.add(this);
		while (!queue.isEmpty()) {
			TreeGenerator<T> current = queue.poll();
			result.add(current.value);
			for (TreeGenerator<T> child : current.childrenList) {
				queue.add(child);
			}
		}
		return result;
	}
}
