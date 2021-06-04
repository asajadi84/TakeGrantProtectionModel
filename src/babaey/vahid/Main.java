package babaey.vahid;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.AsUndirectedGraph;
import org.jgrapht.graph.SimpleDirectedGraph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {

        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        System.out.println(":: Take Grant Protection Model implementation by Vahid Bebaey ::");

        //initialize the graph
        Graph<MyGraphVertex, MyGraphEdge> theGraph = new SimpleDirectedGraph<>(MyGraphEdge.class);

        String tempName = "";
        String tempType = "";
        String commandString = "";

        //add vertices to graph
        System.out.println("Enter the first vertex name:");
        tempName = bufferedReader.readLine();
        System.out.println("Enter the first vertex type (enter subject for subject type / anything else for object type):");
        tempType = bufferedReader.readLine();

        theGraph.addVertex(new MyGraphVertex(tempName, tempType.equals("subject") ? MyGraphVertex.VertexType.SUBJECT : MyGraphVertex.VertexType.OBJECT));
        System.out.println("Vertex successfully added.");

        while (true){
            System.out.println("Add another vertex? (enter yes to accept / anything else to skip):");
            commandString = bufferedReader.readLine();
            if(commandString.equals("yes")){
                System.out.println("Enter the vertex name:");
                tempName = bufferedReader.readLine();
                System.out.println("Enter the vertex type (enter subject for subject type / anything else for object type):");
                tempType = bufferedReader.readLine();

                theGraph.addVertex(new MyGraphVertex(tempName, tempType.equals("subject") ? MyGraphVertex.VertexType.SUBJECT : MyGraphVertex.VertexType.OBJECT));
                System.out.println("Vertex successfully added.");
            }else{
                break;
            }
        }

        String tempStart = "";
        String tempEnd = "";
        String tempRule = "";

        //add edges to graph
        System.out.println("----------------------------------------------------------------");
        System.out.println("Now let's define edges. For the first edge enter the start node name:");
        tempStart = bufferedReader.readLine();
        System.out.println("For the first edge enter the end node name:");
        tempEnd = bufferedReader.readLine();
        System.out.println("For the first edge enter the rule type (take for take, grant for grant, anything else will be considered as R):");
        tempRule = bufferedReader.readLine();

        String finalTempStart = tempStart;
        String finalTempEnd = tempEnd;
        theGraph.addEdge(
                theGraph.vertexSet().stream().filter(myGraphVertex -> myGraphVertex.getVertexName().equals(finalTempStart)).findAny().get(),
                theGraph.vertexSet().stream().filter(myGraphVertex -> myGraphVertex.getVertexName().equals(finalTempEnd)).findAny().get(),
                new MyGraphEdge(tempRule.equals("take") ? MyGraphEdge.EdgeRule.TAKE : (tempRule.equals("grant") ? MyGraphEdge.EdgeRule.GRANT : MyGraphEdge.EdgeRule.R))
        );
        System.out.println("Edge successfully added.");

        while (true){
            System.out.println("Add another edge? (enter yes to accept / anything else to skip):");
            commandString = bufferedReader.readLine();
            if(commandString.equals("yes")){

                System.out.println("For the edge enter the start node name:");
                tempStart = bufferedReader.readLine();
                System.out.println("For the edge enter the end node name:");
                tempEnd = bufferedReader.readLine();
                System.out.println("For the edge enter the rule type (take for take, grant for grant, anything else will be considered as R):");
                tempRule = bufferedReader.readLine();

                String finalTempStart1 = tempStart;
                String finalTempEnd1 = tempEnd;
                theGraph.addEdge(
                        theGraph.vertexSet().stream().filter(myGraphVertex -> myGraphVertex.getVertexName().equals(finalTempStart1)).findAny().get(),
                        theGraph.vertexSet().stream().filter(myGraphVertex -> myGraphVertex.getVertexName().equals(finalTempEnd1)).findAny().get(),
                        new MyGraphEdge(tempRule.equals("take") ? MyGraphEdge.EdgeRule.TAKE : (tempRule.equals("grant") ? MyGraphEdge.EdgeRule.GRANT : MyGraphEdge.EdgeRule.R))
                );
                System.out.println("Edge successfully added.");

            }else{
                break;
            }
        }

        System.out.println("----------------------------------------------------------------");

        //ASCII ART
        System.out.println("");
        System.out.println("  _____          ______              ___ ");
        System.out.println(" / ___/__ ____  / __/ /  ___ _______/__ \\");
        System.out.println("/ /__/ _ `/ _ \\_\\ \\/ _ \\/ _ `/ __/ -_)__/");
        System.out.println("\\___/\\_,_/_//_/___/_//_/\\_,_/_/  \\__(_)  ");
        System.out.println("");

        System.out.println("----------------------------------------------------------------");

        System.out.println("Now let's check if canShare(alpha, x, y, g0) is true or not...");
        System.out.println("You've already defined g0, so the program only needs to know three others.");

        String vertexXName = "";
        String vertexYName = "";
        String alphaRule = "";
        System.out.println("Enter the vertex x name:");
        vertexXName = bufferedReader.readLine();
        String finalVertexXName = vertexXName;
        MyGraphVertex x = theGraph.vertexSet().stream().filter(myGraphVertex -> myGraphVertex.getVertexName().equals(finalVertexXName)).findAny().get();
        System.out.println("Enter the vertex y name:");
        vertexYName = bufferedReader.readLine();
        String finalVertexYName = vertexYName;
        MyGraphVertex y = theGraph.vertexSet().stream().filter(myGraphVertex -> myGraphVertex.getVertexName().equals(finalVertexYName)).findAny().get();
        System.out.println("Enter the rule you want to check (take for take, grant for grant, anything else will be considered as R):");
        alphaRule = bufferedReader.readLine();
        MyGraphEdge.EdgeRule alpha = alphaRule.equals("take") ? MyGraphEdge.EdgeRule.TAKE : (alphaRule.equals("grant") ? MyGraphEdge.EdgeRule.GRANT : MyGraphEdge.EdgeRule.R);

        System.out.println("----------------------------------------------------------------");

        Set<MyGraphVertex> t1 = theGraph.vertexSet();
        for(MyGraphVertex m11 : t1){
            System.out.println("vertex name: " + m11.getVertexName() + " - vertex type: " + m11.getVertexType());
            Set<MyGraphEdge> outEdges = theGraph.outgoingEdgesOf(m11);
            for(MyGraphEdge m22 : outEdges){
                System.out.println("    outgoingEdgeType: "+m22.getEdgeRule());
            }
        }
        System.out.println("x node: "+x.getVertexName()+" - type: "+x.getVertexType());
        System.out.println("y node: "+y.getVertexName()+" - type: "+y.getVertexType());
        System.out.println("alpha rule: "+alpha);

        Graph<MyGraphVertex, MyGraphEdge> theGraphUndirected = new AsUndirectedGraph<>(theGraph);

        System.out.println("----------------------------------------------------------------");

        System.out.println("The predicate canShare is true if and only if condition0 is true");
        System.out.println("condition 0: there is an edge from x to y in g0 labeled alpha...");

        boolean condition0 = false;
        MyGraphEdge condition0Edge = theGraph.getEdge(x, y);
        if(condition0Edge != null && condition0Edge.getEdgeRule().equals(alpha)){
            condition0 = true;
            System.out.println(">> Condition 0: true");
        }else{
            System.out.println(">> Condition 0: false");
        }

        System.out.println("----------------------------------------------------------------");
        System.out.println("...or if the following hold simultaneously:");

        boolean conditionA = false;
        boolean conditionB = false;
        boolean conditionC = false;
        boolean conditionD = false;

        Set<MyGraphVertex> myGraphVertexSet = theGraph.vertexSet();

        System.out.println("Condition A: There is a vertex, s ∈ g0 with s-to-y edge labeled alpha");
        MyGraphVertex s = null;
        for(MyGraphVertex forLoopVertex : myGraphVertexSet){
            MyGraphEdge forLoopEdge = theGraph.getEdge(forLoopVertex, y);
            if(forLoopEdge != null && forLoopEdge.getEdgeRule().equals(alpha)){
                conditionA = true;
                s = forLoopVertex;
            }
        }
        if(conditionA){
            System.out.println(">> Condition A: true");
        }else{
            System.out.println(">> Condition A: false");
        }

        System.out.println("Condition B: There exists a subject vertex x' such that x' = x or initially spans to x");
        MyGraphVertex xPrime = null;
        if(x.getVertexType().equals(MyGraphVertex.VertexType.SUBJECT)){
            conditionB = true;
            xPrime = x;
        }else{
            for(MyGraphVertex forLoopVertex : myGraphVertexSet){
                if(isV1InitiallySpansV2(theGraphUndirected, forLoopVertex, x)!=null){
                    conditionB = true;
                    xPrime = isV1InitiallySpansV2(theGraphUndirected, forLoopVertex, x);
                }
            }
        }
        if(conditionB){
            System.out.println(">> Condition B: true");
        }else{
            System.out.println(">> Condition B: false");
        }

        System.out.println("Condition C: There exists a subject vertex s' such that s' = s or s' terminally spans to s");
        MyGraphVertex sPrime = null;
        if(s!=null){

            if(s.getVertexType().equals(MyGraphVertex.VertexType.SUBJECT)){
                conditionC = true;
                sPrime = s;
                System.out.println(">> Condition C: true");
            }else{
                for(MyGraphVertex forLoopVertex : myGraphVertexSet){
                    if(isV1TerminallySpansV2(theGraphUndirected, forLoopVertex, x)!=null){
                        conditionC = true;
                        sPrime = isV1TerminallySpansV2(theGraphUndirected, forLoopVertex, x);
                    }
                }
                if(conditionC){
                    System.out.println(">> Condition C: true");
                }else{
                    System.out.println(">> Condition C: false");
                }
            }

        }else{
            System.out.println(">> Condition C: false");
        }

        System.out.println("Condition D: There exist islands l[0], l[1], ..., l[n] such that x' ∈ l[0], s' ∈ l[n] and there is a bridge from l[j] to l[j+1] (1 <= j <= n)");
        if(xPrime!=null && sPrime!=null){
            //First we should get the tg-path between xPrime and sPrime
            GraphPath<MyGraphVertex, MyGraphEdge> xPrimeToSPrime = DijkstraShortestPath.findPathBetween(theGraphUndirected, xPrime, sPrime);
            if(xPrimeToSPrime!=null){
                List<MyGraphEdge> xPrimeToSPrimeEdges = xPrimeToSPrime.getEdgeList();

                //Now we search through xPrimeToSPrimeEdges to find any sequence that could make a bridge
                //For that matter we use a temporary collection called tempBridgeCandidate:
                List<MyGraphEdge> tempBridgeCandidate = new ArrayList<>();

                //Each time an edge meets the conditions of being part of a bridge (source != subject || target != subject)
                //It will be added to tempBridgeCandidate list
                //On the other hand if we reach an island the candidate will be validate using isCandidateABridge
                for(int i = 0; i < xPrimeToSPrimeEdges.size(); i++){
                    boolean bothEdgeVerticesAreSubject =
                            theGraph.getEdgeSource(xPrimeToSPrimeEdges.get(i)).getVertexType() == MyGraphVertex.VertexType.SUBJECT &&
                                    theGraph.getEdgeTarget(xPrimeToSPrimeEdges.get(i)).getVertexType() == MyGraphVertex.VertexType.SUBJECT;

                    boolean onlySourceVertexIsSubject =
                            theGraph.getEdgeSource(xPrimeToSPrimeEdges.get(i)).getVertexType() == MyGraphVertex.VertexType.SUBJECT &&
                                    theGraph.getEdgeTarget(xPrimeToSPrimeEdges.get(i)).getVertexType() == MyGraphVertex.VertexType.OBJECT;

                    boolean onlyTargetVertexIsSubject =
                            theGraph.getEdgeSource(xPrimeToSPrimeEdges.get(i)).getVertexType() == MyGraphVertex.VertexType.OBJECT &&
                                    theGraph.getEdgeTarget(xPrimeToSPrimeEdges.get(i)).getVertexType() == MyGraphVertex.VertexType.SUBJECT;

                    boolean noVertexIsSubject =
                            theGraph.getEdgeSource(xPrimeToSPrimeEdges.get(i)).getVertexType() == MyGraphVertex.VertexType.OBJECT &&
                                    theGraph.getEdgeTarget(xPrimeToSPrimeEdges.get(i)).getVertexType() == MyGraphVertex.VertexType.OBJECT;

                    //if bothEdgeVerticesAreSubject => do nothing cause in this case we are still inside an island, so it should not be added to bridges list
                    if(noVertexIsSubject || onlySourceVertexIsSubject){
                        //we are in a potential bridge
                        tempBridgeCandidate.add(xPrimeToSPrimeEdges.get(i));
                    }else if(onlyTargetVertexIsSubject){
                        //just hit an island, the candidate should be checked
                        tempBridgeCandidate.add(xPrimeToSPrimeEdges.get(i));
                        if(isCandidateABridge(tempBridgeCandidate)){
                            //we have a bridge here
                            conditionD = true;
                            break;
                        }else{
                            //clear the candidate collection for the next candidate
                            tempBridgeCandidate.clear();
                        }
                    }
                }
            }
        }

        if(conditionD){
            System.out.println(">> Condition D: true");
        }else{
            System.out.println(">> Condition D: false");
        }

        System.out.println("----------------------------------------------------------------");
        boolean finalResult = condition0 || (conditionA && conditionB && conditionC && conditionD);
        if(finalResult){
            System.out.println(">> Final Result: true");
        }else{
            System.out.println(">> Final Result: false");
        }
    }

    private static MyGraphVertex isV1InitiallySpansV2(Graph<MyGraphVertex, MyGraphEdge> graph, MyGraphVertex v1, MyGraphVertex v2){
        if(v1.getVertexType().equals(MyGraphVertex.VertexType.SUBJECT)){
            return v1;
        }else{
            //get v1 to v2 tg-path
            GraphPath<MyGraphVertex, MyGraphEdge> tgpath = DijkstraShortestPath.findPathBetween(graph, v1, v2);
            if(tgpath!=null){
                List<MyGraphEdge> myGraphEdges = tgpath.getEdgeList();
                //check the t* g pattern
                MyGraphVertex patternFlag = v2;
                for(int i = 0; i < myGraphEdges.size(); i++){
                    if(i != myGraphEdges.size()-1){
                        if(!myGraphEdges.get(i).getEdgeRule().equals(MyGraphEdge.EdgeRule.TAKE)){
                            patternFlag = null;
                        }
                    }else{
                        if(!myGraphEdges.get(i).getEdgeRule().equals(MyGraphEdge.EdgeRule.GRANT)){
                            patternFlag = null;
                        }
                    }
                }
                return patternFlag;
            }else{
                return null;
            }
        }
    }

    private static MyGraphVertex isV1TerminallySpansV2(Graph<MyGraphVertex, MyGraphEdge> graph, MyGraphVertex v1, MyGraphVertex v2){
        if(v1.getVertexType().equals(MyGraphVertex.VertexType.SUBJECT)){
            return v1;
        }else{
            //get v1 to v2 tg-path
            GraphPath<MyGraphVertex, MyGraphEdge> tgpath = DijkstraShortestPath.findPathBetween(graph, v1, v2);
            if(tgpath!=null){
                List<MyGraphEdge> myGraphEdges = tgpath.getEdgeList();
                //check the t* pattern
                MyGraphVertex patternFlag = v2;
                for(int i = 0; i < myGraphEdges.size(); i++){
                    if(!myGraphEdges.get(i).getEdgeRule().equals(MyGraphEdge.EdgeRule.TAKE)){
                        patternFlag = null;
                    }
                }
                return patternFlag;
            }else{
                return null;
            }
        }
    }

    private static boolean isCandidateABridge(List<MyGraphEdge> candidate){

        //There are two cases which this method would need to return true
        //Scenario 1: the candidate follows t* pattern
        //Scenario 2: the candidate follows t* g t* pattern
        //at first we consider these assumptions true and in case of any counterexample we make them false
        boolean scenario1 = true;
        boolean scenario2 = true;

        if(candidate.size()>0){
            //if candidate size is 1 either t or g would be accepted
            if(candidate.size()==1){
                return true;
            }else{
                int numberOfGs = 0; //should not be more than one
                for(int i = 0; i<candidate.size(); i++){
                    if(candidate.get(i).getEdgeRule() == MyGraphEdge.EdgeRule.R){
                        scenario1 = false;
                        scenario2 = false;
                    }else if(candidate.get(i).getEdgeRule() == MyGraphEdge.EdgeRule.GRANT){
                        scenario1 = false;
                        numberOfGs++;
                    }
                }
                if(numberOfGs>1){
                    scenario2 = false;
                }

                return scenario1 || scenario2;
            }

        }else{
            return false;
        }
    }
}
