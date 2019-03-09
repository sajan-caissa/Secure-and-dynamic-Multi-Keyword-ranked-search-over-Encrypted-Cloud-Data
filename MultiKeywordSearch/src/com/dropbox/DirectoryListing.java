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
import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.json.JsonReader;
import java.util.List;
import java.util.Locale;

public class DirectoryListing {

    private String authFile = "";
    private DbxAuthInfo authInfo;
    private DbxClient dbxClient;
    private String userLocale = Locale.getDefault().toString();
    private static final String clientIdentifier = "TextEditor/1.0";

    public DirectoryListing(String authFile) throws JsonReader.FileLoadException {
        this.authFile = authFile;
        loadAuthFile();
    }

    private void loadAuthFile() throws JsonReader.FileLoadException {
        authInfo = DbxAuthInfo.Reader.readFromFile(authFile);
        DbxRequestConfig requestConfig = new DbxRequestConfig(clientIdentifier, userLocale);
        dbxClient = new DbxClient(requestConfig, authInfo.accessToken, authInfo.host);
    }
    
    public List<DbxEntry> getChildrens(String dropboxPath) throws DbxException{
        // Directory Listing
        DbxEntry.WithChildren listing = dbxClient.getMetadataWithChildren(dropboxPath);
        return listing.children;
    }
    
    public void listDirectory(String dropboxPath) throws DbxException{
        // Directory Listing        
        System.out.println("Files in the " + dropboxPath + " path:");        
        for (DbxEntry child : getChildrens(dropboxPath)) {
            System.out.println("  " + child.name + ": " + child.toString());
        }
    }
}
