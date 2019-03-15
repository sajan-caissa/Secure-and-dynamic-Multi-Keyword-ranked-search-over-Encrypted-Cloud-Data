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



import com.dropbox.core.DbxAuthInfo;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.json.JsonReader;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;

public class DirectoryListing {

    private String authFile = "";
    private DbxAuthInfo authInfo;
    private DbxClientV2 dbxClient;
    
    public DirectoryListing(String authFile) throws JsonReader.FileLoadException {
        this.authFile = authFile;
        loadAuthFile();
    }

    private void loadAuthFile() throws JsonReader.FileLoadException {
        authInfo = DbxAuthInfo.Reader.readFromFile(authFile);
        DbxRequestConfig requestConfig = new DbxRequestConfig("examples-account-info");
        dbxClient = new DbxClientV2(requestConfig, authInfo.getAccessToken(), authInfo.getHost());
    }
    
    
    public void listDirectory(String dropboxPath) throws DbxException{
        // Directory Listing    
    	ListFolderResult result = dbxClient.files().listFolder(dropboxPath);
    	
        System.out.println("Files in the " + dropboxPath + " path:");        
        while (true) {
			for (Metadata metadata : result.getEntries()) {
				System.out.println("  " + metadata.getName() + ": " + metadata.getPathLower());
			}
		}
    }
}
