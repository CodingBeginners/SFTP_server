package com.codingbeginners.sftp;


import java.io.File;
import java.io.FileInputStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class SFTP_FileUploader {
	public  void fileUpload(String fileName) {
        String SFTPHOST = "xxx";
        int SFTPPORT = 44;
        String SFTPUSER = "abc";
        String SFTPPASS = "abc";
        String SFTPWORKINGDIR = "/x/x/CodingBeginners";

        Session session = null;
        Channel channel = null;
        ChannelSftp channelSftp = null;
        System.out.println("preparing the host information for SFTP.");
        try {
            JSch jsch = new JSch();
            session = jsch.getSession(SFTPUSER, SFTPHOST, SFTPPORT);
            session.setPassword(SFTPPASS);
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();
            System.out.println("Host connected.");
            channel = session.openChannel("sftp");
            channel.connect();
            System.out.println("sftp channel opened and connected.");
            channelSftp = (ChannelSftp) channel;
            channelSftp.cd(SFTPWORKINGDIR);
            File f = new File(fileName);
            channelSftp.put(new FileInputStream(f), f.getName());
        } catch (Exception ex) {
             System.out.println("Exception found while tranfer the response.");
        }
        finally{

            channelSftp.exit();
            System.out.println("SFTP Channel exited.");
            channel.disconnect();
            System.out.println("SFTP Channel disconnected.");
            session.disconnect();
            System.out.println("Host Session disconnected.");
        }
    }
	
	public static void main(String[] args) {
		SFTP_FileUploader sftp=new SFTP_FileUploader();
		sftp.fileUpload("C:\\Users\\nilesh_vispute\\Desktop\\CodingBeginners_SFTP.wsdl");
	}
}
