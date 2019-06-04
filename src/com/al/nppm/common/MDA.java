package com.al.nppm.common;

import com.al.common.annotation.AppModule;

import java.util.HashMap;
import java.util.Map;

@AppModule(moduleName="PpmCommon")
public class MDA {
	// ���ó�������
	public static final String LOGIC_Y = new String("Y");// �߼��ж�,��Y
	public static final String LOGIC_N = new String("N");// �߼��ж�,��N
	
	/**
	 * �����ַ���������ʽ
	 */
	public static final String FILTER_SPECIAL_CHARECTER_REG ="<scrip>.*?</scrip>|'\\+.*?\\+'";//ƥ��<script></scrip>��ǩ�Լ��м����ݣ���������+���м�����
	public static final String FILTER_SPECIAL_CHARECTER_FLAG =new String("N");//�Ƿ���Ҫ���������ַ��Ĺ��� Ĭ�ϲ�����

	
	//���ݿ�·������
	// hashCode��ʼֵ
	public static final Integer DEFAULT_HASH_CODE = new Integer(23);
	public static final Map<String, String> DBROUTING_MAPPING = new HashMap<String, String>();
	static {
		DBROUTING_MAPPING.put("areaId_10900", "ds_a");//�ڽ���
		DBROUTING_MAPPING.put("areaId_11000", "ds_a");//��ɽ��
		DBROUTING_MAPPING.put("areaId_11100", "ds_a");//�ϳ���
		DBROUTING_MAPPING.put("areaId_11200", "ds_a");//�˱���
		DBROUTING_MAPPING.put("areaId_11300", "ds_a");//�㰲��
		DBROUTING_MAPPING.put("areaId_11500", "ds_a");//�Ű�����
		DBROUTING_MAPPING.put("areaId_11600", "ds_a");//������
		DBROUTING_MAPPING.put("areaId_11700", "ds_a");//������
		DBROUTING_MAPPING.put("areaId_12000", "ds_a");//üɽ��
		DBROUTING_MAPPING.put("areaId_10400", "ds_a");//������
		DBROUTING_MAPPING.put("areaId_10500", "ds_a");//������
		DBROUTING_MAPPING.put("areaId_10600", "ds_a");//������
		DBROUTING_MAPPING.put("areaId_12100", "ds_a");//����
		DBROUTING_MAPPING.put("areaId_10200", "ds_a");//�Թ���
		
		DBROUTING_MAPPING.put("areaId_10100", "ds_b");//�ɶ���
	}
	public static final String SEQ_TYPE_PK = new String("1"); //PK,��������acct_idʱ��
	public static final String SEQ_TYPE_AK = new String("2"); //AK��������acct_cdʱ��
	public static final String SEQ_TYPE_SPESIL = new String("3"); //�����������
	
	
	/**
	 * Session��¼��Ϣ
	 */
	public static final String SESSION_KEY_LOGIN_USER = new String("userId");// ��¼��Id
	public static final String SESSION_KEY_LOGIN_SUPERUSER = new String("isSuperManager");// ��¼��Id
	public static final String SESSION_KEY_LOGIN_VERRIFY_CODE = new String("verify_code");// ��֤��
	
	/**
	 * ģ�����ͣ����󵥡�����Ʒ����Ʒ�ȡ�
	 */
	public static final String TEMPLATE_TYPE_DEMAND = new String("9000");// ����
	public static final String TEMPLATE_TYPE_CONFIG_ORDER = new String("8000");// ���õ�
	public static final String TEMPLATE_TYPE_OFFER = new String("1000");// ����Ʒ
	public static final String TEMPLATE_TYPE_OFFER_KXB = new String("2000");// ��ѡ��
	public static final String TEMPLATE_TYPE_OFFER_CX = new String("3000");// ����
	public static final String TEMPLATE_TYPE_OFFERS = new String("4000");// �����������Ʒ��������,4000����1000+2000+3000
	
	/**
	 * ҵ�񵥽ű���ʶ
	 */
	public static final String BUSI_ORDER_SCRIPT_SIGN_0 = new String("0");// �������ɽű�
	public static final String BUSI_ORDER_SCRIPT_SIGN_1 = new String("1");// �����ɽű�
	public static final String BUSI_ORDER_SCRIPT_SIGN_2 = new String("2");// �����ɽű�
	
	/**
	 * ҵ������״̬
	 */
	public static final String BUSI_ORDER_STATE_0 = new String("0");// δ����
	public static final String BUSI_ORDER_STATE_1 = new String("1");// ���̽�����
	public static final String BUSI_ORDER_STATE_9 = new String("9");// �ѽ���
	public static final String BUSI_ORDER_STATE_1_1 = new String("-1");// ����
	public static final String BUSI_ORDER_STATE_1_2 = new String("-2");// ����
	
