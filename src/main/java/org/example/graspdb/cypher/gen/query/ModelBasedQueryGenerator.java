package org.example.graspdb.cypher.gen.query;

import org.example.graspdb.Randomly;
import org.example.graspdb.cypher.CypherGlobalState;
import org.example.graspdb.cypher.ast.IAlias;
import org.example.graspdb.cypher.ast.IClauseSequence;
import org.example.graspdb.cypher.ast.ICypherClause;
import org.example.graspdb.cypher.ast.IPattern;
import org.example.graspdb.cypher.ast.analyzer.*;
import org.example.graspdb.cypher.dsl.*;
import org.example.graspdb.cypher.gen.expr.RandomExpressionGenerator;
import org.example.graspdb.cypher.schema.CypherSchema;
import org.example.graspdb.cypher.standard_ast.*;

import java.util.*;
import java.util.stream.Collectors;

public abstract class ModelBasedQueryGenerator<S extends CypherSchema<G, ?>, G extends CypherGlobalState<?, S>> implements IQueryGenerator<S, G> {
    private G global;
    public static Map<String, HashSet<String>> node_to_relationship = new HashMap<>();
    //标识当前clause的表达式生成器
    public static RandomExpressionGenerator randomExpressionGenerator = null;
    //标识exists{},count{}
    public static Boolean subquery = false;
    //标识foreach
    public static Boolean foreach_flag = false;
    //标识merge
    public static Boolean merge_clause = false;
    //标识return
    public static Boolean return_clause = false;
    //标识delete
    public static Boolean delete_clause = false;
    //标识模式理解
    public static Boolean pattern_comprehension = false;
    //标识列表理解
    public static Boolean list_comprehension = false;
    //标识列表理解的alias
    public static IAlias list_comprehension_alias = null;
    //标识当前模式理解中的局部模式
    public static IPattern pattern_in_comprehension = null;
    public IIdentifierBuilder identifierBuilder;
    public IPatternGenerator patternGenerator;
    public IConditionGenerator conditionGenerator;
    public IAliasGenerator aliasGenerator;
    public IListGenerator listGenerator;
    //标识call{}
    public static Boolean call_query = false;
    public static Boolean aggfunc = true;

    public void clear_map() {
        node_to_relationship.clear();
    }

    public void clear_identifierBuilder() {
        identifierBuilder.clear();
    }

    @Override
    public void setGlobal(G g) {
        this.global = g;
    }

    public IIdentifierBuilder getIdentifierBuilder() {
        return this.identifierBuilder;
    }

    public IClauseSequenceBuilder generateSubQueryClauses(CypherClause clause, IClauseSequenceBuilder seq, int len, List<String> generateClause) {
        //设置子查询表达式中的第一个clause
        if (!seq.build().getClauseList().isEmpty())
            seq.build().getClauseList().get(0).setFirstClause(true);
        for (ICypherClause Clause : seq.build().getClauseList()) {
            //标识每个语句是子查询表达式中的
            Clause.setInSubQuery(true);
            //标识每个语句的父语句
            Clause.setParentClause(clause);
        }
        if (len == 0) {
            return seq;
        }
        List<String> update = new ArrayList<>();
        //todo redis不支持foreach,以及防止remove (null).k引起的容器异常退出
        Randomly r = new Randomly();
        String generate = generateClause.get(r.getInteger(0, generateClause.size()));
        List<String> list = new ArrayList<>();
        list.add("WITH");
        //todo redis不支持call{}
        if (!call_query && !(update.contains(generate)) && !global.getDatabaseName().contains("redis"))
            list.add("CALL");
        if (generate == "MATCH") {
            list.addAll(Arrays.asList("MATCH", "OPTIONAL MATCH", "UNWIND"));
            return generateSubQueryClauses(clause, seq.MatchClause(), len - 1, list);
        } else if (generate == "OPTIONAL MATCH") {
            //todo memgraph,redisgraph中optional match后面不能跟match
            if (global.getDatabaseName().contains("memgraph") || global.getDatabaseName().contains("redis"))
                list.addAll(Arrays.asList("OPTIONAL MATCH", "UNWIND"));
            else
                list.addAll(Arrays.asList("MATCH", "OPTIONAL MATCH", "UNWIND"));
            return generateSubQueryClauses(clause, seq.OptionalMatchClause(), len - 1, list);
        } else if (generate == "WITH") {
            list.addAll(Arrays.asList("MATCH", "OPTIONAL MATCH", "UNWIND"));
            return generateSubQueryClauses(clause, seq.WithClause(), len - 1, list);
        } else if (generate == "CALL") {
            list.addAll(Arrays.asList("MATCH", "OPTIONAL MATCH", "UNWIND"));
            return generateSubQueryClauses(clause, seq.CallClause(), len - 1, list);
        } else if (generate == "UNWIND") {
            list.addAll(Arrays.asList("MATCH", "OPTIONAL MATCH", "UNWIND"));
            return generateSubQueryClauses(clause, seq.UnwindClause(), len - 1, list);
        } else if (generate == "FOREACH") {
            return generateSubQueryClauses(clause, seq.ForeachClause(), len - 1, list);
        } else if (generate == "CREATE") {
            return generateSubQueryClauses(clause, seq.CreateClause(), len - 1, list);
        } else if (generate == "MERGE") {
            return generateSubQueryClauses(clause, seq.MergeClause(), len - 1, list);
        } else if (generate == "DELETE") {
            return generateSubQueryClauses(clause, seq.DeleteClause(), len - 1, list);
        } else if (generate == "SET") {
            return generateSubQueryClauses(clause, seq.SetClause(), len - 1, list);
        } else if (generate == "REMOVE") {
            return generateSubQueryClauses(clause, seq.RemoveClause(), len - 1, list);
        } else
            throw new RuntimeException("UNKNOWN CLAUSE");
    }

