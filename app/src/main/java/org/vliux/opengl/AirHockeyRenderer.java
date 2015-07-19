package org.vliux.opengl;

import android.content.Context;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import static android.opengl.GLES20.*;

/**
 * Created by vliux on 7/19/15.
 */
public class AirHockeyRenderer implements GLSurfaceView.Renderer {
    private static final int POSITION_COMPONENT_COUNT = 2;
    private static final int BYTES_PER_FLOAT = 4;
    private final FloatBuffer vertexData;

    private Context context;
    public AirHockeyRenderer(Context context) {
        this.context = context;
        float[] tableVerticesWithTriangles = new float[]{
                // triangle 1
                0f, 0f,
                9f, 14f,
                0, 14f,
                // triangle 2
                0f, 0f,
                9f, 0f,
                9f, 14f,
                // line 1
                0f, 7f,
                9f, 7f,
                // mallets
                4.5f, 2f,
                4.5f, 12f
        };
        vertexData = ByteBuffer.allocateDirect(tableVerticesWithTriangles.length * BYTES_PER_FLOAT)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        vertexData.put(tableVerticesWithTriangles);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        glClearColor(1.0f, 0.0f, 1.0f, 0.0f);
        String vertexShaderSource = TextResourceReader.readTextFileFromResource(context, R.raw.simple_vertext_shader);
        int vertextShader = ShaderHelper.compileVertexShader(vertexShaderSource);

        String fragmentShaderSource = TextResourceReader.readTextFileFromResource(context, R.raw.simple_fragment_shader);
        int fragmentShader = ShaderHelper.compileFragmentShader(fragmentShaderSource);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        glClear(GL_COLOR_BUFFER_BIT);
    }
}
