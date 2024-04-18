package org.example.graspdb;

import com.google.gson.JsonObject;
import org.example.graspdb.common.schema.AbstractSchema;

public abstract class ProviderAdapter<G extends GlobalState<O, ? extends AbstractSchema<G, ?>, C>, O extends DBMSSpecificOptions, C extends GDSmithDBConnection>
        implements DatabaseProvider<G, O, C> {

    private final Class<G> globalClass;
    private final Class<O> optionClass;

    public ProviderAdapter(Class<G> globalClass, Class<O> optionClass) {
        this.globalClass = globalClass;
        this.optionClass = optionClass;
    }

    @Override
    public StateToReproduce getStateToReproduce(String databaseName) {
        return new StateToReproduce(databaseName, this);
    }

    @Override
    public Class<G> getGlobalStateClass() {
        return globalClass;
    }

    @Override
    public Class<O> getOptionClass() {
        return optionClass;
    }

    @Override
    public abstract void generateAndTestDatabase(G globalState) throws Exception;

    protected abstract void checkViewsAreValid(G globalState);


    public abstract void generateDatabase(G globalState) throws Exception;
    public abstract O generateOptionsFromConfig(JsonObject config);

}
