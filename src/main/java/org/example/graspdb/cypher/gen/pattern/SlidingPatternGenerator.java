package org.example.graspdb.cypher.gen.pattern;

import org.example.graspdb.Randomly;
import org.example.graspdb.cypher.ast.INodeIdentifier;
import org.example.graspdb.cypher.ast.IPattern;
import org.example.graspdb.cypher.ast.IPatternElement;
import org.example.graspdb.cypher.ast.IRelationIdentifier;
import org.example.graspdb.cypher.ast.analyzer.IClauseAnalyzer;
import org.example.graspdb.cypher.ast.analyzer.ICreateAnalyzer;
import org.example.graspdb.cypher.ast.analyzer.IMatchAnalyzer;
import org.example.graspdb.cypher.ast.analyzer.IMergeAnalyzer;
import org.example.graspdb.cypher.dsl.BasicPatternGenerator;
import org.example.graspdb.cypher.dsl.IIdentifierBuilder;
import org.example.graspdb.cypher.gen.*;
import org.example.graspdb.cypher.schema.CypherSchema;
import org.example.graspdb.cypher.schema.ILabelInfo;
import org.example.graspdb.cypher.standard_ast.*;
import org.example.graspdb.cypher.standard_ast.expr.GraphObjectVal;
//import org.mariadb.jdbc.internal.com.read.resultset.SelectResultSet;

import java.util.*;

import static java.lang.Math.min;

public class SlidingPatternGenerator <S extends CypherSchema<?,?>> extends BasicPatternGenerator<S> {
    private List<SubgraphTreeNode> selected = new ArrayList<>();
    private GraphManager graphManager;
    private int presentNum = 0;

    private boolean overrideOld;

    private Randomly randomly = new Randomly();

    private Map<String, Object> varToProperties;


    private class PatternCluster{
        List<SubgraphTreeNodeInstance> treeNodes;
        Map<AbstractNode, INodeIdentifier> nodeMap = new HashMap<>();
        Map<AbstractRelationship, IRelationIdentifier> relationMap = new HashMap<>();

        List<IPattern> recordedClusters = new ArrayList<>();
    }

    private List<PatternCluster> patternClusters = new ArrayList<>();


    public SlidingPatternGenerator(S schema, Map<String, Object> varToProperties, GraphManager graphManager, IIdentifierBuilder identifierBuilder, boolean overrideOld) {
        super(schema, identifierBuilder);
        this.graphManager = graphManager;
        int num = randomly.getInteger(1, 4);

        for(int i = 0; i < num; i++){
            PatternCluster cluster = new PatternCluster();
            cluster.nodeMap = new HashMap<>();
            cluster.relationMap = new HashMap<>();

            cluster.treeNodes = new ArrayList<>(graphManager.randomCluster());
            if(cluster.treeNodes.size() == 0){
                int z = 0;
            }
            patternClusters.add(cluster);
        }
        this.overrideOld = overrideOld;
        this.varToProperties = varToProperties;
    }

    @Override
    public List<IPattern> generatePattern(IMatchAnalyzer matchClause, IIdentifierBuilder identifierBuilder, S schema) {
        if (matchClause.getPatternTuple().size()!=0 && !overrideOld) {
            return matchClause.getPatternTuple();
        }
        //match可能在match、create和merge中嵌套
        CypherClause old_cypherClause=Pattern.cypherClause;
        Pattern.cypherClause=(Match)matchClause;
        List<IPattern> patterns = new ArrayList<>();
        //最多同时匹配两个模式
        int r=randomly.getInteger(1, 3);
        for(int i = 0; i < r; i++){
            int clusterIndex = randomly.getInteger(0, patternClusters.size());
            PatternCluster patternCluster = patternClusters.get(clusterIndex);
            List<SubgraphTreeNodeInstance> treeNodeInstances = patternCluster.treeNodes;
            Map<AbstractNode, INodeIdentifier> nodeMap = patternCluster.nodeMap;
            Map<AbstractRelationship, IRelationIdentifier> relationMap = patternCluster.relationMap;


            SubgraphTreeNodeInstance treeNodeInstance = treeNodeInstances.get(randomly.getInteger(0, treeNodeInstances.size()));
            Subgraph subgraph = treeNodeInstance.getTreeNode().getSubgraph();
            IPattern pattern = subgraph.translateMatch(matchClause,identifierBuilder, nodeMap, relationMap);
            //加入模式
            ((Match)matchClause).symtab.addPattern(pattern);
            patterns.add(pattern);
        }
        Pattern.cypherClause=old_cypherClause;
        return patterns;
    }

