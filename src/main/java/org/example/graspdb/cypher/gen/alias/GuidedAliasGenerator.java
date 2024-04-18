package org.example.graspdb.cypher.gen.alias;

import org.example.graspdb.Randomly;
import org.example.graspdb.cypher.CypherGlobalState;
import org.example.graspdb.cypher.ast.IDele;
import org.example.graspdb.cypher.ast.IRet;
import org.example.graspdb.cypher.ast.analyzer.*;
import org.example.graspdb.cypher.oracle.DifferentialNonEmptyBranchOracle;
import org.example.graspdb.cypher.schema.CypherSchema;
import org.example.graspdb.cypher.standard_ast.CypherType;
import org.example.graspdb.cypher.standard_ast.Dele;
import org.example.graspdb.cypher.standard_ast.Ret;
import org.example.graspdb.cypher.standard_ast.expr.ConstExpression;
import org.example.graspdb.cypher.standard_ast.expr.GetPropertyExpression;
import org.example.graspdb.cypher.standard_ast.expr.IdentifierExpression;
import org.example.graspdb.cypher.dsl.BasicAliasGenerator;
import org.example.graspdb.cypher.dsl.IIdentifierBuilder;
import org.example.graspdb.cypher.schema.IPropertyInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.example.graspdb.cypher.gen.query.ModelBasedQueryGenerator.randomExpressionGenerator;

public class GuidedAliasGenerator<S extends CypherSchema<G, ?>, G extends CypherGlobalState<?, S>> extends BasicAliasGenerator<S> {
    private boolean overrideOld;
    public G global;

    //    private Map<String, Object> varToVal;
    public GuidedAliasGenerator(G global, IIdentifierBuilder identifierBuilder, boolean overrideOld) {
        super(global.getSchema(), identifierBuilder);
        this.overrideOld = overrideOld;
        this.global = global;
    }

