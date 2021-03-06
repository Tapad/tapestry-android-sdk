package com.tapad.tapestry.deviceidentification;

import android.content.Context;
import android.net.wifi.WifiManager;
import com.tapad.tapestry.Logging;

import java.util.ArrayList;
import java.util.List;

/**
 * This class knows how to fetch and encode the WIFI MAC address
 * <p/>
 * Gets the WIFI MAC address hashed with MD5 and formatted as a 32 byte hexadecimal number.
 * Gets the WIFI MAC address hashed with SHA1 and formatted as a 40 byte hexadecimal number.
 * <p/>
 * Usage of this class requires adding the following to the AndroidManifest.xml
 * <p/>
 * <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
 */
public class WifiMac implements IdentifierSource {
    @Override
    public List<TypedIdentifier> get(Context context) {
    	List<TypedIdentifier> ids = new ArrayList<TypedIdentifier>();
        try {
	        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
	        String wifiMac = wifiManager.getConnectionInfo().getMacAddress();
	        ids.add(new TypedIdentifier(TypedIdentifier.TYPE_WIFI_MAC_MD5, DigestUtil.md5Hash(wifiMac)));
	        ids.add(new TypedIdentifier(TypedIdentifier.TYPE_WIFI_MAC_SHA1, DigestUtil.sha1Hash(wifiMac)));
        } catch (Exception e) {
            Logging.e("Error retrieving WIFI_MAC.", e);
        }
        return (ids);
    }
}
