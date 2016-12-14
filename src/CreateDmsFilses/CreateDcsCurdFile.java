package CreateDmsFilses;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import CreateDmsFilses.Utils.DbInfoUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class CreateDcsCurdFile {

   //数据库名
	private static String tableName = "NT_TP_SO_CONTRACT_T";
	//vo名
	private static String voName = "NtTpSoContractt";// VO   

	// 数据库连接
	private static final String URL = "jdbc:oracle:thin:@127.0.0.1:1521:dcs";
	private static final String NAME = "xx";
	private static final String PASS = "xx";
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	
	
	
	private static String packageOutPath = "temp";// 指定实体生成所在包的路径
	private static String username = "靳阳阳";
	
public static void main(String[] args) throws IOException, TemplateException {
	Configuration cfg = new Configuration();
	cfg.setDirectoryForTemplateLoading(new File("template"));
	Map<String, Object> root = new HashMap<String, Object>();
	root.put("name", username);
	root.put("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date())); 
	root.put("tableName", tableName);
	root.put("voName", voName);
	root.put("packageOutPath", packageOutPath);
	
	
	List list =DbInfoUtil.getTableInfo(DRIVER, URL,NAME, PASS, tableName.toUpperCase());
	root.put("lists", list);
	createVo();
	createFile1(cfg, root);
	createFile2(cfg, root);
	createFile3(cfg, root);
}

	public static void createFile1(Configuration cfg, Map<String, Object> root)
			throws IOException, TemplateException {
		Template template = cfg.getTemplate("ifaceTemp.ftl");
		// 建立数据模型（Map）
		File file1 = new File("src/temp/I" + voName + ".java");
		Writer writer1 = new FileWriter(file1);
		// StringWriter out = new StringWriter();
		// System.out.println(out.toString());
		// 数据与模板合并（数据+模板=输出）
		template.process(root, writer1);

	}

	// 实现类
	public static void createFile2(Configuration cfg, Map<String, Object> root)
			throws IOException, TemplateException {
		Template template = cfg.getTemplate("implTemp.ftl");
		File file1 = new File("src/temp/" + voName + "Impl.java");
		Writer writer1 = new FileWriter(file1);
		template.process(root, writer1);

	}

	// 配置文件
	public static void createFile3(Configuration cfg, Map<String, Object> root)
			throws IOException, TemplateException {
		Template template = cfg.getTemplate("xmlTemp.ftl");
		File file1 = new File("src/temp/" + voName + "Impl.xml");
		Writer writer1 = new FileWriter(file1);
		template.process(root, writer1);
		
		System.out.println("生成成功！");

	}
	
	private static void createVo() throws IOException, TemplateException {
		Configuration cfg = new Configuration();
		cfg.setDirectoryForTemplateLoading(new File("template"));
		Map<String, Object> root = new HashMap<String, Object>();
		
		List<Map<String, String>> list = DbInfoUtil.getTableInfo(DRIVER, URL,NAME, PASS, tableName.toUpperCase());
		
		
		root.put("lists", list);
		root.put("tableName", tableName);
		root.put("voName",voName);
		root.put("packageOutPath",packageOutPath);
		root.put("authorName",username);
		root.put("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));//yyyy-MM-dd hh:mm:ss
		
		root.put("lists",list);
		
		
		Template template = cfg.getTemplate("dmsVO.ftl");
		// 建立数据模型（Map）
		File file1 = new File("src/temp/" + voName + ".java");
		Writer writer1 = new FileWriter(file1);
		template.process(root, writer1);
		/*Template template = cfg.getTemplate("xmlZhuShi.ftl");
		// 建立数据模型（Map）
		File file1 = new File("src/temp/" + voName + ".xml");
		Writer writer1 = new FileWriter(file1);
		template.process(root, writer1);*/
	}
}


