package Lab14;

//Author: Ori Weiss
//Date: 5/6/2018
import java.util.Stack;

public class UndirectedGraph<T> extends DirectedGraph<T> implements GraphInterface<T>, java.io.Serializable
        ,ConnectedGraphInterface<T>{

    public UndirectedGraph(){
        super();
    }
    @Override
    public boolean addEdge(T begin, T end, double edgeWeight) {
        return  super.addEdge(begin, end, edgeWeight) && super.addEdge(end, begin,edgeWeight);
    }

    @Override
    public boolean addEdge(T begin, T end) {
        return this.addEdge(begin, end,0);
    }


    @Override
    public int getNumberOfEdges() {
        return super.getNumberOfEdges()/2;
    }

    @Override
    public Stack<T> getTopologicalOrder() throws UnsupportedOperationException{
        throw new UnsupportedOperationException("Topological sort illegal in an undirected graph.");
    }
    public boolean isConnected(T origin){
        int verticesInGraph = this.getNumberOfVertices();
        int graphSize = getBreadthFirstTraversal(origin).size();
        if(graphSize == verticesInGraph){
            return true;
        }
        else{
            return false;
        }
    }
}
