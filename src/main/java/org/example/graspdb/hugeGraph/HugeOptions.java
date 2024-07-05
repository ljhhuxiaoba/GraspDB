package org.example.graspdb.hugeGraph;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.google.gson.JsonObject;
import org.example.graspdb.DBMSSpecificOptions;
import org.example.graspdb.cypher.dsl.IQueryGenerator;

@Parameters(separators = "=", commandDescription = "Huge (default port: " + org.example.graspdb.hugeGraph.HugeOptions.DEFAULT_PORT
        + ", default host: " + org.example.graspdb.hugeGraph.HugeOptions.DEFAULT_HOST)
public class HugeOptions implements DBMSSpecificOptions {

    public static final String DEFAULT_HOST = "localhost";
    public static final int DEFAULT_PORT = 8080; //todo 改

    @Parameter(names = "--config_file")
    public String configFile = null;

    public static org.example.graspdb.hugeGraph.HugeOptions parseOptionFromFile(JsonObject jsonObject){
        org.example.graspdb.hugeGraph.HugeOptions options = new org.example.graspdb.hugeGraph.HugeOptions();
        if(jsonObject.has("host")){
            options.host = jsonObject.get("host").getAsString();
        }
        if(jsonObject.has("port")){
            options.port = jsonObject.get("port").getAsInt();
        }
        if(jsonObject.has("username")){
            options.username = jsonObject.get("username").getAsString();
        }
        if(jsonObject.has("password")){
            options.password = jsonObject.get("password").getAsString();
        }
        if(jsonObject.has("config_file")){
            options.configFile = jsonObject.get("config_file").getAsString();
        }
        return options;
    }

    public String getHost() {
        return host;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getPort() {
        return port;
    }

    @Parameter(names = "--host")
    public String host = DEFAULT_HOST;

    @Parameter(names = "--port")
    public int port = DEFAULT_PORT;

    @Parameter(names = "--username")
    public String username = "admin";

    @Parameter(names = "--password")
    public String password = "admin";

    @Override
    public IQueryGenerator getQueryGenerator() {
        return null;
    }

}
