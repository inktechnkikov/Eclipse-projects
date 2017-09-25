package starter.nodefactory;
import java.util.ArrayList;
import java.util.List;

import starter.interfaces.ISimpleNode;
import starter.models.ColoredNode;
import starter.models.Node;
import starter.models.ShapeNode;
import starter.models.SimpleNode;
import starter.randomgenerator.RandomColorGenerator;
import starter.randomgenerator.RandomNodeGenerator;
import starter.treeimpl.TreeGenerator;

public class NodeFacotry {
	private RandomNodeGenerator randomNodeGenerator;

	public RandomNodeGenerator getRandomNodeGenerator() {
		return this.randomNodeGenerator;
	}

	public void setRandomNodeGenerator(RandomNodeGenerator randomNodeGenerator) {
		this.randomNodeGenerator = randomNodeGenerator;
	}

}
