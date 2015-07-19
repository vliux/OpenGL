package org.vliux.opengl;

import android.opengl.GLES20;
import android.util.Log;

import static android.opengl.GLES20.*;
import static org.vliux.opengl.Consts.*;

/**
 * Created by vliux on 7/19/15.
 */
public class ShaderHelper {
    public static int compileVertexShader(String shaderCode){
        return compileShader(GLES20.GL_VERTEX_SHADER, shaderCode);
    }

    public static int compileFragmentShader(String shaderCode){
        return compileShader(GLES20.GL_FRAGMENT_SHADER, shaderCode);
    }

    private static int compileShader(int type, String shaderCode){
        int shaderObjectId = glCreateShader(type);
        if(0 == shaderObjectId){
            Log.e(TAG, "unable to create new shader of type " + type);
            return 0;
        }

        glShaderSource(shaderObjectId, shaderCode);
        glCompileShader(shaderObjectId);

        Log.d(TAG, String.format("shader(type %d) compile info: \n%s", type, glGetShaderInfoLog(shaderObjectId)));

        int[] compileStatus = new int[1];
        glGetShaderiv(shaderObjectId, GL_COMPILE_STATUS, compileStatus, 0);
        if(compileStatus[0] == 0){
            glDeleteShader(shaderObjectId);
            Log.e(TAG, "failed to compile shader, shader object deleted from GL");
            return 0;
        }
        return shaderObjectId;
    }
}
