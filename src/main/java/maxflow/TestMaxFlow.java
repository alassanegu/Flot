package maxflow;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.stream.GraphParseException;

import java.io.IOException;

public class TestMaxFlow {

	public static void main(String[] args) throws IOException, GraphParseException {

		System.setProperty("org.graphstream.ui", "swing");

		Graph g = new SingleGraph("test");

		g.read("data/réseauxQE.dgs");
		g.getNode("S").setAttribute("ui.style", "fill-color: red;");
		g.getNode("P").setAttribute("ui.style", "fill-color: blue;");

		g.setAttribute("ui.stylesheet", "node {size: 23px;fill-color: green;}");// on change la taille et la couleur en vert
		for (Node n : g) { //pour chanque noeud dans dans notre graphe écart
			n.setAttribute("ui.label", n.getId()); // on modifie l'attribue du noeud en son identifiant
		}
		g.display(false);
		
		MaxFlow mf = new MaxFlow();
		mf.setCapacityAttribute("capacite");
		mf.init(g);
		mf.setSource(g.getNode("S"));
		mf.setSink(g.getNode("P"));
		mf.compute();
		
		System.out.println("Flot Max = " + mf.getFlow());
		g.edges().forEach((Edge e) -> {
			double flow = mf.getFlow(e);
			double cap = mf.getCapacity(e);
			if (flow >= 0) e.setAttribute("ui.label", "" + flow);
			if (cap == flow) e.setAttribute("ui.style", "fill-color: red;");


		});
	}

}
