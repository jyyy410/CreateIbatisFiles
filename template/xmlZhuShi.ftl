<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE sqlMap PUBLIC "-//ibatis.apache.org//DTD SQL Map 2.0//EN" 
	"http://ibatis.apache.org/dtd/sql-map-2.dtd">


	
	
		SELECT 
		<#list lists as item>
		 A.${item.code} as ${item.remarks},
         </#list>
		  FROM ${tableName}
		 WHERE 1=1

    
  