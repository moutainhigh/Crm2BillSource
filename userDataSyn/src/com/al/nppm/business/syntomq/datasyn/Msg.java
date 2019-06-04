package com.al.nppm.business.syntomq.datasyn;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Msg {
	private Map<String, Object> map = new HashMap<String, Object>();
	private String[] fields = {"message_id","message_type","message_time","custSoNumber","CrmMQID","CrmMsgStoreTime"};//需要检查的必填项
	
	public Msg(){//已经没有需要通过HDFS更新资料了
		setStorageMode( "RS" );
		setStorageFile( "null" );
		setPartitionId( 0 );
		setGroupId( 0L );
	}
	public Map<String, Object> getMap(){
		return map;
	}
	//合并
	public Map<String, Object> merge(Map<String, Object> map){
		Set<String> keys = map.keySet();
		for(String key:keys){
			this.map.put( key, map.get( key ) );
		}
		return this.map;
	}
	//检查必填
	public String check(){
		for(String f :fields){
			if (!map.containsKey( f )) {
				return "没有必填字段"+f;
			}
		}
		return null;
	}
	
	
	
	public Msg setMessageId(String messageId){
		map.put( "message_id", messageId );
		return this;
	}
	public Msg setMessageType(Integer messageType){
		map.put( "message_type", messageType );
		return this;
	}
	public Msg setMessageTime(String messageTime){
		map.put( "message_time", messageTime );
		return this;
	}
	//crm订单竣工送计费包里，该字段必填（其他情况可为空）
	public Msg setCrmOrderid(String crmOrderid){
		map.put( "crm_orderid", crmOrderid );
		return this;
	}
	public Msg setCustSoNumber(String custSoNumber){
		map.put( "custSoNumber", custSoNumber );
		return this;
	}
	public Msg setCrmMQID(String crmMQID){
		map.put( "CrmMQID", crmMQID );
		return this;
	}
	public Msg setCrmMsgStoreTime(String crmMsgStoreTime){
		map.put( "CrmMsgStoreTime", crmMsgStoreTime );
		return this;
	}
	public Msg setType(Integer type){
		map.put( "type", type );
		return this;
	}
	public Msg setLatnId(Integer latnId){
		map.put( "latnId", latnId );
		return this;
	}
	public Msg setObjId(Long objId){
		map.put( "obj_id", objId );
		return this;
	}
	public Msg setObjType(Integer objType){
		map.put( "obj_type", objType );
		return this;
	}
	public Msg setObjectCount(Integer objectCount){
		map.put( "object_count", objectCount );
		return this;
	}
	public Msg setGroupId(Long groupId){
		map.put( "group_id", groupId );
		return this;
	}
	public Msg setPartitionId(Integer partitionId){
		map.put( "partition_id", partitionId );
		return this;
	}
	public Msg setPartitionType(String partitionType){
		map.put( "partition_type",partitionType );
		return this;
	}
	public Msg setPriPartitionIId(Integer priPartitionId){
		map.put( "pri_partition_id", priPartitionId );
		return this;
	}
	public Msg setRedundantData(Integer redundantData){
		map.put( "redundant_data", redundantData );
		return this;
	}
	public Msg setForwardPartitions(String forwardPartitions){
		map.put( "forward_partitions", forwardPartitions );
		return this;
	}
	public Msg setStorageMode(String storageMode){
		map.put( "storage_mode", storageMode );
		return this;
	}
	public Msg setstorage_rs(List<?> list){
		map.put( "storage_rs", list );
		return this;
	}
	public Msg setStorageFile(String storageFile){
		map.put( "storage_file", storageFile );
		return this;
	}
}
