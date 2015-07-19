package org.vliux.opengl;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by vliux on 7/19/15.
 */
public class TextResourceReader {
    public static String readTextFileFromResource(Context context, int rawResId){
        StringBuilder body = new StringBuilder();
        try{
            InputStream inputStream = context.getResources().openRawResource(rawResId);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String nextLine;
            while((nextLine = bufferedReader.readLine()) != null){
                body.append(nextLine);
                body.append("\n");
            }
        }catch (IOException e){
            throw new RuntimeException("Could not open raw resource: " + rawResId, e);
        }
        return body.toString();
    }
}
