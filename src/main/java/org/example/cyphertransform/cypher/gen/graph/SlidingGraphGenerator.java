package org.example.cyphertransform.cypher.gen.graph;

import org.example.cyphertransform.Randomly;
import org.example.cyphertransform.cypher.CypherGlobalState;
import org.example.cyphertransform.cypher.CypherQueryAdapter;
import org.example.cyphertransform.cypher.ast.IPattern;
import org.example.cyphertransform.cypher.dsl.IGraphGenerator;
import org.example.cyphertransform.cypher.gen.GraphManager;
import org.example.cyphertransform.cypher.schema.CypherSchema;
import org.example.cyphertransform.cypher.standard_ast.CypherType;
import org.example.cyphertransform.cypher.standard_ast.expr.ConstExpression;

import java.util.List;

public class SlidingGraphGenerator<G extends CypherGlobalState<?,S>, S extends CypherSchema<G,?>> implements IGraphGenerator<G> {

    private static int minNumOfNodes = 200;
    private static int maxNumOfNodes = 200;
    private static double percentOfEdges = 0.001;
    private static List<IPattern> INodesPattern;

    private final G globalState;


    private GraphManager graphManager;


    public SlidingGraphGenerator(G globalState){
        this.globalState = globalState;
        graphManager = new GraphManager(globalState.getSchema(), globalState.getOptions());
    }

    // todo(rly): handle Exception
    private ConstExpression generatePropertyValue(Randomly r, CypherType type) throws Exception {
        switch (type){
            case NUMBER: return new ConstExpression(r.getInteger());
            case STRING: return new ConstExpression(r.getString());
            case BOOLEAN: return new ConstExpression(r.getInteger(0, 2) == 0);
            default:
                throw new Exception("undefined type in generator!");
        }
    }

    public GraphManager getGraphManager(){
        return graphManager;
    }


    @Override
    public List<CypherQueryAdapter> createGraph(G globalState) throws Exception {
        List<CypherQueryAdapter> queries = graphManager.generateCreateGraphQueries();
        return queries;
    }
}

