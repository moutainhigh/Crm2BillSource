package com.al.nppm.common.errorcode;

/**
 * @Author WangBaoQiang
 * @Description 错误信息
 * @Date 16:06 2019/8/25
 * @Param
 * @return
*/
public interface IErrorCodeEnum {
    /**
     * 获取状态码
     * @return 状态码
     */
    public String getCode();
    /**
     * 获取提示信息
     * @return 提示信息
     */
    public String getMessage();
}
