/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Struktura_jung;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.ISOMLayout;
import edu.uci.ics.jung.algorithms.layout.KKLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.SpringLayout;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.EdgeShape;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Stack;
import model.nodeShape;
import org.apache.commons.collections15.Transformer;
import painter.*;


/**
 *
 * @author Epifaniusz jak już to napiszę to będzie robiła przerabianie grafu na
 * tabelkę sąsiadów i przerabianie warunków na graf i jeszcze przerabanie grafu
 * na viewer
 */
public class Przerob {

    /**
     * Zwraca tabelę klas Wiersz reprezentującą graf, ID wierzchołka powinno
     * zgadzać się z indeksem tablicy
     *
     * @param g Graf zgodny z naszą strukturą, czyli UndirectedSparseGraph<node,
     * Integer>
     * @return
     */
    public static int kolorujSL(UndirectedSparseGraph<node, Integer> gr) { //zwraca lbe kolorow
        UndirectedSparseGraph<node, Integer> g = new UndirectedSparseGraph<>();  //tymczasowa kopia grafu
        for (node v : gr.getVertices()) {               //tu kopiowanie do nowego grafu
            g.addVertex(v);
        }
        for (int e : gr.getEdges()) {
            g.addEdge(e, gr.getIncidentVertices(e));
        }   //

        node w = new node(-1, nodeShape.RECTANGLE);
        int minst = Integer.MAX_VALUE;       //jaki jest maksymalny rozmiar inta?
        //System.out.println("Tyle wejdzie do inta: "+minst);
        int tymst;
        Stack<node> stos = new Stack<>();

        while (g.getVertexCount() > 0) {    // w tym whilu dokładam do stosu wierzchołki
            minst=Integer.MAX_VALUE;
            for (node w1 : g.getVertices()) {   //tu się wysypuje przy osobnym wierzcholku
                tymst = g.degree(w1);
                if (tymst < minst) {
                    minst = tymst;
                    w = w1;
                }
            }
            stos.push(w);
            //System.out.println("vertexcount: "+g.getVertexCount());
            if (!g.removeVertex(w)) System.out.println("Nie usunąłem");
            //System.out.println("vertexcount: "+g.getVertexCount()+" po usunieciu");
        }
        //System.out.println("Wsadziłem wszystko na stos");
        int chromatyczna = 0;
        boolean[] uzyte = new boolean[gr.getVertexCount()];
        //System.out.println("Wynik powinien byc dlugi na: "+uzyte.length);
        ArrayList<Integer> wynikaraj=new ArrayList<>();
        
        for (int i = 0; i < uzyte.length; i++) {
            uzyte[i] = false;
            wynikaraj.add(0);
        }       //na wszelki wypadek
        
        
        while (!stos.empty()) {
            //zapisz do uzyte kolorki:
            w = stos.pop();
            for (node w1 : gr.getNeighbors(w)) {
              uzyte[w1.getColor()] = true; //System.out.print("uzyte["+w1.getColor()+"] ");
            }
            //pokoloruj wierzchołek zachłannie
            //i zapisz do arraylista
            for (int i = 1; i < uzyte.length; i++) {
                if (!uzyte[i]) {
                    wynikaraj.set(w.getID(), i);
                    //System.out.println("\nwynik ma tymczasowo size: "+wynik.length+" a wpisałem do niego na miejscu: "+w.getID()+" wartość: "+i);
                    break;
                }
            }
            for (int i = 1; i < uzyte.length; i++) {uzyte[i] = false;}
            
            przemaluj(gr, wynikaraj);
        }
        int lbakolorow=0;
        boolean zero=false;
        for (int i=0;i<wynikaraj.size();i++){
            if (lbakolorow<wynikaraj.get(i)){lbakolorow=wynikaraj.get(i);}
            if (wynikaraj.get(i)==0) zero=true;
        }
        return zero?++lbakolorow:lbakolorow;
    }

