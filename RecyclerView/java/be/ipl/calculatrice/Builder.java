package be.ipl.calculatrice;

import android.app.Application;

/**
 * Created by Gregg on 03-05-18.
 */

public class Builder extends Application {

    public CalculatriceModel getModel() {
        return model;
    }

    private CalculatriceModel model;

    @Override
    public void onCreate() {
        super.onCreate();
        model = new CalculatriceModel();
    }


}
