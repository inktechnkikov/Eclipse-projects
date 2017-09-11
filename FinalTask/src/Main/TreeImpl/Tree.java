package Main.TreeImpl;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class Tree<T> {

	 public T value;
	    public ArrayList<Tree<T>> children;

	        public Tree(T value,Tree<T>...children){
	            this.value = value;
	            this.children = new ArrayList<>(Arrays.asList(children));
	    }
	    public void print(){
	            StringBuilder builder = new StringBuilder();
	            builder.append(' ');
	        System.out.println(this.value);
	        for (Tree<T> child : this.children) {
	            System.out.printf("%s",builder);
	         child.print();
	        }
	    }
	    public Iterable<T> orderDFS(){
	        List<T> result = new ArrayList<>();
	       this.DFS(this,result);
	       return result;
	    }
	    public void DFS(Tree<T> tree,List<T> resultList){
	        for (Tree<T> child : tree.children) {
	            this.DFS(child,resultList);
	        }
	        resultList.add(tree.value);
	    }
	    public Iterable<T> orderBFS(){
	        List<T> result = new ArrayList<>();
	        Queue<Tree<T>> queue = new ArrayDeque<>();
	        queue.add(this);
	        while (!queue.isEmpty()){
	            Tree<T> current = queue.poll();
	            result.add(current.value);
	            for (Tree<T> child : current.children) {
	                queue.add(child);
	            }
	        }
	        return result;
	    }
}
