import os
import time
import sys
from query_mutator import *

print(sys.argv[1])
mutator = CypherQueryMutator([],[],[],[]);
r1, r2 = mutator.generate_equivalent_queries(sys.argv[1]);
with open('output.txt', 'w') as f:
        for s in r1:
                f.write(s + '\n')
