/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dropbox;

/**
 *
 * @author SAJAN
 */


import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxAuthFinish;
import com.dropbox.core.DbxAuthInfo;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxWebAuthNoRedirect;
import com.dropbox.core.json.JsonReader;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Locale;


public class Authorization {

    private static final String userLocale = Locale.getDefault().toString();
    private static final String clientIdentifier = "TextEditor/1.0";
    private final DbxAppInfo appInfo;
    private DbxWebAuthNoRedirect webAuth;
    private String authorizeUrl;
    private DbxAuthFinish authFinish;    

    /**
     *
     * @param APP_KEY
     * @param APP_SECRET
     */
    public Authorization(String APP_KEY, String APP_SECRET) {
        appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);        
        generateAuthURL();
    }

    /**
     *
     * @param appInfoFile
     * @throws JsonReader.FileLoadException
     */
    public Authorization(String appInfoFile) throws JsonReader.FileLoadException {
        // Read app info file (contains app key and app secret)        
        appInfo = DbxAppInfo.Reader.readFromFile(appInfoFile);
        generateAuthURL();
    }

    /**
     *
     * @param appInfoFile
     * @throws JsonReader.FileLoadException
     */
    public Authorization(File appInfoFile) throws JsonReader.FileLoadException {
        // Read app info file (contains app key and app secret)
        appInfo = DbxAppInfo.Reader.readFromFile(appInfoFile);
        generateAuthURL();
    }

    private void generateAuthURL() {
        // Run through Dropbox API authorization process        
        DbxRequestConfig requestConfig = new DbxRequestConfig(clientIdentifier, userLocale);
        webAuth = new DbxWebAuthNoRedirect(requestConfig, appInfo);
        authorizeUrl = webAuth.start();
        System.out.println("The Authorization URL is : "+authorizeUrl);        
    }

    /**
     *
     * @throws IOException
     */
    public void generateAuthCode() throws IOException {
        Desktop.getDesktop().browse(java.net.URI.create(authorizeUrl));
    }

    /**
     *
     * @param code
     * @throws DbxException
     */
    public void authorize(String code) throws DbxException {
        authFinish = webAuth.finish(code);
        System.out.println("Authorization complete.");
        System.out.println("- User ID: " + authFinish.userId);
        System.out.println("- Access Token: " + authFinish.accessToken);
    }

    /**
     *
     * @param authInfoFilePath
     * @throws IOException
     */
    public void saveAuthInfo(String authInfoFilePath) throws IOException {
        // Save auth information to output file.
        File authFile = new File(authInfoFilePath);
        saveAuthInfo(authFile);        
    }
    
    /**
     *
     * @param authInfoFile
     * @throws IOException
     */
    public void saveAuthInfo(File authInfoFile) throws IOException {
        // Save auth information to output file.
        DbxAuthInfo authInfo = new DbxAuthInfo(authFinish.accessToken, appInfo.host);
        DbxAuthInfo.Writer.writeToFile(authInfo, authInfoFile);
        System.out.println("Saved authorization information to \"" + authInfoFile + "\".");
    }
    
    /**
     *
     * @return gives <b>Authorization URL</b> for generating access code
     */
    public String getAuthURL(){
        return this.authorizeUrl;
    }
}
