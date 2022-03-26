package com.orugasumu.android.sproject;

import android.opengl.GLSurfaceView;
import android.content.Context;

//The Surface view where the RENDERER sits in
public class MyGLSurfaceView extends GLSurfaceView {

    private final MyGLRenderer renderer;

    public MyGLSurfaceView(Context context){
        super(context);

        setEGLContextClientVersion(2);
        renderer = new MyGLRenderer();
        setRenderer(renderer);
        /** use this if you are going to change drawing data
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
        **/
    }
}
