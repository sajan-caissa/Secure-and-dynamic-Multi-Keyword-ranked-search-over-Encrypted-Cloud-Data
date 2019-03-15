/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dropbox;

import java.io.FileNotFoundException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Scanner;

/**
 *
 * @author SAJAN
 */

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;

public class DropBoxAccess {

    private static DbxClientV2 dbxClient;

    public static DbxClientV2 getDbxClient() throws FileNotFoundException {
    	Path path = FileSystems.getDefault().getPath("dropbox_access_token.txt").toAbsolutePath();
    	Scanner scan = new Scanner(path.toFile());
    	String tokenString = scan.useDelimiter("\\Z").next();
    	scan.close();
    	
        DbxRequestConfig requestConfig = new DbxRequestConfig("examples-account-info");
        if (dbxClient == null) {
            dbxClient = new DbxClientV2(requestConfig, tokenString);
        }
        return dbxClient;
    }

}

