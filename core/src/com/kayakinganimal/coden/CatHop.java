package com.kayakinganimal.coden;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import states.AdsController;
import states.DummyAdsController;
import states.GameStateManager;
import states.MenuState;

public class CatHop extends ApplicationAdapter {
	public static final int HEIGHT = 480;
	public static final int WIDTH = 850;
	public static boolean gameOver = false;//created to control ads
    public static boolean adToBeDisplayed = false;
    public static boolean wifi = false;
    public static int adCount = 0;
	public static int currentScore;
    private int whenToDisplayAd = 3;
	private Texture background;


	SpriteBatch batch;
	private GameStateManager gsm;

	private AdsController adsController;
	private DummyAdsController dummy;


	public CatHop(AdsController adsController){


		if(adsController != null) {
			this.adsController = adsController;
		}else{
			this.adsController = dummy;
		     }
		}


	
	@Override
	public void create () {
		background = new Texture("bg.png");
		if(adsController.isDataConnected()) {adsController.showBannerAd();}
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		gsm.push(new MenuState(gsm));
		Gdx.gl.glClearColor(0, 33, 110, 1);
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(background,0,0);
		batch.end();

        if(gameOver && adCount == whenToDisplayAd){

            adCount = 0;
            if (adsController.isWifiConnected()) {
				adToBeDisplayed = true;
                wifi = true;

                adsController.showInterstitialAd(new Runnable() {

                    @Override
                    public void run() {



                    }
                });
            } else {

            }

        }





		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);

	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
