package com.mall.util;

import com.mall.config.FTPConfig;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author ZZH
 * @date 2018/10/2 0002 16:30
 **/
@Getter
@Setter
@Slf4j
public class FTPUtil {


    private String ip;
    private int port = 21;
    private String user;
    private String pwd;
    private FTPClient ftpClient;

    private void init(FTPConfig ftpConfig){
        this.ip = ftpConfig.getIp();
        this.port = ftpConfig.getPort();
        this.user = ftpConfig.getUser();
        this.pwd = ftpConfig.getPassword();
    }
    public static boolean uploadFile(List<File> fileList , FTPConfig ftpConfig) throws IOException {
        FTPUtil ftpUtil = new FTPUtil();
        ftpUtil.init(ftpConfig);
        log.info("开始连接ftp服务器");
        boolean result = ftpUtil.uploadFile("img",fileList);
        log.info("开始连接ftp服务器,结束上传,上传结果:{}");
        return result;
    }


    private  boolean uploadFile(String remotePath,List<File> fileList) throws IOException {
        boolean uploaded = true;
        FileInputStream fis = null;
        //连接FTP服务器
        if(connectServer(this.ip,this.port,this.user,this.pwd)){
            try {
                ftpClient.changeWorkingDirectory(remotePath);
                ftpClient.setBufferSize(1024);
                ftpClient.setControlEncoding("UTF-8");
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                ftpClient.enterLocalPassiveMode();
                for(File fileItem : fileList){
                    fis = new FileInputStream(fileItem);
                    ftpClient.storeFile(fileItem.getName(),fis);
                }

            } catch (IOException e) {
                log.error("上传文件异常",e);
                uploaded = false;
                e.printStackTrace();
            } finally {
                fis.close();
                ftpClient.disconnect();
            }
        }
        return uploaded;
    }



    private boolean connectServer(String ip,int port,String user,String pwd){

        boolean isSuccess = false;
        ftpClient = new FTPClient();
        try {
            ftpClient.connect(ip);
            isSuccess = ftpClient.login(user,pwd);
        } catch (IOException e) {
            log.error("连接FTP服务器异常",e);
        }
        return isSuccess;
    }






}