    public IPattern generateComprehensionPattern(IClauseAnalyzer clauseAnalyzer, IIdentifierBuilder identifierBuilder, S schema) {
            Pattern.pattern_in_comprehension=true;
            int clusterIndex = randomly.getInteger(0, patternClusters.size());
            PatternCluster patternCluster = patternClusters.get(clusterIndex);
            List<SubgraphTreeNodeInstance> treeNodeInstances = patternCluster.treeNodes;
            Map<AbstractNode, INodeIdentifier> nodeMap = patternCluster.nodeMap;
            Map<AbstractRelationship, IRelationIdentifier> relationMap = patternCluster.relationMap;


            SubgraphTreeNodeInstance treeNodeInstance = treeNodeInstances.get(randomly.getInteger(0, treeNodeInstances.size()));
            Subgraph subgraph = treeNodeInstance.getTreeNode().getSubgraph();
            IPattern pattern = subgraph.translateComprehension(clauseAnalyzer,identifierBuilder, nodeMap, relationMap);
            if(pattern.getPatternElements().size() == 1)
                throw new RuntimeException();

            reducePattern(treeNodeInstance, pattern, subgraph, nodeMap, relationMap);
            if(pattern.getPatternElements().size() == 1)
                throw new RuntimeException();

            Pattern.pattern_in_comprehension=false;
        return pattern;
    }

    @Override
    public IPattern generatePattern(IMergeAnalyzer mergeClause, IIdentifierBuilder identifierBuilder, S schema) {
        CypherClause old_cypherClause=Pattern.cypherClause;
        Pattern.cypherClause=(Merge)mergeClause;
        if (mergeClause.getPattern()!=null && !overrideOld) {
            return mergeClause.getPattern();
        }
            int clusterIndex = randomly.getInteger(0, patternClusters.size());
            PatternCluster patternCluster = patternClusters.get(clusterIndex);
            List<SubgraphTreeNodeInstance> treeNodeInstances = patternCluster.treeNodes;
            Map<AbstractNode, INodeIdentifier> nodeMap = patternCluster.nodeMap;
            Map<AbstractRelationship, IRelationIdentifier> relationMap = patternCluster.relationMap;

            SubgraphTreeNodeInstance treeNodeInstance = treeNodeInstances.get(randomly.getInteger(0, treeNodeInstances.size()));
            Subgraph subgraph = treeNodeInstance.getTreeNode().getSubgraph();
            IPattern pattern = subgraph.translateMerge(mergeClause,identifierBuilder, nodeMap, relationMap);

//            reducePattern(treeNodeInstance, pattern, subgraph, nodeMap, relationMap);

            ((Merge) mergeClause).symtab.addPattern(pattern);
            Pattern.cypherClause=old_cypherClause;
        return pattern;
    }
//todo 用于exists函数的单模式
    public IPattern generateSinglePattern(IClauseAnalyzer clauseAnalyzer, IIdentifierBuilder identifierBuilder, S schema) {
        CypherClause old_cypherClause=Pattern.cypherClause;
        Pattern.cypherClause=(CypherClause) clauseAnalyzer;
        int clusterIndex = randomly.getInteger(0, patternClusters.size());
        PatternCluster patternCluster = patternClusters.get(clusterIndex);
        List<SubgraphTreeNodeInstance> treeNodeInstances = patternCluster.treeNodes;
        Map<AbstractNode, INodeIdentifier> nodeMap = patternCluster.nodeMap;
        Map<AbstractRelationship, IRelationIdentifier> relationMap = patternCluster.relationMap;


        SubgraphTreeNodeInstance treeNodeInstance = treeNodeInstances.get(randomly.getInteger(0, treeNodeInstances.size()));
        Subgraph subgraph = treeNodeInstance.getTreeNode().getSubgraph();
        IPattern pattern = subgraph.translateSingle(clauseAnalyzer,identifierBuilder, nodeMap, relationMap);

        reducePattern(treeNodeInstance, pattern, subgraph, nodeMap, relationMap);

        Pattern.cypherClause=old_cypherClause;
        return pattern;
    }

