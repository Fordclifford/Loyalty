package com.kcbgroup.loyalty.configuration;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.kcbgroup.loyalty.repository.DirectoryRepository;
import com.kcbgroup.loyalty.service.StorageService;

@Configuration
public class SSHConfiguration {
	
	private static final Logger LOG = LoggerFactory.getLogger(StorageService.class);
	private static final Integer PORT = 22;
	
	@Value("${dir.ip}")
    private String dirIp;
	
	@Value("${dir.user}")
    private String dirUser;
	
	@Value("${dir.password}")
    private String dirPassword;
	
	@Value("${dir.edwh}")
    private String dirEdwh;
	
	@Autowired
	private DirectoryRepository directoryRepository;
	
	private Path kcbInLocation() {
		return Paths.get(directoryRepository.findAll().get(0).getKcbInDir());
	}
	
	private Path kcbOutLocation() {
		return Paths.get(directoryRepository.findAll().get(0).getKcbOutDir());
	}
	
	public void getFromEDWH(String identifier) throws JSchException, SftpException {
        JSch jsch = new JSch();
        Session session = jsch.getSession(dirUser, dirIp, PORT);
        session.setPassword(dirPassword);
        session.setConfig("StrictHostKeyChecking", "no");
        
        LOG.info("Establishing server connection...");
        session.connect();
        LOG.info("Server connection established");
        
        LOG.info("Creating SFTP channel...");
        ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
        channelSftp.connect();
        LOG.info("SFTP channel created");
        
        channelSftp.cd(dirEdwh);
        Vector<?> fileList = channelSftp.ls(identifier.concat("*"));
        fileList.forEach(file -> {
        	LsEntry entry = (LsEntry) file;
        	String filename = entry.getFilename();
        	String destination = kcbInLocation().toString().concat("/").concat(filename);
        	
        	if (filename.contains(identifier)) {
        		try {
        			LOG.info("Copying {} to local server...", filename);
        			channelSftp.get(filename, destination);
        			channelSftp.rm(dirEdwh.concat("/").concat(filename));
        			LOG.info("Copy complete");
        		}
        		catch (SftpException e) {
    				LOG.error("context", e);
    			}
			}
        });
        
        LOG.info("Closing SFTP channel...");
        channelSftp.quit();
        LOG.info("SFTP channel closed");
        
        LOG.info("Disconnecting session...");
        session.disconnect();
        LOG.info("Session disconnected");
	}
	
	public void postToEDWH(String filename) throws JSchException, SftpException {
        JSch jsch = new JSch();
        Session session = jsch.getSession(dirUser, dirIp, PORT);
        session.setPassword(dirPassword);
        session.setConfig("StrictHostKeyChecking", "no");
        
        LOG.info("Establishing server connection...");
        session.connect();
        LOG.info("Server connection established");
        
        LOG.info("Creating SFTP channel...");
        ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
        channelSftp.connect();
        LOG.info("SFTP channel created");
        
    	if (filename.contains("BNKTXN")) {
            String source = kcbOutLocation().toString().concat("/").concat(filename);
            String destination = dirEdwh.concat("/").concat(filename);
            channelSftp.cd(dirEdwh);
            
    		try {
    			LOG.info("Copying {} to remote server...", filename);
    			channelSftp.put(source, destination);
    			LOG.info("Copy complete");
    		}
    		catch (SftpException e) {
				LOG.error("context", e);
			}
		}
        
        LOG.info("Closing SFTP channel...");
        channelSftp.quit();
        LOG.info("SFTP channel closed");
        
        LOG.info("Disconnecting session...");
        session.disconnect();
        LOG.info("Session disconnected");
	}

}
