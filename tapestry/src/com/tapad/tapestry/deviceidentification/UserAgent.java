/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tapad.tapestry.deviceidentification;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import com.tapad.tapestry.Logging;

public class UserAgent {
    /**
     * Gets the device's browser user agent without creating a WebView (which throws exceptions in some cases).
     */
    public static String getUserAgent(Context context) {
        try {
            StringBuffer buffer = new StringBuffer();
            // Add version
            final String version = Build.VERSION.RELEASE;
            if (version.length() > 0) {
                buffer.append(version);
            } else {
                // default to "1.0"
                buffer.append("1.0");
            }
            buffer.append("; ");
            // default to "en"
            buffer.append("en");
            buffer.append(";");
            // add the model for the release build
            if ("REL".equals(Build.VERSION.CODENAME)) {
                final String model = Build.MODEL;
                if (model.length() > 0) {
                    buffer.append(" ");
                    buffer.append(model);
                }
            }
            final String id = Build.ID;
            if (id.length() > 0) {
                buffer.append(" Build/");
                buffer.append(id);
            }
            int webUserAgentTargetContentResourceId = Resources.getSystem().getIdentifier("web_user_agent_target_content", "string", "android");
            String mobile = "";
            if (webUserAgentTargetContentResourceId > 0) {
                mobile = context.getResources().getText(webUserAgentTargetContentResourceId).toString();
            }
            int webUserAgentResourceId = Resources.getSystem().getIdentifier("web_user_agent", "string", "android");
            final String base = context.getResources().getText(webUserAgentResourceId).toString();
            final String userAgent = String.format(base, buffer, mobile);

            Logging.d("UserAgent successfully constructed: " + userAgent);
            return userAgent;
        }
        catch (Throwable t) {
            Logging.e("Exception caught while generating user agent: " + t.getMessage(), t);
            return "Android";
        }
    }
}