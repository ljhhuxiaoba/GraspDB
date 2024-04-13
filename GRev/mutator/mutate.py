import os
import time
import sys
from query_transformer import *

print("start")
print(sys.argv[1])
mutator =QueryTransformer()
res = mutator.mutant_query_generator(sys.argv[1]);
with open('output.txt', 'w') as f:
 	f.write(res + '\n')