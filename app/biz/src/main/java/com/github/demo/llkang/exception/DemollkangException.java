package com.github.demo.llkang.exception;

import com.ly.sof.api.error.LYError;
import com.ly.sof.api.exception.LYException;

/**
 * 自定义异常
 * 
 * @author kllp0648
 * @version $Id: OrderLogException.java, v 0.1 2017年2月4日 下午2:07:20 kllp0648 Exp $
 */
public class DemollkangException extends LYException {

    /**  */
    private static final long serialVersionUID = 1L;

    public DemollkangException(LYError error) {
        super(error);
    }

    public DemollkangException(LYError error, Throwable cause) {
        super(error, cause);
    }
    
    public DemollkangException(Throwable cause) {
        super(cause);
    }
}