    public IClauseSequenceBuilder generateClauses(IClauseSequenceBuilder seq, int len, List<String> generateClause) {
        if (len == 0) {
            return seq;
        }
        List<String> update = new ArrayList<>();
        //todo redis不支持foreach,以及防止remove (null).k引起的容器异常退出
        Randomly r = new Randomly();
        String generate = generateClause.get(r.getInteger(0, generateClause.size()));
        List<String> list = new ArrayList<>();
        list.add("WITH");
        //todo redis不支持call{}
        if (!call_query && !(update.contains(generate)) && !global.getDatabaseName().contains("redis"))
            list.add("CALL");
        if (generate == "MATCH") {
            list.addAll(Arrays.asList("MATCH", "OPTIONAL MATCH", "UNWIND"));
            return generateClauses(seq.MatchClause(), len - 1, list);
        } else if (generate == "OPTIONAL MATCH") {
            //todo memgraph,redisgraph中optional match后面不能跟match
            if (global.getDatabaseName().contains("memgraph") || global.getDatabaseName().contains("redis"))
                list.addAll(Arrays.asList("OPTIONAL MATCH", "UNWIND"));
            else
                list.addAll(Arrays.asList("MATCH", "OPTIONAL MATCH", "UNWIND"));
            return generateClauses(seq.OptionalMatchClause(), len - 1, list);
        } else if (generate == "WITH") {
            list.addAll(Arrays.asList("MATCH", "OPTIONAL MATCH", "UNWIND"));
            return generateClauses(seq.WithClause(), len - 1, list);
        } else if (generate == "CALL") {
            list.addAll(Arrays.asList("MATCH", "OPTIONAL MATCH", "UNWIND"));
            return generateClauses(seq.CallClause(), len - 1, list);
        } else if (generate == "UNWIND") {
            list.addAll(Arrays.asList("MATCH", "OPTIONAL MATCH", "UNWIND"));
            return generateClauses(seq.UnwindClause(), len - 1, list);
        } else if (generate == "FOREACH") {
            return generateClauses(seq.ForeachClause(), len - 1, list);
        } else if (generate == "CREATE") {
            return generateClauses(seq.CreateClause(), len - 1, list);
        } else if (generate == "MERGE") {
            return generateClauses(seq.MergeClause(), len - 1, list);
        } else if (generate == "DELETE") {
            return generateClauses(seq.DeleteClause(), len - 1, list);
        } else if (generate == "SET") {
            return generateClauses(seq.SetClause(), len - 1, list);
        } else if (generate == "REMOVE") {
            return generateClauses(seq.RemoveClause(), len - 1, list);
        } else
            throw new RuntimeException("UNKNOWN CLAUSE");
    }

