package org.example.graspdb.redisGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.example.graspdb.cypher.ast.analyzer.ICypherTypeDescriptor;
import org.example.graspdb.cypher.standard_ast.CypherTypeDescriptor;
import org.example.graspdb.redisGraph.RedisGraphSchema.RedisGraphTable;
import org.example.graspdb.cypher.standard_ast.CypherType;
import org.example.graspdb.common.schema.AbstractTable;
import org.example.graspdb.common.schema.AbstractTableColumn;
import org.example.graspdb.common.schema.TableIndex;
import org.example.graspdb.cypher.ast.IExpression;
import org.example.graspdb.cypher.schema.CypherSchema;
import org.example.graspdb.cypher.schema.IFunctionInfo;
import org.example.graspdb.cypher.schema.IParamInfo;

public class RedisGraphSchema extends CypherSchema<RedisGraphGlobalState, RedisGraphTable> {


    public static RedisGraphSchema createEmptyNewSchema(){
        return new RedisGraphSchema(new ArrayList<RedisGraphTable>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }

    //todo complete
    public RedisGraphSchema(List<RedisGraphTable> databaseTables, List<CypherLabelInfo> labels,
                            List<CypherRelationTypeInfo> relationTypes, List<CypherPatternInfo> patternInfos) {
        super(databaseTables, labels, relationTypes, patternInfos);
    }

    @Override
    public List<IFunctionInfo> getFunctions() {
        return Arrays.asList(RedisGraphBuiltInFunctions.values());
    }


    public enum RedisGraphBuiltInFunctions implements IFunctionInfo{
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
//        MAX_NUMBER("max", "max@number", CypherType.NUMBER, new CypherParamInfo(CypherType.NUMBER, false)){
//            @Override
//            public ICypherTypeDescriptor calculateReturnType(List<IExpression> params) {
//                return new CypherTypeDescriptor(CypherType.NUMBER);
//            }
//        },
//        MAX_STRING("max", "max@string", CypherType.STRING, new CypherParamInfo(CypherType.STRING, false)){
//            @Override
//            public ICypherTypeDescriptor calculateReturnType(List<IExpression> params) {
//                return new CypherTypeDescriptor(CypherType.STRING);
//            }
//        },
//        MIN_NUMBER("min", "min@number", CypherType.NUMBER, new CypherParamInfo(CypherType.NUMBER, false)){
//            @Override
//            public ICypherTypeDescriptor calculateReturnType(List<IExpression> params) {
//                return new CypherTypeDescriptor(CypherType.NUMBER);
//            }
//        },
//        MIN_STRING("min", "min@string", CypherType.STRING, new CypherParamInfo(CypherType.STRING, false)){
//            @Override
//            public ICypherTypeDescriptor calculateReturnType(List<IExpression> params) {
//                return new CypherTypeDescriptor(CypherType.STRING);
//            }
//        },
//        PERCENTILE_COUNT_NUMBER("percentileCount", "percentileCount@number", CypherType.NUMBER,
//                new CypherParamInfo(CypherType.NUMBER, false), new CypherParamInfo(CypherType.NUMBER, false)){
//            @Override
//            public ICypherTypeDescriptor calculateReturnType(List<IExpression> params) {
//                return new CypherTypeDescriptor(CypherType.NUMBER);
//            }
//        },
//        PERCENTILE_COUNT_STRING("percentileCount", "percentileCount@string", CypherType.NUMBER,
//                new CypherParamInfo(CypherType.STRING, false), new CypherParamInfo(CypherType.NUMBER, false)){
//            @Override
//            public ICypherTypeDescriptor calculateReturnType(List<IExpression> params) {
//                return new CypherTypeDescriptor(CypherType.NUMBER);
//            }
//        },
//        PERCENTILE_DISC_NUMBER("percentileDisc", "percentileDisc@number", CypherType.NUMBER,
//                new CypherParamInfo(CypherType.NUMBER, false), new CypherParamInfo(CypherType.NUMBER, false)){
//            @Override
//            public ICypherTypeDescriptor calculateReturnType(List<IExpression> params) {
//                return new CypherTypeDescriptor(CypherType.NUMBER);
//            }
//        },
//        PERCENTILE_DISC_STRING("percentileDisc", "percentileDisct@string", CypherType.NUMBER,
//                new CypherParamInfo(CypherType.STRING, false), new CypherParamInfo(CypherType.NUMBER, false)){
//            @Override
//            public ICypherTypeDescriptor calculateReturnType(List<IExpression> params) {
//                return new CypherTypeDescriptor(CypherType.NUMBER);
//            }
//        },
//        ST_DEV("stDev", "stDev", CypherType.NUMBER, new CypherParamInfo(CypherType.NUMBER, false)){
//            @Override
//            public ICypherTypeDescriptor calculateReturnType(List<IExpression> params) {
//                return new CypherTypeDescriptor(CypherType.NUMBER);
//            }
//        },
//        ST_DEV_P("stDevP", "stDevP", CypherType.NUMBER, new CypherParamInfo(CypherType.NUMBER, false)){
//            @Override
//            public ICypherTypeDescriptor calculateReturnType(List<IExpression> params) {
//                return new CypherTypeDescriptor(CypherType.NUMBER);
//            }
//        },
//        SUM("sum", "sum", CypherType.NUMBER, new CypherParamInfo(CypherType.NUMBER, false)){
//            @Override
//            public ICypherTypeDescriptor calculateReturnType(List<IExpression> params) {
//                return new CypherTypeDescriptor(CypherType.NUMBER);
//            }
//        },
//        COLLECT("collect", "collect", CypherType.LIST, new CypherParamInfo(CypherType.ANY, false)){
//            @Override
//            public ICypherTypeDescriptor calculateReturnType(List<IExpression> params) {
//                return new CypherTypeDescriptor(CypherType.UNKNOWN);
//            }
//        }
        ;

        RedisGraphBuiltInFunctions(String name, String signature, CypherType expectedReturnType, IParamInfo... params){
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

    public enum RedisGraphDataType{

    }
    public static class RedisGraphTable extends AbstractTable<RedisGraphTableColumn, TableIndex, RedisGraphGlobalState> {

        //todo complete
        public RedisGraphTable(String name, List<RedisGraphTableColumn> columns, List<TableIndex> indexes, boolean isView) {
            super(name, columns, indexes, isView);
        }

        @Override
        public long getNrRows(RedisGraphGlobalState globalState) {
            return 0;
        }
    }

    public static class RedisGraphTableColumn extends AbstractTableColumn<RedisGraphTable, RedisGraphDataType> {
        //todo complete
        public RedisGraphTableColumn(String name, RedisGraphTable table, RedisGraphDataType type) {
            super(name, table, type);
        }
    }
}
