package util;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;

public class DataTransferPool {
	private static final DataTransferPool dtPool = new DataTransferPool();
	
	private static Map<String, Object> dataMap = new HashMap();
	
	private DataTransferPool() {
		
	}
	
	public static DataTransferPool getInstance() {
		return dtPool;
	}
	
	public static Object getParamMapValueByKey(String key) throws Exception {
		Set<String> keySet = dataMap.keySet();
		if (!keySet.contains(key)) {
			throw new Exception("无法根据索引[" + key + "]找到对应的数据");
		}else {
			return dataMap.get(key);
		}
	}
	
	public static void putKeyValue(String key, Object value) {
		dataMap.put(key, value);
	}
}
