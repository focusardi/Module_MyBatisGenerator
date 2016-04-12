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
		
		String targetProject = "/Users/focusardi/git/WebModule/WebModule/src";//"Module_MyBatisGenerator";
		String targetPackageJavaModel = "com.main.db.model";
		String targetPackageSqlMap = "com.main.db.sqlxml";
		String targetPackageJavaClient = "com.main.db.dao";
		
		List<String> tableList  = new ArrayList<String>();
		tableList.add("WM_SYS_CONFIG");
		
		
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
		javaModelGeneratorConfiguration.setTargetPackage(targetPackageJavaModel);
		javaModelGeneratorConfiguration.setTargetProject(targetProject);
		javaModelGeneratorConfiguration.addProperty("enableSubPackages", "true");
		javaModelGeneratorConfiguration.addProperty("trimStrings", "true");
		context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);
		
		SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = new SqlMapGeneratorConfiguration();
		sqlMapGeneratorConfiguration.setTargetPackage(targetPackageSqlMap);
		sqlMapGeneratorConfiguration.setTargetProject(targetProject);
		sqlMapGeneratorConfiguration.addProperty("enableSubPackages", "true");
		context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfiguration);
		
		JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = new JavaClientGeneratorConfiguration();
		javaClientGeneratorConfiguration.setTargetPackage(targetPackageJavaClient);
		javaClientGeneratorConfiguration.setTargetProject(targetProject);
		javaClientGeneratorConfiguration.addProperty("enableSubPackages", "true");
		javaClientGeneratorConfiguration.setConfigurationType("XMLMAPPER");
		context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfiguration);
		
		
		
		for (int i = 0;i < tableList.size();i++) {
			TableConfiguration tableConfiguration = new TableConfiguration(context);
			//tableConfiguration.setSchema("WebModuleDB");
			tableConfiguration.setTableName(tableList.get(i));		
			tableConfiguration.setDelimitIdentifiers(true);
			tableConfiguration.setAllColumnDelimitingEnabled(true);
			
			context.addTableConfiguration(tableConfiguration);
			
		}
		
		
		
		config.addContext(context);

		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		
		myBatisGenerator.generate(null);
		
		for(int i =0;i < warnings.size();i++){
			System.out.println(warnings.get(i));
		}
		
		
	}
}

