package states;

/**
 * Created by david on 7/14/2016.
 */
public interface DummyAdsController extends  AdsController{
    public void showBannerAd();
    public void hideBannerAd();
    public void showInterstitialAd(Runnable then);
    public boolean isWifiCOnnected();
}
