package com.webmodule.mybatisGenerator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.mariadb.jdbc.Driver;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.JDBCConnectionConfiguration;
import org.mybatis.generator.config.ModelType;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

public class ModuleGenerator {
	public ModuleGenerator() {
		
	}
	public void run() throws Exception {
		
		Driver projectJDBCDriver = new Driver();
		System.out.println(projectJDBCDriver.getClass().getProtectionDomain().getCodeSource().getLocation());
		System.out.println(projectJDBCDriver.getClass().getName());
		
		String entry = projectJDBCDriver.getClass().getProtectionDomain().getCodeSource().getLocation().toString();
		
		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;
		Configuration config = new Configuration();

		config.addClasspathEntry(entry);
		Context context = new Context(ModelType.HIERARCHICAL);
		context.setId("ModuleTable");
		context.setTargetRuntime("MyBatis3");
		
//		http://www.mybatis.org/generator/configreference/xmlconfig.html
		JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
		jdbcConnectionConfiguration.setDriverClass(projectJDBCDriver.getClass().getName());
		jdbcConnectionConfiguration.setConnectionURL("jdbc:mariadb://106.185.54.94:3306/WebModuleDB");
		jdbcConnectionConfiguration.setUserId("WebModuleDB");
		jdbcConnectionConfiguration.setPassword("Webmodule123");
		context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);
		
		
		
		
		config.addContext(context);

		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		myBatisGenerator.generate(null);
	}
}
