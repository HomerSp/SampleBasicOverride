package eu.elpollo.sample.basicoverride.elpollo;

import eu.elpollo.sample.basicoverride.Module;
import se.aqba.framework.elpollo.ElPolloModuleLoader;

/**
 * Created by homer on 16/02/15.
 */
public class Loader extends ElPolloModuleLoader {
    @Override
    public void main() {
        add(Module.class);
    }
}
