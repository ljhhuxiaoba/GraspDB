package org.example.graspdb.tinkerGraph;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.google.gson.JsonObject;
import org.example.graspdb.DBMSSpecificOptions;
import org.example.graspdb.cypher.dsl.IQueryGenerator;



@Parameters(separators = "=", commandDescription = "Tinker (default port: " + TinkerOptions.DEFAULT_PORT
        + ", default host: " + TinkerOptions.DEFAULT_HOST)
public class TinkerOptions implements DBMSSpecificOptions {

    public static final String DEFAULT_HOST = "localhost";
    public static final int DEFAULT_PORT = 8182; //todo 改

    public static TinkerOptions parseOptionFromFile(JsonObject jsonObject){
        TinkerOptions options = new TinkerOptions();
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


    @Parameter(names = "--config_file")
    public String configFile = null;

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
    public String username = "Tinker";

    @Parameter(names = "--password")
    public String password = "sqlancer";


    @Override
    public IQueryGenerator getQueryGenerator() {
        return null;
    }

}
