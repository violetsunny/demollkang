/**
 * LY.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.github.demo.llkang.http;

import com.alibaba.fastjson.JSON;
import com.github.demo.llkang.error.DemollkangBizErrorFactory;
import com.github.demo.llkang.utils.SignRuleUtil;
import com.google.gson.Gson;
import com.github.demo.llkang.exception.DemollkangException;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 * 客户端接口请求
 * @author jt39175
 * @version $Id: IBEPlusHttpClient.java, v 0.1 2016年9月1日 下午1:50:06 jt39175 Exp $
 */
public abstract class AbstractBaseHttpClient {
    /** logger */
    protected final Logger                  logger       = LoggerFactory.getLogger(getClass());
    /** 错误工厂 */
    protected final DemollkangBizErrorFactory errorFactory = DemollkangBizErrorFactory.getInstance();
    /** 请求地址 */
    protected String                          url;
    /** secret */
    protected String                          secret;
    /** gson */
    private Gson gson = new Gson();
    /**
     * 
     * @param postMethod
     * @param httpClient
     * @param t
     * @throws IOException
     * @throws JAXBException
     */
    public final <T> void initHttp(PostMethod postMethod, HttpClient httpClient, T t, String signRule) throws IOException, JAXBException {
        // 使用系统提供的默认的恢复策略
        postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        // 请求参数数据
        RequestEntity requestEntity = new ByteArrayRequestEntity(gson.toJson(t).getBytes("utf-8"));
        postMethod.setRequestEntity(requestEntity);
        postMethod.addRequestHeader("Content-Type", "application/json;charset=utf-8");
        postMethod.addRequestHeader("Authorization", signRule);
    }


    public <T, E> T get(E e, String customerSign, String requestId) throws Exception {
        // 创建POST 方法的实例
        PostMethod postMethod = new PostMethod(url);
        // 构造HttpClient 的实例
        HttpClient httpClient = new HttpClient();

        //签名规则
        String requestParam = gson.toJson(e);
        String signRule = SignRuleUtil.getSignRule(requestParam, secret, url, customerSign, requestId);

        // 初始化
        initHttp(postMethod, httpClient, e, signRule);

        T t = null;
        HttpHandle<T, E> handle = new HttpHandle<T, E>(e, t, postMethod, httpClient);
        // 请求
        // 获取response对象
        t = handle.getResponse();
        // 释放连接
        postMethod.releaseConnection();

        return t;
    }

    public <T, E> void getHttpResult(HttpHandle<T, E> handle) throws HttpException, IOException, JAXBException,DemollkangException, Exception {
        // 执行getMethod
        int statusCode = handle.getHttpClient().executeMethod(handle.getPostMethod());
        if (HttpStatus.SC_OK != statusCode) {
            throw new DemollkangException(errorFactory.callFail("","请求的服务没有响应"));
        }
        // 获取返回结果
        String result = handle.getPostMethod().getResponseBodyAsString();
        logger.info("<Integration><FlightFreeChangeClientImpl><getHttpResult><getHttpResult>" + ",url=" + url + ",结果=" + JSON.toJSONString(result));

        //json转换Object
        Object t = gson.fromJson(result, handle.getResponse().getClass());
        if (null == t) {
            throw new DemollkangException(errorFactory.callFail("","服务器没有返回值"));
        }
        //返回结果给handle
        handle.setResponse((T) t);
    }

    /**
     * post请求获取返回json
     *
     * @param jsonStr
     * @return
     * @throws Exception
     */
    protected String post(String jsonStr) throws Exception {
        logger.info("<interflightorderapi><AbstractIntBaseHttpClient><getPost><getPost>" + "post请求:" + jsonStr);
        //String response = Request.Post(url).connectTimeout(10000).socketTimeout(10000).bodyString(jsonStr, ContentType.DEFAULT_TEXT).execute().returnContent().asString();
        //logger.info("<interflightorderapi><AbstractIntBaseHttpClient><getPost><getPost>" + "返回原始值:" + response);
        //String ret = GzipUtils.unZip(response);
        //logger.info("<interflightorderapi><AbstractIntBaseHttpClient><getPost><getPost>" + "base64解码,GZIP解压缩后的值:" + ret);
        return "";
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