    @Override
    public List<IRet> generateReturnAlias(IReturnAnalyzer returnClause, IIdentifierBuilder identifierBuilder, S schema) {
        if (returnClause.getReturnList().size() != 0 && !overrideOld) {
            return returnClause.getReturnList();
        }
        List<IRet> results = new ArrayList<>();
        List<INodeAnalyzer> idNode = returnClause.getExtendableNodeIdentifiers();
        List<IRelationAnalyzer> idRelation = returnClause.getExtendableRelationIdentifiers();
        List<IAliasAnalyzer> idAlias = returnClause.getExtendableAliases();
        List<IPathAnalyzer> idPath = returnClause.getExtendablePathIdentifiers();
        Randomly r = new Randomly();
        int sizeOfAlias = idAlias.size();
        int sizeOfNode = idNode.size();
        int sizeOfRelation = idRelation.size();
        int sizeOfPath = idPath.size();
        int numOfExpressions = r.getInteger(1, 3);
        if (DifferentialNonEmptyBranchOracle.union_flag == 2 && returnClause.getInQuery())
            numOfExpressions = DifferentialNonEmptyBranchOracle.returns.size();
        if (numOfExpressions == 0)
            throw new RuntimeException("numOfExpressions==0 exception!");
        for (int i = 0; i < numOfExpressions; i++) {
            Ret result = null;
            int kind = r.getInteger(0, 10);
            //返回别名
            if (kind == 0 && sizeOfAlias > 0) {
                IAliasAnalyzer alias = idAlias.get(r.getInteger(0, sizeOfAlias));
                IdentifierExpression ie = new IdentifierExpression(alias);
                result = Ret.createNewExpressionAlias(identifierBuilder, ie);
            }
            //返回路径
            else if (kind == 1 && sizeOfPath > 0) {
                IPathAnalyzer path = idPath.get(r.getInteger(0, sizeOfPath));
                IdentifierExpression ie = new IdentifierExpression(path);
                result = Ret.createNewExpressionAlias(identifierBuilder, ie);
            }
            //返回节点或节点属性
            else if (kind < 4 && sizeOfNode > 0) {
                INodeAnalyzer node = idNode.get(r.getInteger(0, sizeOfNode));
                List<IPropertyInfo> props = node.getAllPropertiesAvailable(schema);
                IdentifierExpression ie = new IdentifierExpression(node);
                if (props.size() > 0 && Randomly.getBoolean()) {
                    IPropertyInfo prop = props.get(r.getInteger(0, props.size()));
                    GetPropertyExpression exp = new GetPropertyExpression(ie, prop.getKey());
                    result = Ret.createNewExpressionAlias(identifierBuilder, exp);
                } else {
                    result = Ret.createNewExpressionAlias(identifierBuilder, ie);
                }
            } else if (kind == 4 && sizeOfRelation > 0)
            //返回关系或关系属性
            {
                IRelationAnalyzer relation = idRelation.get(r.getInteger(0, sizeOfRelation));
                if (relation.isSingleRelation()) {
                    List<IPropertyInfo> props = relation.getAllPropertiesAvailable(schema);
                    IdentifierExpression ie = new IdentifierExpression(relation);
                    if (props.size() > 0 && Randomly.getBoolean()) {
                        IPropertyInfo prop = props.get(r.getInteger(0, props.size()));
                        GetPropertyExpression exp = new GetPropertyExpression(ie, prop.getKey());
                        result = Ret.createNewExpressionAlias(identifierBuilder, exp);
                    } else {
                        result = Ret.createNewExpressionAlias(identifierBuilder, ie);
                    }
                }
            }
            //返回表达式
            else if (kind == 5) {
                CypherType type = CypherType.getRandomType();
                result = Ret.createNewExpressionAlias(identifierBuilder,
                        randomExpressionGenerator.generateAllExpression(type, true, 3));
            }
            //todo 返回*,在call{}中可能出问题
            else if (kind == 6) {
                //todo 不在call{}中并且有变量的时候才可以返回*
                if (i == 0 && !returnClause.getInCall() && (DifferentialNonEmptyBranchOracle.union_flag == 0) && (sizeOfAlias+sizeOfNode+sizeOfRelation+sizeOfPath)!= 0)
                    result = Ret.createStar();
                else {
                    CypherType type = CypherType.getRandomType();
                    result = Ret.createNewExpressionAlias(identifierBuilder,
                            randomExpressionGenerator.generateAllExpression(type, true, 3));
                }
            }
            //返回1
            else {
                result = Ret.createNewExpressionAlias(identifierBuilder, new ConstExpression(1));
            }
            if (result == null)
                throw new RuntimeException("null result exception!");
            //todo 列名一定不重复，可以直接加入
            results.add(result);
        }
        //对于UNION ALL中的第二个查询的return,需要保持与第一个查询的列名相同
        if (DifferentialNonEmptyBranchOracle.union_flag == 2 && returnClause.getInQuery()) {
            for (int i = 0; i < numOfExpressions; i++) {
                IRet result = results.get(i);
                result.setIdentifier(DifferentialNonEmptyBranchOracle.returns.get(i).getIdentifier());
            }
        }
        //设置 DISTINCT
        returnClause.setDistinct(Randomly.getBooleanWithRatherLowProbability());
        //设置limit skip
        if (Randomly.getBooleanWithRatherLowProbability()) {
            returnClause.setSkip(new ConstExpression(new Randomly().getInteger(0, 2)));
        }
        if (Randomly.getBooleanWithRatherLowProbability()) {
            returnClause.setLimit(new ConstExpression(new Randomly().getInteger(0, 2)));
        }
        return results;
    }

