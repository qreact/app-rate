package net.qreact.app.example;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import net.qreact.app.apprate.QreactDialog;
import net.qreact.app.apprate.QreactRate;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        QreactDialog dialog = new QreactDialog(this)
            .setAppDescription(R.string.app_name)
            .setButtonsColor(R.color.colorAccent)
            .setCancelButtonTitle(R.string.app_name);

        QreactRate rate = new QreactRate()
            .setDialog(dialog)
            .setAppTitle(R.string.app_name)
            .setLaunchesUntilPrompt(0)
            .setDaysUntilPrompt(0)
            .setTargetLevel(2)
            .setToken("qreact token")
            .prepare(this);

        rate.show();
    }
}
