package org.example.cyphertransform.cypher;

import org.example.cyphertransform.cypher.algorithm.*;
import org.example.cyphertransform.cypher.schema.CypherSchema;
import org.example.cyphertransform.DBMSSpecificOptions;
import org.example.cyphertransform.MainOptions;
import org.example.cyphertransform.ProviderAdapter;

public abstract  class CypherProviderAdapter <G extends CypherGlobalState<O, S>, S extends CypherSchema<G,?>, O extends DBMSSpecificOptions> extends ProviderAdapter<G, O, CypherManager> {

    public CypherProviderAdapter(Class<G> globalClass, Class<O> optionClass) {
        super(globalClass, optionClass);
    }

    @Override
    public void generateAndTestDatabase(G globalState) throws Exception { //todo 主过程
        CypherTestingAlgorithm<S,G,O, CypherManager> algorithm;
        switch (globalState.getOptions().getAlgorithm()){
            case COMPARED3:
                algorithm = new Compared3AlgorithmNew<>(this);
                break;
            default:
                throw new RuntimeException();
        }
        algorithm.generateAndTestDatabase(globalState);
        System.gc();
    }

    @Override
    protected void checkViewsAreValid(G globalState){

    }

    public abstract CypherManager createDatabaseWithOptions(MainOptions mainOptions, O specificOptions) throws Exception;

}
