package app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import butterknife.ButterKnife;

/*
 *Name：fanyanlong
 *Time：15:00
 *Name:project_five
 */
public class App extends Application {
   Context context;
    @Override
    public void onCreate() {
        super.onCreate();
         context=this;

    }
}