    public List<IDele> generateDeleteNoAlias(IDeleteAnalyzer deleteClause, IIdentifierBuilder identifierBuilder, S schema) {
        if (deleteClause.getDeleteList().size() != 0 && !overrideOld) {
            return deleteClause.getDeleteList();
        }
        List<IDele> results = new ArrayList<>();
        Randomly r = new Randomly();
        int numOfExpressions = r.getInteger(1, 3);
        //todo janusgraph一次delete只删除一个
        if(global.getDatabaseName().contains("janus"))
            numOfExpressions = 1;
        for (int i = 0; i < numOfExpressions; i++) {
            Dele delete = null;
            int kind = r.getInteger(1, 6);
            //todo agensgraph不能删除关系
            if (kind == 4&& !global.getDatabaseName().contains("agens"))
                delete = new Dele(randomExpressionGenerator.generateUseVar(CypherType.RELATION, false, 3));
            //todo memgraph不能删除路径
            else if (kind == 5 && !global.getDatabaseName().contains("memgraph"))
                delete = new Dele(randomExpressionGenerator.generateUseVar(CypherType.PATH, false, 3));
            else
                delete = new Dele(randomExpressionGenerator.generateUseVar(CypherType.NODE, false, 3));
            //todo 对于delete NULL的情况
            if(delete.getExpression() instanceof ConstExpression){
//                System.out.println(((ConstExpression) delete.getExpression()).getValue());
                List<IIdentifierAnalyzer> identifierAnalyzers = new ArrayList<>();
                identifierAnalyzers.addAll(deleteClause.toAnalyzer().getAvailableNodeIdentifiers());
                identifierAnalyzers.addAll(deleteClause.toAnalyzer().getAvailableRelationIdentifiers());
                identifierAnalyzers.addAll(deleteClause.toAnalyzer().getAvailablePathIdentifiers());
                identifierAnalyzers.addAll(deleteClause.toAnalyzer().getAvailableAliases().stream().filter(a->a.analyzeType(schema).getType()==CypherType.NODE||a.analyzeType(schema).getType()==CypherType.RELATION||a.analyzeType(schema).getType()==CypherType.PATH).collect(Collectors.toList()));
                if(!identifierAnalyzers.isEmpty()) {
                    IIdentifierAnalyzer identifierAnalyzer = Randomly.fromList(identifierAnalyzers);
                    delete.setExpression(new IdentifierExpression(identifierAnalyzer));
                }
            }
            results.add(delete);
            //todo 同时删除两个对象时先将第一个被删除的对象写入，防止生成第二个被删除的对象时访问它
            if(i==0 && numOfExpressions==2)
                deleteClause.setDeleteList(Arrays.asList(delete));
        }
        return results;
    }

