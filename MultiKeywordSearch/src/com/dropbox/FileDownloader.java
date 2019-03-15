/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dropbox;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author SAJAN
 */


import com.dropbox.core.DbxAuthInfo;
import com.dropbox.core.DbxDownloader;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.json.JsonReader;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;

public class FileDownloader {
    
    private String authFile = "";
    private DbxAuthInfo authInfo;
    private DbxClientV2 dbxClient;
    
    public FileDownloader(String authFile) throws JsonReader.FileLoadException {
        this.authFile = authFile;
        loadAuthFile();
    }
    
    private void loadAuthFile() throws JsonReader.FileLoadException {
        authInfo = DbxAuthInfo.Reader.readFromFile(authFile);
        DbxRequestConfig requestConfig = new DbxRequestConfig("examples-account-info");
        dbxClient = new DbxClientV2(requestConfig, authInfo.getAccessToken(), authInfo.getHost());
    }
    
    public void downloadFile(String dropBoxPath, String localPath) throws IOException, DbxException{
    	DbxDownloader<FileMetadata> dbxDownloader = dbxClient.files().download(dropBoxPath);
        try {
        	FileOutputStream outputStream = new FileOutputStream(localPath);
        	dbxDownloader.download(outputStream);
        } finally {
        	dbxDownloader.close();
        }
    }
    
}

