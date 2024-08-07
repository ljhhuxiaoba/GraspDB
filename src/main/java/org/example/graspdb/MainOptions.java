package org.example.graspdb;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import org.example.graspdb.Randomly.StringGenerationStrategy;

@Parameters(separators = "=", commandDescription = "Options applicable to all DBMS")
public class MainOptions {
    public static final int NO_SET_PORT = -1;
    @Parameter(names = { "--help", "-h" }, description = "Lists all supported options and commands", help = true)
    private boolean help; // NOPMD

    @Parameter(names = {
            "--num-threads" }, description = "How many threads should run concurrently to test separate databases")
    private int nrConcurrentThreads = 1; // NOPMD

    @Parameter(names = {
            "--random-seed" }, description = "A seed value != -1 that can be set to make the query and database generation deterministic")
    private long randomSeed = -1; // NOPMD

    @Parameter(names = { "--num-tries" }, description = "Specifies after how many found errors to stop testing")
    private int totalNumberTries = 10; // NOPMD

    @Parameter(names = {
            "--num-queries" }, description = "Specifies the number of queries to be issued to a database before creating a new database")
    private int nrQueries = 10; // NOPMD

    @Parameter(names = {
            "--num-statement-kind-retries" }, description = "Specifies the number of times a specific statement kind (e.g., INSERT) should be retried when the DBMS indicates that it failed")
    private int nrStatementRetryCount = 1000; // NOPMD

    @Parameter(names = "--log-each-select", description = "Logs every statement issued", arity = 1)
    private boolean logEachSelect = true; // NOPMD

    @Parameter(names = "--log-execution-time", description = "Logs the execution time of each statement (requires --log-each-select to be enabled)", arity = 1)
    private boolean logExecutionTime = false; // NOPMD


    @Parameter(names = "--print-progress-information", description = "Whether to print progress information such as the number of databases generated or queries issued", arity = 1)
    private boolean printProgressInformation = true; // NOPMD

    @Parameter(names = "--print-progress-summary", description = "Whether to print an execution summary when exiting SQLancer", arity = 1)
    private boolean printProgressSummary; // NOPMD

    @Parameter(names = "--timeout-seconds", description = "The timeout in seconds")
    private int timeoutSeconds = -1; // NOPMD

    @Parameter(names = "--max-generated-databases", description = "The maximum number of databases that are generated by each thread")
    private int maxGeneratedDatabases = -1; // NOPMD

    @Parameter(names = "--exit-code-error", description = "The exit code that should be returned when an error is encountered (or a bug is found)")
    private int errorExitCode = -1; // NOPMD

    @Parameter(names = "--print-statements", description = "Print all statements to stdout, before they are sent to the DBMS (not yet implemented for all oracles)", arity = 1)
    private boolean printStatements; // NOPMD

    @Parameter(names = "--print-succeeding-statements", description = "Print statements that are successfully processed by the DBMS to stdout (not yet implemented for all oracles)", arity = 1)
    private boolean printSucceedingStatements; // NOPMD

    @Parameter(names = "--test-only-nonempty-tables", description = "Test only databases each of whose tables contain at least a single row", arity = 1)
    private boolean testOnlyWithMoreThanZeroRows; // NOPMD

    @Parameter(names = "--pqs-test-aggregates", description = "Partially test aggregate functions when all tables contain only a single row.", arity = 1)
    private boolean testAggregateFunctions; // NOPMD

    @Parameter(names = "--random-string-generation", description = "Select the random-string eneration approach")
    private StringGenerationStrategy randomStringGenerationStrategy = StringGenerationStrategy.ALPHANUMERIC; // NOPMD

    @Parameter(names = "--string-constant-max-length", description = "Specify the maximum-length of generated string constants")
    private int maxStringConstantLength = 10; // NOPMD

    @Parameter(names = "--use-constant-caching", description = "Specifies whether constants should be cached and re-used with a certain probability", arity = 1)
    private boolean useConstantCaching = true; // NOPMD

    @Parameter(names = "--use-connection-test", description = "Test whether the DBMS is accessible before trying to connect using multiple threads", arity = 1)
    private boolean useConnectionTest = true; // NOPMD

    @Parameter(names = "--constant-cache-size", description = "Specifies the size of the constant cache. This option only takes effect when constant caching is enabled")
    private int constantCacheSize = 100; // NOPMD

