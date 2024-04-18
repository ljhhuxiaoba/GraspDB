package org.example.graspdb.CypherTransform;

import org.example.graspdb.parsercypher.CypherParser;
import org.example.graspdb.parsercypher.CypherParserBaseListener;

public class CypherListenerImp extends CypherParserBaseListener {
    @Override
    public void enterSubqueryExist(CypherParser.SubqueryExistContext ctx) {
           CypherVisitorImp.in_subquery=true;
    }
    @Override
    public void enterSubqueryCount(CypherParser.SubqueryCountContext ctx) {
        CypherVisitorImp.in_subquery=true;
    }
    @Override
    public void exitSubqueryExist(CypherParser.SubqueryExistContext ctx) {
        CypherVisitorImp.in_subquery=false;
    }
    @Override
    public void exitSubqueryCount(CypherParser.SubqueryCountContext ctx) {
        CypherVisitorImp.in_subquery=false;
    }
}
