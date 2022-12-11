package org.example;

import maxflow.MaxFlow;

import java.io.IOException;
        import org.graphstream.graph.Edge;
        import org.graphstream.graph.Graph;
        import org.graphstream.graph.Node;
        import org.graphstream.graph.implementations.DefaultGraph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.stream.GraphParseException;

public class Main {

    private static Node source; // Noeud source
    private static Node sink; // Noeud de destination

    public static void main(String[] args) throws IOException, GraphParseException {

        System.setProperty("org.graphstream.ui", "swing"); // Configuration pour l'affichage du graphe

        // Création du graphe



        Graph g_Ecart = new SingleGraph("Graph_Ecart");
        g_Ecart.read("data/grapheEcart.dgs"); // lecture du fichier Graph_Ecart.dgs représentant le graphe ecart
        for (int i = 0; i < g_Ecart.getEdgeCount(); i++) { // boucle allant de 0 au nombre de noeud
            Edge e = g_Ecart.getEdge(i); // on met dans e l'arc i du graphe ecart, i variant de 0 au nombre total d'arc - 1
            if (e.hasAttribute("toColor")) // si e a pour attribue toColor dans Graph_Ecart.dgs
                e.setAttribute("ui.style", "fill-color: red;");//on change sa couleur en rouge
        }
        g_Ecart.setAttribute("ui.stylesheet", "node {size: 23px;fill-color: green;}");// on change la taille et la couleur en vert
        for (Node n : g_Ecart) { //pour chanque noeud dans dans notre graphe écart
            n.setAttribute("ui.label", n.getId()); // on modifie l'attribue du noeud en son identifiant
        }
        g_Ecart.display(false); // on affiche le graphe écart
    }

}
