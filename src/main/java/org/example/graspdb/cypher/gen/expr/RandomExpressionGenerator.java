package org.example.graspdb.cypher.gen.expr;


import org.example.graspdb.Randomly;
import org.example.graspdb.cypher.CypherGlobalState;
import org.example.graspdb.cypher.ast.*;
import org.example.graspdb.cypher.ast.analyzer.*;
import org.example.graspdb.cypher.dsl.IQueryGenerator;
import org.example.graspdb.cypher.gen.pattern.SlidingPatternGenerator;
import org.example.graspdb.cypher.gen.query.ModelBasedQueryGenerator;
import org.example.graspdb.cypher.oracle.DifferentialNonEmptyBranchOracle;
import org.example.graspdb.cypher.schema.CypherSchema;
import org.example.graspdb.cypher.standard_ast.Alias;
import org.example.graspdb.cypher.standard_ast.CypherClause;
import org.example.graspdb.cypher.standard_ast.CypherType;
import org.example.graspdb.cypher.standard_ast.expr.*;
import org.example.graspdb.neo4j.schema.Neo4jSchema;

import java.util.*;
import java.util.stream.Collectors;

public class RandomExpressionGenerator<G extends CypherGlobalState<?, S>, S extends CypherSchema<G, ?>>
{
    IClauseAnalyzer clauseAnalyzer;
    private S schema;
    private G global;
    public Boolean distinct;
    public Boolean agg_flag;
    public Boolean order_by;
    public Boolean where_flag;
    private Boolean case_flag;
    private Boolean reduce_flag;
    public static IQueryGenerator queryGenerator;
    public RandomExpressionGenerator(IClauseAnalyzer clauseAnalyzer, G global){
        this.distinct=false;
        this.where_flag=false;
        this.order_by=false;
        this.agg_flag=false;
        this.clauseAnalyzer = clauseAnalyzer;
        this.global=global;
        this.schema = global.getSchema();
        this.case_flag = false;
        this.reduce_flag=false;
        queryGenerator= DifferentialNonEmptyBranchOracle.queryGenerator;
    }
    public void copy_flag(RandomExpressionGenerator randomExpressionGenerator){
        this.agg_flag=randomExpressionGenerator.agg_flag;
        this.distinct=randomExpressionGenerator.distinct;
        this.order_by=randomExpressionGenerator.order_by;
        this.where_flag=randomExpressionGenerator.where_flag;
    }
// 生成聚合函数
    public IExpression generateAggFunction(CypherType type,Boolean agg,int depth){
        if(type == CypherType.LIST)
            return generateAggCollect(agg,1);
        if(type == CypherType.NUMBER)
            return generateNumberAgg(agg,1);
        if(type == CypherType.STRING)
            return generateStringAgg(agg,1);
        return null;
    }
    //生成变量表达式、函数表达式以及常量表达式，其中depth限制函数递归调用栈的深度
    public IExpression generateAllExpression(CypherType type,Boolean agg,int depth){
        //todo 避免聚合函数被其他函数嵌套所引起的语法错误
        if(depth<3)
            agg=false;
        //避免聚合函数中的子查询里的聚合函数嵌套
        if(queryGenerator.getsubqueryflag()&&!queryGenerator.getaggfuncflag())
            agg=false;
        //delete中不能用聚合函数
        if(queryGenerator.getdeleteflag())
            agg=false;
        int i = new Randomly().getInteger(0,11);
        if(depth==0)
            return basicTypeExpression(type,agg,0,0);
        // todo 防止“In a WITH/RETURN with DISTINCT or an aggregation, it is not possible to access variables declared before the WITH/RETURN”语法错误
        if(i<4&&!(order_by&&distinct)&&!(order_by&&agg_flag)&&!(where_flag&&distinct)&&!(where_flag&&agg_flag))
            return generateUseVar(type,agg,depth-1);
        if(i<8&&!(type==CypherType.NODE)&&!(type==CypherType.RELATION))
            return generateConstExpression(type,agg,depth-1);
        return generateFunction(type,agg,depth-1);
    }
    //生成常量表达式
    public IExpression generateConstExpression(CypherType type,Boolean agg,int depth){
        Randomly randomly = new Randomly();
        switch (type){
            case NUMBER:
                if(Randomly.getBoolean())
                    return basicTypeExpression(CypherType.NUMBER,agg,0,depth);
                else
                    return Randomly.getBoolean()?basicTypeExpression(CypherType.NUMBER,agg,1,depth):basicTypeExpression(CypherType.NUMBER,agg,2,depth);
            case STRING:
                if(Randomly.getBoolean())
                    return basicTypeExpression(CypherType.STRING,agg,0,depth);
                else{
                    return Randomly.getBoolean()?basicTypeExpression(CypherType.STRING,agg,1,depth):basicTypeExpression(CypherType.STRING,agg,2,depth);
                }
            case BOOLEAN:
                if(Randomly.getBoolean())
                    return basicTypeExpression(CypherType.BOOLEAN,agg,0,depth);
                else{
                    return Randomly.getBoolean()?basicTypeExpression(CypherType.BOOLEAN,agg,1,depth):basicTypeExpression(CypherType.BOOLEAN,agg,2,depth);
                }
            //常量LIST,MAP生成时支持函数的嵌套
            case LIST:
            {
                    //元素类型相同的列表
                    List<IExpression> l=new ArrayList<>();
                    CypherType t=CypherType.getRandomType();
                    for(int i=0;i<randomly.getInteger(1,3);i++){
                        l.add(generateAllExpression(t,agg,depth));
                    }
                    return new CreateListExpression(l,t);
            }
            case MAP:
            {
                    List<IIdentifier> availableExpressions = new ArrayList<>();
                    List<IAliasAnalyzer> aliasAnalyzers = clauseAnalyzer.getAvailableAliases();
                    List<INodeAnalyzer> nodeAnalyzers = clauseAnalyzer.getAvailableNodeIdentifiers();
                    List<IRelationAnalyzer> relationAnalyzers = clauseAnalyzer.getAvailableRelationIdentifiers();
                    for(IAliasAnalyzer a:aliasAnalyzers){
                        if(a.analyzeType(schema).getType()==CypherType.NODE||a.analyzeType(schema).getType()==CypherType.RELATION){
                            availableExpressions.add(a);
                        }
                    }
                    availableExpressions.addAll(nodeAnalyzers);
                    availableExpressions.addAll(relationAnalyzers);
                //todo 生成Map projection，同时防止“In a WITH/RETURN with DISTINCT or an aggregation, it is not possible to access variables declared before the WITH/RETURN”语法错误
                    if(Randomly.getBoolean()&&availableExpressions.size()>0&&!(order_by&&distinct)&&!(order_by&&agg_flag)&&!(where_flag&&distinct)&&!(where_flag&&agg_flag)){
                        String p_name="";
                        IIdentifier id = availableExpressions.get(randomly.getInteger(0,availableExpressions.size()));
                        if(id instanceof INodeIdentifier){
                            int size=((INodeIdentifier) id).getProperties().size();
                            if(size>0){
                            IProperty p=((INodeIdentifier) id).getProperties().get(randomly.getInteger(0,size));
                            p_name=p.getKey();}
                            else
                                p_name="k";
                        }
                        else if(id instanceof IRelationIdentifier){
                            int size=((IRelationIdentifier) id).getProperties().size();
                            if(size>0)
                            {IProperty p=((IRelationIdentifier) id).getProperties().get(randomly.getInteger(0,size));
                            p_name=p.getKey();}
                            else
                                p_name="k";
                        }
                        else if(id instanceof IAliasAnalyzer){
                            IAliasAnalyzer aliasID=(IAliasAnalyzer) id;
                            List<IProperty> l=new ArrayList<>();
                            if(aliasID.analyzeType(schema).getType()==CypherType.NODE){
                                //如果能获取节点分析器（即节点不是来自函数的返回值等），则可以获得节点中的属性有哪些
                                if(aliasID.analyzeType(schema).getNodeAnalyzer()!=null)
                                    l=aliasID.analyzeType(schema).getNodeAnalyzer().getProperties();
                            }
                            else if(aliasID.analyzeType(schema).getType()==CypherType.RELATION){
                                if(aliasID.analyzeType(schema).getRelationAnalyzer()!=null)
                                    l=aliasID.analyzeType(schema).getRelationAnalyzer().getProperties();
                            }
                            else {
                                System.out.println(aliasID.analyzeType(schema).getType());
                                throw new RuntimeException("other type in Map projection");
                            }
                            if(l.size()>0){
                            IProperty p=l.get(randomly.getInteger(0,l.size()));
                            p_name=p.getKey();}
                            else
                                p_name="k";
                        }
                        else {
                            System.out.println(id.getClass());
                            throw new RuntimeException("other type of id in Map projection");
                        }
                        return new CreateMapExpression(id,p_name);
                    }
                //直接生成map
                else {
                    Map<String, IExpression> m =new HashMap<>();
                    m.put("k1",generateAllExpression(CypherType.getRandomType(),agg,depth));
                    m.put("k2",generateAllExpression(CypherType.getRandomType(),agg,depth));
                    return new CreateMapExpression(m);}
            }
            //无法生成node和relation类型的常量
            case NODE:
            case RELATION: {
                return new ConstExpression(null);
            }
            default:
                return new ConstExpression(null);
        }
    }
    //生成变量表达式(node,relationship,alias,path)
    public IExpression generateUseVar(CypherType type,Boolean agg,int depth){
        Randomly randomly = new Randomly();
        List<IExpression> availableExpressions = new ArrayList<>();
//获取Alias
        List<IAliasAnalyzer> aliasAnalyzers = clauseAnalyzer.getAvailableAliases();
        availableExpressions.addAll(aliasAnalyzers.stream().filter(a->a.analyzeType(schema).getType()==type).map(a->new IdentifierExpression(Alias.createIdentifierRef(a)))
                .collect(Collectors.toList()));
//获取NodeIdentifier
        List<INodeAnalyzer> nodeAnalyzers = clauseAnalyzer.getAvailableNodeIdentifiers();
        nodeAnalyzers.addAll(aliasAnalyzers.stream().filter(a->a.analyzeType(schema).getType()==CypherType.NODE).map(
                a-> a.analyzeType(schema).getNodeAnalyzer()
        ).collect(Collectors.toList()));

//获取RelationIdentifier
        List<IRelationAnalyzer> relationAnalyzers = clauseAnalyzer.getAvailableRelationIdentifiers();
        relationAnalyzers.addAll(aliasAnalyzers.stream().filter(a->a.analyzeType(schema).getType()==CypherType.RELATION).map(
                a-> a.analyzeType(schema).getRelationAnalyzer()
        ).collect(Collectors.toList()));

        //获取PathIdentifier
        List<IPathAnalyzer> pathAnalyzers = clauseAnalyzer.getAvailablePathIdentifiers();
        pathAnalyzers.addAll(aliasAnalyzers.stream().filter(a->a.analyzeType(schema).getType()==CypherType.PATH).map(
                a-> a.analyzeType(schema).getPathAnalyzer()
        ).collect(Collectors.toList()));

        nodeAnalyzers.stream()
                .filter(n -> n != null)
                .forEach(n -> {
                    n.getAllPropertiesWithType(schema, type)
                            .forEach(p -> {
                                availableExpressions.add(new GetPropertyExpression(new IdentifierExpression(n),
                                        p.getKey()));
                            });
                });

        relationAnalyzers.stream()
                .filter(r -> r != null)
                .forEach(r -> {
                    r.getAllPropertiesWithType(schema, type)
                            .forEach(p -> {
                                availableExpressions.add(new GetPropertyExpression(new IdentifierExpression(r),
                                        p.getKey()));
                            });
                });
        if(type==CypherType.NODE) {
            nodeAnalyzers.stream()
                    .filter(n -> n != null)
                    .forEach(n->availableExpressions.add(new IdentifierExpression(n)));
        }
        if(type==CypherType.RELATION) {
            relationAnalyzers.stream()
                    .filter(n -> n != null)
                    .forEach(n->availableExpressions.add(new IdentifierExpression(n)));
        }
        if(type==CypherType.PATH) {
            pathAnalyzers.stream()
                    .filter(n -> n != null)
                    .forEach(n->availableExpressions.add(new IdentifierExpression(n)));
        }
        if(availableExpressions.size() == 0){
            //没有可用变量就用null替代
            return new ConstExpression(null);
        }
        return availableExpressions.get(randomly.getInteger(0, availableExpressions.size()));
    }
    //生成函数表达式
    public IExpression generateFunction(CypherType type,Boolean agg,int depth){
        //todo agensgraph的where中不能有case when
        if(new Randomly().getInteger(0,10)==0&&!case_flag&&!(where_flag&&global.getDatabaseName().contains("agens")))
        {
            return generateCaseWhen(type,agg,depth);
        }
        //生成reduce  todo agensgraph,janusgraph不支持reduce()
        if(new Randomly().getInteger(0,10)==0&&!reduce_flag&&!global.getDatabaseName().contains("agens")&&!global.getDatabaseName().contains("janus"))
        {
            return generateReduce(type,agg,depth);
        }
        //生成number函数
        if(type == CypherType.NUMBER){
            int i=new Randomly().getInteger(0,4);
            //merge中不能有子查询
            if(!queryGenerator.getsubqueryflag()&&i<2&&!queryGenerator.getmergeflag())
                return generateNumberCount(agg,depth);
            if(agg){
                if(Randomly.getBoolean())
                    return generateNumberAgg(agg,depth);
                else
                    return generateNumberNotAgg(agg,depth);
            }
            else
                return generateNumberNotAgg(agg,depth);
        }
        //生成string函数
        if(type == CypherType.STRING){
            if(agg){
            if(Randomly.getBoolean())
                return generateStringAgg(agg,depth);
            else
                return generateStringNotAgg(agg,depth);}
            else
                return generateStringNotAgg(agg,depth);
        }
        //生成bool函数
        if(type == CypherType.BOOLEAN){
            //merge中不能有子查询
            if(Randomly.getBoolean()&&!queryGenerator.getsubqueryflag()&&!queryGenerator.getmergeflag())
                return generateBooleanExists(agg,depth);
            else
                return generateBooleanNotExists(agg,depth);
        }
        //生成list函数
        if(type == CypherType.LIST){
            if(agg){
            int r=new Randomly().getInteger(0,5);
            if(r<1)
                return generateAggCollect(agg,depth);
//                return generatePatternComprehension(false,depth);
// todo agensgraph不支持模式理解
            else if(r<2&&!queryGenerator.get_pattern_comprehension_flag()&&!global.getDatabaseName().contains("agens"))
                //模式理解中不能使用聚合函数
                return generatePatternComprehension(false,depth);
            //todo memgraph不支持列表理解
            else if(r<3&&!queryGenerator.get_list_comprehension_flag()&&!global.getDatabaseName().contains("memgraph"))
                return generateListComprehension(agg,depth);
            else
                return generateListFunction(agg,depth);
            }
            else{
                int r=new Randomly().getInteger(0,4);
                if(r<1&&!queryGenerator.get_pattern_comprehension_flag()&&!global.getDatabaseName().contains("agens"))
                    //模式理解中不能使用聚合函数
                    return generatePatternComprehension(false,depth);
                else if(r<2&&!queryGenerator.get_list_comprehension_flag()&&!global.getDatabaseName().contains("memgraph"))
                    return generateListComprehension(agg,depth);
                else
                    return generateListFunction(agg,depth);
//                    return generateListComprehension(agg,depth);

            }
        }
        //生成Map函数properties()
        if(type == CypherType.MAP){
                return generateMapFunction(agg,depth);
        }
        //生成node函数
        if(type == CypherType.NODE){
                return generateNode(agg,depth);
        }
        //生成relationship函数
        if(type == CypherType.RELATION){
                return generateRelation(agg,depth);
        }
        return new ConstExpression(null);
    }
    private IExpression generateCaseWhen(CypherType type,Boolean agg,int depth){
        case_flag=true;
        IExpression condition=generateAllExpression(CypherType.BOOLEAN,agg,depth);
        IExpression value=generateAllExpression(type,agg,depth);
        case_flag=false;
        return new CaseWhenExpression(condition,value,type);
    }
    private IExpression generateReduce(CypherType type,Boolean agg,int depth){
        reduce_flag=true;
        IExpression a=generateAllExpression(type,agg,depth);
        List<IExpression> l=new ArrayList<>();
        l.add(generateAllExpression(type,agg,depth));
        if(Randomly.getBoolean())
            l.add(generateAllExpression(type,agg,depth));
        IExpression b=new CreateListExpression(l,type);
        reduce_flag=false;
        return new ReduceExpression(a,b,type);
    }
    //todo
    private IExpression generatePatternComprehension(Boolean agg,int depth){
        queryGenerator.set_pattern_comprehension_flag(true);
        SlidingPatternGenerator slidingPatternGenerator=((SlidingPatternGenerator)(((ModelBasedQueryGenerator)queryGenerator).patternGenerator));
        IPattern p_pattern =slidingPatternGenerator.generateComprehensionPattern(clauseAnalyzer,((ModelBasedQueryGenerator)queryGenerator).identifierBuilder,schema);
        //记录理解中的模式，用于计算模式理解中的本地变量
        queryGenerator.set_pattern_in_comprehension(p_pattern);
        IExpression condition=null;
        if(Randomly.getBoolean())
            condition=generateCondition(false,0,depth);
        CypherType type=CypherType.getRandomType();
        IExpression expression=generateAllExpression(type,agg,depth);
        queryGenerator.set_pattern_comprehension_flag(false);
        queryGenerator.set_pattern_in_comprehension(null);
        return new CreateListExpression(p_pattern,condition,expression,type);
    }
    //todo
    private IExpression generateListComprehension(Boolean agg,int depth){
        queryGenerator.set_list_comprehension_flag(true);
        List <IExpression> l=new ArrayList<>();
        CypherType type0=CypherType.getRandomType();
        l.add(generateAllExpression(type0,agg,depth));
        l.add(generateAllExpression(type0,agg,depth));
        IExpression list =new CreateListExpression(l,type0);
        //todo 将x作为LocalAliases加入到可用变量中
        IAlias alias=Alias.createAliasWithName("x",l.get(0));
        queryGenerator.set_list_comprehension_alias(alias);
        IExpression condition=null;
        CypherType type=CypherType.getRandomType();
        //todo 插件不支持列表理解中的where
        if(Randomly.getBoolean()&&!global.getDatabaseName().contains("janus")&&!global.getDatabaseName().contains("huge"))
            condition=generateCondition(false,0,depth);
        IExpression expression=null;
        if(Randomly.getBoolean())
            expression=generateAllExpression(type,false,depth);
        queryGenerator.set_list_comprehension_flag(false);
        //清除在列表理解中定义的x
        queryGenerator.set_list_comprehension_alias(null);
        return new CreateListExpression(list,condition,expression,type0,type);
    }