    public static Wiersz[] graf_tabelka(UndirectedSparseGraph<node, Integer> g) { //powinno zwracać tablicę klas Wiersz
        Wiersz[] tabela = new Wiersz[g.getVertexCount()];
        int i = 0;
        for (node w1 : g.getVertices()) {

            i = w1.getID();
            tabela[i] = new Wiersz();
            tabela[i].kolor = w1.getColor();
            for (node w2 : g.getNeighbors(w1)) {
                tabela[i].sasiedzi.add(w2.getID());
            }
        }
        return tabela;
    }

    public static void przemaluj(UndirectedSparseGraph<node, Integer> g, ArrayList<Integer> kolorki) {
        if (kolorki.size() != g.getVertexCount()) {
            System.out.println("Do przemaluj ktoś dał inną ilość kolorków, niż wierzchołków. Nie pomalujesz. kolorków jest "+kolorki.size()+"a wierzchołków "+g.getVertexCount());
        } else {
            for (node w1 : g.getVertices()) {
                w1.setColor(kolorki.get(w1.getID()));
            }
        }
    }

    public static DirectedSparseGraph<node, Integer> daj_graf(boolean planarny, int ile_wierzcholkow, double gestosc) {
        DirectedSparseGraph<node, Integer> g = null;

//            ErdosRenyiGenerator gen;            //pasuje dołożyć inne generatory, jak się dowiem jakie grafy one generują
//            Factory<DirectedGraph<node, Integer>> faktoria = DirectedSparseGraph.getFactory();
//            gen = new ErdosRenyiGenerator(faktoria,
//                    new CircVertexFactory(), new EdgeFactory(),
//                    ile_wierzcholkow, gestosc);
//            Graph graf = gen.create();
            g = new DirectedSparseGraph<node, Integer>();
       
        return g;
    }

    public static VisualizationViewer<node, Integer> daj_panel(Graph g, String layout, int szerokosc, int wysokosc) {
        Layout<node, Integer> lay = null;
        if (layout.equalsIgnoreCase("ISOM")) {
            lay = new ISOMLayout(g);
        } else if (layout.equalsIgnoreCase("KK")) {
            lay = new KKLayout(g);
        } else if (layout.equalsIgnoreCase("FR")) {
            lay = new FRLayout(g);
        } else if (layout.equalsIgnoreCase("SPRING")) {
            lay = new SpringLayout(g);
        } else if (layout.equalsIgnoreCase("CIRCLE")) {
            lay = new CircleLayout(g);
        } else if (layout.equalsIgnoreCase("SPRING2")) {
            lay = new SpringLayout(g);
        } else if (layout.equalsIgnoreCase("static")) {
            lay=new StaticLayout<>(g, new lokator());
        }
        
        lay.setSize(new Dimension(szerokosc, wysokosc));
        VisualizationViewer<node, Integer> vv =
                new VisualizationViewer<node, Integer>(lay,
                new Dimension(szerokosc, wysokosc));
        DefaultModalGraphMouse gm = new DefaultModalGraphMouse();
        gm.setMode(ModalGraphMouse.Mode.TRANSFORMING);
        vv.setGraphMouse(gm);
        //opis wierzcholkow
        vv.setVertexToolTipTransformer(new Transformer<node, String>() {
            @Override
            public String transform(node w) {
                //return "ID: " + Integer.toString(w.getID()) + " kolor: " + Integer.toString(w.getColor()) + Float.toString(w.getX()) + Float.toString(w.getY());
                return "ID: " + Integer.toString(w.getID()) + " kolor: " + Integer.toString(w.getColor());
            }
        });

        vv.getRenderContext().setEdgeShapeTransformer(new EdgeShape.Line<node, Integer>());
        vv.getRenderContext().setVertexFillPaintTransformer(new VertexColorPainter());
        vv.getRenderContext().setVertexShapeTransformer(new VertexShapePainter());
        //vv.getRenderContext().setVertexLabelRenderer(null);
        return vv;

    }
}
