/** 
* @Title: AvgScreen.java
* @Description: default screen of avg
* @author Hundoy - Zohar  
* @date 2016年3月26日 下午10:11:12
* @version V1.0  
*/
package com.urso.avg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class AvgScreen implements Screen {
	
	private UrsoAvgGame game;
	private OrthographicCamera camera;
	private Viewport viewport;
	private Stage uiStage;
	
	private Array<Sprite> spArr;
	private String picPath;
	
	// crosefade test
	private enum GalleryState{
		PICTURE,
		TRANSITIONING
	}
	private FrameBuffer curFrameBuffer;
	private FrameBuffer nextFrameBuffer;
	private GalleryState state;
	private GalleryState nextState;
	private float time;
	
	// shader test
	private ShaderProgram[] shaders;
	private String[] shaderNames;
	private int currentShader;



	
	public AvgScreen(UrsoAvgGame game){
		this.game = game;
		camera = new OrthographicCamera();
        camera.setToOrtho(false, game.SCW, game.SCH);
        viewport = new FitViewport(game.SCW, game.SCH, camera);
        
		uiStage = new Stage();
		
		// graphics test temp
//		picPath = "data/graphics/";
//		Texture bg1Tex = new Texture(Gdx.files.local(picPath+"bg_wycj.jpg"));
//		Texture bg2Tex = new Texture(Gdx.files.local(picPath+"bg_wyg.jpg"));
//		Sprite bg1 = new Sprite(bg1Tex, 0, 0, game.SCW, game.SCH);
//		Sprite bg2 = new Sprite(bg2Tex, 0, 0, game.SCW, game.SCH);
//		spArr = new Array<Sprite>();
//		spArr.add(bg1);
//		spArr.add(bg2);
		
//		TextureAtlas monAtlas = new TextureAtlas(Gdx.files.local(picPath+"monsters.atlas"));
//		Sprite spAsura = monAtlas.createSprite("Asura");
//		Sprite spPriest = monAtlas.createSprite("Priest");
//		Sprite spCockatrice = monAtlas.createSprite("Cockatrice");
//		spPriest.setPosition(0, 0);
//		spArr.add(spAsura);
//		spArr.add(spPriest);
//		spArr.add(spCockatrice);
		
		// crossfade test
//		curFrameBuffer = new FrameBuffer(Format.RGBA8888, UrsoAvgGame.SCW, UrsoAvgGame.SCH, false);
//		nextFrameBuffer = new FrameBuffer(Format.RGBA8888, UrsoAvgGame.SCW, UrsoAvgGame.SCH, false);
//		time = 0;
//		state = GalleryState.PICTURE;	
//		nextState = GalleryState.PICTURE;
//		
//		// shader test
//		shaders = new ShaderProgram[4];
//		shaderNames = new String[4];
//		shaders[0] = null;
//		shaderNames[0] = "Null";
//		shaders[1] = new ShaderProgram(Gdx.files.internal("data/shaders/grayscale.vert"), 
//				Gdx.files.internal("data/shaders/grayscale.frag"));
//		shaderNames[1] = "GrayScale";
//		shaders[2] = new ShaderProgram(Gdx.files.internal("data/shaders/sepia.vert"),
//				   Gdx.files.internal("data/shaders/sepia.frag"));
//		shaderNames[2] = "Sepia";
//		shaders[3] = new ShaderProgram(Gdx.files.internal("data/shaders/inverted.vert"),
//						   Gdx.files.internal("data/shaders/inverted.frag"));
//		shaderNames[3] = "Inverted";
		
	}

	@Override
	public void show() {
		game.logic.start();
	}

	@Override
	public void render(float delta) {
		game.logic.update();
		handleInput();
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		
		paint();
	}

	/**
	 * paint pics
	 */
	private void paint() {
		game.layer.beforePaint();
		
		game.layer.paint(viewport);
		
		game.layer.afterPaint();
	}


	private void updateTrans() {
		float alpha = Math.min(1f, time/2f);
		game.batch.begin();
		game.batch.setColor(1f, 1f, 1f, 1f-alpha);
		game.batch.draw(curFrameBuffer.getColorBufferTexture(), 0, 0);
		game.batch.setColor(1f, 1f, 1f, alpha);
		game.batch.draw(nextFrameBuffer.getColorBufferTexture(), 0, 0);
		game.batch.end();
		if (time>=2){
			nextState = GalleryState.PICTURE;
			state = GalleryState.PICTURE;
			time = 0;
		}
	}


	private void updatePicture() {
		game.batch.begin();
		game.batch.disableBlending();
		spArr.get(0).draw(game.batch);
		game.batch.enableBlending();
		spArr.get(3).draw(game.batch);
		game.batch.end();
		
		if (nextState!=state){
			state = GalleryState.TRANSITIONING;
			time = 0;
			spArr.get(0).flip(false, true);
			spArr.get(3).flip(false, true);
			
			curFrameBuffer.bind();
			Gdx.gl.glClearColor(0, 0, 0, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
			game.batch.begin();
			game.batch.disableBlending();
			spArr.get(0).draw(game.batch);
			game.batch.enableBlending();
			spArr.get(3).draw(game.batch);
			game.batch.end();
			FrameBuffer.unbind();
			
			spArr.get(0).flip(false, true);
			spArr.get(3).flip(false, true);
			spArr.get(1).flip(false, true);
			spArr.get(4).flip(false, true);
			
			nextFrameBuffer.bind();
			Gdx.gl.glClearColor(0, 0, 0, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
			game.batch.begin();
			game.batch.disableBlending();
			spArr.get(1).draw(game.batch);
			game.batch.enableBlending();
			spArr.get(4).draw(game.batch);
			game.batch.end();
			FrameBuffer.unbind();
			
			spArr.get(1).flip(false, true);
			spArr.get(4).flip(false, true);
		}
	}


	private void handleInput() {
		if (Gdx.input.isKeyJustPressed(Input.Keys.S)){
			currentShader = (currentShader + 1) % shaders.length;
			game.batch.setShader(shaders[currentShader]);
			
			Gdx.app.log("ShaderSample", "Switching to shader " + shaderNames[currentShader]);
		}
		
		if (Gdx.input.isKeyJustPressed(Input.Keys.C)){
			nextState = GalleryState.TRANSITIONING;
		}
	}


	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {
		uiStage.dispose();
		
		// just for test
		for (Sprite sp : spArr){
			sp.getTexture().dispose();
		}
		curFrameBuffer.dispose();
		nextFrameBuffer.dispose();
		
		for (ShaderProgram s: shaders){
			if (s!=null) s.dispose();
		}
	}

}
