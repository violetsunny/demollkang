package com.github.demo.llkang.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import com.amazonaws.AmazonClientException;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.S3ClientOptions;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;

/**
 * 文件上传工具类
 * 
 * @author lwf47686
 * @version $Id: UpLoad.java, v 0.1 2017年4月24日 下午2:01:34 lwf47686 Exp $
 */
public class UpLoadUtils {
	
	private static AmazonS3 s3client;
	
	private static String BUCKET = "";
	private static String ACCESS_KEY;
    private static String ACCESS_SECRET_KEY;
    // 线下END_POINT为 http://10.14.91.113:7480;
    // 线上END_POINT为 http://tcstore1.17usoft.com, 无需配置，只有内网才能访问此域名。
    private static String END_POINT;
	
	static {
        InputStream inputStream = UpLoadUtils.class.getClassLoader().getResourceAsStream("ceph.properties");
        Properties ps = new Properties();
        try {
            ps.load(inputStream);

            BUCKET = ps.getProperty("bucket");
            ACCESS_KEY = ps.getProperty("access_key");
            ACCESS_SECRET_KEY = ps.getProperty("access_secret_key");
            END_POINT = ps.getProperty("end_point");
        } catch (IOException e) {
            e.printStackTrace();
        }

        AWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY, ACCESS_SECRET_KEY);
        ClientConfiguration clientCfg = new ClientConfiguration();
        clientCfg.setProtocol(Protocol.HTTP);

        s3client = new AmazonS3Client(credentials, clientCfg);
        s3client.setEndpoint(END_POINT);
        s3client.setS3ClientOptions(new S3ClientOptions().withPathStyleAccess(true));
    }
	
	/**
     * 上传文件
     *
     * @param key
     * @param input
     * @return
     * @throws IOException
     */
	public static boolean putObjByStream(String key, InputStream input) throws IOException {
    	
    	ObjectMetadata meta = new ObjectMetadata();
        meta.setContentLength(input.available());
        meta.setContentType("image/jpg");
        try {
        	s3client.putObject(BUCKET, key, input, meta);
        } catch (AmazonClientException clientException) {
            return false;
        }
        return true;
    }
	
	/**
     * 获取以上传文件的url地址
     *
     * @param key
     * @return
     */
    public static String getURL(String key) {
        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(BUCKET, key);
        Date expirationDate = null;
        try {
            expirationDate = new SimpleDateFormat("yyyy-MM-dd").parse("2099-12-31");
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setExpiration(expirationDate);
        URL url = s3client.generatePresignedUrl(request);

        return url == null ? "Null URL" : url.toString();
    }

}