    private IExpression generateAggCollect(Boolean agg,int depth){
        agg_flag=true;
        IExpression param = generateUseVar(CypherType.getRandomType(),false,depth);
        return new CallExpression(Neo4jSchema.Neo4jBuiltInFunctions.COLLECT, Arrays.asList(param));
    }
    private IExpression generateListFunction(Boolean agg,int depth){
        Randomly randomly = new Randomly();
        int randNum = randomly.getInteger(0, 80);
        IExpression param_node = generateAllExpression(CypherType.NODE,agg,depth);
        IExpression param_list = generateAllExpression(CypherType.LIST,agg,depth);
        IExpression param_path = generateAllExpression(CypherType.PATH,agg,depth);
        IExpression param_relation = generateAllExpression(CypherType.RELATION,agg,depth);
        IExpression param_map = generateAllExpression(CypherType.MAP,agg,depth);
        //todo
        if( randNum < 10){
            return new CallExpression(Neo4jSchema.Neo4jBuiltInFunctions.KEYS, Arrays.asList(param_node));
        }
        if( randNum < 15){
            return new CallExpression(Neo4jSchema.Neo4jBuiltInFunctions.LABELS, Arrays.asList(param_node));
        }
        if( randNum < 20){
            return new CallExpression(Neo4jSchema.Neo4jBuiltInFunctions.KEYS, Arrays.asList(param_relation));
        }
        //todo memgraph不支持map作为函数keys的参数
        if( randNum < 25&&!global.getDatabaseName().contains("memgraph")){
            return new CallExpression(Neo4jSchema.Neo4jBuiltInFunctions.KEYS, Arrays.asList(param_map));
        }
        if( randNum < 30){
            return new CallExpression(Neo4jSchema.Neo4jBuiltInFunctions.NODES, Arrays.asList(param_path));
        }
        if( randNum < 40){
            return new CallExpression(Neo4jSchema.Neo4jBuiltInFunctions.RELATIONSHIPS, Arrays.asList(param_path));
        }
        if( randNum < 50&&!global.getDatabaseName().contains("memgraph")&&!global.getDatabaseName().contains("agens")){
            return new CallExpression(Neo4jSchema.Neo4jBuiltInFunctions.REVERSE_List, Arrays.asList(param_list));
        }
        if( randNum < 60&&!global.getDatabaseName().contains("memgraph")){
            return new CallExpression(Neo4jSchema.Neo4jBuiltInFunctions.toBooleanList, Arrays.asList(param_list));
        }
        if( randNum < 70&&!global.getDatabaseName().contains("memgraph")){
            return new CallExpression(Neo4jSchema.Neo4jBuiltInFunctions.toIntegerList, Arrays.asList(param_list));
        }
        if( randNum < 80&&!global.getDatabaseName().contains("memgraph")){
            return new CallExpression(Neo4jSchema.Neo4jBuiltInFunctions.toStringList, Arrays.asList(param_list));
        }
        return new ConstExpression(null);
    }
    private IExpression generateMapFunction(Boolean agg,int depth){
        Randomly randomly = new Randomly();
        int randNum = randomly.getInteger(0, 10);
        IExpression param = generateAllExpression(CypherType.NODE,agg,depth);
        IExpression param01 = generateAllExpression(CypherType.RELATION,agg,depth);
        //todo
        if( randNum < 10){
            return new CallExpression(Neo4jSchema.Neo4jBuiltInFunctions.Properties, Arrays.asList(Randomly.getBoolean()?param01:param));
        }
        return new ConstExpression(null);
    }
    private IExpression generateNode(Boolean agg,int depth){
        Randomly randomly = new Randomly();
        int randNum = randomly.getInteger(0, 40);
        IExpression param_path = generateAllExpression(CypherType.PATH,agg,depth);
        IExpression param_node = generateAllExpression(CypherType.NODE,false,depth);
        IExpression param_relation = generateAllExpression(CypherType.RELATION,agg,depth);
        if( randNum < 10&&agg){
            return new GetListElementExpression(new CallExpression(Neo4jSchema.Neo4jBuiltInFunctions.COLLECT,Arrays.asList(param_node)),ToPositiveInt(generateAllExpression(CypherType.NUMBER,agg,0)));
        }
        if( randNum < 20){
            return new CallExpression(Neo4jSchema.Neo4jBuiltInFunctions.endNode, Arrays.asList(param_relation));
        }
        if( randNum < 30){
            return new CallExpression(Neo4jSchema.Neo4jBuiltInFunctions.startNode, Arrays.asList(param_relation));
        }
        if( randNum < 40){
            return new GetListElementExpression(new CallExpression(Neo4jSchema.Neo4jBuiltInFunctions.NODES,Arrays.asList(param_path)),ToPositiveInt(generateAllExpression(CypherType.NUMBER,agg,depth)));
        }
        return new ConstExpression(null);
    }
    private IExpression generateRelation(Boolean agg,int depth){
        Randomly randomly = new Randomly();
        int randNum = randomly.getInteger(0, 20);
        IExpression param_path = generateAllExpression(CypherType.PATH,agg,depth);
        IExpression param_relation = generateAllExpression(CypherType.RELATION,false,depth);
        if( randNum < 10&&agg){
            return new GetListElementExpression(new CallExpression(Neo4jSchema.Neo4jBuiltInFunctions.COLLECT,Arrays.asList(param_relation)),ToPositiveInt(generateAllExpression(CypherType.NUMBER,agg,0)));
        }
        if( randNum < 20){
            return new GetListElementExpression(new CallExpression(Neo4jSchema.Neo4jBuiltInFunctions.RELATIONSHIPS,Arrays.asList(param_path)),ToPositiveInt(generateAllExpression(CypherType.NUMBER,agg,depth)));
        }
        return new ConstExpression(null);
    }
    private IExpression generateNumberAgg(Boolean agg,int depth){
        agg_flag=true;
        Randomly randomly = new Randomly();
        int randNum = randomly.getInteger(0, 80);
        IExpression param = generateUseVar(CypherType.NUMBER,false,depth);
        if( randNum < 10){
            return new CallExpression(Neo4jSchema.Neo4jBuiltInFunctions.MAX_NUMBER, Arrays.asList(param));
        }
        if( randNum < 20){
            return new CallExpression(Neo4jSchema.Neo4jBuiltInFunctions.MIN_NUMBER, Arrays.asList(param));
        }
        if( randNum < 30){
            return new CallExpression(Neo4jSchema.Neo4jBuiltInFunctions.AVG, Arrays.asList(param));
        }
        if( randNum < 40){
            return new CallExpression(Neo4jSchema.Neo4jBuiltInFunctions.SUM, Arrays.asList(param));
        }
        if( randNum < 50&&!global.getDatabaseName().contains("memgraph")){
            return new CallExpression(Neo4jSchema.Neo4jBuiltInFunctions.ST_DEV, Arrays.asList(param));
        }
        if( randNum < 60&&!global.getDatabaseName().contains("memgraph")){
            return new CallExpression(Neo4jSchema.Neo4jBuiltInFunctions.ST_DEV_P, Arrays.asList(param));
        }
        if( randNum < 70&&!global.getDatabaseName().contains("memgraph")) {
            return new CallExpression(Neo4jSchema.Neo4jBuiltInFunctions.PERCENTILE_DISC_NUMBER, Arrays.asList(param, new ConstExpression(1 / randomly.getInteger(1, 101))));
        }
        if( randNum < 80&&!global.getDatabaseName().contains("memgraph")) {
            return new CallExpression(Neo4jSchema.Neo4jBuiltInFunctions.PERCENTILE_DISC_NUMBER, Arrays.asList(param, new ConstExpression(1 / randomly.getInteger(1, 101))));
        }
        return new ConstExpression(null);
    }
    private IExpression generateNumberNotAgg(Boolean agg,int depth){
        Randomly randomly = new Randomly();
        int randNum = randomly.getInteger(0, 60);
        IExpression param_number = generateAllExpression(CypherType.NUMBER,agg,depth);
        IExpression param_list = generateAllExpression(CypherType.LIST,agg,depth);
        IExpression param_path = generateAllExpression(CypherType.PATH,agg,depth);
        IExpression param_num = generateAllExpression(CypherType.NUMBER,false,depth);
        IExpression param_number01 = generateAllExpression(CypherType.NUMBER,agg,depth);
        if( randNum < 10&&agg){
            return new GetListElementExpression(new CallExpression(Neo4jSchema.Neo4jBuiltInFunctions.COLLECT,Arrays.asList(param_num)),ToPositiveInt(generateAllExpression(CypherType.NUMBER,agg,0)));
        }
        //todo redis,memgraph中round函数只支持一个参数
        if( randNum < 17&&global.getDatabaseName().contains("neo4j")){
            return new CallExpression(Neo4jSchema.Neo4jBuiltInFunctions.ROUND, Arrays.asList(param_number,ToPositiveInt(param_number01)));
        }
        if( randNum < 24){
            return new CallExpression(Neo4jSchema.Neo4jBuiltInFunctions.ROUND, Arrays.asList(param_number));
        }
        if( randNum < 30){
            return new CallExpression(Neo4jSchema.Neo4jBuiltInFunctions.SIZE, Arrays.asList(param_list));
        }
        if( randNum < 40&&!global.getDatabaseName().contains("memgraph")){
            return new CallExpression(Neo4jSchema.Neo4jBuiltInFunctions.LENGTH, Arrays.asList(param_path));
        }
        if( randNum < 50&&!global.getDatabaseName().contains("memgraph")){
            return new GetListElementExpression(new CallExpression(Neo4jSchema.Neo4jBuiltInFunctions.toIntegerList,Arrays.asList(param_list)),ToPositiveInt(generateAllExpression(CypherType.NUMBER,agg,depth)));
        }
        if( randNum < 60&&!global.getDatabaseName().contains("memgraph")) {
            return new CallExpression(Neo4jSchema.Neo4jBuiltInFunctions.toIntegerOrNull, Arrays.asList(generateAllExpression(CypherType.getRandomType(), agg, depth)));
        }
        return new ConstExpression(null);
    }
    private IExpression generateBooleanNotExists(Boolean agg,int depth){
        Randomly randomly = new Randomly();
        int randNum = randomly.getInteger(0, 95);
        IExpression p_random=generateAllExpression(CypherType.getRandomType(),agg,depth);
        IExpression p_boolean=generateAllExpression(CypherType.BOOLEAN,false,depth);
        IExpression param_list = generateAllExpression(CypherType.LIST,agg,depth);
        IExpression param_string = generateAllExpression(CypherType.STRING,agg,depth);
        IExpression param_map = generateAllExpression(CypherType.MAP,agg,depth);
        if( randNum < 10&&!global.getDatabaseName().contains("memgraph")){
            return new CallExpression(Neo4jSchema.Neo4jBuiltInFunctions.isEMPTY, Arrays.asList(param_list));
        }
        if( randNum < 20&&!global.getDatabaseName().contains("memgraph")){
            return new CallExpression(Neo4jSchema.Neo4jBuiltInFunctions.isEMPTY, Arrays.asList(param_string));
        }
        if( randNum < 25&&!global.getDatabaseName().contains("memgraph")){
            return new CallExpression(Neo4jSchema.Neo4jBuiltInFunctions.isEMPTY, Arrays.asList(param_map));
        }
        if( randNum < 35&&!global.getDatabaseName().contains("memgraph")){
            return new CallExpression(Neo4jSchema.Neo4jBuiltInFunctions.toBooleanOrNull, Arrays.asList(p_random));
        }
        if( randNum < 45){
            return StringMatchingExpression.randomMatching(generateAllExpression(CypherType.STRING,agg,depth),generateAllExpression(CypherType.STRING,agg,depth));
        }
        if( randNum < 55){
            return BinaryComparisonExpression.randomComparison(generateAllExpression(CypherType.getRandomType(),agg,depth),generateAllExpression(CypherType.getRandomType(),agg,depth));
        }
        if( randNum < 65){
            return BinaryLogicalExpression.randomLogical(generateAllExpression(CypherType.BOOLEAN,agg,depth),generateAllExpression(CypherType.BOOLEAN,agg,depth));
        }
        if( randNum < 75&&agg){
            return new GetListElementExpression(new CallExpression(Neo4jSchema.Neo4jBuiltInFunctions.COLLECT,Arrays.asList(p_boolean)),ToPositiveInt(generateAllExpression(CypherType.NUMBER,agg,0)));
        }
        //todo memgraph在return中使用exists()导致crash
        if(randNum < 85&&!queryGenerator.getmergeflag()&&!(global.getDatabaseName().contains("memgraph")&&queryGenerator.getreturnflag())){
            //todo redis的exists()函数参数不支持模式,memgraph和neo4j的exists()函数参数只支持模式
            if(!global.getDatabaseName().contains("redis")){
            SlidingPatternGenerator slidingPatternGenerator=((SlidingPatternGenerator)(((ModelBasedQueryGenerator)queryGenerator).patternGenerator));
            IPattern p_pattern =slidingPatternGenerator.generateSinglePattern(clauseAnalyzer,((ModelBasedQueryGenerator) queryGenerator).identifierBuilder,schema);
            return new CallExpression(Neo4jSchema.Neo4jBuiltInFunctions.exists, p_pattern);
            }
            else{
                CypherType type=CypherType.getRandomType();
                return new CallExpression(Neo4jSchema.Neo4jBuiltInFunctions.exists, Arrays.asList(generateAllExpression(type,agg,depth)));
            }
        }
        if( randNum < 95&&!global.getDatabaseName().contains("memgraph")){
            return new GetListElementExpression(new CallExpression(Neo4jSchema.Neo4jBuiltInFunctions.toBooleanList,Arrays.asList(param_list)),ToPositiveInt(generateAllExpression(CypherType.NUMBER,agg,depth)));
        }
        return new ConstExpression(null);
    }
    //todo Count{}子查询
    public IExpression generateNumberCount(Boolean agg,int depth){
        //todo agens,redis,janus和memgraph不支持子查询
        if(global.getDatabaseName().contains("redis")||global.getDatabaseName().contains("memgraph")||global.getDatabaseName().contains("agens")||global.getDatabaseName().contains("janus"))
            return basicTypeExpression(CypherType.NUMBER,agg,0,0);
        IClauseSequence sequence=queryGenerator.generateSubQuery((CypherClause) clauseAnalyzer,agg,queryGenerator.getGlobalstate());
        return new CallExpression(Neo4jSchema.Neo4jBuiltInFunctions.COUNT,sequence);
    }
    //todo EXISTS{}子查询
    public IExpression generateBooleanExists(Boolean agg,int depth){
        //todo agens,redis,janus和memgraph不支持子查询
        if(global.getDatabaseName().contains("redis")||global.getDatabaseName().contains("memgraph")||global.getDatabaseName().contains("agens")||global.getDatabaseName().contains("janus"))
            return basicTypeExpression(CypherType.BOOLEAN,agg,0,0);
        IClauseSequence sequence=queryGenerator.generateSubQuery((CypherClause) clauseAnalyzer,agg,queryGenerator.getGlobalstate());
        return new CallExpression(Neo4jSchema.Neo4jBuiltInFunctions.EXISTS,sequence);
    }
    private IExpression generateStringAgg(Boolean agg,int depth){
        agg_flag=true;
        Randomly randomly = new Randomly();
        int randNum = randomly.getInteger(0, 20);
        //聚合函数不能嵌套调用
        IExpression param = generateUseVar(CypherType.STRING,agg,depth);
        if( randNum < 10){
            return new CallExpression(Neo4jSchema.Neo4jBuiltInFunctions.MAX_STRING, Arrays.asList(param));
        }
        if( randNum < 20){
            return new CallExpression(Neo4jSchema.Neo4jBuiltInFunctions.MIN_STRING, Arrays.asList(param));
        }
        return new ConstExpression(null);
    }
    private IExpression generateStringNotAgg(Boolean agg,int depth){
        Randomly randomly = new Randomly();
        int randNum = randomly.getInteger(0, 60);
        IExpression param_string = generateAllExpression(CypherType.STRING,agg,depth);
        CypherType Type=CypherType.getRandomType();
        IExpression param_random = generateAllExpression(Type,agg,depth);
        IExpression param_s = generateAllExpression(CypherType.STRING,false,depth);
        IExpression param_number = generateAllExpression(CypherType.NUMBER,agg,depth);
        IExpression param_number01 = generateAllExpression(CypherType.NUMBER,agg,depth);
        IExpression param_list = generateAllExpression(CypherType.LIST,agg,depth);
        IExpression param_relation = generateAllExpression(CypherType.RELATION,agg,depth);
        IExpression param_node = generateAllExpression(CypherType.NODE,agg,depth);
        if( randNum < 10&&agg){
            return new GetListElementExpression(new CallExpression(Neo4jSchema.Neo4jBuiltInFunctions.COLLECT,Arrays.asList(param_s)),ToPositiveInt(generateAllExpression(CypherType.NUMBER,agg,0)));
        }
        if( randNum < 20){
            return new CallExpression(Neo4jSchema.Neo4jBuiltInFunctions.REVERSE_STRING, Arrays.asList(param_string));
        }
        if( randNum < 30){
            return new CallExpression(Neo4jSchema.Neo4jBuiltInFunctions.SUBSTRING_LENGTH, Arrays.asList(param_string,ToPositiveInt(param_number),ToPositiveInt(param_number01)));
        }
        if( randNum < 35){
            return new CallExpression(Neo4jSchema.Neo4jBuiltInFunctions.TYPE, Arrays.asList(param_relation));
        }
        //todo redis、memgraph不支持elementId函数
        if( randNum < 40&&!global.getDatabaseName().contains("redis")&&!global.getDatabaseName().contains("memgraph")){
            if(Randomly.getBoolean())
                return new CallExpression(Neo4jSchema.Neo4jBuiltInFunctions.ID, Arrays.asList(param_node));
            else
                return new CallExpression(Neo4jSchema.Neo4jBuiltInFunctions.ID, Arrays.asList(param_relation));
        }
        if( randNum < 50&&!global.getDatabaseName().contains("memgraph")){
            return new CallExpression(Neo4jSchema.Neo4jBuiltInFunctions.toStringOrNull, Arrays.asList(param_random));
        }
        if( randNum < 60&&!global.getDatabaseName().contains("memgraph")){
            return new GetListElementExpression(new CallExpression(Neo4jSchema.Neo4jBuiltInFunctions.toStringList,Arrays.asList(param_list)), ToPositiveInt(generateAllExpression(CypherType.NUMBER,agg,depth)));
        }
        return new ConstExpression(null);
    }
    public IExpression generateCondition(Boolean agg,int depth,int generate_depth){
        return generateAllExpression(CypherType.BOOLEAN,agg,generate_depth);
    }
    //生成统一基本类型的列表（无嵌套）
    public IExpression generateListWithBasicType(Boolean agg,int depth, CypherType type,int generate_depth){
        Randomly randomly = new Randomly();
        int randomNum = randomly.getInteger(1,4);
        List<IExpression> expressions = new ArrayList<>();
        for(int i = 0; i < randomNum; i++){
            expressions.add(basicTypeExpression(type,agg,depth,generate_depth));
        }
        return new CreateListExpression(expressions,type);
    }

