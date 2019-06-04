package com.al.nppm.common;

import com.al.common.annotation.AppModule;

import java.util.HashMap;
import java.util.Map;

@AppModule(moduleName="PpmCommon")
public class MDA {
	// 公用常量定义
	public static final String LOGIC_Y = new String("Y");// 逻辑判断,是Y
	public static final String LOGIC_N = new String("N");// 逻辑判断,是N
	
	/**
	 * 特殊字符的正则表达式
	 */
	public static final String FILTER_SPECIAL_CHARECTER_REG ="<scrip>.*?</scrip>|'\\+.*?\\+'";//匹配<script></scrip>标签以及中间内容，或者两个+号中间内容
	public static final String FILTER_SPECIAL_CHARECTER_FLAG =new String("N");//是否需要进行特殊字符的过滤 默认不过滤

	
	//数据库路由配置
	// hashCode初始值
	public static final Integer DEFAULT_HASH_CODE = new Integer(23);
	public static final Map<String, String> DBROUTING_MAPPING = new HashMap<String, String>();
	static {
		DBROUTING_MAPPING.put("areaId_10900", "ds_a");//内江市
		DBROUTING_MAPPING.put("areaId_11000", "ds_a");//乐山市
		DBROUTING_MAPPING.put("areaId_11100", "ds_a");//南充市
		DBROUTING_MAPPING.put("areaId_11200", "ds_a");//宜宾市
		DBROUTING_MAPPING.put("areaId_11300", "ds_a");//广安市
		DBROUTING_MAPPING.put("areaId_11500", "ds_a");//雅安地区
		DBROUTING_MAPPING.put("areaId_11600", "ds_a");//阿坝州
		DBROUTING_MAPPING.put("areaId_11700", "ds_a");//甘孜州
		DBROUTING_MAPPING.put("areaId_12000", "ds_a");//眉山市
		DBROUTING_MAPPING.put("areaId_10400", "ds_a");//泸州市
		DBROUTING_MAPPING.put("areaId_10500", "ds_a");//德阳市
		DBROUTING_MAPPING.put("areaId_10600", "ds_a");//绵阳市
		DBROUTING_MAPPING.put("areaId_12100", "ds_a");//资阳
		DBROUTING_MAPPING.put("areaId_10200", "ds_a");//自贡市
		
		DBROUTING_MAPPING.put("areaId_10100", "ds_b");//成都市
	}
	public static final String SEQ_TYPE_PK = new String("1"); //PK,例如生产acct_id时候
	public static final String SEQ_TYPE_AK = new String("2"); //AK例如生产acct_cd时候
	public static final String SEQ_TYPE_SPESIL = new String("3"); //表的特殊序列
	
	
	/**
	 * Session登录信息
	 */
	public static final String SESSION_KEY_LOGIN_USER = new String("userId");// 登录人Id
	public static final String SESSION_KEY_LOGIN_SUPERUSER = new String("isSuperManager");// 登录人Id
	public static final String SESSION_KEY_LOGIN_VERRIFY_CODE = new String("verify_code");// 验证码
	
	/**
	 * 模板类型：需求单、销售品、产品等。
	 */
	public static final String TEMPLATE_TYPE_DEMAND = new String("9000");// 需求单
	public static final String TEMPLATE_TYPE_CONFIG_ORDER = new String("8000");// 配置单
	public static final String TEMPLATE_TYPE_OFFER = new String("1000");// 销售品
	public static final String TEMPLATE_TYPE_OFFER_KXB = new String("2000");// 可选包
	public static final String TEMPLATE_TYPE_OFFER_CX = new String("3000");// 促销
	public static final String TEMPLATE_TYPE_OFFERS = new String("4000");// 虚拟出的销售品对象类型,4000代表1000+2000+3000
	
	/**
	 * 业务单脚本标识
	 */
	public static final String BUSI_ORDER_SCRIPT_SIGN_0 = new String("0");// 无需生成脚本
	public static final String BUSI_ORDER_SCRIPT_SIGN_1 = new String("1");// 待生成脚本
	public static final String BUSI_ORDER_SCRIPT_SIGN_2 = new String("2");// 已生成脚本
	
	/**
	 * 业务单流程状态
	 */
	public static final String BUSI_ORDER_STATE_0 = new String("0");// 未发起
	public static final String BUSI_ORDER_STATE_1 = new String("1");// 流程进行中
	public static final String BUSI_ORDER_STATE_9 = new String("9");// 已结束
	public static final String BUSI_ORDER_STATE_1_1 = new String("-1");// 撤销
	public static final String BUSI_ORDER_STATE_1_2 = new String("-2");// 挂起
	
