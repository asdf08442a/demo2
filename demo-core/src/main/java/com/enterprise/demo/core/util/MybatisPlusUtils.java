package com.enterprise.demo.core.util;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import java.io.File;

/**
 * 代码生成
 *
 * @author jinzhengang
 * @create 2018-03-14 10:12
 **/
public class MybatisPlusUtils {

  public static void main(String[] args) {
    // 定义需生成模块
    shell("demo-core");
  }

  private static void shell(String model) {
    File file = new File(model);
    String path = file.getAbsolutePath();
    AutoGenerator mpg = new AutoGenerator();
    // 全局配置
    GlobalConfig gc = new GlobalConfig();
    gc.setOutputDir(path + "/src/main/java");
    gc.setFileOverride(true);
    gc.setActiveRecord(false);
    gc.setEnableCache(false);// XML 二级缓存
    gc.setBaseResultMap(true);// XML ResultMap
    gc.setBaseColumnList(true);// XML columList
    gc.setAuthor("jinzhengang");

    // 自定义文件命名，注意 %s 会自动填充表实体属性！
    gc.setMapperName("%sMapper");
    gc.setXmlName("%sMapper");
    gc.setServiceName("%sService");
    gc.setServiceImplName("%sServiceImpl");
    gc.setControllerName("%sController");
    mpg.setGlobalConfig(gc);

    // 数据源配置
    DataSourceConfig dsc = new DataSourceConfig();
    dsc.setDbType(DbType.MYSQL);
    dsc.setDriverName("com.mysql.jdbc.Driver");
    dsc.setUsername("root");
    dsc.setPassword("123456");
    dsc.setUrl("jdbc:mysql://192.168.196.128:3306/demo?useUnicode=true&characterEncoding=utf8");
    mpg.setDataSource(dsc);

    // 策略配置
    StrategyConfig strategy = new StrategyConfig();
    strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
    mpg.setStrategy(strategy);

    // 包配置
    PackageConfig pc = new PackageConfig();
    pc.setParent("com.enterprise.demo");
    pc.setXml("core.dao.xml");
    pc.setMapper("core.dao");
    pc.setEntity("core.entity");
    pc.setServiceImpl("core.service.impl");
    pc.setService("core.service");
    pc.setController("core.controller");
    mpg.setPackageInfo(pc);

    // 控制生成
    TemplateConfig tc = new TemplateConfig();
    tc.setController(null);
//        tc.setService(null);
//        tc.setServiceImpl(null);
    mpg.setTemplate(tc);

    // 执行生成
    mpg.execute();
  }
}
