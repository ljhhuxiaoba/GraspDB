package org.example.graspdb.neo4j.schema;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.example.graspdb.Randomly;
import org.example.graspdb.cypher.ast.IExpression;
import org.example.graspdb.cypher.ast.analyzer.ICypherTypeDescriptor;
import org.example.graspdb.cypher.oracle.DifferentialNonEmptyBranchOracle;
import org.example.graspdb.cypher.schema.CypherSchema;
import org.example.graspdb.cypher.schema.IFunctionInfo;
import org.example.graspdb.cypher.schema.IParamInfo;
import org.example.graspdb.cypher.standard_ast.Alias;
import org.example.graspdb.cypher.standard_ast.CypherTypeDescriptor;
import org.example.graspdb.cypher.standard_ast.expr.*;
import org.example.graspdb.neo4j.Neo4jGlobalState;
import org.example.graspdb.neo4j.schema.Neo4jSchema.Neo4jTable;
import org.example.graspdb.cypher.standard_ast.CypherType;
import org.example.graspdb.common.schema.AbstractTable;
import org.example.graspdb.common.schema.AbstractTableColumn;
import org.example.graspdb.common.schema.TableIndex;

public class Neo4jSchema extends CypherSchema<Neo4jGlobalState, Neo4jTable> {


    public static Neo4jSchema createEmptyNewSchema(){
        return new Neo4jSchema(new ArrayList<Neo4jTable>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }

    //todo complete
    public Neo4jSchema(List<Neo4jTable> databaseTables, List<CypherLabelInfo> labels,
                       List<CypherRelationTypeInfo> relationTypes, List<CypherPatternInfo> patternInfos) {
        super(databaseTables, labels, relationTypes, patternInfos);
    }

    @Override
    public List<IFunctionInfo> getFunctions() {
        return Arrays.asList(Neo4jBuiltInFunctions.values());
    }


    public enum Neo4jBuiltInFunctions implements IFunctionInfo{
        AVG("avg", "avg@number", CypherType.NUMBER, new CypherParamInfo(CypherType.NUMBER, false)){
            @Override
            public ICypherTypeDescriptor calculateReturnType(List<IExpression> params) {
                return new CypherTypeDescriptor(CypherType.NUMBER);
            }
            @Override
            public ICypherTypeDescriptor calculateElementType(List<IExpression> params) {
                throw new RuntimeException("not list_function exception!");
            }
        },
        MAX_NUMBER("max", "max@number", CypherType.NUMBER, new CypherParamInfo(CypherType.NUMBER, false)){
            @Override
            public ICypherTypeDescriptor calculateReturnType(List<IExpression> params) {
                return new CypherTypeDescriptor(CypherType.NUMBER);
            }
            @Override
            public ICypherTypeDescriptor calculateElementType(List<IExpression> params) {
                throw new RuntimeException("not list_function exception!");
            }
        },
        MAX_STRING("max", "max@string", CypherType.STRING, new CypherParamInfo(CypherType.STRING, false)){
            @Override
            public ICypherTypeDescriptor calculateReturnType(List<IExpression> params) {
                return new CypherTypeDescriptor(CypherType.STRING);
            }
            @Override
            public ICypherTypeDescriptor calculateElementType(List<IExpression> params) {
                throw new RuntimeException("not list_function exception!");
            }
        },
        MIN_NUMBER("min", "min@number", CypherType.NUMBER, new CypherParamInfo(CypherType.NUMBER, false)){
            @Override
            public ICypherTypeDescriptor calculateReturnType(List<IExpression> params) {
                return new CypherTypeDescriptor(CypherType.NUMBER);
            }
            @Override
            public ICypherTypeDescriptor calculateElementType(List<IExpression> params) {
                throw new RuntimeException("not list_function exception!");
            }
        },
        MIN_STRING("min", "min@string", CypherType.STRING, new CypherParamInfo(CypherType.STRING, false)){
            @Override
            public ICypherTypeDescriptor calculateReturnType(List<IExpression> params) {
                return new CypherTypeDescriptor(CypherType.STRING);
            }
            @Override
            public ICypherTypeDescriptor calculateElementType(List<IExpression> params) {
                throw new RuntimeException("not list_function exception!");
            }
        },
        PERCENTILE_COUNT_NUMBER("percentileCount", "percentileCount@number", CypherType.NUMBER,
                new CypherParamInfo(CypherType.NUMBER, false), new CypherParamInfo(CypherType.NUMBER, false)){
            @Override
            public ICypherTypeDescriptor calculateReturnType(List<IExpression> params) {
                return new CypherTypeDescriptor(CypherType.NUMBER);
            }
            @Override
            public ICypherTypeDescriptor calculateElementType(List<IExpression> params) {
                throw new RuntimeException("not list_function exception!");
            }
        },
        PERCENTILE_COUNT_STRING("percentileCount", "percentileCount@string", CypherType.NUMBER,
                new CypherParamInfo(CypherType.STRING, false), new CypherParamInfo(CypherType.NUMBER, false)){
            @Override
            public ICypherTypeDescriptor calculateReturnType(List<IExpression> params) {
                return new CypherTypeDescriptor(CypherType.NUMBER);
            }
            @Override
            public ICypherTypeDescriptor calculateElementType(List<IExpression> params) {
                throw new RuntimeException("not list_function exception!");
            }
        },
        PERCENTILE_DISC_NUMBER("percentileDisc", "percentileDisc@number", CypherType.NUMBER,
                new CypherParamInfo(CypherType.NUMBER, false), new CypherParamInfo(CypherType.NUMBER, false)){
            @Override
            public ICypherTypeDescriptor calculateReturnType(List<IExpression> params) {
                return new CypherTypeDescriptor(CypherType.NUMBER);
            }
            @Override
            public ICypherTypeDescriptor calculateElementType(List<IExpression> params) {
                throw new RuntimeException("not list_function exception!");
            }
        },
        ST_DEV("stDev", "stDev", CypherType.NUMBER, new CypherParamInfo(CypherType.NUMBER, false)){
            @Override
            public ICypherTypeDescriptor calculateReturnType(List<IExpression> params) {
                return new CypherTypeDescriptor(CypherType.NUMBER);
            }
    @Override
    public ICypherTypeDescriptor calculateElementType(List<IExpression> params) {
        throw new RuntimeException("not list_function exception!");
    }
        },
        ST_DEV_P("stDevP", "stDevP", CypherType.NUMBER, new CypherParamInfo(CypherType.NUMBER, false)){
            @Override
            public ICypherTypeDescriptor calculateReturnType(List<IExpression> params) {
                return new CypherTypeDescriptor(CypherType.NUMBER);
            }
            @Override
            public ICypherTypeDescriptor calculateElementType(List<IExpression> params) {
                throw new RuntimeException("not list_function exception!");
            }
        },
        SUM("sum", "sum", CypherType.NUMBER, new CypherParamInfo(CypherType.NUMBER, false)){
            @Override
            public ICypherTypeDescriptor calculateReturnType(List<IExpression> params) {
                return new CypherTypeDescriptor(CypherType.NUMBER);
            }
            @Override
            public ICypherTypeDescriptor calculateElementType(List<IExpression> params) {
                throw new RuntimeException("not list_function exception!");
            }
        },
        COLLECT("collect", "collect", CypherType.LIST, new CypherParamInfo(CypherType.ANY, false)){
            @Override
            public ICypherTypeDescriptor calculateReturnType(List<IExpression> params) {
                return new CypherTypeDescriptor(CypherType.LIST);
            }
            @Override
            public ICypherTypeDescriptor calculateElementType(List<IExpression> params) {
                return new CypherTypeDescriptor(params.get(0).analyzeType(DifferentialNonEmptyBranchOracle.queryGenerator.getGlobalstate().getSchema(),null).getType());
            }
        },
        ROUND("round", "round", CypherType.NUMBER, new CypherParamInfo(CypherType.NUMBER, false),new CypherParamInfo(CypherType.NUMBER, false)){
            @Override
            public ICypherTypeDescriptor calculateReturnType(List<IExpression> params) {
                return new CypherTypeDescriptor(CypherType.NUMBER);
            }
            @Override
            public ICypherTypeDescriptor calculateElementType(List<IExpression> params) {
                throw new RuntimeException("not list_function exception!");
            }
        },
        SIZE("size", "size", CypherType.NUMBER, new CypherParamInfo(CypherType.LIST, false)){
            @Override
            public ICypherTypeDescriptor calculateReturnType(List<IExpression> params) {
                return new CypherTypeDescriptor(CypherType.NUMBER);
            }
            @Override
            public ICypherTypeDescriptor calculateElementType(List<IExpression> params) {
                throw new RuntimeException("not list_function exception!");
            }
        },
        SUBSTRING_LENGTH("substring", "substring", CypherType.STRING, new CypherParamInfo(CypherType.STRING, false),new CypherParamInfo(CypherType.NUMBER, false),new CypherParamInfo(CypherType.NUMBER, false)){
            @Override
            public ICypherTypeDescriptor calculateReturnType(List<IExpression> params) {
                return new CypherTypeDescriptor(CypherType.STRING);
            }
            @Override
            public ICypherTypeDescriptor calculateElementType(List<IExpression> params) {
                throw new RuntimeException("not list_function exception!");
            }
        },
        ABS("abs", "abs", CypherType.NUMBER, new CypherParamInfo(CypherType.NUMBER, false)){
            @Override
            public ICypherTypeDescriptor calculateReturnType(List<IExpression> params) {
                return new CypherTypeDescriptor(CypherType.NUMBER);
            }
            @Override
            public ICypherTypeDescriptor calculateElementType(List<IExpression> params) {
                throw new RuntimeException("not list_function exception!");
            }
        },
        REVERSE_STRING("reverse", "reverse_string", CypherType.STRING, new CypherParamInfo(CypherType.STRING, false)){
            @Override
            public ICypherTypeDescriptor calculateReturnType(List<IExpression> params) {
                return new CypherTypeDescriptor(CypherType.STRING);
            }
            @Override
            public ICypherTypeDescriptor calculateElementType(List<IExpression> params) {
                throw new RuntimeException("not list_function exception!");
            }
        },
        REVERSE_List("reverse", "reverse_list", CypherType.LIST, new CypherParamInfo(CypherType.LIST, false)){
            @Override
            public ICypherTypeDescriptor calculateReturnType(List<IExpression> params) {
                return new CypherTypeDescriptor(CypherType.LIST);
            }
            @Override
            public ICypherTypeDescriptor calculateElementType(List<IExpression> params) {
                if(params.get(0) instanceof CreateListExpression)
                    return params.get(0).analyzeType(DifferentialNonEmptyBranchOracle.queryGenerator.getGlobalstate().getSchema(),null);
                else if(params.get(0) instanceof CallExpression)
                    return ((CallExpression) params.get(0)).analyzeElementType(DifferentialNonEmptyBranchOracle.queryGenerator.getGlobalstate().getSchema(),null);
                else if(params.get(0) instanceof ConstExpression)
                    return params.get(0).analyzeType(DifferentialNonEmptyBranchOracle.queryGenerator.getGlobalstate().getSchema(),null);
                else if(params.get(0) instanceof IdentifierExpression)
                    return new CypherTypeDescriptor(((Alias)((IdentifierExpression) params.get(0)).getIdentifier()).getElement_Type());
                else if(params.get(0) instanceof CaseWhenExpression)
                    return new CypherTypeDescriptor(((CaseWhenExpression)params.get(0)).getElement_type());
                else if(params.get(0) instanceof ReduceExpression)
                    return new CypherTypeDescriptor(((ReduceExpression)params.get(0)).getElement_type());
                else
                {   System.out.println(params.get(0).getClass());
                    throw new RuntimeException("other param in reverse_list");}
            }
        },
        toIntegerOrNull("toIntegerOrNull", "toIntegerOrNull", CypherType.NUMBER, new CypherParamInfo(CypherType.UNKNOWN, false)){
            @Override
            public ICypherTypeDescriptor calculateReturnType(List<IExpression> params) {
                return new CypherTypeDescriptor(CypherType.NUMBER);
            }
            @Override
            public ICypherTypeDescriptor calculateElementType(List<IExpression> params) {
                throw new RuntimeException("not list_function exception!");
            }
        },
        toStringOrNull("toStringOrNull", "toStringOrNull", CypherType.STRING, new CypherParamInfo(CypherType.UNKNOWN, false)){
            @Override
            public ICypherTypeDescriptor calculateReturnType(List<IExpression> params) {
                return new CypherTypeDescriptor(CypherType.STRING);
            }
            @Override
            public ICypherTypeDescriptor calculateElementType(List<IExpression> params) {
                throw new RuntimeException("not list_function exception!");
            }
        },
        toBooleanOrNull("toBooleanOrNull", "toBooleanOrNull", CypherType.BOOLEAN, new CypherParamInfo(CypherType.UNKNOWN, false)){
            @Override
            public ICypherTypeDescriptor calculateReturnType(List<IExpression> params) {
                return new CypherTypeDescriptor(CypherType.BOOLEAN);
            }
            @Override
            public ICypherTypeDescriptor calculateElementType(List<IExpression> params) {
                throw new RuntimeException("not list_function exception!");
            }
        },
        exists("exists", "exists", CypherType.BOOLEAN, new CypherParamInfo(CypherType.ANY, false)){
            @Override
            public ICypherTypeDescriptor calculateReturnType(List<IExpression> params) {
                return new CypherTypeDescriptor(CypherType.BOOLEAN);
            }
            @Override
            public ICypherTypeDescriptor calculateElementType(List<IExpression> params) {
                throw new RuntimeException("not list_function exception!");
            }
        },
        toIntegerList("toIntegerList", "toIntegerList", CypherType.LIST, new CypherParamInfo(CypherType.LIST, false)){
            @Override
            public ICypherTypeDescriptor calculateReturnType(List<IExpression> params) {
                return new CypherTypeDescriptor(CypherType.LIST);
            }
            @Override
            public ICypherTypeDescriptor calculateElementType(List<IExpression> params) {
                return new CypherTypeDescriptor(CypherType.NUMBER);
            }
        },
        toStringList("toStringList", "toStringList", CypherType.LIST, new CypherParamInfo(CypherType.LIST, false)){
            @Override
            public ICypherTypeDescriptor calculateReturnType(List<IExpression> params) {
                return new CypherTypeDescriptor(CypherType.LIST);
            }
            @Override
            public ICypherTypeDescriptor calculateElementType(List<IExpression> params) {
                return new CypherTypeDescriptor(CypherType.STRING);
            }
        },
        toBooleanList("toBooleanList", "toBooleanList", CypherType.LIST, new CypherParamInfo(CypherType.LIST, false)){
            @Override
            public ICypherTypeDescriptor calculateReturnType(List<IExpression> params) {
                return new CypherTypeDescriptor(CypherType.LIST);
            }
            @Override
            public ICypherTypeDescriptor calculateElementType(List<IExpression> params) {
                return new CypherTypeDescriptor(CypherType.BOOLEAN);
            }
        },
        NODES("nodes", "nodes", CypherType.LIST, new CypherParamInfo(CypherType.PATH, false)){
            @Override
            public ICypherTypeDescriptor calculateReturnType(List<IExpression> params) {
                return new CypherTypeDescriptor(CypherType.LIST);
            }
            @Override
            public ICypherTypeDescriptor calculateElementType(List<IExpression> params) {
                return new CypherTypeDescriptor(CypherType.NODE);
            }
        },
        RELATIONSHIPS("relationships", "relationships", CypherType.LIST, new CypherParamInfo(CypherType.PATH, false)){
            @Override
            public ICypherTypeDescriptor calculateReturnType(List<IExpression> params) {
                return new CypherTypeDescriptor(CypherType.LIST);
            }
            @Override
            public ICypherTypeDescriptor calculateElementType(List<IExpression> params) {
                return new CypherTypeDescriptor(CypherType.RELATION);
            }
        },
        //可能有参数的语法错误
        RANGE("range", "range", CypherType.LIST, new CypherParamInfo(CypherType.NUMBER, false),new CypherParamInfo(CypherType.NUMBER, false),new CypherParamInfo(CypherType.NUMBER, false)){
            @Override
            public ICypherTypeDescriptor calculateReturnType(List<IExpression> params) {
                return new CypherTypeDescriptor(CypherType.LIST);
            }
            @Override
            public ICypherTypeDescriptor calculateElementType(List<IExpression> params) {
                return new CypherTypeDescriptor(CypherType.NUMBER);
            }
        },
        KEYS("keys", "keys", CypherType.LIST, new CypherParamInfo(CypherType.NODE, false)){
            @Override
            public ICypherTypeDescriptor calculateReturnType(List<IExpression> params) {
                return new CypherTypeDescriptor(CypherType.LIST);
            }
            @Override
            public ICypherTypeDescriptor calculateElementType(List<IExpression> params) {
                return new CypherTypeDescriptor(CypherType.STRING);
            }
        },
        LABELS("labels", "labels", CypherType.LIST, new CypherParamInfo(CypherType.NODE, false)){
            @Override
            public ICypherTypeDescriptor calculateReturnType(List<IExpression> params) {
                return new CypherTypeDescriptor(CypherType.LIST);
            }
            @Override
            public ICypherTypeDescriptor calculateElementType(List<IExpression> params) {
                return new CypherTypeDescriptor(CypherType.STRING);
            }
        },
        TYPE("type", "type", CypherType.STRING, new CypherParamInfo(CypherType.RELATION, false)){
            @Override
            public ICypherTypeDescriptor calculateReturnType(List<IExpression> params) {
                return new CypherTypeDescriptor(CypherType.STRING);
            }
            @Override
            public ICypherTypeDescriptor calculateElementType(List<IExpression> params) {
                throw new RuntimeException("not list_function exception!");
            }
        },
        ID("elementId", "elementId", CypherType.STRING, new CypherParamInfo(CypherType.NODE, false)){
            @Override
            public ICypherTypeDescriptor calculateReturnType(List<IExpression> params) {
                return new CypherTypeDescriptor(CypherType.STRING);
            }
            @Override
            public ICypherTypeDescriptor calculateElementType(List<IExpression> params) {
                throw new RuntimeException("not list_function exception!");
            }
        },
        isEMPTY("isEmpty", "isEmpty", CypherType.BOOLEAN, new CypherParamInfo(CypherType.LIST, false)){
            @Override
            public ICypherTypeDescriptor calculateReturnType(List<IExpression> params) {
                return new CypherTypeDescriptor(CypherType.BOOLEAN);
            }
            @Override
            public ICypherTypeDescriptor calculateElementType(List<IExpression> params) {
                throw new RuntimeException("not list_function exception!");
            }
        },
        Properties("properties", "properties", CypherType.MAP, new CypherParamInfo(Randomly.getBoolean()?CypherType.NODE:CypherType.RELATION, false)){
            @Override
            public ICypherTypeDescriptor calculateReturnType(List<IExpression> params) {
                return new CypherTypeDescriptor(CypherType.MAP);
            }
            @Override
            public ICypherTypeDescriptor calculateElementType(List<IExpression> params) {
                throw new RuntimeException("not list_function exception!");
            }
        },
        startNode("startNode", "startNode", CypherType.NODE, new CypherParamInfo(CypherType.RELATION, false)){
            @Override
            public ICypherTypeDescriptor calculateReturnType(List<IExpression> params) {
                return new CypherTypeDescriptor(CypherType.NODE);
            }
            @Override
            public ICypherTypeDescriptor calculateElementType(List<IExpression> params) {
                throw new RuntimeException("not list_function exception!");
            }
        },
        endNode("endNode", "endNode", CypherType.NODE, new CypherParamInfo(CypherType.RELATION, false)){
            @Override
            public ICypherTypeDescriptor calculateReturnType(List<IExpression> params) {
                return new CypherTypeDescriptor(CypherType.NODE);
            }
            @Override
            public ICypherTypeDescriptor calculateElementType(List<IExpression> params) {
                throw new RuntimeException("not list_function exception!");
            }
        },
        LENGTH("length", "length", CypherType.NUMBER, new CypherParamInfo(CypherType.PATH, false)){
            @Override
            public ICypherTypeDescriptor calculateReturnType(List<IExpression> params) {
                return new CypherTypeDescriptor(CypherType.NUMBER);
            }
            @Override
            public ICypherTypeDescriptor calculateElementType(List<IExpression> params) {
                throw new RuntimeException("not list_function exception!");
            }
        },
        EXISTS("EXISTS", "exists_subquery", CypherType.BOOLEAN){
            @Override
            public ICypherTypeDescriptor calculateReturnType(List<IExpression> params) {
                return new CypherTypeDescriptor(CypherType.BOOLEAN);
            }
            @Override
            public ICypherTypeDescriptor calculateElementType(List<IExpression> params) {
                throw new RuntimeException("not list_function exception!");
            }
        },
        COUNT("COUNT", "count_subquery", CypherType.NUMBER){
            @Override
            public ICypherTypeDescriptor calculateReturnType(List<IExpression> params) {
                return new CypherTypeDescriptor(CypherType.NUMBER);
            }
            @Override
            public ICypherTypeDescriptor calculateElementType(List<IExpression> params) {
                throw new RuntimeException("not list_function exception!");
            }
        },
        SPLIT_LIST("split", "split", CypherType.LIST, new CypherParamInfo(CypherType.STRING, false),new CypherParamInfo(CypherType.LIST, false)){
            @Override
            public ICypherTypeDescriptor calculateReturnType(List<IExpression> params) {
                return new CypherTypeDescriptor(CypherType.LIST);
            }
            @Override
            public ICypherTypeDescriptor calculateElementType(List<IExpression> params) {
                return new CypherTypeDescriptor(CypherType.STRING);
            }
        }
        ;

        Neo4jBuiltInFunctions(String name, String signature, CypherType expectedReturnType, IParamInfo... params){
            this.name = name;
            this.params = Arrays.asList(params);
            this.expectedReturnType = expectedReturnType;
            this.signature = signature;
        }

        private String name, signature;
        private List<IParamInfo> params;
        private CypherType expectedReturnType;

        @Override
        public String getName() {
            return name;
        }

        @Override
        public String getSignature() {
            return signature;
        }

        @Override
        public List<IParamInfo> getParams() {
            return params;
        }

    }
    
    public enum Neo4jDataType{

    }
    public static class Neo4jTable extends AbstractTable<Neo4jTableColumn, TableIndex, Neo4jGlobalState> {

        //todo complete
        public Neo4jTable(String name, List<Neo4jTableColumn> columns, List<TableIndex> indexes, boolean isView) {
            super(name, columns, indexes, isView);
        }

        @Override
        public long getNrRows(Neo4jGlobalState globalState) {
            return 0;
        }
    }

    public static class Neo4jTableColumn extends AbstractTableColumn<Neo4jTable, Neo4jDataType> {
        //todo complete
        public Neo4jTableColumn(String name, Neo4jTable table, Neo4jDataType type) {
            super(name, table, type);
        }
    }
}
