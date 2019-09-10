package com.hoffmannjozsef.feladat;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

/**
 *
 * @author Hoffmann József
 */
public class FTPUpload {

    public FTPUpload(MyConfig conf) {
        FTPClient ftpClient = new FTPClient();
        
        try {
            ftpClient.connect(conf.getFtpserver(), conf.getFtpport());
            ftpClient.login(conf.getFtpuser(), conf.getFtppass());
            ftpClient.enterLocalPassiveMode();

            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            //uploads first file using an InputStream
            File firstLocalFile = new File("report.json");

            String firstRemoteFile = "report.json";
            InputStream inputStream = new FileInputStream(firstLocalFile);

            System.out.println("Start uploading report.json");
            boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
            inputStream.close();
            if (done) {
                System.out.println("The report.json is uploaded successfully.");
            }


        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
