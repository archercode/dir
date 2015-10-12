package com.example.e_directory;

import com.parse.Parse;

public class CMainApplication extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);

        // Add your initialization code here
//        Parse.initialize(this);
        Parse.initialize(this, "9WfD14lR81xKSXbVWGdRTV54UgDUu1wFCIQPYOWW", "aR0yrI9aKcODQON9y4VCrENvAL2665YKnOmSMRpF");
        /*
        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        // If you would like all objects to be private by default, remove this
        // line.
        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);
        */

    }
}
