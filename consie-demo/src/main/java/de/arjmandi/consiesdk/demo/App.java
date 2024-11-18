package de.arjmandi.consiesdk.demo;

import de.arjmandi.consiesdk.ConsentSDK;
import static java.util.Collections.emptyList;

import android.app.Application;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ConsentSDK.initilize(this,emptyList());
    }
}
