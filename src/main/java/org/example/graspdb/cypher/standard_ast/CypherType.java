package org.example.graspdb.cypher.standard_ast;

import org.example.graspdb.Randomly;
import org.example.graspdb.cypher.ast.ICypherType;

import java.util.List;

public enum CypherType implements ICypherType {
    NUMBER, BOOLEAN, STRING, NODE, RELATION, PATH , UNKNOWN, LIST, MAP, BASIC, ANY;

    public static CypherType getRandomType(){
        Randomly randomly = new Randomly();
        int randomNum = randomly.getInteger(0, 13);
        if(randomNum < 2){
            return NUMBER;
        }
        if(randomNum < 4){
            return STRING;
        }
        if(randomNum < 6){
            return BOOLEAN;
        }
        if(randomNum < 7){
            return NODE;
        }
        if(randomNum < 8){
            return RELATION;
        }
        if(randomNum < 10){
            return LIST;
        }
        if(randomNum < 11){
            return MAP;
        }
        if(randomNum < 12){
            return PATH;
        }
        else{
            return UNKNOWN;
        }
    }
    public static CypherType getRandomTypeFromList(List<CypherType> Types){
        Randomly randomly = new Randomly();
        int randomNum = randomly.getInteger(0, Types.size());
        return Types.get(randomNum);
    }
    public static CypherType getRandomTypeExcept(List<CypherType> Types){
        CypherType type=getRandomType();
        while(Types.contains(type)){
            type=getRandomType();
}
        return type;
    }
}
