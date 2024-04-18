package org.example.graspdb.parserutil;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.tree.ParseTree;

public class parserUtils {
    public static String getFullTextOriginal(ParseTree tree) {
        ParserRuleContext context = (ParserRuleContext) tree;
        if (context.children == null) {
            return "";
        }
        Token startToken = context.start;
        Token stopToken = context.stop;
        Interval interval = new Interval(startToken.getStartIndex(), stopToken.getStopIndex());
        return context.start.getInputStream().getText(interval);
    }
}
