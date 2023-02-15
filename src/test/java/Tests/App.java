package Tests;

public enum App {

    APIDEMO("com.touchboarder.android.api.demos","com.example.android.apis.ApiDemos", ""),
    CALCULATOR("com.sec.android.app.popupcalculator","Calculator", "")
    ;

    public String appPackage;
    public String appActivity;
    public String apk;


    App(String appPackage, String appActivity, String apk) {
        this.appPackage = appPackage;
        this.appActivity = appActivity;
        this.apk = apk;
    }

}
