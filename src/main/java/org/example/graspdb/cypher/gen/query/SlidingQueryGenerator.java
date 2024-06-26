package org.example.graspdb.cypher.gen.query;

import org.example.graspdb.cypher.CypherGlobalState;
import org.example.graspdb.cypher.ast.IClauseSequence;
import org.example.graspdb.cypher.gen.GraphManager;
import org.example.graspdb.cypher.oracle.DifferentialNonEmptyBranchOracle;
import org.example.graspdb.cypher.schema.CypherSchema;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SlidingQueryGenerator<S extends CypherSchema<G,?>,G extends CypherGlobalState<?,S>> extends GraphGuidedQueryGenerator<S,G> {
    private final G globalstate;
    public GraphManager graphManager;
    //cannot work on concurrent mode
    public static boolean[] totalCoverage = new boolean[DifferentialNonEmptyBranchOracle.BRANCH_SIZE];
    public static boolean[] totalNonEmptyCoverage = new boolean[DifferentialNonEmptyBranchOracle.BRANCH_SIZE];

    public static File coverageFile;

    public static FileOutputStream outputStream;

    public static int coverageNum = 0;
    public static int nonEmptyCoverageNum = 0;
    public G getGlobalstate(){
        return this.globalstate;
    }
    public GraphManager getGraphManager(){
        return this.graphManager;
    }
    static {
        coverageFile = new File("coverage_log");
        if(coverageFile.exists()){
            coverageFile.delete();
        }
        try {
            coverageFile.createNewFile();
            outputStream = new FileOutputStream(coverageFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static class Seed{
        IClauseSequence sequence;
        boolean bugDetected;
        int resultLength;

        byte[] branchInfo;
        byte[] branchPairInfo;

        public Seed(IClauseSequence sequence, boolean bugDetected, int resultLength, byte[] branchInfo, byte[] branchPairInfo){
            this.sequence = sequence;
            this.bugDetected = bugDetected;
            this.resultLength = resultLength;
            this.branchInfo = branchInfo;
            this.branchPairInfo = branchPairInfo;
        }
    }

    protected List<Seed> seeds = new ArrayList<>();


    public SlidingQueryGenerator(GraphManager graphManager,G globalstate) {
        super(graphManager);
        this.globalstate=globalstate;
        this.graphManager=graphManager;
    }

    @Override
    public boolean shouldDoMutation(G globalState) {
        return false;
//        return new Randomly().getInteger(0, 100) < seeds.size();
    }

    public static void writeInfoln(String info){
        try {
            outputStream.write((""+System.currentTimeMillis()+"\n"+info+"\n").getBytes());
            outputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static boolean checkAndRecordUncoveredBranch(byte[] branchInfo){
        boolean found = false;
        for(int i = 0; i < DifferentialNonEmptyBranchOracle.BRANCH_SIZE; i++){
            if(branchInfo[i] != 0 && !totalCoverage[i]){
                found = true;
                totalCoverage[i] = true;
                coverageNum++;
            }
        }
        return found;
    }

    public static boolean checkAndRecordUncoveredNonEmptyBranch(byte[] branchInfo){
        boolean found = false;
        for(int i = 0; i < DifferentialNonEmptyBranchOracle.BRANCH_SIZE; i++){
            if(branchInfo[i] != 0 && !totalNonEmptyCoverage[i]){
                found = true;
                totalNonEmptyCoverage[i] = true;
                nonEmptyCoverageNum++;
            }
        }

        return found;
    }

    public static boolean checkPossibleUncoveredNonEmptyBranch(byte[] branchInfo){
        boolean found = false;
        int coverageNum = 0;
        for(int i = 0; i < DifferentialNonEmptyBranchOracle.BRANCH_SIZE; i++){
            if(branchInfo[i] != 0 && !totalNonEmptyCoverage[i]){
                found = true;
            }
            if(totalNonEmptyCoverage[i]){
                coverageNum++;
            }
        }
        return found;
    }

    public void reduceSeeds(){
        List<Seed> deletedSeeds = new ArrayList<>();
        for(Seed seed : seeds){
            if(!checkPossibleUncoveredNonEmptyBranch(seed.branchInfo)){
                deletedSeeds.add(seed);
            }
        }
        seeds.removeAll(deletedSeeds);
    }

    @Override
    public void addNewRecord(IClauseSequence sequence, boolean bugDetected, int resultLength, byte[] branchInfo, byte[] branchPairInfo){
        writeInfoln("coverage: "+coverageNum);
        writeInfoln("non_empty_coverage: "+nonEmptyCoverageNum);
        writeInfoln("result_size: "+resultLength);
    }
}
