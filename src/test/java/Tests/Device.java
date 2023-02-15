package Tests;

public enum Device {

    PIXEL2("emulator-5554",
            "11",
            "Pixel2",
            "Android"),
    SAMSUNG_J5(
            "5a42eeaa",
            "7.1.1",
            "Galaxy J5(2016)",
            "Android");

    public String udid;
    public String version;
    public String deviceName;
    public String platformName;

    Device(String udid, String version, String deviceName, String platformName) {
        this.udid = udid;
        this.version = version;
        this.deviceName = deviceName;
        this.platformName = platformName;
    }

    @Override
    public String toString() {
        return this.name();
    }

}
