package com.mn.springboot.utils.es;

import com.mn.springboot.utils.GenericsUtils;
import org.frameworkset.elasticsearch.client.ClientInterface;
import org.frameworkset.elasticsearch.entity.ESDatas;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * es工具类
 */
@Service
public class ESUtil {
	/**
	 * 添加或修改文档
	 */
	public void addOrUpdateDocument(String index,Object obj){
		//获取文档的客户端对象，单实例多线程安全
		ClientInterface clientUtil = ESClient.restClient();
		//向固定index添加或者修改文档,如果id已经存在做修改操作，否则做添加文档操作，返回处理结果
		//索引表 索引类型
		clientUtil.addDocument(index,index,obj);
	}

	/**
	 * 添加或修改文档
	 */
	public <T> void addOrUpdateDocuments(String index,List<T> list){
		//获取文档的客户端对象，单实例多线程安全
		ClientInterface clientUtil = ESClient.restClient();
		//向固定index添加或者修改文档,id，否则做添加文档操作，返回处理结果
		//索引表 索引类型
		clientUtil.addDocuments(index,index,list);
	}
	/**
	 * 获取文档
	 */
	public String getDocumentById(String index,String id){
		//获取文档的客户端对象，单实例多线程安全
		ClientInterface clientUtil = ESClient.restClient();
		//索引表 索引类型 id
		String res = clientUtil.getDocument(index,index,id);
		return res;
	}
	/**
	 * 获取文档
	 */
	public <T> T getDocumentById(String index,String id,Class<T> t){
		//获取文档的客户端对象，单实例多线程安全
		ClientInterface clientUtil = ESClient.restClient();
		//索引表 索引类型 id 对象
		return clientUtil.getDocument(index,index,id,t);
	}
	/**
	 * 删除文档
	 */
	public void deleteDocumentById(String index,String id){
		//获取文档的客户端对象，单实例多线程安全
		ClientInterface clientUtil = ESClient.restClient();
		//索引表 索引类型 id 对象
		clientUtil.deleteDocument(index,index,id);
	}
	/**
	 * 删除文档
	 */
	public void deleteDocumentByIds(String index,String[] ids){
		//获取文档的客户端对象，单实例多线程安全
		ClientInterface clientUtil = ESClient.restClient();
		StringBuilder builder = new StringBuilder();
		String[] var5 = ids;
		for(int i = 0; i < ids.length; ++i) {
			String id = ids[i];
			builder.append("{ \"delete\" : { \"_index\" : \"").append(index).append("\", \"_type\" : \"").append(index).append("\", \"_id\" : \"").append(id).append("\" } }\n");
		}
		//索引表 索引类型 id 对象
		clientUtil.executeHttp("_bulk",builder.toString(),"post");
	}

	/**
	 * 通过mapper文件执行查询语句
	 * @param index 索引
	 * @param params 传递的参数
	 * @param dsl 对应mapper文件的name
	 */
	public <T> List<T> exec(String index,T params,String dsl){
		//获取索引对应的客户端
		ClientInterface clientUtil = ESClient.restClient(index);
		//设定查询条件,通过map传递变量参数值,key对于dsl中的变量名称
		//执行查询，index为索引表，_search为检索操作action
		//使用反射获取Class<T>
		Class<T> clazz = GenericsUtils.getSuperClassGenricType(params.getClass());
		ESDatas<T> esDatas =  //ESDatas包含当前检索的记录集合，最多1000条记录，由dsl中的size属性指定
				clientUtil.searchList(index+"/_search",//demo为索引表，_search为检索操作action
						dsl,//esmapper/xxx.xml中定义的dsl语句的name
						params,//变量参数
						clazz);//返回的文档封装对象类型
		//获取结果对象列表，最多返回1000条记录
		List<T> list = esDatas.getDatas();
		//获取总记录数
		long totalSize = esDatas.getTotalSize();
		return list;
	}
}
