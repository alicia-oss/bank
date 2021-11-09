package com.bank;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;

public class AutoCode {
  public static void main(String[] args) {
    //构建一个代码自动生成器对象
    AutoGenerator mpg = new AutoGenerator();

    //配置策略

    //1.全局配置
    final GlobalConfig gc = new GlobalConfig();
    String projectPath = System.getProperty("user.dir");
    gc.setOutputDir(projectPath+"/src/main/java");
    gc.setAuthor("使命必达");
    gc.setOpen(false);//
    gc.setFileOverride(false);//是否覆盖
    gc.setServiceName("%sService");
    gc.setIdType(IdType.AUTO);
    gc.setDateType(DateType.ONLY_DATE);
    gc.setSwagger2(true);
    mpg.setGlobalConfig(gc);


    //2.设置数据源
    DataSourceConfig dsc = new DataSourceConfig();
    dsc.setUrl("jdbc:mysql://localhost:3306/bank?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8");
    // dsc.setSchemaName("public");
    dsc.setDriverName("com.mysql.cj.jdbc.Driver");
    dsc.setUsername("root");
    dsc.setPassword("root");
    dsc.setDbType(DbType.MYSQL);
    mpg.setDataSource(dsc);

    //3.包
    PackageConfig pc = new PackageConfig();
    pc.setModuleName("bank");
    pc.setParent("com");
    pc.setEntity("pojo");
    pc.setService("service");
    pc.setMapper("mapper");
    pc.setController("controller");
    mpg.setPackageInfo(pc);

    //4.策略配置
    // 策略配置
    StrategyConfig strategy = new StrategyConfig();
    strategy.setInclude("role");
    strategy.setNaming(NamingStrategy.underline_to_camel);
    strategy.setColumnNaming(NamingStrategy.underline_to_camel);
    //strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
    strategy.setEntityLombokModel(true);
    strategy.setRestControllerStyle(true);
    // 公共父类
    // strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
    // 写于父类中的公共字段
    //strategy.setSuperEntityColumns("id");
    strategy.setControllerMappingHyphenStyle(true);  //sds_ddd
    //strategy.setTablePrefix(pc.getModuleName() + "_");

    strategy.setLogicDeleteFieldName("deleted");
    TableFill gmt_create = new TableFill("create_time", FieldFill.INSERT);
    TableFill gmt_modified = new TableFill("update_time", FieldFill.INSERT_UPDATE);
    ArrayList<TableFill> tableFills = new ArrayList<>();
    tableFills.add(gmt_create);
    tableFills.add(gmt_modified);
    strategy.setTableFillList(tableFills);
    mpg.setStrategy(strategy);

    mpg.execute();
  }
}
