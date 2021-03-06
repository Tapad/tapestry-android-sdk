package com.tapad.tapestry.deviceidentification;

import android.content.Context;
import android.provider.Settings;
import com.tapad.tapestry.Logging;

import java.util.ArrayList;
import java.util.List;

/**
 * This class knows how to fetch and encode values from Settings.Secure.ANDROID_ID
 *
 * Gets the Android system ID hashed with MD5 and formatted as a 32 byte hexadecimal number.
 * Gets the Android system ID hashed with SHA1 and formatted as a 40 byte hexadecimal number.
 */
public class AndroidId implements IdentifierSource {
    @Override
    public List<TypedIdentifier> get(Context context) {
        List<TypedIdentifier> ids = new ArrayList<TypedIdentifier>();
        try {
        	String androidId = Settings.System.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
            ids.add(new TypedIdentifier(TypedIdentifier.TYPE_ANDROID_ID_MD5, DigestUtil.md5Hash(androidId)));
            ids.add(new TypedIdentifier(TypedIdentifier.TYPE_ANDROID_ID_SHA1, DigestUtil.sha1Hash(androidId)));
        }
        catch (Exception e) {
            Logging.e("Error retrieving ANDROID_ID.", e);
        }
        return (ids);
    }
}
