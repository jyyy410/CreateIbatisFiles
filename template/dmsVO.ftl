package ${packageOutPath};
import java.util.Date;
/**
 * <p>ClassName:${voName}  数据库名:${tableName}</p>
 * <p>Title:</p>
 * <p>Description:</p>
 * <p>Copyright:Copyright (c) 2016</p>
 * <p>Company:易泓咨询管理公司</p>
 * <p>Date:${date}</p>
 * <p>Modify:</p>
 * <p>Bug:</p>
 * <p>@author ${authorName} </p>
 * <p>@version 1.0</p>	 
 */
 
 public class ${voName} {
 
 <#list lists as item>
	 private  ${item.dbType}  ${item.code} ; //${item.valueType} ||null||null ${item.remarks}
	
 </#list>
 
 
  <#list lists as item>
	 public  ${item.dbType}  get${item.code}(){
	 return ${item.code};
	 }
	 
	 public  void set${item.code}(${item.dbType} ${item.code})
	 {
	 this.${item.code}=${item.code};   
	 }
 </#list>

 }