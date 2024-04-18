package org.example.graspdb.cypher.standard_ast;

import org.example.graspdb.Randomly;
import org.example.graspdb.cypher.ast.*;
import org.example.graspdb.cypher.dsl.IIdentifierBuilder;
import org.example.graspdb.cypher.gen.query.ModelBasedQueryGenerator;

import java.util.*;
import java.util.stream.Collectors;

import static org.example.graspdb.cypher.oracle.DifferentialNonEmptyBranchOracle.queryGenerator;

public class Pattern implements IPattern {
    private List<IPatternElement> patternElements;
    //标识是否是模式理解中的模式
    public static Boolean pattern_in_comprehension=false;
    //记录当前模式所属的语句
    public static CypherClause cypherClause=null;
    //标识是否可以是路径
    public static Boolean path_flag=true;
    private IPathIdentifier pathIdentifier=null;
    Pattern(List<IPatternElement> pattern){
        this.patternElements = pattern;
    }

    @Override
    public List<IPatternElement> getPatternElements() {
        return patternElements;
    }

    @Override
    public void setPatternElements(List<IPatternElement> elements) {
        patternElements = elements;
    }
    public void setPathIdentifier(IPathIdentifier pathIdentifier) {
        this.pathIdentifier = pathIdentifier;
        pathIdentifier.setPattern(this);
    }
    @Override
    public IPathIdentifier getPathIdentifier() {
        return this.pathIdentifier;
    }

    @Override
    public IPattern getCopy() {
        if(patternElements != null){
            return new Pattern(patternElements.stream().map(p->p.getCopy()).collect(Collectors.toList()));
        }
        return new Pattern(new ArrayList<>());

    }

    @Override
    public void toTextRepresentation(StringBuilder sb) {
        if(pathIdentifier!=null){
              pathIdentifier.toTextRepresentation(sb);
        }
        if(patternElements != null){
            for(IPatternElement element : patternElements){
                element.toTextRepresentation(sb);
            }
        }
    }

    public static class  PatternBuilder {
        private final IIdentifierBuilder identifierBuilder;
        private final List<IPatternElement> patternElements;

        public PatternBuilder(IIdentifierBuilder identifierBuilder){
            this.identifierBuilder = identifierBuilder;
            this.patternElements = new ArrayList<>();
        }

        public static class OngoingNode {
            private PatternBuilder patternBuilder;
            private NodeIdentifier.NodeBuilder nodeBuilder;

            private OngoingNode(PatternBuilder patternBuilder, String name){
                this.patternBuilder = patternBuilder;
                nodeBuilder = NodeIdentifier.NodeBuilder.newNodeBuilder(name);
            }

            public OngoingNode withLabels(ILabel...labels){
                nodeBuilder.withLabels(labels);
                return this;
            }

            public OngoingNode withProperties(IProperty...properties){
                nodeBuilder.withProperties(properties);
                return this;
            }

            public OngoingRelation newNamedRelation(){
                INodeIdentifier node = nodeBuilder.build();
                if(!(cypherClause instanceof Create)&&!pattern_in_comprehension)
                    cypherClause.symtab.addNode(node);
                patternBuilder.patternElements.add(node);
                OngoingRelation ongoingRelation = new OngoingRelation(patternBuilder, patternBuilder.identifierBuilder.getNewRelationName());
                if(!node.isAnonymous()){
                    String node_id = node.getName();
                    String relations_id = ongoingRelation.relationBuilder.build().getName();
                    if(ModelBasedQueryGenerator.node_to_relationship.containsKey(node_id))
                        ModelBasedQueryGenerator.node_to_relationship.get(node_id).add(relations_id);
                    else
                        ModelBasedQueryGenerator.node_to_relationship.put(node_id,new HashSet<>(Collections.singleton(relations_id)));
                }
                return ongoingRelation;
            }