	/**
	 * 业务单子对象状态
	 */
	public static final String BUSI_ORDER_SUBOBJECT_STATE_0 = new String("0");// 失效
	public static final String BUSI_ORDER_SUBOBJECT_STATE_1 = new String("1");// 有效
	
	/**
	 * 业务单有无子对象标识
	 */
	public static final String BUSI_ORDER_HAS_SUBOBJECT_Y = new String("Y");// 有
	public static final String BUSI_ORDER_HAS_SUBOBJECT_N = new String("N");// 无
	
	/**
	 * 环节类型定义
	 */
	public static final String TACHE_TYPE_CD_1 = new String("1");// 审批环节
	public static final String TACHE_TYPE_CD_START = new String("START_EVENT");// 开始环节
	public static final String TACHE_TYPE_CD_END = new String("END_EVENT");// 结束环节
	
	/**
	 * 流程实例备注类型
	 */
	public static final String FLOW_INST_REMARK_TYPE_0 = new String("0");// 挂起
	public static final String FLOW_INST_REMARK_TYPE_1 = new String("1");// 发起
	public static final String FLOW_INST_REMARK_TYPE_1_1 = new String("-1");// 撤销
	
	/**
	 * 环节实例备注类型
	 */
	public static final String TACHE_INST_REMARK_TYPE_1 = new String("1");// 回单
	public static final String TACHE_INST_REMARK_TYPE_0 = new String("0");// 退单
	
	/**
	 * 环节实例状态
	 */
	public static final String TACHE_INST_STATE_G = new String("N");// 等待审批
	public static final String TACHE_INST_STATE_N = new String("W");// 未激活
	public static final String TACHE_INST_STATE_T = new String("G");// 已回单
	
	/**
	 * 主数据状态
	 */
	public static final String SYS_DATA_STATE_1 = new String("1");// 有效
	
	/**
	 * 模板实例状态
	 */
	public static final String TEMPLATE_INST_STATE_0 = new String("0");// 失效
	public static final String TEMPLATE_INST_STATE_1 = new String("1");// 有效
	
	/**
	 * 组件实例状态
	 */
	public static final String COMPONENT_INST_STATE_0 = new String("0");// 失效
	public static final String COMPONENT_INST_STATE_1 = new String("1");// 有效
	
	/**
	 * 历史组件实例状态
	 */
	public static final String HIS_COMPONENT_INST_STATE_0 = new String("0");// 失效
	public static final String HIS_COMPONENT_INST_STATE_1 = new String("1");// 有效
	
	/**
	 * 后台规则校验级别
	 */
	public static final String RULE_CHECK_LEVEL_1 = new String("1");// 错误级别
	public static final String RULE_CHECK_LEVEL_2 = new String("2");// 警告级别
	
	/**
	 * SQL脚本记录状态
	 */
	public static final String SQL_GEN_REC_STATE_0 = new String("0");// 失效
	public static final String SQL_GEN_REC_STATE_1 = new String("1");// 有效
	
	/**
	 * 组件识别编码
	 */
	public static final String COMPONET_UI_CODE_FEE = new String("configOfferBilling");// 定价信息
	
	/**
	 * 定义发布管理中具体环节实施具体操作
	 */
	public static final String TACHE_DETAIL_CRM_CONFIG = new String("18");// 允许生成脚本的环节
	public static final String TACHE_DETAIL_SYS_RELEASE = new String("23");// 允许一点发布的环节
	
	/**
	 * 环节关联组件类型编码
	 */
	public static final String COMPONENT_TACHE_RELA_1 = new String("1");// 配置中
	public static final String COMPONENT_TACHE_RELA_2 = new String("2");// 流程中
	public static final String COMPONENT_TACHE_RELA_3 = new String("3");// 视图
	
	/**
	 * 模板校验编码
	 */
	public static final String INIT_RULE_IS_REQURIED_1 = new String("1");// 必填
	
	public static final Object MEMBER_ROLE_STATE_1 = null;
	public static final Object BUSI_ORDER_TYPE_DEMAND = null;
	public static final Object BUSI_ORDER_TYPE_CONFIG_ORDER = null;
	public static final Object BUSI_ORDER_TYPE_OFFER = null;
	
}
