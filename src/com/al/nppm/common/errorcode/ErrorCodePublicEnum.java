package com.al.nppm.common.errorcode;

/**
 * @Author WangBaoQiang
 * @Description //TODO 错误信息描述
 * @Date 16:06 2019/8/25
 * @Param
 * @return
*/
public enum ErrorCodePublicEnum implements IErrorCodeEnum {
    SUCCESS("0", "成功"),
    ERROR("error ", "待完错误描述"),
    CUSTOMER_ERROR_001("CUSTOMER_ERROR_001", "取客户接口表ord_customer数据时出错:记录不存在"),
    CUSTOMER_ERROR_002("CUSTOMER_ERROR_002", "取客户接口表ord_customer数据时出错:记录有多条！"),
    CUSTOMER_ERROR_003("CUSTOMER_ERROR_003", "取客户接口表ord_customer数据时出错:记录有多条！"),
    CUSTOMER_ERROR_004("CUSTOMER_ERROR_004", "新增客户时出错"),
    CUSTOMER_ERROR_005("CUSTOMER_ERROR_005", "修改客户表时出错"),
    CUSTOMER_ERROR_006("CUSTOMER_ERROR_006", "客户不存在,不能修改CUST_ID");


    /**
     * 状态码
     */
    private String code;

    /**
     * 提示信息
     */
    private String message;

    /**
     * 构造函数
     *
     * @param code    状态码
     * @param message 提示信息
     */
    ErrorCodePublicEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 获取状态码
     *
     * @return 状态码
     */
    @Override
    public String getCode() {
        return this.code;
    }

    /**
     * 获取提示信息
     *
     * @return 提示信息
     */
    @Override
    public String getMessage() {
        return this.message;
    }
}
