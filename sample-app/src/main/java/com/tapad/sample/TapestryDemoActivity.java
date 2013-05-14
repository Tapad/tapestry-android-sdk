package com.tapad.sample;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.tapad.tapestry.Logging;
import com.tapad.tapestry.TapestryClient;
import com.tapad.tapestry.TapestryRequest;
import com.tapad.tapestry.TapestryTracking;

public class TapestryDemoActivity extends Activity {
    TapestryClient client = new TapestryClient(this, "1");
    TapestryRequest request = new TapestryRequest();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        TextView requestText = ((TextView) findViewById(R.id.request));
        requestText.setText(client.addParameters(request).toDecodedQuery());

        Spinner spinner = (Spinner) findViewById(R.id.parameters);
        ((ArrayAdapter)spinner.getAdapter()).add("asdf");
// Specify the layout to use when the list of choices appears
// Apply the adapter to the spinner

//        Button custom = (Button) findViewById(R.id.custom_event);
//        custom.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                Tracking.get().onEvent("custom1");
//            }
//        });
//
//        Button adMarkup = (Button) findViewById(R.id.pull_ad_markup);
//        adMarkup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(final View view) {
//                startActivity(new Intent(MainActivity.this, ManualMarkupActivity.class));
//            }
//        });
//
//        Button managedView = (Button) findViewById(R.id.managed_ad_view);
//        managedView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, AdViewActivity.class));
//            }
//        });
//
//        Button optInOut = (Button) findViewById(R.id.opt_in_out);
//        optInOut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (Tracking.isOptedOut())
//                    Tracking.optIn(MainActivity.this);
//                else
//                    Tracking.optOut(MainActivity.this);
//
//                updateDeviceId();
//            }
//        });
    }
}