    public IClauseSequenceBuilder generateCRUDClauses(QueryFiller queryFiller, IClauseSequenceBuilder seq, int len, List<String> generateClause) {
        IClauseAnalyzer clauseAnalyzer = null;
        ICypherClause cypherClause = null;
        if (!seq.build().getClauseList().isEmpty()) {
            cypherClause = seq.build().getClauseList().get(seq.build().getClauseList().size() - 1);
            queryFiller.visitClause(cypherClause);
            clauseAnalyzer = cypherClause.toAnalyzer();
        }
        if (len == 0) {
            return seq;
        }
        List<String> update = new ArrayList<>(Arrays.asList("CREATE", "MERGE"));
        //todo redis,janus不支持foreach
        if (!global.getDatabaseName().contains("redis") && !global.getDatabaseName().contains("janus"))
            update.add("FOREACH");
        Randomly r = new Randomly();
        //todo 剔除掉不能生成的一些SET，REMOVE，DELETE
        if (cypherClause != null) {
            Boolean b = cypherClause instanceof With;
            //获取Alias
            List<IAliasAnalyzer> aliasAnalyzers = b ? clauseAnalyzer.getLocalAliases() : clauseAnalyzer.getAvailableAliases();
            //获取NodeIdentifier
            List<INodeAnalyzer> nodeAnalyzers = b ? clauseAnalyzer.getLocalNodeIdentifiers() : clauseAnalyzer.getAvailableNodeIdentifiers();
            nodeAnalyzers.addAll(aliasAnalyzers.stream().filter(a -> a.analyzeType(global.getSchema()).getType() == CypherType.NODE).map(
                    a -> a.analyzeType(global.getSchema()).getNodeAnalyzer()
            ).collect(Collectors.toList()));

            //获取RelationIdentifier
            List<IRelationAnalyzer> relationAnalyzers = b ? clauseAnalyzer.getLocalRelationIdentifiers() : clauseAnalyzer.getAvailableRelationIdentifiers();
            relationAnalyzers.addAll(aliasAnalyzers.stream().filter(a -> a.analyzeType(global.getSchema()).getType() == CypherType.RELATION).map(
                    a -> a.analyzeType(global.getSchema()).getRelationAnalyzer()
            ).collect(Collectors.toList()));
            //获取PathIdentifier
            List<IPathAnalyzer> pathAnalyzers = b ? clauseAnalyzer.getLocalPathIdentifiers() : clauseAnalyzer.getAvailablePathIdentifiers();
            pathAnalyzers.addAll(aliasAnalyzers.stream().filter(a -> a.analyzeType(global.getSchema()).getType() == CypherType.PATH).map(
                    a -> a.analyzeType(global.getSchema()).getPathAnalyzer()
            ).collect(Collectors.toList()));
            //只能生成一个delete子句
            if (seq.getDelete_flag())
                generateClause.remove("DELETE");
            if (clauseAnalyzer == null || (nodeAnalyzers.isEmpty())) {
                generateClause.remove("SET");
                generateClause.remove("REMOVE");
                if (clauseAnalyzer == null || (pathAnalyzers.isEmpty() && relationAnalyzers.isEmpty()))
                    generateClause.remove("DELETE");
            }
        }
        String generate = generateClause.get(r.getInteger(0, generateClause.size()));
        List<String> list = new ArrayList<>();
        if (!subquery) {
            list.addAll(Arrays.asList("CREATE", "DELETE", "SET", "MERGE"));
        }
        if (!subquery && !foreach_flag && !global.getDatabaseName().contains("redis") && !global.getDatabaseName().contains("janus"))
            list.add("FOREACH");
        if (!subquery && !foreach_flag)
            list.add("REMOVE");
        //todo redis,janus不支持call{}
        if (!call_query && !(update.contains(generate)) && !global.getDatabaseName().contains("redis") && !global.getDatabaseName().contains("janus"))
            list.add("CALL");
        if (Objects.equals(generate, "MATCH")) {
            list.addAll(Arrays.asList("MATCH", "OPTIONAL MATCH", "UNWIND"));
            return generateCRUDClauses(queryFiller, seq.MatchClause(), len - 1, list);
        } else if (generate == "OPTIONAL MATCH") {
            //todo memgraph,redisgraph,janusgraph中optional match后面不能跟match
            if (global.getDatabaseName().contains("memgraph") || global.getDatabaseName().contains("redis") || global.getDatabaseName().contains("janus"))
                list.addAll(Arrays.asList("OPTIONAL MATCH", "UNWIND"));
            else
                list.addAll(Arrays.asList("MATCH", "OPTIONAL MATCH", "UNWIND"));
            return generateCRUDClauses(queryFiller, seq.OptionalMatchClause(), len - 1, list);
        } else if (Objects.equals(generate, "WITH")) {
            list.addAll(Arrays.asList("MATCH", "OPTIONAL MATCH", "UNWIND"));
            return generateCRUDClauses(queryFiller, seq.WithClause(), len - 1, list);
        } else if (Objects.equals(generate, "CALL")) {
            list.addAll(Arrays.asList("MATCH", "OPTIONAL MATCH", "UNWIND"));
            return generateCRUDClauses(queryFiller, seq.CallClause(), len - 1, list);
        } else if (Objects.equals(generate, "UNWIND")) {
            list.addAll(Arrays.asList("MATCH", "OPTIONAL MATCH", "UNWIND"));
            return generateCRUDClauses(queryFiller, seq.UnwindClause(), len - 1, list);
        } else if (Objects.equals(generate, "FOREACH")) {
            return generateCRUDClauses(queryFiller, seq.ForeachClause(), len - 1, list);
        } else if (Objects.equals(generate, "CREATE")) {
            return generateCRUDClauses(queryFiller, seq.CreateClause(), len - 1, list);
        } else if (Objects.equals(generate, "MERGE")) {
            return generateCRUDClauses(queryFiller, seq.MergeClause(), len - 1, list);
        } else if (Objects.equals(generate, "DELETE")) {
            return generateCRUDClauses(queryFiller, seq.DeleteClause(), len - 1, list);
        } else if (Objects.equals(generate, "SET")) {
            return generateCRUDClauses(queryFiller, seq.SetClause(), len - 1, list);
        } else if (Objects.equals(generate, "REMOVE")) {
            return generateCRUDClauses(queryFiller, seq.RemoveClause(), len - 1, list);
        } else
            throw new RuntimeException("UNKNOWN CLAUSE");
    }

