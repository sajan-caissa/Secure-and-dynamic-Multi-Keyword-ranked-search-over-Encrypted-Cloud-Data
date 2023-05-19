/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dropbox;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 *
 * @author SAJAN
 */


import com.dropbox.core.DbxAuthInfo;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.json.JsonReader;
import com.dropbox.core.util.IOUtil;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.DbxPathV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.UploadErrorException;
import com.dropbox.core.v2.files.WriteMode;


public class FileUploader {

    private String authFile = "";
    private String dropboxPath = "/privacyproject/";
    private DbxAuthInfo authInfo;
    private DbxClientV2 dbxClient;
    
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
        DbxRequestConfig requestConfig = new DbxRequestConfig("examples-account-info");
        dbxClient = new DbxClientV2(requestConfig, authInfo.getAccessToken(), authInfo.getHost());
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
        if (DbxPathV2.isValid(dropboxPath)) {
            System.out.println("Is Valid Path : " + dropboxPath);
        } else {
            System.out.println("Is InValid Path : " + dropboxPath);
        }
        return DbxPathV2.isValid(dropboxPath);
    }

    public String checkPathErr(String file_to_upload) {
        String pathError = DbxPathV2.findError(dropboxPath + "/" + file_to_upload);
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

    public boolean uploadFile(String file_to_upload, InputStream in) {
        String pathError = DbxPathV2.findError(dropboxPath + "/" + file_to_upload);
        if (pathError != null) {
            System.err.println("Invalid <dropbox-path>: " + pathError);
            return false;
        }
        // Make the API call to upload the file.
        
        try {
        	File localFile = new File(file_to_upload);
        	
        	FileMetadata metadata = dbxClient.files().uploadBuilder(dropboxPath + "/" + file_to_upload)
        			.withMode(WriteMode.ADD)
        			.withClientModified(new Date(localFile.lastModified()))
        			.uploadAndFinish(in);
        	
        	System.out.println(metadata.toStringMultiline());        	
		} catch (UploadErrorException e) {
			System.err.println("Error uploading to DropBox: " + e.getMessage());
		} catch (DbxException e) {
			System.err.println("Error uploading to DropBox: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("Error reading file: \"" + file_to_upload + "\": " +  e.getMessage());
		}
        return true;
    }
    
    public boolean updateFile(String file_to_upload, InputStream in) throws DbxException, IOException {
        String pathError = DbxPathV2.findError(dropboxPath + "/" + file_to_upload);
        if (pathError != null) {
            System.err.println("Invalid <dropbox-path>: " + pathError);
            return false;
        }
        // Make the API call to upload the file.        
        FileMetadata metadata = dbxClient.files().uploadBuilder(dropboxPath + "/" + file_to_upload)
    			.withMode(WriteMode.OVERWRITE)
    			.uploadAndFinish(in);
        System.out.print(metadata.toStringMultiline());
        return true;
    }

}

