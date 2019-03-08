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
import com.dropbox.core.DbxPath;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxWriteMode;
import com.dropbox.core.json.JsonReader;
import com.dropbox.core.util.IOUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;


public class FileUploader {

    private String authFile = "";
    private String dropboxPath = "/";
    private DbxAuthInfo authInfo;
    private DbxClient dbxClient;
    private String userLocale = Locale.getDefault().toString();
    private static final String clientIdentifier = "TextEditor/1.0";

    public FileUploader(String authFile) throws JsonReader.FileLoadException {
        this.authFile = authFile;
        loadAuthFile();
    }

    public FileUploader(String authFile, String dropboxPath) throws JsonReader.FileLoadException {
        this.authFile = authFile;
        this.dropboxPath = dropboxPath;
        loadAuthFile();
    }

    private void loadAuthFile() throws JsonReader.FileLoadException {
        authInfo = DbxAuthInfo.Reader.readFromFile(authFile);
        DbxRequestConfig requestConfig = new DbxRequestConfig(clientIdentifier, userLocale);
        dbxClient = new DbxClient(requestConfig, authInfo.accessToken, authInfo.host);
    }

    public void setPath(String dropboxPath) {
        if (dropboxPath.endsWith("/")) {
            if (dropboxPath.length() == 1) {
                this.dropboxPath = dropboxPath;
            } else {
                this.dropboxPath = dropboxPath.substring(0, dropboxPath.length() - 1);
            }
        } else {
            this.dropboxPath = dropboxPath;
        }

    }

    public boolean checkValidPath() {
        if (DbxPath.isValid(dropboxPath)) {
            System.out.println("Is Valid Path : " + dropboxPath);
        } else {
            System.out.println("Is InValid Path : " + dropboxPath);
        }
        return DbxPath.isValid(dropboxPath);
    }

    public String checkPathErr(String file_to_upload) {
        String pathError = DbxPath.findError(dropboxPath + "/" + file_to_upload);
        System.out.println("Upload Path : " + dropboxPath + "/" + file_to_upload);
        return pathError;
    }

    public void uploadFile(String localFilePath) throws FileNotFoundException, DbxException, IOException {
        File localFile = new File(localFilePath);
        uploadFile(localFile);
    }

    public boolean uploadFile(File localFile) throws FileNotFoundException, DbxException, IOException {
        String file_to_upload = localFile.getName();
        InputStream in = null;
        try {
            in = new FileInputStream(localFile);
            uploadFile(file_to_upload, in);
            return true;
        } finally {
            if (in != null) {
                IOUtil.closeInput(in);
            }
        }
    }

    public boolean uploadFile(String file_to_upload, InputStream in) throws DbxException, IOException {
        String pathError = DbxPath.findError(dropboxPath + "/" + file_to_upload);
        if (pathError != null) {
            System.err.println("Invalid <dropbox-path>: " + pathError);
            return false;
        }
        // Make the API call to upload the file.
        DbxEntry.File metadata = dbxClient.uploadFile(dropboxPath + "/" + file_to_upload, DbxWriteMode.add(), -1, in);
        System.out.print(metadata.toStringMultiline());
        return true;
    }
    
    public boolean updateFile(String file_to_upload, InputStream in) throws DbxException, IOException {
        String pathError = DbxPath.findError(dropboxPath + "/" + file_to_upload);
        if (pathError != null) {
            System.err.println("Invalid <dropbox-path>: " + pathError);
            return false;
        }
        // Make the API call to upload the file.
        List<DbxEntry.File> file_Rev = dbxClient.getRevisions(dropboxPath + "/" + file_to_upload);
        
        DbxEntry.File metadata = dbxClient.uploadFile(dropboxPath + "/" + file_to_upload, DbxWriteMode.update(file_Rev.get(file_Rev.size()-1).rev), -1, in);
        System.out.print(metadata.toStringMultiline());
        return true;
    }

}