    public IClauseSequenceBuilder generateForeachClauses(CypherClause Clause, QueryFiller queryFiller, IClauseSequenceBuilder seq, int len, List<String> generateClause) {
        //标识foreach中的第一个clause
        if (!seq.build().getClauseList().isEmpty())
            seq.build().getClauseList().get(0).setFirstClause(true);
        for (ICypherClause clause : seq.build().getClauseList()) {
            //标识每个语句是foreach中的
            clause.setInForeach(true);
            //标识每个语句的父语句
            clause.setParentClause(Clause);
        }
        IClauseAnalyzer clauseAnalyzer = null;
        ICypherClause cypherClause = null;
        if (!seq.build().getClauseList().isEmpty()) {
            cypherClause = seq.build().getClauseList().get(seq.build().getClauseList().size() - 1);
            queryFiller.visitClause(cypherClause);
            clauseAnalyzer = cypherClause.toAnalyzer();
        }
        if (len == 0) {
            return seq;
        }
        List<String> list = new ArrayList<>(Arrays.asList("CREATE", "MERGE", "DELETE", "SET", "REMOVE"));
        Randomly r = new Randomly();
        //todo 剔除掉不能生成的一些SET，REMOVE，DELETE
        if (clauseAnalyzer == null || (clauseAnalyzer.getAvailableNodeIdentifiers().isEmpty() && clauseAnalyzer.getAvailableRelationIdentifiers().isEmpty())) {
            generateClause.remove("SET");
            generateClause.remove("REMOVE");
            if (clauseAnalyzer == null || clauseAnalyzer.getAvailablePathIdentifiers().isEmpty())
                generateClause.remove("DELETE");
        }
        String generate = generateClause.get(r.getInteger(0, generateClause.size()));
        if (generate == "CREATE") {
            return generateForeachClauses(Clause, queryFiller, seq.CreateClause(), len - 1, list);
        } else if (generate == "MERGE") {
            return generateForeachClauses(Clause, queryFiller, seq.MergeClause(), len - 1, list);
        } else if (generate == "DELETE") {
            return generateForeachClauses(Clause, queryFiller, seq.DeleteClause(), len - 1, list);
        } else if (generate == "SET") {
            return generateForeachClauses(Clause, queryFiller, seq.SetClause(), len - 1, list);
        } else {
            return generateForeachClauses(Clause, queryFiller, seq.RemoveClause(), len - 1, list);
        }
    }

