package org.example.graspdb.cypher.gen;

import org.example.graspdb.Randomly;
import org.example.graspdb.cypher.ast.*;
import org.example.graspdb.cypher.ast.analyzer.IAliasAnalyzer;
import org.example.graspdb.cypher.ast.analyzer.IClauseAnalyzer;
import org.example.graspdb.cypher.ast.analyzer.IMatchAnalyzer;
import org.example.graspdb.cypher.ast.analyzer.IMergeAnalyzer;
import org.example.graspdb.cypher.dsl.IIdentifierBuilder;
import org.example.graspdb.cypher.oracle.DifferentialNonEmptyBranchOracle;
import org.example.graspdb.cypher.schema.ILabelInfo;
import org.example.graspdb.cypher.schema.IPropertyInfo;
import org.example.graspdb.cypher.standard_ast.*;
import org.example.graspdb.cypher.standard_ast.expr.ConstExpression;
import org.example.graspdb.cypher.standard_ast.expr.IdentifierExpression;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.example.graspdb.cypher.gen.query.ModelBasedQueryGenerator.randomExpressionGenerator;

public class Subgraph {

    private List<AbstractNode> nodes = new ArrayList<>();
    private List<AbstractRelationship> relationships = new ArrayList<>();

    public List<AbstractNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<AbstractNode> nodes) {
        this.nodes = nodes;
    }

    public List<AbstractRelationship> getRelationships() {
        return relationships;
    }

    public void setRelationships(List<AbstractRelationship> relationships) {
        this.relationships = relationships;
    }

    public void addNode(AbstractNode node) {
        nodes.add(node);
    }

    public void addRelationship(AbstractRelationship relationship) {
        relationships.add(relationship);
    }

    public IPattern translateMatch(IMatchAnalyzer matchClause, IIdentifierBuilder identifierBuilder, Map<AbstractNode, INodeIdentifier> nodeToString,
                                   Map<AbstractRelationship, IRelationIdentifier> relationToString) {
        Pattern.PatternBuilder patternBuilder = new Pattern.PatternBuilder(identifierBuilder);
        Pattern.PatternBuilder.OngoingRelation lastRelationship = null;
        Randomly r = new Randomly();

        List<AbstractNode> abstractNodes = new ArrayList<>();
        List<AbstractRelationship> abstractRelations = new ArrayList<>();

        //模式最多匹配3个节点
        int num = r.getInteger(0, 3);
        for (int i = 0; i < nodes.size(); i++) {
            Pattern.PatternBuilder.OngoingNode ongoingNode = null;
            List<IAliasAnalyzer> aliasAnalyzers = matchClause.getAvailableAliases();
            List<IdentifierExpression> availableNodeAlias = new ArrayList<>();
            List<IdentifierExpression> availableRelationAlias = new ArrayList<>();
            availableNodeAlias.addAll(aliasAnalyzers.stream().filter(a -> a.analyzeType(DifferentialNonEmptyBranchOracle.queryGenerator.getGlobalstate().getSchema()).getType() == CypherType.NODE).map(a -> new IdentifierExpression(Alias.createIdentifierRef(a)))
                    .collect(Collectors.toList()));
            availableRelationAlias.addAll(aliasAnalyzers.stream().filter(a -> a.analyzeType(DifferentialNonEmptyBranchOracle.queryGenerator.getGlobalstate().getSchema()).getType() == CypherType.RELATION).map(a -> new IdentifierExpression(Alias.createIdentifierRef(a)))
                    .collect(Collectors.toList()));
            //节点类型的别名作为模式的节点ID
            if (Randomly.getBoolean() && availableNodeAlias.size() > 0) {
                if (lastRelationship != null) {
                    ongoingNode = lastRelationship.newNodeRef(NodeIdentifier.createNodeRefFromAlias((Alias) availableNodeAlias.get(r.getInteger(0, availableNodeAlias.size())).getIdentifier()));
                } else {
                    ongoingNode = patternBuilder.newRefDefinedNode(NodeIdentifier.createNodeRefFromAlias((Alias) availableNodeAlias.get(r.getInteger(0, availableNodeAlias.size())).getIdentifier()));
                }
            } else if (Randomly.getBoolean() && nodeToString.containsKey(nodes.get(i))) {
                if (lastRelationship != null) {
                    ongoingNode = lastRelationship.newNodeRef(nodeToString.get(nodes.get(i)));
                } else {
                    ongoingNode = patternBuilder.newRefDefinedNode(nodeToString.get(nodes.get(i)));
                }
            } else {
                if (lastRelationship != null) {
                    if (Randomly.getBoolean())
                        ongoingNode = lastRelationship.newNamedNode();
                    else
                        ongoingNode = lastRelationship.newAnonymousNode();
                } else {
                    if (Randomly.getBoolean())
                        ongoingNode = patternBuilder.newNamedNode();
                    else
                        ongoingNode = patternBuilder.newAnonymousNode();
                }
                //随机设置标签和属性值
                for (int j = 0; j < r.getInteger(0, 2); j++) {
                    int a = nodes.get(i).getLabelInfos().size();
                    if (a > 0) {
                        ILabelInfo labelInfo = nodes.get(i).getLabelInfos().get(r.getInteger(0, a));
                        if (r.getInteger(0, 10) < 3)
                        {ongoingNode.withLabels(new Label(labelInfo.getName()));
                        }
                        IPropertyInfo p = labelInfo.getProperties().get(r.getInteger(0, labelInfo.getProperties().size()));
                        if (r.getInteger(0, 10) < 3)
                            ongoingNode.withProperties(new Property(p.getKey(), p.getType(), randomExpressionGenerator.generateAllExpression(p.getType(), false, 3)));
                    }
                }
            }
            abstractNodes.add(nodes.get(i));
            //精简模式最多匹配三个节点和两条边
            if (i == nodes.size() - 1 || i == num) {
                IPattern pattern = ongoingNode.build();
                resolveAndAddMap(pattern, abstractNodes, abstractRelations, nodeToString, relationToString);
                return pattern;
            }
            Direction direction = Direction.BOTH;
            if (Randomly.getBoolean()) {
                if (relationships.get(i).getFrom() == nodes.get(i)) {
                    direction = Direction.RIGHT;
                } else {
                    direction = Direction.LEFT;
                }
            }
            Pattern.PatternBuilder.OngoingRelation relation = null;
            if(r.getInteger(0,9)==0){
         relation=ongoingNode.newLengthUnknownRelation();
            }
            else if (Randomly.getBoolean() && availableRelationAlias.size() > 0) {
                relation = ongoingNode.newRelationRef(RelationIdentifier.createRelationRefFromAlias((Alias) availableRelationAlias.get(r.getInteger(0, availableRelationAlias.size())).getIdentifier())).withDirection(direction);
            } else if (relationToString.containsKey(relationships.get(i))) {
                relation = ongoingNode.newRelationRef(relationToString.get(relationships.get(i))).withDirection(direction);
            } else {
                if (Randomly.getBoolean())
                    relation = ongoingNode.newAnonymousRelation().withDirection(direction);
                else
                    relation = ongoingNode.newNamedRelation().withDirection(direction);
                if (relationships.get(i).getType() != null) {
                    if (r.getInteger(0, 10) < 3) {
                        relation.withType(new RelationType(relationships.get(i).getType().getName()));
                        IPropertyInfo p = relationships.get(i).getType().getProperties().get(r.getInteger(0, relationships.get(i).getType().getProperties().size()));
                        relation.withProperties(new Property(p.getKey(), p.getType(), randomExpressionGenerator.generateAllExpression(p.getType(), false, 3)));
                    }
                }
            }
            abstractRelations.add(relationships.get(i));
            lastRelationship = relation;
        }
        throw new RuntimeException();
    }

    public IPattern translateMerge(IMergeAnalyzer mergeClause, IIdentifierBuilder identifierBuilder, Map<AbstractNode, INodeIdentifier> nodeToString,
                                   Map<AbstractRelationship, IRelationIdentifier> relationToString) {
        Pattern.PatternBuilder patternBuilder = new Pattern.PatternBuilder(identifierBuilder);
        Pattern.PatternBuilder.OngoingRelation lastRelationship = null;
        Randomly r = new Randomly();

        List<AbstractNode> abstractNodes = new ArrayList<>();
        List<AbstractRelationship> abstractRelations = new ArrayList<>();
        int num = r.getInteger(0, 3);
        for (int i = 0; i < nodes.size(); i++) {
            Pattern.PatternBuilder.OngoingNode ongoingNode = null;
            if (lastRelationship != null) {
                if (Randomly.getBoolean())
                    ongoingNode = lastRelationship.newNamedNode();
                else
                    ongoingNode = lastRelationship.newAnonymousNode();
            } else {
                if (Randomly.getBoolean())
                    ongoingNode = patternBuilder.newNamedNode();
                else
                    ongoingNode = patternBuilder.newAnonymousNode();
            }
            for (ILabelInfo labelInfo : nodes.get(i).getLabelInfos()) {
                if (r.getInteger(0, 10) < 3)
                    ongoingNode.withLabels(new Label(labelInfo.getName()));
                //设置创建节点的属性值并在identifierBuilder中记录作用域
                IPropertyInfo p = labelInfo.getProperties().get(r.getInteger(0, labelInfo.getProperties().size()));
                if (r.getInteger(0, 10) < 3)
                { IExpression expression=randomExpressionGenerator.generateAllExpression(p.getType(), false, 3);
                    StringBuilder stringBuilder=new StringBuilder();
                    expression.toTextRepresentation(stringBuilder);
                    //merge中点和边的属性值不能为null
                    if(stringBuilder.toString().contains("NULL")||stringBuilder.toString().contains("OrNull")||stringBuilder.toString().contains(".k")||stringBuilder.toString().contains("CASE WHEN"))
                        ongoingNode.withProperties(new Property(p.getKey(), p.getType(), randomExpressionGenerator.generateAllExpression(p.getType(), false, 0)));
                    else
                        ongoingNode.withProperties(new Property(p.getKey(), p.getType(), expression));
                }
            }
            //设置节点id
            ongoingNode.withProperties(new Property("id",CypherType.NUMBER,new ConstExpression(DifferentialNonEmptyBranchOracle.queryGenerator.getGraphManager().getPresentID())));
            DifferentialNonEmptyBranchOracle.queryGenerator.getGraphManager().addPresentID();
            abstractNodes.add(nodes.get(i));

            if (i == nodes.size() - 1 || i == num) {
                IPattern pattern = ongoingNode.build();
                resolveAndAddMap(pattern, abstractNodes, abstractRelations, nodeToString, relationToString);
                return pattern;
            }

            Direction direction = Direction.BOTH;
            if (Randomly.getBoolean()) {
                if (relationships.get(i).getFrom() == nodes.get(i)) {
                    direction = Direction.RIGHT;
                } else {
                    direction = Direction.LEFT;
                }
            }
            Pattern.PatternBuilder.OngoingRelation relation = null;

            if (relationToString.containsKey(relationships.get(i))) {
                relation = ongoingNode.newAnonymousRelation().withDirection(direction);
            } else {
                relation = ongoingNode.newNamedRelation().withDirection(direction);}

            if (relationships.get(i).getType() != null) {
                    relation.withType(new RelationType(relationships.get(i).getType().getName()));
                    //设置创建关系的属性值并在identifierBuilder中记录作用域
                    IPropertyInfo p = relationships.get(i).getType().getProperties().get(r.getInteger(0, relationships.get(i).getType().getProperties().size()));
                    if (r.getInteger(0, 10) < 3)
                    {IExpression expression=randomExpressionGenerator.generateAllExpression(p.getType(), false, 3);
                        StringBuilder stringBuilder=new StringBuilder();
                        expression.toTextRepresentation(stringBuilder);
                        if(stringBuilder.toString().contains("NULL")||stringBuilder.toString().contains("OrNull")||stringBuilder.toString().contains(".k")||stringBuilder.toString().contains("CASE WHEN"))
                            relation.withProperties(new Property(p.getKey(), p.getType(), randomExpressionGenerator.generateAllExpression(p.getType(), false, 0)));
                        else
                            relation.withProperties(new Property(p.getKey(), p.getType(), expression));
                    }}
            //设置关系id
            relation.withProperties(new Property("id",CypherType.NUMBER,new ConstExpression(DifferentialNonEmptyBranchOracle.queryGenerator.getGraphManager().getPresentID())));
            DifferentialNonEmptyBranchOracle.queryGenerator.getGraphManager().addPresentID();

            abstractRelations.add(relationships.get(i));
            lastRelationship = relation;
        }
        throw new RuntimeException();
    }

    public IPattern translateCreate(IIdentifierBuilder identifierBuilder) {
        Randomly r = new Randomly();
        Pattern.PatternBuilder patternBuilder = new Pattern.PatternBuilder(identifierBuilder);
        Pattern.PatternBuilder.OngoingRelation lastRelationship = null;
        int num = r.getInteger(0, 3);
        for (int i = 0; i < nodes.size(); i++) {
            Pattern.PatternBuilder.OngoingNode ongoingNode = null;
            if (lastRelationship != null) {
                if (Randomly.getBoolean())
                    ongoingNode = lastRelationship.newNamedNode();
                else
                    ongoingNode = lastRelationship.newAnonymousNode();
            } else {
                if (Randomly.getBoolean())
                    ongoingNode = patternBuilder.newNamedNode();
                else
                    ongoingNode = patternBuilder.newAnonymousNode();
            }
            for (ILabelInfo labelInfo : nodes.get(i).getLabelInfos()) {
                if (r.getInteger(0, 10) < 3)
                    ongoingNode.withLabels(new Label(labelInfo.getName()));
                //设置创建节点的属性值并在identifierBuilder中记录作用域
                IPropertyInfo p = labelInfo.getProperties().get(r.getInteger(0, labelInfo.getProperties().size()));
                if (r.getInteger(0, 10) < 3)
                    ongoingNode.withProperties(new Property(p.getKey(), p.getType(), randomExpressionGenerator.generateAllExpression(p.getType(), false, 3)));
            }
            //设置节点id
            ongoingNode.withProperties(new Property("id",CypherType.NUMBER,new ConstExpression(DifferentialNonEmptyBranchOracle.queryGenerator.getGraphManager().getPresentID())));
            DifferentialNonEmptyBranchOracle.queryGenerator.getGraphManager().addPresentID();
            if (i == nodes.size() - 1 || i == num) {
                return ongoingNode.build();
            }

            Direction direction;
            if (relationships.get(i).getFrom() == nodes.get(i)) {
                direction = Direction.RIGHT;
            } else {
                direction = Direction.LEFT;
            }
            Pattern.PatternBuilder.OngoingRelation relation = null;
            if(Randomly.getBoolean())
                relation=ongoingNode.newNamedRelation().withDirection(direction);
            else
                relation=ongoingNode.newAnonymousRelation().withDirection(direction);
            if (relationships.get(i).getType() != null) {
                relation.withType(new RelationType(relationships.get(i).getType().getName()));
                //设置创建关系的属性值并在identifierBuilder中记录作用域
                IPropertyInfo p = relationships.get(i).getType().getProperties().get(r.getInteger(0, relationships.get(i).getType().getProperties().size()));
                if (r.getInteger(0, 10) < 3)
                    relation.withProperties(new Property(p.getKey(), p.getType(), randomExpressionGenerator.generateAllExpression(p.getType(), false, 3)));
            }
            //设置关系id
            relation.withProperties(new Property("id",CypherType.NUMBER,new ConstExpression(DifferentialNonEmptyBranchOracle.queryGenerator.getGraphManager().getPresentID())));
            DifferentialNonEmptyBranchOracle.queryGenerator.getGraphManager().addPresentID();
            lastRelationship = relation;
        }
        throw new RuntimeException();
    }

    //todo 用于模式理解的模式
    public IPattern translateComprehension(IClauseAnalyzer clauseAnalyzer, IIdentifierBuilder identifierBuilder, Map<AbstractNode, INodeIdentifier> nodeToString,
                                           Map<AbstractRelationship, IRelationIdentifier> relationToString) {
        Pattern.PatternBuilder patternBuilder = new Pattern.PatternBuilder(identifierBuilder);
        Pattern.PatternBuilder.OngoingRelation lastRelationship = null;
        Randomly r = new Randomly();

        List<AbstractNode> abstractNodes = new ArrayList<>();
        List<AbstractRelationship> abstractRelations = new ArrayList<>();

        //模式包含最少2个，最多3个节点
        int num = r.getInteger(1, 3);
        for (int i = 0; i < nodes.size(); i++) {
            Pattern.PatternBuilder.OngoingNode ongoingNode = null;
            List<IAliasAnalyzer> aliasAnalyzers = clauseAnalyzer.getAvailableAliases();
            List<IdentifierExpression> availableNodeAlias = new ArrayList<>();
            List<IdentifierExpression> availableRelationAlias = new ArrayList<>();
            availableNodeAlias.addAll(aliasAnalyzers.stream().filter(a -> a.analyzeType(DifferentialNonEmptyBranchOracle.queryGenerator.getGlobalstate().getSchema()).getType() == CypherType.NODE).map(a -> new IdentifierExpression(Alias.createIdentifierRef(a)))
                    .collect(Collectors.toList()));
            availableRelationAlias.addAll(aliasAnalyzers.stream().filter(a -> a.analyzeType(DifferentialNonEmptyBranchOracle.queryGenerator.getGlobalstate().getSchema()).getType() == CypherType.RELATION).map(a -> new IdentifierExpression(Alias.createIdentifierRef(a)))
                    .collect(Collectors.toList()));
            //节点类型的别名作为模式的节点ID
            if (Randomly.getBoolean() && availableNodeAlias.size() > 0) {
                if (lastRelationship != null) {
                    ongoingNode = lastRelationship.newNodeRef(NodeIdentifier.createNodeRefFromAlias((Alias) availableNodeAlias.get(r.getInteger(0, availableNodeAlias.size())).getIdentifier()));
                } else {
                    ongoingNode = patternBuilder.newRefDefinedNode(NodeIdentifier.createNodeRefFromAlias((Alias) availableNodeAlias.get(r.getInteger(0, availableNodeAlias.size())).getIdentifier()));
                }
            } else if (nodeToString.containsKey(nodes.get(i))) {
                if (lastRelationship != null) {
                    ongoingNode = lastRelationship.newNodeRef(nodeToString.get(nodes.get(i)));
                } else {
                    ongoingNode = patternBuilder.newRefDefinedNode(nodeToString.get(nodes.get(i)));
                }
            } else {
                if (lastRelationship != null) {
                    ongoingNode = lastRelationship.newNamedNode();
                } else {
                    ongoingNode = patternBuilder.newNamedNode();
                }
                //随机设置标签和属性值
                for (int j = 0; j < r.getInteger(0, 2); j++) {
                    int a = nodes.get(i).getLabelInfos().size();
                    if (a > 0) {
                        ILabelInfo labelInfo = nodes.get(i).getLabelInfos().get(r.getInteger(0, a));
                        //todo 模式理解不能引入标签限制，避免not defined报错
//                        if (r.getInteger(0, 10) < 3)
//                            ongoingNode.withLabels(new Label(labelInfo.getName()));
                        IPropertyInfo p = labelInfo.getProperties().get(r.getInteger(0, labelInfo.getProperties().size()));
                        if (r.getInteger(0, 10) < 3)
                            ongoingNode.withProperties(new Property(p.getKey(), p.getType(), randomExpressionGenerator.generateAllExpression(p.getType(), false, 3)));
                    }
                }
            }
            abstractNodes.add(nodes.get(i));
            //精简模式最多匹配三个节点和两条边
            if (i == nodes.size() - 1 || i == num) {
                //模式只有一个点时需要加入一条边和一个点
                if (i == 0) {
                    lastRelationship = ongoingNode.newAnonymousRelation().withDirection(Direction.BOTH);
                    ongoingNode = lastRelationship.newAnonymousNode();
                }
                IPattern pattern = ongoingNode.build();
                resolveAndAddMap(pattern, abstractNodes, abstractRelations, nodeToString, relationToString);
                if(pattern==null)
                    throw new RuntimeException("null pattern!");
                return pattern;
            }
            Direction direction = Direction.BOTH;
            if (Randomly.getBoolean()) {
                if (relationships.get(i).getFrom() == nodes.get(i)) {
                    direction = Direction.RIGHT;
                } else {
                    direction = Direction.LEFT;
                }
            }
            Pattern.PatternBuilder.OngoingRelation relation = null;

            if (Randomly.getBoolean() && availableRelationAlias.size() > 0) {
                relation = ongoingNode.newRelationRef(RelationIdentifier.createRelationRefFromAlias((Alias) availableRelationAlias.get(r.getInteger(0, availableRelationAlias.size())).getIdentifier())).withDirection(direction);
            } else if (relationToString.containsKey(relationships.get(i))) {
//                relation = ongoingNode.newAnonymousRelation().withDirection(direction);
                relation = ongoingNode.newRelationRef(relationToString.get(relationships.get(i))).withDirection(direction);
            } else {
                if (Randomly.getBoolean())
                    relation = ongoingNode.newAnonymousRelation().withDirection(direction);
                else
                    relation = ongoingNode.newNamedRelation().withDirection(direction);
                if (relationships.get(i).getType() != null) {
                    if (r.getInteger(0, 10) < 3) {
                        relation.withType(new RelationType(relationships.get(i).getType().getName()));
                        IPropertyInfo p = relationships.get(i).getType().getProperties().get(r.getInteger(0, relationships.get(i).getType().getProperties().size()));
                        relation.withProperties(new Property(p.getKey(), p.getType(), randomExpressionGenerator.generateAllExpression(p.getType(), false, 3)));
                    }
                }
            }
            abstractRelations.add(relationships.get(i));
            lastRelationship = relation;
        }
        throw new RuntimeException();
    }

    //TODO 用于函数exists()的模式
    public IPattern translateSingle(IClauseAnalyzer clauseAnalyzer, IIdentifierBuilder identifierBuilder, Map<AbstractNode, INodeIdentifier> nodeToString,
                                    Map<AbstractRelationship, IRelationIdentifier> relationToString) {
        Pattern.PatternBuilder patternBuilder = new Pattern.PatternBuilder(identifierBuilder);
        Pattern.PatternBuilder.OngoingRelation lastRelationship = null;
        Randomly r = new Randomly();

        List<AbstractNode> abstractNodes = new ArrayList<>();
        List<AbstractRelationship> abstractRelations = new ArrayList<>();

        //模式最少2个最多匹配3个节点
        int num = r.getInteger(1, 3);
        for (int i = 0; i < nodes.size(); i++) {
            Pattern.PatternBuilder.OngoingNode ongoingNode = null;
            List<IAliasAnalyzer> aliasAnalyzers = clauseAnalyzer.getAvailableAliases();
            List<IdentifierExpression> availableNodeAlias = new ArrayList<>();
            List<IdentifierExpression> availableRelationAlias = new ArrayList<>();
            availableNodeAlias.addAll(aliasAnalyzers.stream().filter(a -> a.analyzeType(DifferentialNonEmptyBranchOracle.queryGenerator.getGlobalstate().getSchema()).getType() == CypherType.NODE).map(a -> new IdentifierExpression(Alias.createIdentifierRef(a)))
                    .collect(Collectors.toList()));
            availableRelationAlias.addAll(aliasAnalyzers.stream().filter(a -> a.analyzeType(DifferentialNonEmptyBranchOracle.queryGenerator.getGlobalstate().getSchema()).getType() == CypherType.RELATION).map(a -> new IdentifierExpression(Alias.createIdentifierRef(a)))
                    .collect(Collectors.toList()));
            //节点类型的别名作为模式的节点ID
            if (Randomly.getBoolean() && availableNodeAlias.size() > 0) {
                if (lastRelationship != null) {
                    ongoingNode = lastRelationship.newNodeRef(NodeIdentifier.createNodeRefFromAlias((Alias) availableNodeAlias.get(r.getInteger(0, availableNodeAlias.size())).getIdentifier()));
                } else {
                    ongoingNode = patternBuilder.newRefDefinedNode(NodeIdentifier.createNodeRefFromAlias((Alias) availableNodeAlias.get(r.getInteger(0, availableNodeAlias.size())).getIdentifier()));
                }
            }
            //不能引入新的节点ID
            else if (nodeToString.containsKey(nodes.get(i)) && clauseAnalyzer.getAvailableNodeIdentifiers().contains(nodeToString.get(nodes.get(i))) && Randomly.getBoolean()) {
                if (lastRelationship != null) {
                    ongoingNode = lastRelationship.newNodeRef(nodeToString.get(nodes.get(i)));
                } else {
                    ongoingNode = patternBuilder.newRefDefinedNode(nodeToString.get(nodes.get(i)));
                }
            } else {
                if (lastRelationship != null) {
                    ongoingNode = lastRelationship.newAnonymousNode();
                } else {
                    ongoingNode = patternBuilder.newAnonymousNode();
                }
                //随机设置标签和属性值
                for (int j = 0; j < r.getInteger(0, 2); j++) {
                    int a = nodes.get(i).getLabelInfos().size();
                    if (a > 0) {
                        ILabelInfo labelInfo = nodes.get(i).getLabelInfos().get(r.getInteger(0, a));
                        if (r.getInteger(0, 10) < 3)
                            ongoingNode.withLabels(new Label(labelInfo.getName()));
                        IPropertyInfo p = labelInfo.getProperties().get(r.getInteger(0, labelInfo.getProperties().size()));
                        if (r.getInteger(0, 10) < 3)
                            ongoingNode.withProperties(new Property(p.getKey(), p.getType(), randomExpressionGenerator.generateAllExpression(p.getType(), false, 3)));
                    }
                }
            }
            abstractNodes.add(nodes.get(i));
            if (i == nodes.size() - 1 || i == num) {
                //模式只有一个点时需要加入一条边和一个点
                if (i == 0) {
                    lastRelationship = ongoingNode.newAnonymousRelation().withDirection(Direction.BOTH);
                    ongoingNode = lastRelationship.newAnonymousNode();
                }
                //标识当前模式不可以是路径
                Pattern.path_flag = false;
                IPattern pattern = ongoingNode.build();
                Pattern.path_flag = true;
                resolveAndAddMap(pattern, abstractNodes, abstractRelations, nodeToString, relationToString);
                return pattern;
            }
            Direction direction = Direction.BOTH;
            if (Randomly.getBoolean()) {
                if (relationships.get(i).getFrom() == nodes.get(i)) {
                    direction = Direction.RIGHT;
                } else {
                    direction = Direction.LEFT;
                }
            }
            Pattern.PatternBuilder.OngoingRelation relation = null;
            if (Randomly.getBoolean() && availableRelationAlias.size() > 0) {
                relation = ongoingNode.newRelationRef(RelationIdentifier.createRelationRefFromAlias((Alias) availableRelationAlias.get(r.getInteger(0, availableRelationAlias.size())).getIdentifier())).withDirection(direction);
            }
//不能引入新的关系ID
            else if (relationToString.containsKey(relationships.get(i)) && clauseAnalyzer.getAvailableRelationIdentifiers().contains(relationToString.get(relationships.get(i))) && Randomly.getBoolean()) {
                relation = ongoingNode.newRelationRef((relationToString.get(relationships.get(i)))).withDirection(direction);
            } else {
                relation = ongoingNode.newAnonymousRelation().withDirection(direction);
            }
            if (relationships.get(i).getType() != null) {
                if (r.getInteger(0, 10) < 3)
                    relation.withType(new RelationType(relationships.get(i).getType().getName()));
                IPropertyInfo p = relationships.get(i).getType().getProperties().get(r.getInteger(0, relationships.get(i).getType().getProperties().size()));
                if (r.getInteger(0, 10) < 3)
                    relation.withProperties(new Property(p.getKey(), p.getType(), randomExpressionGenerator.generateAllExpression(p.getType(), false, 3)));
            }

            abstractRelations.add(relationships.get(i));
            lastRelationship = relation;
        }
        throw new RuntimeException();
    }

    private static void resolveAndAddMap(IPattern pattern, List<AbstractNode> nodes, List<AbstractRelationship> relationships, Map<AbstractNode, INodeIdentifier> nodeToString,
                                         Map<AbstractRelationship, IRelationIdentifier> relationToString) {
        for (int i = 0; i < nodes.size(); i++) {
            if (!nodeToString.containsKey(nodes.get(i))) {
                nodeToString.put(nodes.get(i), (INodeIdentifier) pattern.getPatternElements().get(i * 2));
            }
        }
        for (int i = 0; i < relationships.size(); i++) {
            if (!relationToString.containsKey(relationships.get(i))) {
                relationToString.put(relationships.get(i), (IRelationIdentifier) pattern.getPatternElements().get(i * 2 + 1));
            }
        }
    }

    private List<List<Integer>> idLists = new ArrayList<>();

    public void putInstance(List<Integer> ids) {
        idLists.add(ids);
    }

}