    @Override
    public List<IRet> generateWithAlias(IWithAnalyzer withClause, IIdentifierBuilder identifierBuilder, S schema) {
        List<IRet> withAlias = withClause.getReturnList();
        if (withAlias.size() != 0 && !overrideOld) {
            return withAlias;
        }
        List<IRet> results = new ArrayList<>();
        List<INodeAnalyzer> idNode = withClause.getExtendableNodeIdentifiers();
        List<IRelationAnalyzer> idRelation = withClause.getExtendableRelationIdentifiers();
        List<IAliasAnalyzer> idAlias = withClause.getExtendableAliases();
        List<IPathAnalyzer> idPath = withClause.getExtendablePathIdentifiers();
        Randomly r = new Randomly();
        int sizeOfAlias = idAlias.size();
        int sizeOfNode = idNode.size();
        int sizeOfRelation = idRelation.size();
        int sizeOfPath = idPath.size();

        int numOfExpressions = r.getInteger(1, 3);

        for (int i = 0; i < numOfExpressions; i++) {
            Ret result = null;
            if (i == 0) {
                int kind = r.getInteger(0, 6);
                if (kind == 0) {
                    if (sizeOfAlias > 0) {
                        IAliasAnalyzer alias = idAlias.get(r.getInteger(0, sizeOfAlias));
                        result = Ret.createAliasRef(alias);
                    }
                } else if (kind == 1) {
                    if (sizeOfNode > 0) {
                        INodeAnalyzer node = idNode.get(r.getInteger(0, sizeOfNode));
                        List<IPropertyInfo> props = node.getAllPropertiesAvailable(schema);
                        IdentifierExpression ie = new IdentifierExpression(node);
                        if (props.size() > 0 && Randomly.getBoolean()) {
                            IPropertyInfo prop = props.get(r.getInteger(0, props.size()));
                            GetPropertyExpression exp = new GetPropertyExpression(ie, prop.getKey());
                            result = Ret.createNewExpressionAlias(identifierBuilder, exp);
                        } else {
                            result = Ret.createNodeRef(node);
                        }
                    }
                } else if (kind == 2) {
                    if (sizeOfRelation > 0) {
                        IRelationAnalyzer relation = idRelation.get(r.getInteger(0, sizeOfRelation));
                        if (relation.isSingleRelation()) {
                            List<IPropertyInfo> props = relation.getAllPropertiesAvailable(schema);
                            IdentifierExpression ie = new IdentifierExpression(relation);
                            if (props.size() > 0 && Randomly.getBoolean()) {
                                IPropertyInfo prop = props.get(r.getInteger(0, props.size()));
                                GetPropertyExpression exp = new GetPropertyExpression(ie, prop.getKey());
                                result = Ret.createNewExpressionAlias(identifierBuilder, exp);
                            } else {
                                result = Ret.createRelationRef(relation);
                            }
                        }
                    }
                } else if (kind == 3) {
                    CypherType type = CypherType.getRandomType();
                    result = Ret.createNewExpressionAlias(identifierBuilder,
                            randomExpressionGenerator.generateAllExpression(type, true, 3));
                } else if (kind == 4) {
                    if (sizeOfPath > 0) {
                        IPathAnalyzer path = idPath.get(r.getInteger(0, sizeOfPath));
                        result = Ret.createPathRef(path);
                    }
                } else {
                    result = Ret.createStar();
                }
            } else {
                int kind = r.getInteger(0, 4);
                if (kind == 0) {
                    if (sizeOfAlias > 0) {
                        IAliasAnalyzer alias = idAlias.get(r.getInteger(0, sizeOfAlias));
                        result = Ret.createAliasRef(alias);
                    }
                } else if (kind == 1) {
                    if (sizeOfNode > 0) {
                        INodeAnalyzer node = idNode.get(r.getInteger(0, sizeOfNode));
                        List<IPropertyInfo> props = node.getAllPropertiesAvailable(schema);
                        IdentifierExpression ie = new IdentifierExpression(node);
                        if (props.size() > 0 && Randomly.getBoolean()) {
                            IPropertyInfo prop = props.get(r.getInteger(0, props.size()));
                            GetPropertyExpression exp = new GetPropertyExpression(ie, prop.getKey());
                            result = Ret.createNewExpressionAlias(identifierBuilder, exp);
                        } else {
                            result = Ret.createNodeRef(node);
                        }
                    }
                } else if (kind == 2) {
                    if (sizeOfRelation > 0) {
                        IRelationAnalyzer relation = idRelation.get(r.getInteger(0, sizeOfRelation));
                        if (relation.isSingleRelation()) {
                            List<IPropertyInfo> props = relation.getAllPropertiesAvailable(schema);
                            IdentifierExpression ie = new IdentifierExpression(relation);
                            if (props.size() > 0 && Randomly.getBoolean()) {
                                IPropertyInfo prop = props.get(r.getInteger(0, props.size()));
                                GetPropertyExpression exp = new GetPropertyExpression(ie, prop.getKey());
                                result = Ret.createNewExpressionAlias(identifierBuilder, exp);
                            } else {
                                result = Ret.createRelationRef(relation);
                            }
                        }
                    }
                } else if (kind == 3) {
                    CypherType type = CypherType.getRandomType();
                    result = Ret.createNewExpressionAlias(identifierBuilder,
                            randomExpressionGenerator.generateAllExpression(type, true, 3));
                }
            }
            if (result != null) {
                boolean flag = true;
                for (IRet res : results) {
                    if (res.equals(result)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    results.add(result);
                }
            }
        }
        if (results.isEmpty()) {
            if (Randomly.getBoolean())
                results.add(Ret.createStar());
            else
                results.add(Ret.createNewExpressionAlias(identifierBuilder,
                        randomExpressionGenerator.generateAllExpression(CypherType.getRandomType(), true, 3)));
        }
        withClause.setDistinct(Randomly.getBooleanWithRatherLowProbability());
        //设置limit skip
        if (Randomly.getBooleanWithRatherLowProbability()) {
            withClause.setSkip(new ConstExpression(new Randomly().getInteger(0, 2)));
        }
        if (Randomly.getBooleanWithRatherLowProbability()) {
            withClause.setLimit(new ConstExpression(new Randomly().getInteger(0, 2)));
        }
        return results;
    }

    @Override
    public List<IRet> generateWithAlias_Call(IWithAnalyzer withClause, IIdentifierBuilder identifierBuilder, S schema) {
        List<IRet> withAlias = withClause.getReturnList();
        if (withAlias.size() != 0 && !overrideOld) {
            return withAlias;
        }
        List<IRet> results = new ArrayList<>();
        List<IIdentifierAnalyzer> list = new ArrayList<>();
        list.addAll(withClause.getExtendableNodeIdentifiers());
        list.addAll(withClause.getExtendableRelationIdentifiers());
        list.addAll(withClause.getExtendableAliases());
        list.addAll(withClause.getExtendablePathIdentifiers());
        Randomly r = new Randomly();
        int numOfExpressions = r.getInteger(0, list.size() + 1);
        for (int i = 0; i < numOfExpressions; i++) {
            Ret result = Ret.importing_with_call(list.get(r.getInteger(0, list.size())));
            boolean flag = true;
            for (IRet res : results) {
                if (res.equals(result)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                results.add(result);
            }
        }
        if (results.isEmpty()) {
            results.add(Ret.createStar());
        }
        return results;
    }
}
