package org.example.graspdb.cypher.algorithm;

import org.example.graspdb.*;
import org.example.graspdb.common.oracle.TestOracle;
import org.example.graspdb.cypher.CypherManager;
import org.example.graspdb.cypher.CypherGlobalState;
import org.example.graspdb.cypher.CypherProviderAdapter;
import org.example.graspdb.cypher.CypherQueryAdapter;
import org.example.graspdb.cypher.gen.graph.SlidingGraphGenerator;
import org.example.graspdb.cypher.gen.query.SlidingQueryGenerator;
import org.example.graspdb.cypher.oracle.DifferentialNonEmptyBranchOracle;
import org.example.graspdb.cypher.schema.CypherSchema;
import org.example.graspdb.cypher.gen.GraphManager;

import java.util.ArrayList;
import java.util.List;

public class Compared3AlgorithmNew<S extends CypherSchema<G,?>, G extends CypherGlobalState<O, S>,
        O extends DBMSSpecificOptions, C extends CypherManager> implements CypherTestingAlgorithm<S, G, O, C> {
    private GraphManager graphManager;
    public List<CypherQueryAdapter> qureys;

    public Compared3AlgorithmNew(CypherProviderAdapter<G, S, O> provider) {
        super();
        this.qureys=new ArrayList<>();
    }

    @Override
    public void generateAndTestDatabase(G globalState) throws Exception {
        try {
            int j=0;
            generateDatabase(globalState); //插入了初始的图数据
            globalState.getManager().incrementCreateDatabase(); //原子操作计数
            List<String> test_next_try = new ArrayList<>();
            TestOracle oracle = new DifferentialNonEmptyBranchOracle<G, S>(globalState, new SlidingQueryGenerator<>(graphManager,globalState));
            for (int i = 0; i < globalState.getOptions().getNrQueries(); j++,i++) {
                //todo 进行下一次try
                if(test_next_try.size()!=0)
                {
                    test_next_try.clear();
                    break;
                }
                try (StateToReproduce.OracleRunReproductionState localState = globalState.getState().createLocalState()) {
                    assert localState != null;
                    try {
                        oracle.check();
                        globalState.getManager().incrementSelectQueryCount();
                    }
                    catch (RuntimeException e){
                        oracle.getFw().flush();
                        if((e.getMessage()!=null)&&((e.getMessage().equals("mismatch database state!"))||e.getMessage().contains("scriptEvaluationTimeout")||e.getMessage().contains("a specific database failed and total time")))
                        { test_next_try.add("test next database");}
                        else{
                            System.out.println("other types of Exception in "+globalState.getDatabaseName());
                            e.printStackTrace();
                            throw e;
                        }
                    }
                    assert localState != null;
                    localState.executedWithoutError();
                }
                catch (RuntimeException e){
                    throw e;
                }
            }
            oracle.getFw().flush();
            oracle.getFw().close();
            DifferentialNonEmptyBranchOracle.test_pair_num+=j;
            throw new RuntimeException("total number reached:"+j);
        }
        catch (RuntimeException e){
            throw e;
        } finally {
            globalState.getConnection().close();
        }
    }

    public void generateDatabase(G globalState) throws Exception{
        SlidingGraphGenerator<G,S> generator = new SlidingGraphGenerator<>(globalState);
        this.graphManager = generator.getGraphManager();
        this.qureys = generator.createGraph(globalState);
        for(CypherQueryAdapter query : this.qureys){
            System.out.println(query+";");
            globalState.executeStatement(query);
            globalState.getState().logCreateStatement(query);
        }
    }
}
