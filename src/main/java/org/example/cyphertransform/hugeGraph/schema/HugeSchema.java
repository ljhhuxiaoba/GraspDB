package org.example.cyphertransform.hugeGraph.schema;

import org.example.cyphertransform.common.schema.AbstractTable;
import org.example.cyphertransform.common.schema.AbstractTableColumn;
import org.example.cyphertransform.common.schema.TableIndex;
import org.example.cyphertransform.cypher.ast.IExpression;
import org.example.cyphertransform.cypher.ast.analyzer.ICypherTypeDescriptor;
import org.example.cyphertransform.cypher.schema.CypherSchema;
import org.example.cyphertransform.cypher.schema.IFunctionInfo;
import org.example.cyphertransform.cypher.schema.IParamInfo;
import org.example.cyphertransform.cypher.standard_ast.CypherType;
import org.example.cyphertransform.cypher.standard_ast.CypherTypeDescriptor;
import org.example.cyphertransform.hugeGraph.HugeGlobalState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HugeSchema extends CypherSchema<HugeGlobalState, org.example.cyphertransform.hugeGraph.schema.HugeSchema.HugeTable> {


    public static org.example.cyphertransform.hugeGraph.schema.HugeSchema createEmptyNewSchema(){
        return new org.example.cyphertransform.hugeGraph.schema.HugeSchema(new ArrayList<org.example.cyphertransform.hugeGraph.schema.HugeSchema.HugeTable>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }

    //todo complete
    public HugeSchema(List<org.example.cyphertransform.hugeGraph.schema.HugeSchema.HugeTable> databaseTables, List<CypherLabelInfo> labels,
                       List<CypherRelationTypeInfo> relationTypes, List<CypherPatternInfo> patternInfos) {
        super(databaseTables, labels, relationTypes, patternInfos);
    }

    @Override
    public List<IFunctionInfo> getFunctions() {
        return Arrays.asList(org.example.cyphertransform.hugeGraph.schema.HugeSchema.HugeBuiltInFunctions.values());
    }


    public enum HugeBuiltInFunctions implements IFunctionInfo{
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

        HugeBuiltInFunctions(String name, String signature, CypherType expectedReturnType, IParamInfo... params){
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

    public enum HugeDataType{

    }
    public static class HugeTable extends AbstractTable<org.example.cyphertransform.hugeGraph.schema.HugeSchema.HugeTableColumn, TableIndex, HugeGlobalState> {

        //todo complete
        public HugeTable(String name, List<org.example.cyphertransform.hugeGraph.schema.HugeSchema.HugeTableColumn> columns, List<TableIndex> indexes, boolean isView) {
            super(name, columns, indexes, isView);
        }

        @Override
        public long getNrRows(HugeGlobalState globalState) {
            return 0;
        }
    }

    public static class HugeTableColumn extends AbstractTableColumn<org.example.cyphertransform.hugeGraph.schema.HugeSchema.HugeTable, org.example.cyphertransform.hugeGraph.schema.HugeSchema.HugeDataType> {
        //todo complete
        public HugeTableColumn(String name, org.example.cyphertransform.hugeGraph.schema.HugeSchema.HugeTable table, org.example.cyphertransform.hugeGraph.schema.HugeSchema.HugeDataType type) {
            super(name, table, type);
        }
    }
}
