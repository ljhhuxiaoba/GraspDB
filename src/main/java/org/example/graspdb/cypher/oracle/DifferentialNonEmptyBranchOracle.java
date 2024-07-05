package org.example.graspdb.cypher.oracle;

import org.example.graspdb.CypherTransform.CypherVisitorImp;
import org.example.graspdb.CypherTransform.CypherVisitorImpNoUpdate;
import org.example.graspdb.Randomly;
import org.example.graspdb.common.query.GDSmithResultSet;
import org.example.graspdb.common.oracle.TestOracle;
import org.example.graspdb.cypher.CypherGlobalState;
import org.example.graspdb.cypher.CypherQueryAdapter;
import org.example.graspdb.cypher.ast.IClauseSequence;
import org.example.graspdb.cypher.ast.IRet;
import org.example.graspdb.cypher.ast.IReturn;
import org.example.graspdb.cypher.dsl.IQueryGenerator;
import org.example.graspdb.cypher.schema.CypherSchema;
import org.example.graspdb.exceptions.ResultMismatchException;
import org.opencypher.gremlin.translation.TranslationFacade;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class DifferentialNonEmptyBranchOracle<G extends CypherGlobalState<?, S>, S extends CypherSchema<G, ?>> implements TestOracle {

    private final G globalState;
    private CypherVisitorImp cv;
    private CypherVisitorImpNoUpdate cv_noupdate;

    public static IQueryGenerator queryGenerator;
    public static final int BRANCH_PAIR_SIZE = 65536;
    public static final int BRANCH_SIZE = 1000000;
    public static int union_flag = 0;
    public static Boolean result_changed = false;
    public static List<IRet> returns = new ArrayList<>();
    public static final byte PRINT_MEM = 2;
    private FileWriter fw;
    static int num = 0;
    // 执行测试用例的数量
    public static int test_pair_num=0;
    // 执行测试用例的用时
    public static long time_query=0;
// 检查返回结果的用时
    public static long time_oracle1=0;
    // 检查图状态的用时
    public static long time_oracle2=0;
    public DifferentialNonEmptyBranchOracle(G globalState, IQueryGenerator<S, G> generator) throws IOException {
        this.globalState = globalState;
        this.cv = new CypherVisitorImp();
        this.cv_noupdate=new CypherVisitorImpNoUpdate<>();
        cv.setGlobalState(globalState);
        cv_noupdate.setGlobalState(globalState);
        queryGenerator = generator;
        queryGenerator.setGlobal(globalState);
      //  this.fw=new FileWriter("/home/lanjunhao/Projects/"+globalState.getOptions().getDatabaseType()+"_mr_removed_"+globalState.getOptions().getRelation_removed()+".txt",true);
        this.fw = new FileWriter("log.txt", true);
    }

    public G getGlobalState() {
        return this.globalState;
    }
    public List<String> mutate_graphgenie(String query){
        List<String> res = new ArrayList<String>();
        try{
            //String query = "MATCH (s1:B)-[*1..2]-(s3:A) RETURN count(s1);";
            String[] str = {"python3", "../../../../../../../GraphGenie/mutate.py", query};
            Process pr = Runtime.getRuntime().exec(str);
            pr.waitFor();
            File file = new File("../../../../../../../GraphGenie/output.txt");
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(file));
                String tempString = null;
                while ((tempString = reader.readLine()) != null) {
                    //System.out.println("oracle _string :" + tempString);
                    res.add(tempString);
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return res;
    }
    public List<String> mutate_grev(String query){
        List<String> res = new ArrayList<String>();
        try{
            //String query = "MATCH (s1:B)-[*1..2]-(s3:A) RETURN count(s1);";
            String[] str = {"python", "D:\\MUGDB\\MUGDB\\GRev\\mutate.py", query};
            Process pr = Runtime.getRuntime().exec(str);
            pr.waitFor();
            InputStream is = pr.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String line = br.readLine();
            while (line != null){
                //System.out.println(line);
                res.add(line);
                line = br.readLine();
            }
            is.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return res;
    }
    public void check_message(String message, String sb, String another_sb) throws Exception {
        if (message != null) {
            if(message.contains("is not an instance of the expected data type for property key")||message.contains("Unknown function")||message.contains("Cannot use the same relationship variable")||message.contains("Type mismatch: expected")||message.contains("is shadowing a variable with the same name from the outer scope and needs to be renamed")||(message.contains("argument at position")&&message.contains("must be")))
                System.out.println();
            else if(message.contains("Unsupported nodes() or relationships() argument:")||message.contains("Custom functions and predicates are not supported on target implementation: cypherRound. Consider installing Extensions to Gremlin to enable full support for Cypher functionality")||message.contains("/ by zero")||message.contains("Not yet implemented:")||message.contains("Transaction was asked to abort because of transaction timeout"))
                System.out.println();
            else if(message.contains("scriptEvaluationTimeout")||message.contains("The provided object does not have accessible keys")||message.contains("Java heap space")||(message.contains("Variable")&&message.contains("not defined"))||message.contains("greater than 2147483647 or lesser than -2147483648")||message.contains("There are no variables in scope to use for '*'")||message.contains("Argument of UNWIND must be a list"))
                System.out.println();
            else if(message.contains("a specific database failed and total time is")||message.contains("Unsupported value expression:")||message.contains("Custom functions and predicates are not supported on target implementation")||message.contains("The allocation of an extra 2.0 MiB would use more than the limit")||message.contains("Variables are not allowed in")||message.contains("Redeclaring variable"))
                System.out.println();
            else if(message.contains("Returning a deleted object as a result")||message.contains("Invalid types")||message.contains("Only edges and vertices can be deleted")||message.contains("Comparison is not defined for")||message.contains("MATCH can't be put after OPTIONAL MATCH")||message.contains("DELETE can only be called on")||message.contains("Unable to locate a value with")||message.contains("did not resolve to a graph entity"))
                System.out.println();
            else if(message.contains("must be a positive integer")||message.contains("A WITH clause is required to introduce")||message.contains("Division by zero")||message.contains("Cannot merge node using null property value")||message.contains("Unbound variable:")||message.contains("defined with conflicting type")||message.contains("Collections containing null values can not be stored in properties")||message.contains("long overflow"))
                System.out.println();
            else if(message.contains("In a WITH/RETURN with DISTINCT or an aggregation, it is not possible to access variables declared before the WITH/RETURN")||message.contains("Using aggregation functions inside of CASE is not allowed")||message.contains("Properties can only be set on edges and vertices")||message.contains("Cannot merge the following node because of null property value for")||message.contains("Expected an integer between -2147483648 and 2147483647, but got"))
                System.out.println();
            else if(message.contains("BigInteger would overflow supported range"))
                System.out.println();
            else {
                String s=null;
                String s_another=null;
               try{
                   if(globalState.getDatabaseName().contains("janus")){
                       s= (new TranslationFacade()).toGremlinGroovy(sb);
                       s_another=(new TranslationFacade()).toGremlinGroovy(another_sb);
                   }
                   else{
                       s=sb;
                       s_another=another_sb;
                   }
               }
               catch (Exception e){
                   if(e.getMessage().contains("Unsupported clause")||e.getMessage().contains("Unknown function")||e.getMessage().contains("Invalid input")||e.getMessage().contains("cannot follow"))
                       System.out.println();
                   else {
                       e.printStackTrace();
                       System.out.println(e.getMessage() + "\n");
                   }
               }
               if(s!=null&&s_another!=null)
               {
                   num++;
                fw.write(num + "、 EXCEPTIONS in:" + globalState.getDatabaseName() + "\n" + message + "\n");
                fw.write(s + "\n" + s_another + "\n***********************\n");
               }

            }
        } else {
            num++;
            fw.write(num+"、 EXCEPTIONS of null message in:" + globalState.getDatabaseName() + "\n");
            fw.write(sb + "\n" + another_sb + "\n++++++++++++++++++++++++++\n");
        }
    }

    @Override
    public void check() throws Exception {
        result_changed=false;
        union_flag = 0;
        if (new Randomly().getInteger(0, 10) == 0)
            union_flag = 1;
        IClauseSequence sequence = globalState.getOptions().getUpdate_related()==0?queryGenerator.generateCRUDQuery(globalState):queryGenerator.generateQuery(globalState);
        StringBuilder sb = new StringBuilder();
        sequence.toTextRepresentation(sb);
        //不采用GraphGenie的mr时，生成UNION (ALL)复合查询
        if (union_flag == 1&&globalState.getOptions().getMr_selected()!=1) {
            union_flag = 2;
            returns = ((IReturn) (sequence.getClauseList().get(sequence.getClauseList().size() - 1))).getReturnList();
            IClauseSequence sequence01 = globalState.getOptions().getUpdate_related()==0?queryGenerator.generateCRUDQuery(globalState):queryGenerator.generateQuery(globalState);
            sb.append(Randomly.getBoolean()?" UNION ALL ":" UNION ");
            sequence01.toTextRepresentation(sb);
        }
        List<Exception> exception_in_database01 = new ArrayList<>();
        List<Exception> exception_in_database02 = new ArrayList<>();
        String another_sb="";
        //todo 选择哪种蜕变关系
        if(globalState.getOptions().getMr_selected()==0)
            another_sb = globalState.getOptions().getUpdate_related()==0?CypherVisitorImp.transform(this.cv, sb.toString()):CypherVisitorImpNoUpdate.transform(this.cv_noupdate, sb.toString());
        else if(globalState.getOptions().getMr_selected()==1)
            another_sb=Randomly.fromList(mutate_graphgenie(sb.toString()));
        else if(globalState.getOptions().getMr_selected()==2)
            another_sb=Randomly.fromList(mutate_grev(sb.toString()));
        List<GDSmithResultSet> results = new ArrayList<>();
        List<GDSmithResultSet> node_results = new ArrayList<>();
        List<GDSmithResultSet> relation_results = new ArrayList<>();
        int resultLength = 0;
        byte[] branchCoverage = new byte[BRANCH_SIZE];
        byte[] branchPairCoverage = new byte[BRANCH_PAIR_SIZE];
        System.out.println(sb);
        System.out.println(another_sb);
        long query_timestart=System.currentTimeMillis();
        //todo 标识数据库状态一致性，false表示当前数据库不再可用，需要抛出异常
        boolean database_state_same = true;
        try {
            //记录第一个数据库查询返回结果
            results.addAll(globalState.executeStatementAndGet(new CypherQueryAdapter(sb.toString())));
        } catch (Exception e) {
            exception_in_database01.add(e);
        }
        try {
            //记录第一个数据库查询返回结果
            results.addAll(globalState.executeStatementAndGet(new CypherQueryAdapter(another_sb)));
        } catch (Exception e) {
            exception_in_database02.add(e);
        }
        long query_timeend=System.currentTimeMillis();
        long query_time=query_timeend-query_timestart;
        time_query+=query_time;
        //todo 蜕变前后两个查询语句有相同的报错信息
        if (exception_in_database01.size() == 1 && exception_in_database02.size() == 1 && exception_in_database01.get(0).getMessage().equals(exception_in_database02.get(0).getMessage())) {
            check_message(exception_in_database01.get(0).getMessage(), sb.toString(), another_sb);
        }
        //todo 蜕变前后两个查询语句都无报错信息
        else if (exception_in_database01.size() == 0 && exception_in_database02.size() == 0) {
            System.out.println("both queries success!");
        }
        //todo 只有一个数据库执行成功或者蜕变前后的报错信息不一样
        else {
            if (exception_in_database01.size() != 0) {
                check_message(exception_in_database01.get(0).getMessage(), sb.toString(), another_sb);
            } else if (exception_in_database02.size() != 0) {
                check_message(exception_in_database02.get(0).getMessage(), sb.toString(), another_sb);
            }
            if (exception_in_database01.size() + exception_in_database02.size() == 1)
            {
                database_state_same=false;
            }
        }
        long time_gs_start=System.currentTimeMillis();
        boolean found = false;
        // check node
        if(database_state_same&&globalState.getOptions().getGraph_state_oracle()==0) {
        try {
            String check_node="MATCH (a) RETURN a";
            if(globalState.getOptions().getDatabaseType().contains("janus"))
                check_node="MATCH (a) RETURN count(a)";
            //记录所有节点信息
            node_results.addAll(globalState.executeStatementAndGet(new CypherQueryAdapter(check_node)));
            node_results.addAll(globalState.executeStatementAndGet(new CypherQueryAdapter(check_node)));
        } catch (Exception e) {
            System.out.println("exception in checking nodes: "+e.getMessage());
            //超时就进入下一轮迭代
            if(e.getMessage().contains("scriptEvaluationTimeout")||e.getMessage().contains("a specific database failed and total time is 0")){
//                throw e;
            }
            else if(e.getMessage().equals("cannot be cast to"))
                System.out.println();
            else {
                check_message(e.getMessage(), sb.toString(), another_sb);
                e.printStackTrace();
                throw e;
            }
        }
            for (int i = 1; i < node_results.size(); i++) {
                if (!node_results.get(i).compareWithOutOrder(this, node_results.get(i - 1), false)) {
                        check_message("mismatch in nodes", sb.toString(), another_sb);
                        found = true;
                    database_state_same = false;
                }
            }
        }
        // check relationship
        if(database_state_same&&globalState.getOptions().getGraph_state_oracle()==0)
        {
        try {
            String check_relation="MATCH (b)-[a]->(c) RETURN a";
            if(globalState.getOptions().getDatabaseType().contains("janus"))
                check_relation="MATCH (b)-[a]->(c) RETURN count(a)";
            relation_results.addAll(globalState.executeStatementAndGet(new CypherQueryAdapter(check_relation)));
            relation_results.addAll(globalState.executeStatementAndGet(new CypherQueryAdapter(check_relation)));
        }
        catch (Exception e) {
            System.out.println("exception in checking relationships: "+e.getMessage());
            //超时就进入下一轮迭代
            if(e.getMessage().contains("scriptEvaluationTimeout")||e.getMessage().contains("a specific database failed and total time is 0")){
//                throw e;
            }
            else if(e.getMessage().equals("cannot be cast to"))
                System.out.println();
            else {
                check_message(e.getMessage(), sb.toString(), another_sb);
                e.printStackTrace();
                throw e;
            }
        }
        for (int i = 1; i < relation_results.size(); i++) {
            if (!relation_results.get(i).compareWithOutOrder(this, relation_results.get(i - 1),false)) {
                check_message("mismatch in relationships",sb.toString(),another_sb);
                found = true;
                database_state_same = false;
            }
        }}
        long time_gs_end=System.currentTimeMillis();
        long time_gs=time_gs_end-time_gs_start;
        time_oracle2+=time_gs;
        if (!database_state_same) {
            throw new ResultMismatchException("mismatch database state!");
        }
        long time_oracle1_start=System.currentTimeMillis();
        //todo 判断不同的resultSet返回是否一致
        if (globalState.getOptions().getCoverage_port() != 0) {
            try (Socket clientSocket = new Socket("127.0.0.1", globalState.getOptions().getCoverage_port())) {
                OutputStream os = clientSocket.getOutputStream();
                InputStream is = clientSocket.getInputStream();
                os.write(PRINT_MEM);

                byte[] allBytes = is.readAllBytes();
                for (int j = 0; j < BRANCH_PAIR_SIZE; j++) {
                    branchPairCoverage[j] = allBytes[j];
                }

                int coveredBranch = 0;
                for (int j = 0; j < BRANCH_SIZE; j++) {
                    branchCoverage[j] = allBytes[j + BRANCH_PAIR_SIZE];
                    if (allBytes[j + BRANCH_PAIR_SIZE] != 0) {
                        coveredBranch++;
                    }
                }
                System.out.println("" + coveredBranch + "\n");
                clientSocket.shutdownInput();
                clientSocket.shutdownOutput();
            }
        }
        if(!found){
        for (int i = 1; i < results.size(); i++) {
            if (!results.get(i).compareWithOutOrder(this, results.get(i - 1),true)&&!result_changed) {
                //todo 过滤掉一些导致误报的情况（如RETURN *，RETURN DISTINCT *）
                if (sb.toString().contains("RETURN *") || sb.toString().contains("RETURN DISTINCT *") || sb.toString().endsWith("SKIP 1") || sb.toString().endsWith("LIMIT 1")) {

                } else {
                    check_message("mismatch in results",sb.toString(),another_sb);
                    found = true;
                }
            }
        }}
        long time_oracle1_end=System.currentTimeMillis();
        time_oracle1+=(time_oracle1_end-time_oracle1_start);
        if (results.size() == 2) {
            resultLength = results.get(0).getRowNum();
        }
        boolean isBugDetected = false;
        //todo 上层通过抛出的异常检测是否通过，所以这里可以捕获并检测异常的类型，可以计算一些统计数据，然后重抛异常
        queryGenerator.addNewRecord(sequence, isBugDetected, resultLength, branchCoverage, branchPairCoverage);
    }

    @Override
    public FileWriter getFw() {
        return fw;
    }
}