            public OngoingRelation newNamedRelation(IRelationIdentifier relationIdentifier){
                INodeIdentifier node = nodeBuilder.build();
                if(!(cypherClause instanceof Create)&&!pattern_in_comprehension)
                    cypherClause.symtab.addNode(node);
                patternBuilder.patternElements.add(node);
                OngoingRelation ongoingRelation = new OngoingRelation(patternBuilder, relationIdentifier.getName());
                ongoingRelation.withDirection(relationIdentifier.getDirection());
                ongoingRelation.withProperties(relationIdentifier.getProperties().
                        toArray(new IProperty[relationIdentifier.getProperties().size()]));
                ongoingRelation.withType(relationIdentifier.getTypes().get(0));
                return ongoingRelation;
            }
            public OngoingRelation newLengthUnknownRelation(){
                INodeIdentifier node = nodeBuilder.build();
                if(!(cypherClause instanceof Create)&&!pattern_in_comprehension)
                    cypherClause.symtab.addNode(node);
                patternBuilder.patternElements.add(node);
                OngoingRelation ongoingRelation = new OngoingRelation(patternBuilder, null);
                ongoingRelation.getRelationBuilder().setLengthUnknown(true);
                ongoingRelation.withDirection(Randomly.fromList(Arrays.asList(Direction.LEFT,Direction.RIGHT,Direction.BOTH)));
                return ongoingRelation;
            }

            public OngoingRelation newAnonymousRelation(){
                INodeIdentifier node = nodeBuilder.build();
                if(!(cypherClause instanceof Create)&&!pattern_in_comprehension)
                    cypherClause.symtab.addNode(node);
                patternBuilder.patternElements.add(node);
                OngoingRelation ongoingRelation = new OngoingRelation(patternBuilder, "");
                return ongoingRelation;
            }


            public OngoingRelation newRelationRef(IRelationIdentifier relation){
                INodeIdentifier node = nodeBuilder.build();
                if(!(cypherClause instanceof Create)&&!pattern_in_comprehension)
                    cypherClause.symtab.addNode(node);
                patternBuilder.patternElements.add(node);
                if(!node.isAnonymous()){
                    String node_id = node.getName();
                    String relations_id = relation.getName();
                    if(ModelBasedQueryGenerator.node_to_relationship.containsKey(node_id))
                        ModelBasedQueryGenerator.node_to_relationship.get(node_id).add(relations_id);
                    else
                        ModelBasedQueryGenerator.node_to_relationship.put(node_id,new HashSet<>(Collections.singleton(relations_id)));
                }
                OngoingRelation ongoingRelation = new OngoingRelation(patternBuilder, relation.getName());
                return ongoingRelation;
            }

            public IPattern build(){
                INodeIdentifier node = nodeBuilder.build();
                if(!(cypherClause instanceof Create)&&!pattern_in_comprehension)
                    cypherClause.symtab.addNode(node);
                patternBuilder.patternElements.add(node);
                IPattern pattern=new Pattern(patternBuilder.patternElements);
                //生成路径ID
                if(path_flag&&new Randomly().getInteger(0,6)==0){
                    IPathIdentifier pathIdentifier=new PathIdentifier(queryGenerator.getIdentifierBuilder().getNewPathName());
                    pattern.setPathIdentifier(pathIdentifier);
                }
                return pattern;
            }
        }

        public static class OngoingRelation {
            private PatternBuilder patternBuilder;
            private RelationIdentifier.RelationBuilder relationBuilder;
            private Boolean LengthUnknown;

            public void setLengthUnknown(Boolean b){
                this.LengthUnknown=b;
            }
            public boolean getLengthUnknown(){
                return LengthUnknown;
            }
            public RelationIdentifier.RelationBuilder getRelationBuilder(){
                return relationBuilder;
            }
            private OngoingRelation(PatternBuilder patternBuilder, String name){
                this.patternBuilder = patternBuilder;
                this.LengthUnknown=false;
                relationBuilder = RelationIdentifier.RelationBuilder.newRelationBuilder(name);
            }

            public OngoingRelation withType(IType relationType){
                relationBuilder.withType(relationType);
                return this;
            }

            public OngoingRelation withDirection(Direction direction){
                relationBuilder.withDirection(direction);
                return this;
            }

            public OngoingRelation withProperties(IProperty ...properties){
                relationBuilder.withProperties(properties);
                return this;
            }

            public OngoingRelation withOnlyLengthUpperBound(int lengthUpperBound){
                relationBuilder.withOnlyLengthUpperBound(lengthUpperBound);
                return this;
            }