	/**
	 * ҵ���Ӷ���״̬
	 */
	public static final String BUSI_ORDER_SUBOBJECT_STATE_0 = new String("0");// ʧЧ
	public static final String BUSI_ORDER_SUBOBJECT_STATE_1 = new String("1");// ��Ч
	
	/**
	 * ҵ�������Ӷ����ʶ
	 */
	public static final String BUSI_ORDER_HAS_SUBOBJECT_Y = new String("Y");// ��
	public static final String BUSI_ORDER_HAS_SUBOBJECT_N = new String("N");// ��
	
	/**
	 * �������Ͷ���
	 */
	public static final String TACHE_TYPE_CD_1 = new String("1");// ��������
	public static final String TACHE_TYPE_CD_START = new String("START_EVENT");// ��ʼ����
	public static final String TACHE_TYPE_CD_END = new String("END_EVENT");// ��������
	
	/**
	 * ����ʵ����ע����
	 */
	public static final String FLOW_INST_REMARK_TYPE_0 = new String("0");// ����
	public static final String FLOW_INST_REMARK_TYPE_1 = new String("1");// ����
	public static final String FLOW_INST_REMARK_TYPE_1_1 = new String("-1");// ����
	
	/**
	 * ����ʵ����ע����
	 */
	public static final String TACHE_INST_REMARK_TYPE_1 = new String("1");// �ص�
	public static final String TACHE_INST_REMARK_TYPE_0 = new String("0");// �˵�
	
	/**
	 * ����ʵ��״̬
	 */
	public static final String TACHE_INST_STATE_G = new String("N");// �ȴ�����
	public static final String TACHE_INST_STATE_N = new String("W");// δ����
	public static final String TACHE_INST_STATE_T = new String("G");// �ѻص�
	
	/**
	 * ������״̬
	 */
	public static final String SYS_DATA_STATE_1 = new String("1");// ��Ч
	
	/**
	 * ģ��ʵ��״̬
	 */
	public static final String TEMPLATE_INST_STATE_0 = new String("0");// ʧЧ
	public static final String TEMPLATE_INST_STATE_1 = new String("1");// ��Ч
	
	/**
	 * ���ʵ��״̬
	 */
	public static final String COMPONENT_INST_STATE_0 = new String("0");// ʧЧ
	public static final String COMPONENT_INST_STATE_1 = new String("1");// ��Ч
	
	/**
	 * ��ʷ���ʵ��״̬
	 */
	public static final String HIS_COMPONENT_INST_STATE_0 = new String("0");// ʧЧ
	public static final String HIS_COMPONENT_INST_STATE_1 = new String("1");// ��Ч
	
	/**
	 * ��̨����У�鼶��
	 */
	public static final String RULE_CHECK_LEVEL_1 = new String("1");// ���󼶱�
	public static final String RULE_CHECK_LEVEL_2 = new String("2");// ���漶��
	
	/**
	 * SQL�ű���¼״̬
	 */
	public static final String SQL_GEN_REC_STATE_0 = new String("0");// ʧЧ
	public static final String SQL_GEN_REC_STATE_1 = new String("1");// ��Ч
	
	/**
	 * ���ʶ�����
	 */
	public static final String COMPONET_UI_CODE_FEE = new String("configOfferBilling");// ������Ϣ
	
	/**
	 * ���巢�������о��廷��ʵʩ�������
	 */
	public static final String TACHE_DETAIL_CRM_CONFIG = new String("18");// �������ɽű��Ļ���
	public static final String TACHE_DETAIL_SYS_RELEASE = new String("23");// ����һ�㷢���Ļ���
	
	/**
	 * ���ڹ���������ͱ���
	 */
	public static final String COMPONENT_TACHE_RELA_1 = new String("1");// ������
	public static final String COMPONENT_TACHE_RELA_2 = new String("2");// ������
	public static final String COMPONENT_TACHE_RELA_3 = new String("3");// ��ͼ
	
	/**
	 * ģ��У�����
	 */
	public static final String INIT_RULE_IS_REQURIED_1 = new String("1");// ����
	
	public static final Object MEMBER_ROLE_STATE_1 = null;
	public static final Object BUSI_ORDER_TYPE_DEMAND = null;
	public static final Object BUSI_ORDER_TYPE_CONFIG_ORDER = null;
	public static final Object BUSI_ORDER_TYPE_OFFER = null;
	
}