    @Override
    public List<IPattern> generatePattern(ICreateAnalyzer createClause, IIdentifierBuilder identifierBuilder, S schema) {
        CypherClause old_cypherClause=Pattern.cypherClause;
        Pattern.cypherClause=(Create)createClause;
        if (createClause.getPatternTuple().size()!=0 && !overrideOld) {
            return createClause.getPatternTuple();
        }
        List<IPattern> patterns = new ArrayList<>();
        for(int i = 0; i < randomly.getInteger(1, 3); i++){
            int clusterIndex = randomly.getInteger(0, patternClusters.size());
            PatternCluster patternCluster = patternClusters.get(clusterIndex);
            List<SubgraphTreeNodeInstance> treeNodeInstances = patternCluster.treeNodes;
            Map<AbstractNode, INodeIdentifier> nodeMap = patternCluster.nodeMap;
            Map<AbstractRelationship, IRelationIdentifier> relationMap = patternCluster.relationMap;


            SubgraphTreeNodeInstance treeNodeInstance = treeNodeInstances.get(randomly.getInteger(0, treeNodeInstances.size()));
            Subgraph subgraph = treeNodeInstance.getTreeNode().getSubgraph();
            IPattern pattern = subgraph.translateCreate(identifierBuilder);

//            reducePattern(treeNodeInstance, pattern, subgraph, nodeMap, relationMap);

            //生成完一个模式后才可以加入节点和关系
            for(IPatternElement patternElement:pattern.getPatternElements()){
                if(patternElement instanceof INodeIdentifier)
                    ((Create)createClause).symtab.addNode((INodeIdentifier)patternElement);
                if(patternElement instanceof IRelationIdentifier)
                    ((Create)createClause).symtab.addRelation((IRelationIdentifier)patternElement);
            }
            //加入模式
            ((Create)createClause).symtab.addPattern(pattern);
            patterns.add(pattern);
        }
        Pattern.cypherClause=old_cypherClause;
        return patterns;
    }
//将三个节点的模式以一定概率缩减为两个节点(不会缩减非匿名节点或边变量)
    private void shrinkPattern(IPattern pattern){
        int randNum = randomly.getInteger(0, 100);
        INodeIdentifier nodeIdentifier1 = (INodeIdentifier) pattern.getPatternElements().get(0);
        INodeIdentifier nodeIdentifier2 = (INodeIdentifier) pattern.getPatternElements().get(4);
        IRelationIdentifier relationIdentifier1 = (IRelationIdentifier) pattern.getPatternElements().get(1);
        IRelationIdentifier relationIdentifier2 = (IRelationIdentifier) pattern.getPatternElements().get(3);
        if(randNum<30)
            return;
        if(relationIdentifier1.isAnonymous()&&nodeIdentifier1.isAnonymous()){
            pattern.setPatternElements(pattern.getPatternElements().subList(2, 5));
            return;
        }
        if(nodeIdentifier2.isAnonymous() && relationIdentifier2.isAnonymous()){
            pattern.setPatternElements(pattern.getPatternElements().subList(0, 3));
        }
    }

    private void editLabels(int id, INodeIdentifier nodeIdentifier, AbstractNode abstractNode){
        while (randomly.getInteger(0, 100) < 20){
            if(nodeIdentifier.getLabels().size() != 0){
                nodeIdentifier.getLabels().remove(0);
            }
        }
        for(ILabelInfo labelInfo : abstractNode.getLabelInfos()){
            if(nodeIdentifier.getLabels().stream().noneMatch(l->l.getName().equals(labelInfo.getName()))){
                if(randomly.getInteger(0, 100) < 30){
                    nodeIdentifier.getLabels().add(new Label(labelInfo.getName()));
                }
            }
        }
    }

    private void recordPropertyInfo(INodeIdentifier nodeIdentifier, AbstractNode abstractNode, Map<String, Object> properties){
        varToProperties.put(nodeIdentifier.getName(), new GraphObjectVal(nodeIdentifier.getName(), properties));
    }

    private void recordPropertyInfo(IRelationIdentifier relationIdentifier, AbstractRelationship abstractRelation, Map<String, Object> properties){
        varToProperties.put(relationIdentifier.getName(), new GraphObjectVal(relationIdentifier.getName(), properties));
    }

    private void editRelationTypes(int id, IRelationIdentifier relationIdentifier, AbstractRelationship abstractRelationship){
//        relationIdentifier.getProperties().add(new Property("id", CypherType.NUMBER, new ConstExpression(id)));
        if(relationIdentifier.getTypes().size() != 0 && randomly.getInteger(0, 100) < 20){
            relationIdentifier.getTypes().remove(0);
        }
        else if(randomly.getInteger(0, 100) < 30){
            relationIdentifier.getTypes().add(new RelationType(abstractRelationship.getType().getName()));
        }
    }

    private void reducePattern(SubgraphTreeNodeInstance instance, IPattern pattern, Subgraph subgraph, Map<AbstractNode, INodeIdentifier> nodeMap, Map<AbstractRelationship, IRelationIdentifier> relationMap){

        int patternSize = pattern.getPatternElements().size();
        int nodeSize = min((patternSize + 1) / 2,subgraph.getNodes().size());
        int relationshipSize = min((patternSize - 1)/2,subgraph.getRelationships().size());

        for(int i = 0; i < nodeSize; i++){
            editLabels(instance.getIds().get(i * 2), (INodeIdentifier) pattern.getPatternElements().get(i * 2), subgraph.getNodes().get(i));
        }
        for(int i = 0; i < relationshipSize; i++){
            editRelationTypes(instance.getIds().get(i * 2 + 1), (IRelationIdentifier) pattern.getPatternElements().get(i * 2 + 1), subgraph.getRelationships().get(i));
        }

        for(int i = 0; i < nodeSize; i++){
            recordPropertyInfo((INodeIdentifier) pattern.getPatternElements().get(i * 2), subgraph.getNodes().get(i), instance.getProperties().get(i * 2));
        }
        for(int i = 0; i < relationshipSize; i++){
            recordPropertyInfo((IRelationIdentifier) pattern.getPatternElements().get(i * 2 + 1), subgraph.getRelationships().get(i), instance.getProperties().get(i * 2 + 1));
        }

    }
}
