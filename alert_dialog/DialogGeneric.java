package com.fdananda.gitalertdialog.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.fdananda.gitalertdialog.R;

public class DialogGeneric {

    public void OpenDialog(Activity activity, Drawable drawable, String title, String text,
                           String option1, Class activityOption1, String option2,
                           Class activityOption2, String option3, int color){

        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_generic);

        ImageView imageView = dialog.findViewById(R.id.imageViewDialog);
        imageView.setImageDrawable(drawable);

        TextView textViewTitle = dialog.findViewById(R.id.textViewTitle);
        textViewTitle.setText(title);

        TextView textViewText = dialog.findViewById(R.id.textViewText);
        textViewText.setText(text);

        LinearLayout linearLayout = dialog.findViewById(R.id.ll_dialog);
        linearLayout.setBackgroundColor(color);

        Button buttonOption1 = dialog.findViewById(R.id.buttonOption1);
        if (!option1.equals("")){
            buttonOption1.setText(option1);
            buttonOption1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, activityOption1);
                    activity.startActivity(intent);
                    activity.finish();
                    dialog.dismiss();
                }
            });
        }else {
            buttonOption1.setVisibility(View.GONE);
        }

        Button buttonOption2 = dialog.findViewById(R.id.buttonOption2);
        if (!option2.equals("")){
            buttonOption2.setText(option2);
            buttonOption2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, activityOption2);
                    activity.startActivity(intent);
                    activity.finish();
                    dialog.dismiss();
                }
            });
        }else {
            buttonOption2.setVisibility(View.GONE);
        }

        Button buttonOption3 = dialog.findViewById(R.id.buttonOption3);
        if (!option3.equals("")){
            buttonOption3.setText(option3);
            buttonOption3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }else {
            buttonOption3.setVisibility(View.GONE);
        }
        dialog.show();
    }
}
