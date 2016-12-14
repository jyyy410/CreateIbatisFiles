<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE sqlMap PUBLIC "-//ibatis.apache.org//DTD SQL Map 2.0//EN" 
	"http://ibatis.apache.org/dtd/sql-map-2.dtd">
<sqlMap namespace="${voName}Impl">
	<typeAlias alias="${voName}" type="${packageOutPath}.${voName}" />
	
	<select id="query${voName}List" parameterClass="${voName}" resultClass="${voName}">
  SELECT 
		 <#list lists as item>
		 <#if item_index+1==lists?size>
		 A.${item.code}
		 <#else>
		 A.${item.code},
		 </#if>
         </#list>
	FROM ${tableName} A
		 WHERE 1=1
	</select>
    
    
    <insert id="insert${voName}" parameterClass="${voName}">
INSERT INTO ${tableName}
		  (  
		  <#list lists as item>
				 <#if item_index+1==lists?size>
				 ${item.code}
				 <#else>
				 ${item.code},
				 </#if>
		   </#list>
		   
		         
		  )
		VALUES
		  ( 
		   <#list lists as item>
						 <#if item_index+1==lists?size>
							 #${item.code}#  
						 <#else>
						  #${item.code}# ,
						 </#if>
         </#list>
		  )
	</insert>
	<update id="update${voName}" parameterClass="${voName}" >
	     UPDATE ${tableName}
      SET 
		        <#list lists as item>
				 <#if item_index+1==lists?size>
				 ${item.code} = #${item.code}#
				 <#else>
				 ${item.code} = #${item.code}#,
				 </#if>
		        </#list>
        WHERE VERSION=#VERSION# 
	</update>

    <delete id="delete${voName}" parameterClass="${voName}">
    DELETE FROM　${tableName} 
           WHERE 1=1
    </delete>
   
</sqlMap>