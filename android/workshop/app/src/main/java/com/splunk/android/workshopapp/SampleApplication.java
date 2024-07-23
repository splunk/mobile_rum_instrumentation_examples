/*
 * Copyright Splunk Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.splunk.android.workshopapp;

import android.app.Application;

import com.splunk.rum.SplunkRum;
import com.splunk.rum.StandardAttributes;

import io.opentelemetry.api.common.AttributeKey;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.trace.Span;


public class SampleApplication extends Application {

    private final String realm = "us1";
    private final String rumAccessToken = "O8xtu5zI6W6YUNuh7a86fA";

    @Override
    public void onCreate() {
        super.onCreate();

        SplunkRum.builder()
                .setApplicationName("trn_android_app")
                .setDeploymentEnvironment("trn_env")
                .setRealm(realm)
                .setRumAccessToken(rumAccessToken)
                .enableDebug()
                .build(this);

        // Add global metadata to every span
        SplunkRum.getInstance().setGlobalAttribute(AttributeKey.stringKey("user.role"), "premium");

        // Add a custom RUM event
        SplunkRum.getInstance().addRumEvent("Application Started", Attributes.builder().build());

        // Add a custom workflow
        Span workinghard = SplunkRum.getInstance().startWorkflow("Main thread working hard");

        try {
            Thread.sleep(1000);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }

        workinghard.end();


    }


}
