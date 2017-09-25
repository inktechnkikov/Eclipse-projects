package starter.models;

import java.util.ArrayList;
import java.util.Collection;

public abstract class Node {
	private ArrayList<Node> children = new ArrayList<>();

    public ArrayList<Node> getChildren() {
        return this.children;
    }

    public void setChildren(ArrayList<Node> children) {
        this.children = children;
    }

    public void setChild(Node child) {
        this.children.add(child);
    }
    public abstract ArrayList<Object> characteristics();
}
