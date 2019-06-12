package wsw.github.io.lessgenerator;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class LessGeneratorApplication {

	/**
	 * <p>
	 * 读取控制台内容
	 * </p>
	 */
	public static String scanner(String tip) {
		Scanner scanner = new Scanner(System.in);
		StringBuilder help = new StringBuilder();
		help.append("请输入" + tip + "：");
		System.out.println(help.toString());
		if (scanner.hasNext()) {
			String ipt = scanner.next();
			if (StringUtils.isNotEmpty(ipt)) {
				return ipt;
			}
		}
		throw new MybatisPlusException("请输入正确的" + tip + "！");
	}

	public static void main(String[] args) {
		// 代码生成器
		AutoGenerator mpg = new AutoGenerator();

		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		String projectPath = System.getProperty("user.dir");
		gc.setOutputDir(projectPath);
		gc.setAuthor("wsw");
		gc.setOpen(false);
		gc.setServiceName("%sService");
		mpg.setGlobalConfig(gc);

		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setUrl("jdbc:mysql://localhost:3306/less?useUnicode=true&useSSL=false&characterEncoding=utf8");
		// dsc.setSchemaName("public");
		dsc.setDriverName("com.mysql.cj.jdbc.Driver");
		dsc.setUsername("root");
		dsc.setPassword("123456");
		mpg.setDataSource(dsc);

		// 包配置
		PackageConfig pc = new PackageConfig();
		pc.setParent("wsw.github.io");
		pc.setEntity("less.dao.entity");
		pc.setMapper("less.dao.mapper");
		pc.setService("less.service");
		pc.setServiceImpl("less.service.impl");
		mpg.setPackageInfo(pc);

		// 自定义配置
		InjectionConfig cfg = new InjectionConfig() {
			@Override
			public void initMap() {
				// to do nothing

			}
		};


        String templatePath = "/templates/mapper.xml.vm";
        String entityPath = "/templates/entity.java.vm";
        String servicePath = "/templates/service.java.vm";
        String serviceImplPath = "/templates/serviceImpl.java.vm";
        String mapperPath = "/templates/mapper.java.vm";

		// 自定义输出配置
		List<FileOutConfig> focList = new ArrayList<>();
		// 自定义配置会被优先输出
		focList.add(new FileOutConfig(templatePath) {
			@Override
			public String outputFile(TableInfo tableInfo) {
				// 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
				return projectPath + "/less-dao/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper.xml";
			}
		});
        focList.add(new FileOutConfig(entityPath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/less-dao/src/main/java/wsw/github/io/less/dao/entity/" + tableInfo.getEntityName() + ".java";
            }
        });
        focList.add(new FileOutConfig(mapperPath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/less-dao/src/main/java/wsw/github/io/less/dao/mapper/" + tableInfo.getEntityName() + "Mapper.java";
            }
        });
        focList.add(new FileOutConfig(servicePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/less-service/src/main/java/wsw/github/io/less/service/" + tableInfo.getEntityName() + "Service.java";
            }
        });
        focList.add(new FileOutConfig(serviceImplPath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/less-service/src/main/java/wsw/github/io/less/service/impl/" + tableInfo.getEntityName() + "ServiceImpl.java";
            }
        });
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录");
                return false;
            }
        });
        */
		cfg.setFileOutConfigList(focList);
		mpg.setCfg(cfg);

		// 配置模板
		TemplateConfig templateConfig = new TemplateConfig();

		templateConfig.setXml(null);
		mpg.setTemplate(templateConfig);

		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		strategy.setNaming(NamingStrategy.underline_to_camel);
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);
		strategy.setEntityLombokModel(true);
		strategy.setRestControllerStyle(true);
		strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
		strategy.setSuperEntityColumns("id");
		strategy.setControllerMappingHyphenStyle(true);
		mpg.setStrategy(strategy);
		mpg.setTemplateEngine(new VelocityTemplateEngine());
		mpg.execute();
	}

}