    private IExpression basicTypeExpression(CypherType type,Boolean agg,int depth,int generate_depth){
        switch (type){
            case BOOLEAN:
                return booleanExpression(agg,depth,generate_depth);
            case STRING:
                return stringExpression(agg,depth,generate_depth);
            case NUMBER:
                return numberExpression(agg,depth,generate_depth);
            case LIST:
            { List l =new ArrayList<>();
                return new ConstExpression(l);}
            case MAP:
            { Map map =new HashMap<>();
                return new ConstExpression(map);}
            default:
                return new ConstExpression(null);
        }
    }

    private IExpression booleanExpression(Boolean agg,int depth,int generate_depth){
        Randomly randomly = new Randomly();
        int expressionChoice = randomly.getInteger(0, 100);
        if(depth == 0 || expressionChoice < 30){
            return new ConstExpression(randomly.getInteger(0, 100) < 50);
        }
        //尚有深度
        if(expressionChoice < 50){
            if(depth==1)
                return BinaryComparisonExpression.randomComparison(generateAllExpression(CypherType.NUMBER,agg,generate_depth), generateAllExpression(CypherType.NUMBER,agg,generate_depth));
            return BinaryComparisonExpression.randomComparison(numberExpression(agg,depth - 1,generate_depth), numberExpression(agg,depth - 1,generate_depth));
        }
        if(expressionChoice < 60){
            if(depth==1)
                return BinaryComparisonExpression.randomComparison(generateAllExpression(CypherType.STRING,agg,generate_depth), generateAllExpression(CypherType.STRING,agg,generate_depth));
            return BinaryComparisonExpression.randomComparison(stringExpression(agg,depth - 1,generate_depth), stringExpression(agg,depth - 1,generate_depth));
        }
        if(expressionChoice < 70){
            if(depth==1)
                return StringMatchingExpression.randomMatching(generateAllExpression(CypherType.STRING,agg,generate_depth), generateAllExpression(CypherType.STRING,agg,generate_depth));
            return StringMatchingExpression.randomMatching(stringExpression(agg,depth - 1,generate_depth), stringExpression(agg,depth - 1,generate_depth));
        }
        if(expressionChoice < 80){
            if(depth==1)
                return SingleLogicalExpression.randomLogical(generateAllExpression(CypherType.BOOLEAN,agg,generate_depth));
            return SingleLogicalExpression.randomLogical(booleanExpression(agg,depth - 1,generate_depth));
        }
        if(depth==1)
            return BinaryLogicalExpression.randomLogical(generateAllExpression(CypherType.BOOLEAN,agg,generate_depth), generateAllExpression(CypherType.BOOLEAN,agg,generate_depth));
        return BinaryLogicalExpression.randomLogical(booleanExpression(agg,depth - 1,generate_depth), booleanExpression(agg,depth - 1,generate_depth));
    }

