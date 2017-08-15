package states;

/**
 * Created by david on 7/14/2016.
 */
public interface AdsController {

    public void showBannerAd();
    public void hideBannerAd();
    public void showInterstitialAd(Runnable then);
    public boolean isWifiConnected();
    public boolean isDataConnected();
}
