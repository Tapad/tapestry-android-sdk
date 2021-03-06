## Quick Setup
1. Download the [Tapestry SDK](https://github.com/Tapad/tapestry-android-sdk/releases) (tapestry-android-sdk-X-X-X.zip) from the releases page ([JavaDoc here](https://tapad.github.com/tapestry-android-sdk/docs)).

2. Copy `tapestry-android-sdk-X-X-X.jar` into the `libs` folder of your Android project.

3. Add your Tapestry Partner Id and permissions into the `AndroidManifest.xml` of your application:
```xml
    <!-- Optional permissions for detecting phone's connectivity and caching requests when offline -->
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />

    <!-- Permissions for accessing the phone's ids (at least one is required) -->
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />

    <application>
        ...
      <!-- Your Tapestry partner id will be provided by Tapad -->
        <meta-data android:name="tapad.PARTNER_ID" android:value="1" />
    </application>
```

## Using Tapestry
Intialize the TapestryService prior to use, preferably in your `Application` or in every `Activity` that uses it:
```java
public class MyApplication extends Application {
    public void onCreate() {
        TapestryService.initialize(this);
    }
}
```

Now you can get cross-device data out of Tapestry:
```java
TapestryService.send(new TapestryCallback() {
    public void receive(TapestryResponse response, Exception exception, long millisSinceInvocation) {
        if (response.getData("color").contains("blue"))
          // user has a preference for blue
        if (response.getAudiences().contains("buying-car"))
          // user is in a buying car audience
        if (response.getPlatforms().contains("XBox"))
          // user has an XBox
    }
});
```

Or set the Tapestry data of the current device:
```java
TapestryService.send(new TapestryRequest()
    .addAudiences("buying-car")
    .addData("color", "blue")
    .userIds("uid", "bob123")
);
```

Or do both at once:
```java
TapestryRequest = new TapestryRequest()
    .addData("color", "blue")
    .strength(1)
    .depth(2);

TapestryService.send(request, new TapestryCallback()  {
    public void receive(TapestryResponse response, Exception exception, long millisSinceInvocation) {
        // do stuff
    }
);
```

## Using Tapestry Google Analytics Plugin

### v3

Use this version of the Tapestry plugin if your android app currently uses v3 of the Google Analytics SDK for Android or if this is your first time using the Google Analytics SDK for Android. Users will need to create an App view for their property if they do not have one already.

The Tapestry plugin uses up to six custom dimensions. Custom dimensions are a Universal Analytics feature which are available for any property with an App view or a Universal Analytics Web view. Below is an example of a custom dimension configuration for the Tapestry plugin. These will need to be configured for the property in the Google Analytics Admin page.

Custom dimension configuration:

Custom Dimension Name                  | Index | Scope   | Status
-------------------------------------- | ----- | ------- | ------
Tapestry: Visited Platforms            | 1     | Session | Active
Tapestry: Platforms Associated         | 2     | Session | Active
Tapestry: Platform Types               | 3     | Session | Active
Tapestry: First Visited Platform       | 4     | Session | Active
Tapestry: Most Recent Visited Platform | 5     | Session | Active
Tapestry: Most Often Visited Platform  | 6     | Session | Active

To start using the plugin, first complete the instructions in [Quick Setup](## Quick Setup).

Next, add your Google Analytics property id to the `analytics.xml` located in the `res/values/` directory:
```xml
    <resources xmlns:tools="http://schemas.android.com/tools"
           tools:ignore="TypographyDashes">
        <!--Replace placeholder ID with your tracking ID-->
        <string name="ga_trackingId">UA-XXXXXXXX-X</string>
    ...
    </resources>
```

Note that the above `analytics.xml` has options `xmlns:tools="http://schemas.android.com/tools"` and `tools:ignore="TypographyDashes"` in the resources tag. Some users experience an issue with Android lint that causes the Google Analytics property id to be malformed with encoded dashes, so it is recommended to include these options in the resources tag of analytics.xml.

The `AndroidManifest.xml` will need to be modified to provide the Tapestry partner id and to ensure that the Google Analytics dimension indices match those in your Google Analytics configuration. By default the indices are assigned to dimensions 1-6. An analytics variable that is not used should be omitted or given a value of 0.

```xml
    <application>
        ...
        <!-- Your Tapestry partner id will be provided by Tapad -->
        <meta-data android:name="tapad.PARTNER_ID" android:value="1"/>

        <!-- Google Analytics custom dimension indices for Tapestry data -->
        <meta-data android:name="ga.VISITED_PLATFORMS_DIM_IDX" android:value="1" />
        <meta-data android:name="ga.PLATFORMS_ASSOC_DIM_IDX" android:value="2" />
        <meta-data android:name="ga.PLATFORM_TYPES_DIM_IDX" android:value="3" />
        <meta-data android:name="ga.FIRST_VISITED_DIM_IDX" android:value="4" />
        <meta-data android:name="ga.MOST_RECENT_DIM_IDX" android:value="5" />
        <meta-data android:name="ga.MOST_OFTEN_DIM_IDX" android:value="6" />
    </application>
```

Next, ensure that the TapestryService has been initialized, as detailed in [Using Tapestry](## Using Tapestry).

See `GoogleAnalyticsActivity.java` for an example Activity that utilizes the plugin. The code provided in this example should be added to the Activity that you would like to use to send analytics. The custom dimension indices in the sample code may need to be adjusted to match the appropriate indices in the property configuration.

### v2

We do not currently support use of the Tapestry Analytics plugin with Google Analytics SDK for Android v2.

### v1 (Legacy)

Use this version of the Tapestry plugin if your android app currently uses v1 of the Google Analytics SDK for Android. The Tapestry plugin uses up to six custom variable slots in Google Analytics. To take advantage of all six analytics, users will either need a Premium Google Analytics account or a Universal property implemented with v3 of the SDK.

To start using the plugin, first complete the instructions in [Quick Setup](## Quick Setup).

Next, add your Google Analytics property id to the `AndroidManifest.xml`:
```xml
    <application>
    ...
    <!-- Your Tapestry partner id will be provided by Tapad -->
    <meta-data android:name="tapad.PARTNER_ID" android:value="1"/>
        
    <!-- Place your Google Analytics property id here -->
    <meta-data android:name="ga.PROPERTY_ID" android:value="UA-XXXXXXXX-X"/>
    </application>
```
Ensure that the TapestryService has been initialized, as detailed in [Using Tapestry](## Using Tapestry).

See `GoogleAnalyticsActivity.java` for an example Activity that utilizes the plugin. The code provided in this example should be added to the Activity that you would like to use to send analytics.

Note: This example does NOT use EasyTracker. If your analytics code has been implemented with EasyTracker you will need to make the appropriate adjustments to the provided code.

## License

Copyright (c) 2012-2013 Tapad, INC.

Published under The MIT License, see LICENSE