    private IExpression stringExpression(Boolean agg,int depth,int generate_depth){
        Randomly randomly = new Randomly();
        int expressionChoice = randomly.getInteger(0, 100);
        if(depth == 0 || expressionChoice < 70){
            return new ConstExpression(randomly.getString());
        }
        if(depth==1)
            return new StringCatExpression(generateAllExpression(CypherType.STRING,agg,generate_depth), generateAllExpression(CypherType.STRING,agg,generate_depth));
        return new StringCatExpression(stringExpression(agg,depth - 1,generate_depth), stringExpression(agg,depth - 1,generate_depth));
    }

    private IExpression numberExpression(Boolean agg,int depth,int generate_depth){
        Randomly randomly = new Randomly();
        int expressionChoice = randomly.getInteger(0, 100);
        if(depth == 0 || expressionChoice < 50){
            return new ConstExpression((int)randomly.getInteger());
        }
        if(depth==1)
            return BinaryNumberExpression.randomBinaryNumber(generateAllExpression(CypherType.NUMBER,agg,generate_depth), generateAllExpression(CypherType.NUMBER,agg,generate_depth));
        return BinaryNumberExpression.randomBinaryNumber(numberExpression(agg,depth - 1,generate_depth), numberExpression(agg,depth - 1,generate_depth));
    }
    private IExpression ToPositiveInt(IExpression param){
        if(!global.getDatabaseName().contains("memgraph"))
            return new CallExpression(Neo4jSchema.Neo4jBuiltInFunctions.toIntegerOrNull,Arrays.asList(new CallExpression(Neo4jSchema.Neo4jBuiltInFunctions.ABS, Arrays.asList(param))));
        else
            return new CallExpression(Neo4jSchema.Neo4jBuiltInFunctions.ABS, Arrays.asList(param));
    }
}
