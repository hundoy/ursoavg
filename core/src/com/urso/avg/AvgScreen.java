/** 
* @Title: AvgScreen.java
* @Description: default screen of avg
* @author Hundoy - Zohar  
* @date 2016年3月26日 下午10:11:12
* @version V1.0  
*/
package com.urso.avg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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
	
	public AvgScreen(UrsoAvgGame game){
		this.game = game;
		camera = new OrthographicCamera();
        camera.setToOrtho(false, game.SCW, game.SCH);
        viewport = new FitViewport(game.SCW, game.SCH, camera);
        
		uiStage = new Stage();
		
		// graphics test temp
		picPath = "data/graphics/";
		Texture bg1Tex = new Texture(Gdx.files.local(picPath+"bg_wycj.jpg"));
		Texture bg2Tex = new Texture(Gdx.files.local(picPath+"bg_wyg.jpg"));
		Sprite bg1 = new Sprite(bg1Tex, 0, 0, game.SCW, game.SCH);
		Sprite bg2 = new Sprite(bg2Tex, 0, 0, game.SCW, game.SCH);
		spArr = new Array<Sprite>();
		spArr.add(bg1);
		spArr.add(bg2);
		
		TextureAtlas monAtlas = new TextureAtlas(Gdx.files.local(picPath+"monsters.atlas"));
		Sprite spAsura = monAtlas.createSprite("Asura");
		Sprite spPriest = monAtlas.createSprite("Priest");
		Sprite spCockatrice = monAtlas.createSprite("Cockatrice");
		spPriest.setPosition(500, 300);
		spArr.add(spAsura);
		spArr.add(spPriest);
		spArr.add(spCockatrice);
	}
	
	
	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		handleInput();
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		
		game.batch.begin();
		game.batch.disableBlending();
		spArr.get(0).draw(game.batch);
		game.batch.enableBlending();
		spArr.get(3).draw(game.batch);
		game.batch.end();
	}

	private void handleInput() {
		// TODO Auto-generated method stub
		
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
		for (Sprite sp : spArr){
			sp.getTexture().dispose();
		}
	}

}