            public OngoingRelation withOnlyLengthLowerBound(int lengthLowerBound){
                relationBuilder.withOnlyLengthLowerBound(lengthLowerBound);
                return this;
            }

            public OngoingRelation withLength(int length){
                relationBuilder.withLength(length);
                return this;
            }

            public OngoingRelation withLengthUnbounded(){
                relationBuilder.withLengthUnbounded();
                return this;
            }

            public OngoingNode newNamedNode(){
                IRelationIdentifier relation = relationBuilder.build();
                if(!(cypherClause instanceof Create)&&!pattern_in_comprehension)
                    cypherClause.symtab.addRelation(relation);
                patternBuilder.patternElements.add(relation);
                OngoingNode node= patternBuilder.newNamedNode();
                if(!relation.isAnonymous()){
                    String node_id = node.nodeBuilder.build().getName();
                    String relations_id = relation.getName();
                    if(ModelBasedQueryGenerator.node_to_relationship.containsKey(node_id))
                        ModelBasedQueryGenerator.node_to_relationship.get(node_id).add(relations_id);
                    else
                        ModelBasedQueryGenerator.node_to_relationship.put(node_id,new HashSet<>(Collections.singleton(relations_id)));
                }
                return node;
            }

            public OngoingNode newNamedNode(INodeIdentifier nodeIdentifier){
                IRelationIdentifier relation = relationBuilder.build();
                if(!relation.isAnonymous()){
                    String node_id = nodeIdentifier.getName();
                    String relations_id = relation.getName();
                    if(ModelBasedQueryGenerator.node_to_relationship.containsKey(node_id))
                        ModelBasedQueryGenerator.node_to_relationship.get(node_id).add(relations_id);
                    else
                        ModelBasedQueryGenerator.node_to_relationship.put(node_id,new HashSet<>(Collections.singleton(relations_id)));
                }
                if(!(cypherClause instanceof Create)&&!pattern_in_comprehension)
                    cypherClause.symtab.addRelation(relation);
                patternBuilder.patternElements.add(relation);
                return patternBuilder.newNamedNode(nodeIdentifier);
            }

            public OngoingNode newAnonymousNode(){
                IRelationIdentifier relation = relationBuilder.build();
                if(!(cypherClause instanceof Create)&&!pattern_in_comprehension)
                    cypherClause.symtab.addRelation(relation);
                patternBuilder.patternElements.add(relation);
                return patternBuilder.newAnonymousNode();
            }

            public OngoingNode newNodeRef(INodeIdentifier node){
                IRelationIdentifier relation = relationBuilder.build();
                if(!relation.isAnonymous()){
                    String node_id = node.getName();
                    String relations_id = relation.getName();
                    if(ModelBasedQueryGenerator.node_to_relationship.containsKey(node_id))
                        ModelBasedQueryGenerator.node_to_relationship.get(node_id).add(relations_id);
                    else
                        ModelBasedQueryGenerator.node_to_relationship.put(node_id,new HashSet<>(Collections.singleton(relations_id)));
                }
                if(!(cypherClause instanceof Create)&&!pattern_in_comprehension)
                    cypherClause.symtab.addRelation(relation);
                patternBuilder.patternElements.add(relation);
                return patternBuilder.newRefDefinedNode(node);
            }
        }

        public OngoingNode newNamedNode(){
            OngoingNode builder = new OngoingNode(this, identifierBuilder.getNewNodeName());
            return builder;
        }

        public OngoingNode newNamedNode(INodeIdentifier nodeIdentifier){
            OngoingNode builder = new OngoingNode(this, identifierBuilder.getNewNodeName());
            builder.withProperties(nodeIdentifier.getProperties().toArray(new IProperty[nodeIdentifier.getProperties().size()]));
            builder.withLabels(nodeIdentifier.getLabels().toArray(new ILabel[nodeIdentifier.getProperties().size()]));
            return builder;
        }

        public OngoingNode newAnonymousNode(){
            OngoingNode builder = new OngoingNode(this, "");
            return builder;
        }

        public OngoingNode newRefDefinedNode(INodeIdentifier node){
            OngoingNode builder = new OngoingNode(this, node.getName());
            return builder;
        }
    }
}
