package com.gold.InterviewTest.util;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class Util {

    public Bitmap GetBitmapMarker(Context mContext, int resourceId,  String mText)
    {
        try
        {
            Resources resources = mContext.getResources();
            float scale = resources.getDisplayMetrics().density;
            Bitmap bitmapimage = BitmapFactory.decodeResource(resources, resourceId);
            int height = 200;
            int width = 200;
            Bitmap bitmap = Bitmap.createScaledBitmap(bitmapimage, width, height, false);
            android.graphics.Bitmap.Config bitmapConfig = bitmap.getConfig();

            // set default bitmap config if none
            if(bitmapConfig == null)
                bitmapConfig = android.graphics.Bitmap.Config.ARGB_8888;

            bitmap = bitmap.copy(bitmapConfig, true);



            Typeface tf = Typeface.create("Helvetica", Typeface.BOLD);

            Paint paint = new Paint();

            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.WHITE);
            paint.setTypeface(tf);
            paint.setTextAlign(Paint.Align.CENTER);
            paint.setTextSize(convertToPixels(mContext, 16));

            Rect textRect = new Rect();
            paint.getTextBounds(mText, 0, mText.length(), textRect);

            Canvas canvas = new Canvas(bitmap);

            //If the text is bigger than the canvas , reduce the font size
            if(textRect.width() >= (canvas.getWidth() - 4))     //the padding on either sides is considered as 4, so as to appropriately fit in the text
                paint.setTextSize(convertToPixels(mContext, 7));        //Scaling needs to be used for different dpi's

            //Calculate the positions
            int xPos = (canvas.getWidth() / 2) - 2;     //-2 is for regulating the x position offset

            //"- ((paint.descent() + paint.ascent()) / 2)" is the distance from the baseline to the center.
            int yPos = (int) ((canvas.getHeight() / 2) - ((paint.descent() + paint.ascent()) / 2)) ;

            canvas.drawText(mText, xPos, yPos, paint);

            return bitmap;

        }
        catch (Exception e)
        {
            return null;
        }
    }
    public static int convertToPixels(Context context, int nDP)
    {
        final float conversionScale = context.getResources().getDisplayMetrics().density;

        return (int) ((nDP * conversionScale) + 0.5f) ;

    }
}