    public IClauseSequenceBuilder generateCRUDCallClauses(CypherClause clause, QueryFiller queryFiller, IClauseSequenceBuilder seq, int len, List<String> generateClause) {
        for (ICypherClause Clause : seq.build().getClauseList()) {
            //标识每个语句是call{}中的
            Clause.setInCall(true);
            //标识每个语句的父语句
            Clause.setParentClause(clause);
        }
        IClauseAnalyzer clauseAnalyzer = null;
        ICypherClause cypherClause = null;
        if (!seq.build().getClauseList().isEmpty()) {
            cypherClause = seq.build().getClauseList().get(seq.build().getClauseList().size() - 1);
            queryFiller.visitClause(cypherClause);
            clauseAnalyzer = cypherClause.toAnalyzer();
        }
        if (len == 0) {
            return seq;
        }
        Randomly r = new Randomly();
        if (clause != null) {
            Boolean b = cypherClause instanceof With;
            //获取Alias
            List<IAliasAnalyzer> aliasAnalyzers = b ? clauseAnalyzer.getLocalAliases() : clauseAnalyzer.getAvailableAliases();
            //获取NodeIdentifier
            List<INodeAnalyzer> nodeAnalyzers = b ? clauseAnalyzer.getLocalNodeIdentifiers() : clauseAnalyzer.getAvailableNodeIdentifiers();
            nodeAnalyzers.addAll(aliasAnalyzers.stream().filter(a -> a.analyzeType(global.getSchema()).getType() == CypherType.NODE).map(
                    a -> a.analyzeType(global.getSchema()).getNodeAnalyzer()
            ).collect(Collectors.toList()));

            //获取RelationIdentifier
            List<IRelationAnalyzer> relationAnalyzers = b ? clauseAnalyzer.getLocalRelationIdentifiers() : clauseAnalyzer.getAvailableRelationIdentifiers();
            relationAnalyzers.addAll(aliasAnalyzers.stream().filter(a -> a.analyzeType(global.getSchema()).getType() == CypherType.RELATION).map(
                    a -> a.analyzeType(global.getSchema()).getRelationAnalyzer()
            ).collect(Collectors.toList()));
            //获取PathIdentifier
            List<IPathAnalyzer> pathAnalyzers = b ? clauseAnalyzer.getLocalPathIdentifiers() : clauseAnalyzer.getAvailablePathIdentifiers();
            pathAnalyzers.addAll(aliasAnalyzers.stream().filter(a -> a.analyzeType(global.getSchema()).getType() == CypherType.PATH).map(
                    a -> a.analyzeType(global.getSchema()).getPathAnalyzer()
            ).collect(Collectors.toList()));
            //todo 剔除掉不能生成的一些SET，REMOVE，DELETE
            if (clauseAnalyzer == null || (nodeAnalyzers.isEmpty())) {
                generateClause.remove("SET");
                generateClause.remove("REMOVE");
                if (clauseAnalyzer == null || (pathAnalyzers.isEmpty() && relationAnalyzers.isEmpty()))
                    generateClause.remove("DELETE");
            }
        }
        String generate = generateClause.get(r.getInteger(0, generateClause.size()));
        List<String> list = new ArrayList<>();
        list.addAll(Arrays.asList("WITH"));
        if (!subquery) {
            list.addAll(Arrays.asList("CREATE", "DELETE", "SET", "REMOVE", "MERGE"));
        }
        if (!subquery && !foreach_flag)
            list.add("FOREACH");
        if (generate == "MATCH") {
            list.addAll(Arrays.asList("MATCH", "OPTIONAL MATCH", "UNWIND"));
            return generateCRUDClauses(queryFiller, seq.MatchClause(), len - 1, list);
        } else if (generate == "OPTIONAL MATCH") {
            list.addAll(Arrays.asList("MATCH", "OPTIONAL MATCH", "UNWIND"));
            return generateCRUDClauses(queryFiller, seq.OptionalMatchClause(), len - 1, list);
        } else if (generate == "WITH") {
            list.addAll(Arrays.asList("MATCH", "OPTIONAL MATCH", "UNWIND"));
            return generateCRUDClauses(queryFiller, seq.WithClause(), len - 1, list);
        } else if (generate == "CREATE") {
            return generateCRUDClauses(queryFiller, seq.CreateClause(), len - 1, list);
        } else if (generate == "MERGE") {
            return generateCRUDClauses(queryFiller, seq.MergeClause(), len - 1, list);
        } else if (generate == "DELETE") {
            return generateCRUDClauses(queryFiller, seq.DeleteClause(), len - 1, list);
        } else if (generate == "FOREACH") {
//            list.addAll(Arrays.asList("MATCH","OPTIONAL MATCH"));
            return generateCRUDClauses(queryFiller, seq.ForeachClause(), len - 1, list);
        } else if (generate == "SET") {
            return generateCRUDClauses(queryFiller, seq.SetClause(), len - 1, list);
        } else if (generate == "REMOVE") {
            return generateCRUDClauses(queryFiller, seq.RemoveClause(), len - 1, list);
        } else {
            list.addAll(Arrays.asList("MATCH", "OPTIONAL MATCH", "UNWIND"));
            return generateCRUDClauses(queryFiller, seq.UnwindClause(), len - 1, list);
        }
    }

