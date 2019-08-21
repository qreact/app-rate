package net.qreact.app.apprate;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatDialog;
import androidx.core.content.ContextCompat;

public final class QreactDialog extends AppCompatDialog {

    private Context mContext;
    private TextView appTitle;
    private TextView appDescription;
    private RatingBar ratingBar;
    private TextView neverBtn, cancelBtn, rateBtn;
    private View layout;

    public QreactDialog(Context context) {
        super(context);
        mContext = context;

        layout = View.inflate(context, R.layout.qreact_popup_dialog, null);

        appTitle = layout.findViewById(R.id.popupTitleText);
        appDescription = layout.findViewById(R.id.popupDescriptionText);
        ratingBar = layout.findViewById(R.id.popupRatingBar);
        neverBtn = layout.findViewById(R.id.popupNeverBtn);
        cancelBtn = layout.findViewById(R.id.popupCancelBtn);
        rateBtn = layout.findViewById(R.id.popupRateBtn);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout);
    }

    @Override
    public void onBackPressed() {
        dismiss();
    }

    public QreactDialog setAppTitle(Integer title) {
        this.appTitle.setText(title != null ? title : R.string.app_name);
        return this;
    }

    public QreactDialog setAppDescription(Integer description) {
        this.appDescription.setText(description != null ? description : R.string.popupDescription);
        return this;
    }

    public QreactDialog setNeverButtonTitle(Integer title) {
        this.neverBtn.setText(title != null ? title : R.string.popupNeverBtn);
        return this;
    }

    public QreactDialog setCancelButtonTitle(Integer title) {
        this.cancelBtn.setText(title != null ? title : R.string.popupNeverCancel);
        return this;
    }

    public QreactDialog setRateButtonTitle(Integer title) {
        this.rateBtn.setText(title != null ? title : R.string.popupRateBtn);
        return this;
    }

    public QreactDialog setNeverButtonColor(Integer color) {
        this.neverBtn.setTextColor(ContextCompat.getColor(mContext, color != null ? color : R.color.defaultColor));
        return this;
    }

    public QreactDialog setCancelButtonColor(Integer color) {
        this.cancelBtn.setTextColor(ContextCompat.getColor(mContext, color != null ? color : R.color.defaultColor));
        return this;
    }

    public QreactDialog setrateButtonColor(Integer color) {
        this.rateBtn.setTextColor(ContextCompat.getColor(mContext, color != null ? color : R.color.defaultColor));
        return this;
    }

    public QreactDialog setButtonsColor(Integer color) {
        this.neverBtn.setTextColor(ContextCompat.getColor(mContext, color != null ? color : R.color.defaultColor));
        this.cancelBtn.setTextColor(ContextCompat.getColor(mContext, color != null ? color : R.color.defaultColor));
        this.rateBtn.setTextColor(ContextCompat.getColor(mContext, color != null ? color : R.color.defaultColor));
        return this;
    }

    public RatingBar getRatingBar() {
        return ratingBar;
    }

    public TextView getNeverBtn() {
        return neverBtn;
    }

    public TextView getCancelBtn() {
        return cancelBtn;
    }

    public TextView getRateBtn() {
        return rateBtn;
    }

    @Override
    public void onDetachedFromWindow() {
        mContext = null;
        super.onDetachedFromWindow();
    }
}
