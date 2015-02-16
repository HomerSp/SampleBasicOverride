package eu.elpollo.sample.basicoverride;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import se.aqba.framework.elpollo.ElPollo;
import se.aqba.framework.elpollo.ElPolloModule;
import se.aqba.framework.elpollo.exception.OverrideException;


public class Module extends ElPolloModule {
    // The class tag, used for log output.
    private static final String TAG = "BasicOverrideOverride";

    // List of overrides, so we can destroy them.
    private List<ElPollo.MemberOverride> mOverrides = new ArrayList<ElPollo.MemberOverride>();

    @Override
    public void main() {
        // For the sake of this guide we are not using a package load listener because
        // we are overriding a method in the module, and the modules are not sent to the listener.
        // Normally you would need to insert a package load listener here, and move the override
        // code to within the onLoad method.

        // No class loader needed.
        ElPollo.MemberOverride.Builder builder = new ElPollo.MemberOverride.Builder();
        builder.setSource(Module.class, "testMethod");
        builder.setTarget(new ElPollo.OverrideCall<Module, Void>() {
            @Override
            public Void call(Module obj, Object... args) {
                // This will be called instead of the source.
                Log.d(TAG, "testMethod override");

                // Returning null because of the Void return type.
                return null;
            }
        });

        try {
            ElPollo.MemberOverride override = builder.create();
            ElPollo.Overrides.add(override);

            mOverrides.add(override);
        } catch(OverrideException e) {
            Log.e(TAG, "Failure creating override", e);
        }

        // Call testMethod, this should end up in the override.
        testMethod();
    }

    @Override
    public void destroy() {
        super.destroy(mOverrides);
    }

    // The source method, this should never be called.
    public void testMethod() {
        Log.d(TAG, "testMethod");
    }
}