    public IClauseSequenceBuilder generateCallClauses(CypherClause clause, IClauseSequenceBuilder seq, int len, List<String> generateClause) {
        for (ICypherClause Clause : seq.build().getClauseList()) {
            //标识每个语句是call{}中的
            Clause.setInCall(true);
            //标识每个语句的父语句
            Clause.setParentClause(clause);
        }
        if (len == 0) {
            return seq;
        }
        Randomly r = new Randomly();
        String generate = generateClause.get(r.getInteger(0, generateClause.size()));
        List<String> list = new ArrayList<>();
        list.addAll(Arrays.asList("WITH"));
        if (generate == "MATCH") {
            list.addAll(Arrays.asList("MATCH", "OPTIONAL MATCH", "UNWIND"));
            return generateClauses(seq.MatchClause(), len - 1, list);
        } else if (generate == "OPTIONAL MATCH") {
            list.addAll(Arrays.asList("MATCH", "OPTIONAL MATCH", "UNWIND"));
            return generateClauses(seq.OptionalMatchClause(), len - 1, list);
        } else if (generate == "WITH") {
            list.addAll(Arrays.asList("MATCH", "OPTIONAL MATCH", "UNWIND"));
            return generateClauses(seq.WithClause(), len - 1, list);
        } else if (generate == "CREATE") {
            return generateClauses(seq.CreateClause(), len - 1, list);
        } else if (generate == "MERGE") {
            return generateClauses(seq.MergeClause(), len - 1, list);
        } else if (generate == "DELETE") {
            return generateClauses(seq.DeleteClause(), len - 1, list);
        } else if (generate == "FOREACH") {
//            list.addAll(Arrays.asList("MATCH","OPTIONAL MATCH"));
            return generateClauses(seq.ForeachClause(), len - 1, list);
        } else if (generate == "SET") {
            return generateClauses(seq.SetClause(), len - 1, list);
        } else if (generate == "REMOVE") {
            return generateClauses(seq.RemoveClause(), len - 1, list);
        } else {
            list.addAll(Arrays.asList("MATCH", "OPTIONAL MATCH", "UNWIND"));
            return generateClauses(seq.UnwindClause(), len - 1, list);
        }
    }

    public abstract IPatternGenerator createPatternGenerator(G globalState, IIdentifierBuilder identifierBuilder);

    public abstract IConditionGenerator createConditionGenerator(G globalState);

    public abstract IAliasGenerator createAliasGenerator(G globalState, IIdentifierBuilder identifierBuilder);

    public abstract IListGenerator createListGenerator(G globalState, IIdentifierBuilder identifierBuilder);

    public abstract boolean shouldDoMutation(G globalState);

    protected IClauseSequence postProcessing(G globalState, IClauseSequence clauseSequence) {
        return clauseSequence;
    }

