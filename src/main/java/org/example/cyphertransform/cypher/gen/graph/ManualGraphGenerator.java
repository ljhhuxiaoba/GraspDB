package org.example.cyphertransform.cypher.gen.graph;

import org.example.cyphertransform.cypher.CypherGlobalState;
import org.example.cyphertransform.cypher.CypherQueryAdapter;
import org.example.cyphertransform.cypher.dsl.IGraphGenerator;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class ManualGraphGenerator <G extends CypherGlobalState<?,?>> implements IGraphGenerator<G> {

    private String filePath;
    private List<CypherQueryAdapter> queries;

    public ManualGraphGenerator(){

    }

    public void loadFile(String filePath){
        this.filePath = filePath;
        File file = new File(filePath);
        if(!file.exists()){
            throw new RuntimeException();
        }

        try(FileReader fileReader = new FileReader(file)){
            try (BufferedReader bufferedReader = new BufferedReader(fileReader)){
                queries = bufferedReader.lines().map(s->new CypherQueryAdapter(s)).collect(Collectors.toList());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<CypherQueryAdapter> createGraph(G globalState) throws Exception {
        return queries;
    }

}
