import os
import time
import sys
from mutator.query_transformer import QueryTransformer

mutator = QueryTransformer()
res = mutator.mutant_query_generator(sys.argv[1]);
print(res)