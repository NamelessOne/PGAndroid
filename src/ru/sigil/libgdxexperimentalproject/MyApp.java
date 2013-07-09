package ru.sigil.libgdxexperimentalproject;

import android.app.Application;

import org.acra.ACRA;
import org.acra.ErrorReporter;
import org.acra.annotation.ReportsCrashes;

@ReportsCrashes(formKey = "dC1MWHBtNjgwZTQ4MXhrZ3Vpd1RpOHc6MQ", logcatArguments = {
        "-t", "50"})
public class MyApp extends Application {

    @Override
    public void onCreate() {
        ACRA.init(this);
        ErrorReporter.getInstance().checkReportsOnApplicationStart();
        super.onCreate();
    }
}