    @Override
    public IClauseSequence generateCRUDQuery(G globalState) {
        clear_map();
        IClauseSequenceBuilder builder = ClauseSequence.createClauseSequenceBuilder();
        identifierBuilder = builder.getIdentifierBuilder();
        clear_identifierBuilder();
        patternGenerator = createPatternGenerator(globalState, identifierBuilder);
        conditionGenerator = createConditionGenerator(globalState);
        aliasGenerator = createAliasGenerator(globalState, identifierBuilder);
        listGenerator = createListGenerator(globalState, identifierBuilder);
        S schema = globalState.getSchema();
        IClauseSequence sequence = null;
        if (!shouldDoMutation(globalState)) {
            //numOfClauses=0表示只有return子句的情况
            int numOfClauses = globalState.getOptions().getMaxClauseSize();
            List<String> clause_list = new ArrayList<>(Arrays.asList("MATCH", "OPTIONAL MATCH", "MERGE", "CREATE", "UNWIND", "WITH"));
            if (!global.getDatabaseName().contains("redis") && !global.getDatabaseName().contains("agens") && !global.getDatabaseName().contains("janus"))
                clause_list.addAll(Arrays.asList("CALL", "FOREACH"));
            //todo redis查询不能以with开头
            if (global.getDatabaseName().contains("redis"))
                clause_list.remove("WITH");
            QueryFiller queryFiller = new QueryFiller<>(sequence,
                    patternGenerator,
                    conditionGenerator,
                    aliasGenerator,
                    listGenerator,
                    schema, identifierBuilder);
            sequence = generateCRUDClauses(queryFiller, builder, numOfClauses, clause_list).ReturnClause().build();
            while (!sequence.check_update()) {
                builder.clear_flag();
                builder.clear_clause();
                sequence = generateCRUDClauses(queryFiller, builder, numOfClauses, clause_list).ReturnClause().build();
            }
            ICypherClause cypherClause = sequence.getClauseList().get(sequence.getClauseList().size() - 1);
            //标识查询结尾的return，用于UNION
            cypherClause.setend_return(true);
            //填充return子句
            queryFiller.visitClause(cypherClause);
        } else {
            System.out.println("doMutation!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
        return postProcessing(globalState, sequence);
    }

    @Override
    public IClauseSequence generateQuery(G globalState) {
        clear_map();
        IClauseSequenceBuilder builder = ClauseSequence.createClauseSequenceBuilder();
        identifierBuilder = builder.getIdentifierBuilder();
        clear_identifierBuilder();
        patternGenerator = createPatternGenerator(globalState, identifierBuilder);
        conditionGenerator = createConditionGenerator(globalState);
        aliasGenerator = createAliasGenerator(globalState, identifierBuilder);
        listGenerator = createListGenerator(globalState, identifierBuilder);
        S schema = globalState.getSchema();
        IClauseSequence sequence = null;
        //numOfClauses=0表示只有return子句的情况
        int numOfClauses = globalState.getOptions().getMaxClauseSize();
        List<String> clause_list = new ArrayList<>(Arrays.asList("MATCH", "OPTIONAL MATCH", "UNWIND", "WITH"));
        if (!global.getDatabaseName().contains("redis"))
            clause_list.addAll(Arrays.asList("CALL"));
        //todo redis查询不能以with开头
        if (global.getDatabaseName().contains("redis"))
            clause_list.remove("WITH");
        sequence = generateClauses(builder, numOfClauses, clause_list).ReturnClause().build();
        //标识查询结尾的return，区分子查询中的return
        (sequence.getClauseList().get(sequence.getClauseList().size() - 1)).setend_return(true);
        new QueryFiller<S>(sequence,
                patternGenerator,
                conditionGenerator,
                aliasGenerator,
                listGenerator,
                schema, identifierBuilder).startVisit();
        return postProcessing(globalState, sequence);
    }

    @Override
    //生成exists{},count{}
    public IClauseSequence generateSubQuery(CypherClause cypherClause, Boolean agg, G globalState) {
        //正在生成的是子查询内部的查询语句
        subquery = true;
        //子查询中是否可以有聚合函数
        aggfunc = agg;
        S schema = globalState.getSchema();
        IClauseSequence sequence = null;
        IClauseSequenceBuilder builder = ClauseSequence.createClauseSequenceBuilder();
        if (!shouldDoMutation(globalState)) {
            //子查询要继承外部的IdentifierBuilder
            builder.setIdentifierBuilder(identifierBuilder);
            int numOfClauses = globalState.getOptions().getMaxClauseSize();
            List<String> clause_list = new ArrayList<>(Arrays.asList("MATCH", "WITH", "OPTIONAL MATCH", "UNWIND", "CALL"));
            //todo
            if (Randomly.getBoolean() && numOfClauses > 0)
                //without return
                sequence = generateSubQueryClauses(cypherClause, builder, numOfClauses, clause_list).build();
            else {
                //with return
                sequence = generateSubQueryClauses(cypherClause, builder, numOfClauses, clause_list).ReturnClause().build();
            }
            new QueryFiller<S>(sequence,
                    patternGenerator,
                    conditionGenerator,
                    aliasGenerator,
                    listGenerator,
                    schema, identifierBuilder).startVisit();
        } else {
            System.out.println("doMutation!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
        subquery = false;
        return postProcessing(globalState, sequence);
    }

    @Override
    //生成带更新的call{}
    public IClauseSequence generateCRUDCallSubQuery(CypherClause cypherClause, G globalState) {
        //标识是否生成过call{}
        call_query = true;
        S schema = globalState.getSchema();
        IClauseSequence sequence = null;
        QueryFiller queryFiller = new QueryFiller<S>(sequence,
                patternGenerator,
                conditionGenerator,
                aliasGenerator,
                listGenerator,
                schema, identifierBuilder);
        if (!shouldDoMutation(globalState)) {
            IClauseSequenceBuilder builder = ClauseSequence.createClauseSequenceBuilder();
            //子查询要继承外部的IdentifierBuilder
            builder.setIdentifierBuilder(identifierBuilder);
            int numOfClauses = globalState.getOptions().getMaxClauseSize();
            //todo
            List<String> clause_list = new ArrayList<>();
            clause_list.addAll(Arrays.asList("MATCH", "UNWIND", "WITH", "OPTIONAL MATCH"));
            if (!subquery && !global.getDatabaseName().contains("janus"))
                clause_list.addAll(Arrays.asList("FOREACH"));
            if (!subquery)
                clause_list.addAll(Arrays.asList("CREATE", "DELETE", "MERGE", "SET", "REMOVE"));
            //call子查询中先用with引入外部变量
            builder.WithClause();
            builder.build().getClauseList().get(0).setFirstClause(true);
            builder.build().getClauseList().get(0).setInCall(true);
            builder.build().getClauseList().get(0).setParentClause(cypherClause);
            queryFiller.visitClause(builder.build().getClauseList().get(0));
            sequence = generateCRUDCallClauses(cypherClause, queryFiller, builder, numOfClauses - 1, clause_list).ReturnClause().build();
            while (!sequence.check_update() && !subquery) {
                builder.clear_flag();
                builder.clear_clause();
                //call子查询中先用with引入外部变量
                builder.WithClause();
                builder.build().getClauseList().get(0).setFirstClause(true);
                builder.build().getClauseList().get(0).setInCall(true);
                builder.build().getClauseList().get(0).setParentClause(cypherClause);
                queryFiller.visitClause(builder.build().getClauseList().get(0));
                sequence = generateCRUDCallClauses(cypherClause, queryFiller, builder, numOfClauses - 1, clause_list).ReturnClause().build();
            }
            //填充return子句
            ICypherClause cypher_Clause = sequence.getClauseList().get(sequence.getClauseList().size() - 1);
            queryFiller.visitClause(cypher_Clause);
        } else {
            System.out.println("doMutation!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
        return postProcessing(globalState, sequence);
    }

    @Override
    //生成call{}
    public IClauseSequence generateCallSubQuery(CypherClause cypherClause, G globalState) {
        //标识是否生成过call{}
        call_query = true;
        S schema = globalState.getSchema();
        IClauseSequence sequence = null;
        if (!shouldDoMutation(globalState)) {
            IClauseSequenceBuilder builder = ClauseSequence.createClauseSequenceBuilder();
            //子查询要继承外部的IdentifierBuilder
            builder.setIdentifierBuilder(identifierBuilder);
            int numOfClauses = globalState.getOptions().getMaxClauseSize();
            //todo
            List<String> clause_list = new ArrayList<>();
            clause_list.addAll(Arrays.asList("MATCH", "UNWIND", "WITH", "OPTIONAL MATCH"));
            //call子查询中先用with引入外部变量
            builder.WithClause();
            builder.build().getClauseList().get(0).setFirstClause(true);
            builder.build().getClauseList().get(0).setInCall(true);
            builder.build().getClauseList().get(0).setParentClause(cypherClause);
            sequence = generateCallClauses(cypherClause, builder, numOfClauses - 1, clause_list).ReturnClause().build();
            new QueryFiller<S>(sequence,
                    patternGenerator,
                    conditionGenerator,
                    aliasGenerator,
                    listGenerator,
                    schema, identifierBuilder).startVisit();
        } else {
            System.out.println("doMutation!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
        return postProcessing(globalState, sequence);
    }

    @Override
    //生成foreach
    public IClauseSequence generateForeachQuery(CypherClause cypherClause, G globalState) {
        //标识是否生成过FOREACH
        foreach_flag = true;
        S schema = globalState.getSchema();
        IClauseSequence sequence = null;
        QueryFiller queryFiller = new QueryFiller<S>(sequence,
                patternGenerator,
                conditionGenerator,
                aliasGenerator,
                listGenerator,
                schema, identifierBuilder);
        if (!shouldDoMutation(globalState)) {
            IClauseSequenceBuilder builder = ClauseSequence.createClauseSequenceBuilder();
            //Foreach要继承外部的IdentifierBuilder
            builder.setIdentifierBuilder(identifierBuilder);
            int numOfClauses = 3;
            //todo
            List<String> clause_list = new ArrayList<>();
            clause_list.addAll(Arrays.asList("CREATE", "MERGE", "DELETE", "SET", "REMOVE"));
            sequence = generateForeachClauses(cypherClause, queryFiller, builder, numOfClauses, clause_list).build();
        } else {
            System.out.println("doMutation!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
        return postProcessing(globalState, sequence);
    }

    public Boolean getsubqueryflag() {
        return subquery;
    }

    public Boolean getmergeflag() {
        return merge_clause;
    }

    public Boolean getreturnflag() {
        return return_clause;
    }

    public void setmergeflag(Boolean bool) {
        merge_clause = bool;
    }

    public void setreturnflag(Boolean bool) {
        return_clause = bool;
    }

    public Boolean getdeleteflag() {
        return delete_clause;
    }

    public void setdeleteflag(Boolean bool) {
        delete_clause = bool;
    }

    public Boolean getcallqueryflag() {
        return call_query;
    }

    public void setcallqueryflag(Boolean bool) {
        call_query = bool;
    }

    public Boolean get_pattern_comprehension_flag() {
        return pattern_comprehension;
    }

    public IPattern get_pattern_in_comprehension() {
        return pattern_in_comprehension;
    }

    public void set_pattern_in_comprehension(IPattern pattern) {
        pattern_in_comprehension = pattern;
    }

    public void set_pattern_comprehension_flag(Boolean bool) {
        pattern_comprehension = bool;
    }

    public Boolean get_list_comprehension_flag() {
        return list_comprehension;
    }

    public void set_list_comprehension_flag(Boolean bool) {
        list_comprehension = bool;
    }

    public void set_list_comprehension_alias(IAlias alias) {
        list_comprehension_alias = alias;
    }

    public IAlias get_list_comprehension_alias() {
        return list_comprehension_alias;
    }

    public Boolean getaggfuncflag() {
        return aggfunc;
    }
}
