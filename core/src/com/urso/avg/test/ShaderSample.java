package com.urso.avg.test;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class ShaderSample extends GdxSample {
	private static final float WORLD_TO_SCREEN = 1;
	
	private static final float SCENE_WIDTH = 1280f;
	private static final float SCENE_HEIGHT = 720f;
	
	private static final int NUM_SHADERS = 4;
	
	private OrthographicCamera camera;
	private Viewport viewport;
	private SpriteBatch batch;
	private Texture background;
	
	private ShaderProgram shaders[];
	private String shaderNames[];
	private int currentShader;

	private Texture tx;
	private Sprite sp;
	
	@Override
	public void create() {		
		camera = new OrthographicCamera();
		viewport = new FitViewport(SCENE_WIDTH, SCENE_HEIGHT, camera);
		batch = new SpriteBatch();
		
		background = new Texture(Gdx.files.local("data/graphics/bg_rest.jpg"));

		tx = new Texture(Gdx.files.local("data/graphics/lh_gg.png"));
		sp = new Sprite(tx);
		sp.setPosition(0,0);
		
		shaders = new ShaderProgram[NUM_SHADERS];
		shaderNames = new String[NUM_SHADERS];
		currentShader = 0;
		
		shaders[0] = null;
		shaderNames[0] = "Null";
		shaders[1] = new ShaderProgram(Gdx.files.local("data/shader/grayscale.vert"),
									   Gdx.files.local("data/shader/grayscale.frag"));
		shaderNames[1] = "Grayscale";
		shaders[2] = new ShaderProgram(Gdx.files.local("data/shader/sepia.vert"),
									   Gdx.files.local("data/shader/sepia.frag"));
		shaderNames[2] = "Sepia";
		shaders[3] = new ShaderProgram(Gdx.files.local("data/shader/inverted.vert"),
									   Gdx.files.local("data/shader/inverted.frag"));
		shaderNames[3] = "Inverted";
		
		camera.position.set(SCENE_WIDTH * 0.5f, SCENE_HEIGHT * 0.5f, 0.0f);
		
		Gdx.input.setInputProcessor(this);
		
		for (ShaderProgram shader : shaders) {
			if (shader != null && !shader.isCompiled()) {
				Gdx.app.error("ShaderSample: ", shader.getLog());
			}
		}
		
		Gdx.app.log("ShaderSample", "Switching to shader " + shaderNames[currentShader]);
	}

	@Override
	public void dispose() {
		batch.dispose();
		background.dispose();
		
		for (ShaderProgram shader : shaders) {
			if (shader != null) {
				shader.dispose();
			}
		}

		sp = null;
		tx.dispose();
	}

	@Override
	public void render() {		
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
		int width = background.getWidth();
		int height = background.getHeight();

        batch.setColor(1,1,1,1);
		batch.draw(background,
				   0.0f, 0.0f,
				   0.0f, 0.0f,
				   width, height,
				   WORLD_TO_SCREEN, WORLD_TO_SCREEN,
				   0.0f,
				   0, 0,
				   width, height,
				   false, false);

		sp.draw(batch);

		batch.end();
	}
	
	@Override
	public boolean touchDown (int screenX, int screenY, int pointer, int button) {
		currentShader = (currentShader + 1) % shaders.length;
		batch.setShader(shaders[currentShader]);
		
		Gdx.app.log("ShaderSample", "Switching to shader " + shaderNames[currentShader]);
		
		return true;
	}
	
	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
	}
}
