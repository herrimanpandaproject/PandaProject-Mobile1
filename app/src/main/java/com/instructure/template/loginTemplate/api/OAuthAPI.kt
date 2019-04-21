/*
 * Copyright (C) 2019 - present Instructure, Inc.
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */
package com.instructure.template.loginTemplate.api

import com.instructure.template.loginTemplate.api.apiHelpers.RestBuilder
import com.instructure.template.loginTemplate.api.apiHelpers.RestParams
import com.instructure.template.loginTemplate.api.apiHelpers.StatusCallback
import com.instructure.template.loginTemplate.api.models.AuthenticatedSession
import com.instructure.template.loginTemplate.api.models.OAuthToken
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.io.IOException


object OAuthAPI {

    internal interface OAuthInterface {
        @POST("/login/oauth2/token")
        fun getToken(@Query("client_id") clientId: String, @Query("client_secret") clientSecret: String, @Query("code") oAuthRequest: String, @Query(value = "redirect_uri", encoded = true) redirectURI: String): Call<OAuthToken>
    }
    fun getToken(adapter: RestBuilder, params: RestParams, clientID: String, clientSecret: String, oAuthRequest: String, callback: StatusCallback<OAuthToken>) {
        callback.addCall(adapter.build(OAuthInterface::class.java, params).getToken(clientID, clientSecret, oAuthRequest, "urn:ietf:wg:oauth:2.0:oob")).enqueue(callback)
    }

}

