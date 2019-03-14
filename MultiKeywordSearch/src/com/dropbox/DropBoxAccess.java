/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dropbox;

import java.util.Locale;

/**
 *
 * @author SAJAN
 */

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v1.DbxClientV1;

public class DropBoxAccess {

    private final static String ACCESS_TOKEN = "Your dropbox access token";
    private final static String userLocale = Locale.getDefault().toString();
    private static DbxClientV1 dbxClient;

    public static DbxClientV1 getDbxClient() {
        DbxRequestConfig requestConfig = new DbxRequestConfig("examples-account-info", userLocale);
        if (dbxClient == null) {
            dbxClient = new DbxClientV1(requestConfig, ACCESS_TOKEN);
        }
        return dbxClient;
    }

}

