import random
from mutator.pattern_transformer import PatternTransformer


class QueryTransformer():
    def __parse_patterns(self, query):
        pos = query.find("MATCH ")
        keywords = ["RETURN ", "OPTIONAL MATCH ", "WHERE ", "CONTAINS ", "WITHIN ",
                    "WITH ", "UNION ", "ALL ", "UNWIND ", "AS ", "MERGE ", "ON ",
                    "CREATE ", "SET ", "DETACH ", "DELETE ", "REMOVE ", "CALL ",
                    "YIELD ", "DISTINCT ", "ORDER ", "BY ", "L_SKIP ", "LIMIT ",
                    "ASCENDING ", "ASC ", "DESCENDING ", "DESC ", "OR ", "XOR ",
                    "AND ", "NOT ", "STARTS ", "ENDS ", "CONTAINS ", "IN ", "IS ",
                    "NULL ", "COUNT ", "CASE ", "ELSE ", "END ", "WHEN ", "THEN ",
                    "ANY ", "NONE ", "SINGLE ", "EXISTS ", "MATCH "
                    ]
        res = []
        while pos != -1:
            f = lambda x: query.find(x, pos + 1) if query.find(x, pos + 1) > -1 else len(query)
            next_pos = min(f(x) for x in keywords)
            res.append((pos + len("MATCH "), next_pos))
            pos = query.find("MATCH ", pos + 1)

        return res

    def mutant_query_generator(self, query):
        patterns = self.__parse_patterns(query)
        patterns = random.sample(patterns, random.randint(1, len(patterns)))

        new_query = ""
        index = 0
        for pos in sorted(patterns):
            while index < pos[0]:
                new_query = new_query + query[index]
                index = index + 1
            pattern = query[pos[0]:pos[1]]
            P = PatternTransformer()
            asg = P.pattern2asg(pattern)
            new_pattern = P.asg2pattern(asg)
            new_query = new_query + new_pattern + " "
            index = pos[1]
        while index < len(query):
            new_query = new_query + query[index]
            index = index + 1
        return new_query


if __name__ == "__main__":
    Q = QueryTransformer()
    print(Q.mutant_query_generator(
        "MATCH (n0)<-[r0 :T3]-(n1 :L1)-[r1 :T3]->(n2), (n4)-[r3 :T5]->(n5 :L0 :L2) WHERE (((((n1.k7) OR (n1.k7)) AND ((r0.id) <> (r1.id))) AND ((r0.id) <> (r3.id))) AND ((r1.id) <> (r3.id))) OPTIONAL MATCH (n3)<-[]-(n4)-[]->(n5 :L2), (n6 :L0)-[r4 :T0]->(n7 :L4)<-[r5 :T2]-(n1) WHERE (true AND ((r4.id) <> (r5.id))) OPTIONAL MATCH (n7 :L4), (n9 :L3 :L1)<-[r8 :T3]-(n10 :L0 :L3 :L2)-[r9 :T4]->(n11 :L4 :L1) WHERE ((n1.k9) AND ((r8.id) <> (r9.id))) WITH max('RD') AS a0, max('sJB3c') AS a1, max('R') AS a2 ORDER BY a0 DESC OPTIONAL MATCH (n12 :L0)-[r10 :T5]->(n13 :L4 :L2)<-[r11 :T4]-(n14 :L4 :L3 :L0) WHERE ((n13.k23) AND ((r10.id) <> (r11.id))) WITH DISTINCT a1, n13, (n12.k2) AS a3, r11 WHERE (NOT true) WITH DISTINCT n13, r11, avg(-935939402) AS a4 MATCH (n15 :L2 :L1)-[r12 :T5]->(n8)<-[r13 :T0]-(n16 :L2) WHERE (((r12.id) > -1) AND ((r12.id) <> (r13.id))) RETURN DISTINCT (n16.k12) AS a5, (r12.k65) AS a6;"))



