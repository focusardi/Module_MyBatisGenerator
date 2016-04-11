package com.webmodule.mybatisGenerator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.mariadb.jdbc.Driver;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.JDBCConnectionConfiguration;
import org.mybatis.generator.config.JavaClientGeneratorConfiguration;
import org.mybatis.generator.config.JavaModelGeneratorConfiguration;
import org.mybatis.generator.config.JavaTypeResolverConfiguration;
import org.mybatis.generator.config.ModelType;
import org.mybatis.generator.config.SqlMapGeneratorConfiguration;
import org.mybatis.generator.config.TableConfiguration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

public class ModuleGenerator {
	public ModuleGenerator() {
		
	}
	public void run() throws Exception {
		
		String targetProject = "\\Module_MyBatisGenerator\\src";
		
		Driver projectJDBCDriver = new Driver();
		System.out.println(projectJDBCDriver.getClass().getProtectionDomain().getCodeSource().getLocation());
		System.out.println(projectJDBCDriver.getClass().getName());
	
		String entry = projectJDBCDriver.getClass().getProtectionDomain().getCodeSource().getLocation().toString().replace("file:", "");
		
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
		
		JavaTypeResolverConfiguration javaTypeResolverConfiguration = new JavaTypeResolverConfiguration();
		javaTypeResolverConfiguration.addProperty("forceBigDecimals", "false");
		context.setJavaTypeResolverConfiguration(javaTypeResolverConfiguration);
		
		JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = new JavaModelGeneratorConfiguration();
		javaModelGeneratorConfiguration.setTargetPackage("test.model");
		javaModelGeneratorConfiguration.setTargetProject(targetProject);
		javaModelGeneratorConfiguration.addProperty("enableSubPackages", "true");
		javaModelGeneratorConfiguration.addProperty("trimStrings", "true");
		context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);
		
		SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = new SqlMapGeneratorConfiguration();
		sqlMapGeneratorConfiguration.setTargetPackage("test.xml");
		sqlMapGeneratorConfiguration.setTargetProject(targetProject);
		sqlMapGeneratorConfiguration.addProperty("enableSubPackages", "true");
		context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfiguration);
		
		JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = new JavaClientGeneratorConfiguration();
		javaClientGeneratorConfiguration.setTargetPackage("test.dao");
		javaClientGeneratorConfiguration.setTargetProject(targetProject);
		javaClientGeneratorConfiguration.addProperty("enableSubPackages", "true");
		javaClientGeneratorConfiguration.setConfigurationType("XMLMAPPER");
		context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfiguration);
		
		TableConfiguration WM_SYS_CONFIG = new TableConfiguration(context);
		WM_SYS_CONFIG.setSchema("WebModuleDB");
		WM_SYS_CONFIG.setTableName("WM_SYS_CONFIG");
		WM_SYS_CONFIG.setDelimitIdentifiers(true);
		WM_SYS_CONFIG.setAllColumnDelimitingEnabled(true);
		
		context.addTableConfiguration(WM_SYS_CONFIG);
		
		
		
		
		
		config.addContext(context);

		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		myBatisGenerator.generate(null);
	}
}
