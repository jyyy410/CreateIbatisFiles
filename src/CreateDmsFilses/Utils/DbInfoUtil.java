package CreateDmsFilses.Utils;


	  
	import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
	  
	/** 
	 *  
	 * <p>Description: 获取数据库基本信息的工具类</p> 
	 *  
	 * @author qxl 
	 * @date 2016年7月22日 下午1:00:34 
	 */  
	public class DbInfoUtil {  
	      
	    /** 
	     * 根据数据库的连接参数，获取指定表的基本信息：字段名、字段类型、字段注释 
	     * @param driver 数据库连接驱动 
	     * @param url 数据库连接url 
	     * @param user  数据库登陆用户名 
	     * @param pwd 数据库登陆密码 
	     * @param table 表名 
	     * @return Map集合 
	     */  
	    public static List getTableInfo(String driver,String url,String user,String pwd,String table){  
	        List result = new ArrayList();  
	          
	        Connection conn = null;       
	        DatabaseMetaData dbmd = null;  
	          
	        try {  
	            conn = getConnections(driver,url,user,pwd);  
	              
	            dbmd = conn.getMetaData();  
	            ResultSet resultSet = dbmd.getTables(null, "%", table, new String[] { "TABLE" }); 
	            dbmd.getPrimaryKeys(null, null, table);
	              int i=0;
	            while (resultSet.next()&&i==0) {  
	                String tableName=resultSet.getString("TABLE_NAME");  
	                  i=1;
	                if(tableName.equals(table)){  
	                    ResultSet rs = conn.getMetaData().getColumns(null, getSchema(conn),tableName.toUpperCase(), "%");  
	                 
	                    
	  
	                    while(rs.next()){  
	                    	  Map map = new HashMap();  
	                          String colName = rs.getString("COLUMN_NAME").toUpperCase();  
	                          map.put("code", colName);  
	                            
	                          String remarks = rs.getString("REMARKS");  
	                          if(remarks == null || remarks.equals("")){  
	                              remarks = colName;  
	                          }  
	                          map.put("remarks",remarks);  
	                            
	                          String dbType = rs.getString("TYPE_NAME");  
	                          int xiaoshuwei = rs.getInt("DECIMAL_DIGITS");  
	                          map.put("dbType",sqlType2JavaType(dbType,xiaoshuwei));  
	                            
	                          String valueType = rs.getString("COLUMN_SIZE");  
	                          
	                          
	                          map.put("valueType", valueType);  
	                          result.add(map);  
	                    }  
	                }  
	            }  
	        } catch (SQLException e) {  
	            e.printStackTrace();  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }finally{  
	            try {  
	                conn.close();  
	            } catch (SQLException e) {  
	                e.printStackTrace();  
	            }  
	        }  
	          
	        return result;  
	    }  
	      
	
	    //获取连接  
	    private static Connection getConnections(String driver,String url,String user,String pwd) throws Exception {  
	        Connection conn = null;  
	        try {  
	            Properties props = new Properties();  
	            props.put("remarksReporting", "true");  
	            props.put("user", user);  
	            props.put("password", pwd);  
	            Class.forName(driver);  
	            conn = DriverManager.getConnection(url, props);  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	            throw e;  
	        }  
	        return conn;  
	    }  
	      
	    //其他数据库不需要这个方法 oracle和db2需要  
	    private static String getSchema(Connection conn) throws Exception {  
	        String schema;  
	        schema = conn.getMetaData().getUserName();  
	        if ((schema == null) || (schema.length() == 0)) {  
	            throw new Exception("ORACLE数据库模式不允许为空");  
	        }  
	        return schema.toUpperCase().toString();  
	  
	    }  
	  
	    public static void main(String[] args) {  
	          
	        //这里是Oracle连接方法  
	          
	        String driver = "oracle.jdbc.driver.OracleDriver";  
	        String url = "jdbc:oracle:thin:@10.86.86.24:1521:dcs";  
	        String user = "BIZ_DCS";  
	        String pwd = "duoreimi123";  
	        //String table = "FZ_USER_T";  
	        String table = "NT_VS_PSO";  
	        
	          
	        //mysql  
	        /* 
	        String driver = "com.mysql.jdbc.Driver"; 
	        String user = "root"; 
	        String pwd = "123456"; 
	        String url = "jdbc:mysql://localhost/onlinexam" 
	                + "?useUnicode=true&characterEncoding=UTF-8"; 
	        String table = "oe_student"; 
	        */  
	          
	        List list = getTableInfo(driver,url,user,pwd,table);  
	        System.out.println(list);  
	    } 
	    
	    
	    
	    /** 
	     * 功能：获得列的数据类型 
	     * @param sqlType 
	     * @param xiaoshuwei 
	     * @return 
	     */  
	    private static String sqlType2JavaType(String sqlType, int xiaoshuwei) {  
	    	
	    	
	          
	        if(sqlType.equalsIgnoreCase("binary_double")){  
	            return "Double";  
	        }else if(sqlType.equalsIgnoreCase("binary_float")){  
	            return "float";  
	        }else if(sqlType.equalsIgnoreCase("blob")){  
	            return "byte[]";  
	        }else if(sqlType.equalsIgnoreCase("blob")){  
	            return "byte[]";  
	        }else if(sqlType.equalsIgnoreCase("char") || sqlType.equalsIgnoreCase("nvarchar2")   
	                || sqlType.equalsIgnoreCase("varchar2")){  
	            return "String";  
	        }else if(sqlType.equalsIgnoreCase("date") || sqlType.equalsIgnoreCase("timestamp")  
	                 || sqlType.equalsIgnoreCase("timestamp with local time zone")   
	                 || sqlType.equalsIgnoreCase("timestamp with time zone")||sqlType.indexOf("TIMESTAMP")>-1){  
	            return "Date";  
	        }else if(sqlType.equalsIgnoreCase("number")){  
	        	
	        	if (xiaoshuwei>0) {
	        		 return "Double";  
				}
	            return "Integer";  
	        }  
	          
	        return "String";  
	    }  
	      
	}
