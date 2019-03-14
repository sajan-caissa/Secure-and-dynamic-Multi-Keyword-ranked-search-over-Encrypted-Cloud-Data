/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dropbox;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;

/**
 *
 * @author SAJAN
 */


import com.dropbox.core.DbxAuthInfo;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.json.JsonReader;
import com.dropbox.core.v1.DbxClientV1;

public class FileDownloader {
    
    private String authFile = "";
    private DbxAuthInfo authInfo;
    private DbxClientV1 dbxClient;
    private String userLocale = Locale.getDefault().toString();
    private static final String clientIdentifier = "TextEditor/1.0";
    
    public FileDownloader(String authFile) throws JsonReader.FileLoadException {
        this.authFile = authFile;
        loadAuthFile();
    }
    
    private void loadAuthFile() throws JsonReader.FileLoadException {
        authInfo = DbxAuthInfo.Reader.readFromFile(authFile);
        DbxRequestConfig requestConfig = new DbxRequestConfig(clientIdentifier, userLocale);
        dbxClient = new DbxClientV1(requestConfig, authInfo.getAccessToken(), authInfo.getHost());
    }
    
    public void downloadFile(String dropBoxPath, String localPath) throws IOException, DbxException{
        FileOutputStream outputStream = new FileOutputStream(localPath);
        try {
            com.dropbox.core.v1.DbxEntry.File downloadedFile = dbxClient.getFile(dropBoxPath, null,
                outputStream);
            System.out.println("Metadata: " + downloadedFile.toString());
        } finally {
            outputStream.close();
        }
    }
    
}

