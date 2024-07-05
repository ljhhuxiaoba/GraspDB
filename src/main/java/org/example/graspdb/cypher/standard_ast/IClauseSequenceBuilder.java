package org.example.graspdb.cypher.standard_ast;

import org.example.graspdb.cypher.ast.*;
import org.example.graspdb.cypher.dsl.IIdentifierBuilder;

import java.util.List;

public interface IClauseSequenceBuilder {
    IIdentifierBuilder getIdentifierBuilder();
    void setIdentifierBuilder(IIdentifierBuilder identifierBuilder);
    IClauseSequenceBuilder RandomStartClause(Boolean subquery);
    IOngoingMatch MatchClause();
    IOngoingMatch MatchClause(IExpression condition, List<IPattern> patternTuple);

    IOngoingMatch OptionalMatchClause();
    IOngoingMatch OptionalMatchClause(IExpression condition, List<IPattern> patternTuple);

    IClauseSequenceBuilder UnwindClause();
    IClauseSequenceBuilder UnwindClause(IRet listAsAlias);

    Boolean getDelete_flag();

    void setDelete_flag(Boolean b);

    interface IOngoingMatch extends IClauseSequenceBuilder{
    }
    interface IOngoingMerge extends IClauseSequenceBuilder{
    }
    interface IOngoingCreate extends IClauseSequenceBuilder{
    }
    interface IOngoingDelete extends IClauseSequenceBuilder{
    }
    interface IOngoingCall extends IClauseSequenceBuilder{
    }
    interface IOngoingForeach extends IClauseSequenceBuilder{
    }
    interface IOngoingSet extends IClauseSequenceBuilder{
    }
    interface IOngoingRemove extends IClauseSequenceBuilder{
    }


    IOngoingWith WithClause();
    IOngoingWith WithClause(IExpression condition, List<IRet> aliasTuple);

    interface IOngoingWith extends IClauseSequenceBuilder{
        IOngoingWith orderBy(boolean isDesc, IExpression ...expression);
        IOngoingWith limit(IExpression expression);
        IOngoingWith skip(IExpression expression);
        IOngoingWith distinct();
    }

    IOngoingReturn ReturnClause(List<IRet> returnList);
    IOngoingReturn ReturnClause();

    interface IOngoingReturn extends IClauseSequenceBuilder{
        IOngoingReturn orderBy(boolean isDesc, IExpression ...expression);
        IOngoingReturn limit(IExpression expression);
        IOngoingReturn skip(IExpression expression);
        IOngoingReturn distinct();
    }
    IOngoingCreate CreateClause();
    IOngoingCreate CreateClause(List<IPattern> patterns);

    //todo
    IOngoingDelete DeleteClause(List<IDele> deleteList);
    IOngoingDelete DeleteClause();

    //todo
    IOngoingCall CallClause();
    IOngoingForeach ForeachClause();
    IOngoingSet SetClause();
    IOngoingRemove RemoveClause();

    IOngoingMerge MergeClause();
    IOngoingMerge MergeClause(IPattern pattern);

    //public IClauseSequence build(IConditionGenerator conditionGenerator, IAliasGenerator aliasGenerator,
    //                            IPatternGenerator patternGenerator, Neo4jSchema schema);

    IClauseSequence build();
    void clear_clause();
    void clear_flag();
}
