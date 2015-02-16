package foo.bar.application.elpollo;

import foo.bar.application.Module;
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
