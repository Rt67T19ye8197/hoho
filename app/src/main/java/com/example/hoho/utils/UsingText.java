package com.example.hoho.utils;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

public class UsingText {
    public static void setColoredText(TextView textView, String[] texts, int[] colors) {
        StringBuilder builder = new StringBuilder();
        for (String text : texts) {
            builder.append(text);
        }

        SpannableString spannable = new SpannableString(builder.toString());
        int currentPosition = 0;

        for (int i = 0; i < texts.length; i++) {
            int start = currentPosition;
            int end = currentPosition + texts[i].length();

            spannable.setSpan(
                    new ForegroundColorSpan(colors[i]),
                    start,
                    end,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            );

            currentPosition = end;
        }

        textView.setText(spannable);
    }
}