    @Parameter(names = "--database-prefix", description = "The prefix used for each database created")
    private String databasePrefix = "_database"; // NOPMD
    //todo 命令去掉第几类蜕变关系
    @Parameter(names = "--relation-removed", description = "The relation removed")
    private int relation_removed = 0; // NOPMD
    //todo 是否采用图状态oracle
    @Parameter(names = "--graph-state-oracle", description = "Whether to check GSO")
    private int graph_state_oracle = 0; // NOPMD
    //todo 是否添加更新
    @Parameter(names = "--update-related", description = "update-related or not")
    private int update_related = 0; // NOPMD
    //todo 选择哪个蜕变关系，0 for ours,1 for graphgenie,2 for grev
    @Parameter(names = "--mr-selected", description = "select which mr")
    private int mr_selected = 0; // NOPMD
    //todo 命令参数声明数据库的类型
    @Parameter(names = "--database-instance", description = "The name of tested database")
    private String databaseType = "unknown_database_type"; // NOPMD
    @Parameter(names = "--tcp-guidance")
    private boolean tcpGuidance = false;

    @Parameter(names = "--algorithm")
    private Algorithms algorithm = Algorithms.COMPARED3;

    @Parameter(names = "--coverage-port")
    private int coverage_port = 0;

    @Parameter(names = "--label-num")
    private int labelNum = 3;

    @Parameter(names = "--avoid-limitations")
    private boolean avoidLimitations = false;

    @Parameter(names = "--max-clause-size")
    private int maxClauseSize = 3;

    @Parameter(names = "--max-node-num")
    private int maxNodeNum = 4;

    @Parameter(names = "--force-compare" )
    private boolean forceCompareAndIgnoreException = false;

    @Parameter(names = "--manual-starting")
    private int manualStarting = 0;

    public int getManualStarting() {
        return manualStarting;
    }

    public boolean forceCompareAndIgnoreException() {
        return forceCompareAndIgnoreException;
    }

    public int getMaxClauseSize() {
        return maxClauseSize;
    }

    public int getMaxNodeNum() {
        return maxNodeNum;
    }

    public boolean isAvoidLimitations(){
        return avoidLimitations;
    }

    public int getLabelNum() {
        return labelNum;
    }

    public int getTotalNumberTries() {
        return totalNumberTries;
    }

    public int getNumberConcurrentThreads() {
        return nrConcurrentThreads;
    }

    public boolean logEachSelect() {
        return logEachSelect;
    }

    public boolean printAllStatements() {
        if (printSucceedingStatements && printStatements) {
            throw new AssertionError();
        }
        return printStatements;
    }

    public boolean printSucceedingStatements() {
        if (printStatements && printSucceedingStatements) {
            throw new AssertionError();
        }
        return printSucceedingStatements;
    }

    public boolean logExecutionTime() {
        if (!logEachSelect) {
            throw new AssertionError();
        }
        return logExecutionTime;
    }

    public int getNrQueries() {
        return nrQueries;
    }
    public int getmaxNodeNum() {
        return maxNodeNum;
    }

    public int getNrStatementRetryCount() {
        return nrStatementRetryCount;
    }

    public boolean printProgressInformation() {
        return printProgressInformation;
    }

    public boolean printProgressSummary() {
        return printProgressSummary;
    }

    public int getTimeoutSeconds() {
        return timeoutSeconds;
    }

    public int getMaxGeneratedDatabases() {
        return maxGeneratedDatabases;
    }

    public int getErrorExitCode() {
        return errorExitCode;
    }

    public long getRandomSeed() {
        return randomSeed;
    }

    public boolean testOnlyWithMoreThanZeroRows() {
        return testOnlyWithMoreThanZeroRows;
    }

    public StringGenerationStrategy getRandomStringGenerationStrategy() {
        return randomStringGenerationStrategy;
    }

    public int getMaxStringConstantLength() {
        return maxStringConstantLength;
    }

    public boolean useConstantCaching() {
        return useConstantCaching;
    }

    public int getConstantCacheSize() {
        return constantCacheSize;
    }

    public boolean isHelp() {
        return help;
    }

    public String getDatabasePrefix() {
        return databasePrefix;
    }
    public String getDatabaseType() {
        return databaseType;
    }
    public int getRelation_removed() {
        return relation_removed;
    }
    public int getGraph_state_oracle() {
        return graph_state_oracle;
    }
    public int getUpdate_related() {
        return update_related;
    }
    public int getMr_selected() {
        return mr_selected;
    }
    public boolean performConnectionTest() {
        return useConnectionTest;
    }

    public boolean isTcpGuidance() {
        return tcpGuidance;
    }

    public Algorithms getAlgorithm(){
        return algorithm;
    }

    public int getCoverage_port() {
        return coverage_port;
    }

    public enum Algorithms{
        SIMPLE, PATTERN_GUIDED, MANUAL, NON_EMPTY, COMPARED1, COMPARED2, COMPARED3, COMPARED4, COMPARED5
    }
}
