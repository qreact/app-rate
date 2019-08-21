package net.qreact.app.apprate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.RatingBar;
import androidx.annotation.NonNull;

public final class QreactRate {

    private Integer appTitle = R.string.popupTitle;
    private String qreactToken = "";

    private int daysUntilPrompt = 0; // minimum number of days
    private int launchesUntilPrompt = 0; // minimum number of launches
    private int targetLevel = 0;
    private int ratedIndex = 0;
    private Boolean neverShow = false;

    private QreactDialog dialog = null;
    private Context mContext = null;

    private SharedPreferences prefs;

    public QreactRate prepare(@NonNull Context context) {
        mContext = context;
        prefs = context.getSharedPreferences("qreact", 0);

        if (prefs.getBoolean("qreactNeverAgain", false)) {
            neverShow = true;
            return this;
        }

        SharedPreferences.Editor editor = prefs.edit();

        // increment launch counter
        long launchCt = prefs.getLong("qreactLaunchCt", 0) + 1;
        editor.putLong("qreactLaunchCt", launchCt);

        // get date first launch
        long dateFirstLaunch = prefs.getLong("qreactDateFirst", 0);
        if (dateFirstLaunch == 0) {
            dateFirstLaunch = System.currentTimeMillis();
            editor.putLong("qreactDateFirst", dateFirstLaunch);
        }

        if (launchCt >= launchesUntilPrompt) {
            if (System.currentTimeMillis() >= (dateFirstLaunch + (daysUntilPrompt * 24
                    * 60
                    * 60
                    * 1000))) {
                if (dialog == null) {
                    dialog = new QreactDialog(context);
                    dialog.setAppTitle(appTitle);
                }
                prepareDialog();
            }
        }

        editor.apply();
        return this;
    }

    private void prepareDialog() {
        final SharedPreferences.Editor editor = prefs.edit();

        dialog.getRatingBar().setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                ratedIndex = (int)v;
            }
        });

        dialog.getNeverBtn().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putBoolean("qreactNeverAgain", true);
                dialog.dismiss();
            }
        });

        dialog.getCancelBtn().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.getRateBtn().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ratedIndex <= targetLevel) {
                    onQreact();
                } else {
                    onPlayStore();
                }
            }
        });

        editor.apply();
    }

    private void onPlayStore() {
        if(mContext == null) return;
        String packageName = BuildConfig.APPLICATION_ID;

        try {
            String marketPrefix = "market://details?id=";
            mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(marketPrefix + packageName)));
        } catch (android.content.ActivityNotFoundException e) {
            String playStorePrefix = "https://play.google.com/store/apps/details?id=";
            mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(playStorePrefix + packageName)));
        }
    }

    private void onQreact() {
        try {
            String qreactUrl = "https://www.qreact.net/public/form?token=";
            mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(qreactUrl + qreactToken)));
        } catch (android.content.ActivityNotFoundException e) {
            Log.d("ActivityFoundException", e.toString());
        }
    }

    public void show() {
        if (!neverShow)
            dialog.show();
    }

    public QreactRate setDialog(@NonNull QreactDialog dialog) {
        this.dialog = dialog;
        return this;
    }

    public QreactRate setAppTitle(@NonNull Integer appTitle) {
        this.appTitle = appTitle;
        return this;
    }

    public QreactRate setDaysUntilPrompt(int day) {
        this.daysUntilPrompt = day > 0 ? day : 0;
        return this;
    }

    public QreactRate setLaunchesUntilPrompt(int day) {
        this.launchesUntilPrompt = day > 0 ? day : 0;
        return this;
    }

    public QreactRate setToken(String token) {
        this.qreactToken = token != null ? token : "";
        return this;
    }

    public QreactRate setTargetLevel(int targetLevel) {
        if (targetLevel >= 1 && targetLevel <= 5) {
            this.targetLevel = targetLevel;
        }
        return this;
    }
}
