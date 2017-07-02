package com.urso.avg.test;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by zohar on 2016/11/26.
 */
public class CubeTets extends ApplicationAdapter {
    public PerspectiveCamera cam;

    public Model model;
    public ModelInstance instance;
    public ModelBatch mbatch;
    public Environment env;

    @Override
    public void create() {
        cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(10f,10f,10f);
        cam.lookAt(0,0,0);
        cam.near = 1f;
        cam.far = 300f;
        cam.update();

        ModelBuilder modelBuilder = new ModelBuilder();
        Material mat = new Material(ColorAttribute.createDiffuse(Color.BROWN));
        model = modelBuilder.createBox(5,5,5,mat,
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
        instance = new ModelInstance(model);

        mbatch = new ModelBatch();
        env = new Environment();
        env.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        env.add(new DirectionalLight().set(0.8f,0.8f,0.8f,-1f,-0.8f,-0.2f));

    }

    @Override
    public void render() {
        movement();
        rotate();
        scale();
        updateTransformation();

        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        mbatch.begin(cam);
        mbatch.render(instance, env);
        mbatch.end();
    }

    @Override
    public void dispose() {
        model.dispose();
    }

    Vector3 position = new Vector3();
    private void movement(){
        instance.transform.getTranslation(position);
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            position.x+=Gdx.graphics.getDeltaTime();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            position.z+=Gdx.graphics.getDeltaTime();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            position.z-=Gdx.graphics.getDeltaTime();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            position.x-=Gdx.graphics.getDeltaTime();
        }
//        instance.transform.setTranslation(position);
    }

    float rotation;
    private void rotate() {
//        if (Gdx.input.isKeyPressed(Input.Keys.NUM_1))
//            instance.transform.rotate(Vector3.X,
//                    Gdx.graphics.getDeltaTime() * 100);
//        if (Gdx.input.isKeyPressed(Input.Keys.NUM_2))
//            instance.transform.rotate(Vector3.Y,
//                    Gdx.graphics.getDeltaTime() * 100);
//        if (Gdx.input.isKeyPressed(Input.Keys.NUM_3))
//            instance.transform.rotate(Vector3.Z,
//                    Gdx.graphics.getDeltaTime() * 100);
        rotation = (rotation + Gdx.graphics.getDeltaTime() * 100) %
                360;
//        instance.transform.setFromEulerAngles(0, 0, rotation)
//                .trn(position.x, position.y, position.z); ;
    }

    boolean increment = true;
    float scale = 1;
    void scale() {
        scale = (scale + Gdx.graphics.getDeltaTime());
    }

    private void updateTransformation(){
        instance.transform.setFromEulerAngles(0, 0,
                rotation).trn(position.x, position.y,
                position.z).scale(scale,scale,scale);
    }


}
