package com.haier.codegenerate;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.mysql.cj.jdbc.Driver;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static com.baomidou.mybatisplus.generator.config.rules.DateType.TIME_PACK;

/**
 * 代码生成工具
 * <p>
 * 参考文档：
 * Mybatis-Plus :https://mybatis.plus/
 * 代码生成器：https://mybatis.plus/guide/generator.html
 * 原模板：https://gitee.com/baomidou/mybatis-plus/tree/3.0/mybatis-plus-generator/src/main/resources/templates
 */
public class CodeGenerator {

    public static void main(String[] args) {
        //代码生成
        codeGenerator();
    }

    private static void codeGenerator() {
        //定义变量
        String deleted = "deleted";//逻辑删除字段
        String version = "version";//乐观锁字段
        DbType dbType = DbType.MYSQL;//数据库类型
        String[] tablePrefix = new String[]{"t_", "v_"};//要去掉的表前缀
        String outDir = System.getProperty("user.dir" ) + "\\code";//代码输出目录
        String moduleName = "";//模块名
        String parentPackage = "";//父包名
        String tableName = "";//表名
        //数据源
        String url;
        String user;
        String password;


        //订单中心 测试
//        String url = "jdbc:mysql://rm-xxx.mysql.rds.aliyuncs.com:3310/jsh_order?useAffectedRows=true&useUnicode=true&characterEncoding=utf8&allowMultiQueries=true&serverTimezone=GMT%2B8";
//        String user = "xxx";
//        String password = "xxx";

        //hibp 测试
        url = "jdbc:mysql://rm-xxx.mysql.rds.aliyuncs.com:3333/hibp_user";
        user = "xxx";
        password = "xxx";
        parentPackage = "com.haier.xxx.user.dao";


        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("请输入要生成代码的表名：" );
            tableName = scanner.nextLine();
            if (StrUtil.isEmpty(tableName)) {
                System.out.println("输入不能为空！" );
                break;
            }
            //生成工具对象
            AutoGenerator generator = new AutoGenerator();
            //注意！引擎默认Velocity 如果您选择了非默认引擎，需要在 AutoGenerator 中 设置模板引擎。
//            generator.setTemplateEngine(new FreemarkerTemplateEngine());//Freemarker
//            generator.setTemplateEngine(new BeetlTemplateEngine());//Beetl
//            generator.setTemplateEngine(new CustomTemplateEngine());//自定义

            //配置文档：https://mybatis.plus/config/generator-config.html#%E4%BB%A3%E7%A0%81%E7%94%9F%E6%88%90%E5%99%A8%E9%85%8D%E7%BD%AE
            // 全局策略配置
            String author = "自动生成";//开发人员
            generator.setGlobalConfig(
                    new GlobalConfig()
                            .setOutputDir(outDir)//输出目录
                            .setFileOverride(true)//是否覆盖已有文件
                            .setOpen(false)//是否打开输出目录
                            .setEnableCache(false)//是否在xml中添加二级缓存配置
                            .setAuthor(author)//开发人员
                            .setKotlin(false) //开启 Kotlin 模式
                            .setSwagger2(true)//开启 swagger2 模式
                            .setActiveRecord(true)// 开启 activeRecord 模式
                            .setBaseResultMap(true)//开启 BaseResultMap  XML ResultMap
                            .setBaseColumnList(true)//开启 baseColumnList XML columList
                            .setDateType(TIME_PACK)//时间类型对应策略  TIME_PACK:使用 java.time包下的java8 新的时间类型
                            .setIdType(IdType.AUTO)//指定生成的主键的ID类型 AUTO:数据库ID自增
                            // 自定义文件命名，注意 如下配置 %s 为占位符
                            .setEntityName("%s" )//实体命名方式
                            .setMapperName("%sMapper" )//mapper 命名方式
                            .setXmlName("%sMapper" )//Mapper xml 命名方式
//                            .setServiceName("%sService")//service 命名方式
//                            .setServiceImplName("%sServiceImpl")//service impl 命名方式
                            .setServiceImplName("%sService" )//目前都无需解耦，干掉无用service impl，单个service
                            .setControllerName("%sController" )//controller 命名方式
            );
            //策略配置 数据库表配置，通过该配置，可指定需要生成哪些表或者排除哪些表
            generator.setStrategy(
                    new StrategyConfig()
                            .setCapitalMode(true)//是否大写命名
                            .setSkipView(true)//是否跳过视图
                            .setNaming(NamingStrategy.underline_to_camel)// 表名生成策略  underline_to_camel: 下划线转驼峰命名
//                            .setColumnNaming(NamingStrategy.underline_to_camel)//数据库表字段映射到实体的命名策略, 未指定按照 naming 执行
                            .setTablePrefix(tablePrefix)//要去掉的表前缀
//                            .setFieldPrefix("")//字段前缀
//                            .setSuperEntityClass()//自定义继承的Entity类全称，带包名
//                            .setSuperEntityColumns("")//自定义基础的Entity类，公共字段
//                            .setSuperMapperClass()//自定义继承的Mapper类全称，带包名
//                            .setSuperServiceClass()//自定义继承的Service类全称，带包名
//                            .setSuperServiceImplClass()//自定义继承的ServiceImpl类全称，带包名
//                            .setSuperControllerClass()//自定义继承的Controller类全称，带包名
//                            .setEnableSqlFilter()//默认激活进行sql模糊表名匹配 关闭之后likeTable与notLikeTable将失效，include和exclude将使用内存过滤
                            .setInclude(tableName)//需要包含的表名，当enableSqlFilter为false时，允许正则表达式（与exclude二选一配置）
//                            .setExclude()//需要排除的表名，当enableSqlFilter为false时，允许正则表达式
//                            .setLikeTable()//模糊匹配表名（与notLikeTable二选一配置）
//                            .setNotLikeTable()//模糊排除表名
//                            .setEntityColumnConstant()//【实体】是否生成字段常量（默认 false）
                            .setChainModel(true)//【实体】是否为链式模型（默认 false）)
                            .setEntityLombokModel(true)//【实体】是否为lombok模型（默认 false）
                            .setEntityBooleanColumnRemoveIsPrefix(false)//Boolean类型字段是否移除is前缀（默认 false）
                            .setRestControllerStyle(true)//生成 @RestController 控制器
                            .setEntityTableFieldAnnotationEnable(true)//是否生成实体时，生成字段注解
                            .setVersionFieldName(version)//乐观锁属性名称
                            .setLogicDeleteFieldName(deleted)//逻辑删除属性名称
//                            .setTableFillList()//表填充字段
            );
            //数据源配置，通过该配置，指定需要生成代码的具体数据库
            generator.setDataSource(
                    new DataSourceConfig()
                            .setDbType(dbType) //数据库类型
                            //.setDbQuery()//数据库信息查询类,实现 IDbQuery 接口自定义数据库查询 SQL 语句 定制化返回自己需要的内容,默认由 dbType 类型决定选择对应数据库内置实现
                            //.setSchemaName("")//数据库 schema name
                            //.setTypeConvert()//类型转换 实现 ITypeConvert 接口自定义数据库 字段类型 转换为自己需要的 java 类型，内置转换类型无法满足可实现 IColumnType 接口自定义 默认由 dbType 类型决定选择对应数据库内置实现
                            .setDriverName(Driver.class.getName())//驱动名称
                            .setUrl(url)//数据库连接
                            .setUsername(user)//数据库连接用户名
                            .setPassword(password)//数据库连接密码
            );
            //包名配置，通过该配置，指定生成代码的包路径
            generator.setPackageInfo(
                    new PackageConfig()
                            .setParent(parentPackage)//父包名。如果为空，将下面子包名必须写全部， 否则就只需写子包名
                            .setModuleName(moduleName)//父包模块名
//                            .setEntity()//Entity包名
//                            .setService()//Service包名
                            .setServiceImpl("service" )//Service Impl包名
                            .setMapper("mapper" )//Mapper包名
                            .setXml("mapper" )//Mapper XML包名 设置xml跟mapper同路径
                            .setController("controller" )// Controller包名
//                    .setPathInfo()//路径配置信息
            );
            //模板配置，可自定义代码生成的模板，实现个性化操作
            generator.setTemplate( //todo 自定义模板修改
                    new TemplateConfig()
                            //以下设置模板文件路径即可，设置null则不生成相关代码
                            // 自定义模板配置，模板可以参考源码 /mybatis-plus/src/main/resources/template 拷贝至您项目 src/main/resources/template 目录下，模板名称也可自定义如下配置：
                            .setEntity("mybatis-plus-curd-no-service-impl/entity.java.vm" )//Java 实体类模板
//                            .setEntityKt("")//Kotin 实体类模板
                            .setService(null)//Service 类模板
                            .setServiceImpl("mybatis-plus-curd-no-service-impl/serviceImpl.java.vm" )//Service impl 实现类模板
                            .setMapper("mybatis-plus-curd-no-service-impl/mapper.java.vm" )//mapper 模板
                            .setXml("mybatis-plus-curd-no-service-impl/mapper.xml.vm" )//mapper xml 模板
                            .setController("mybatis-plus-curd-no-service-impl/controller.java.vm" )//controller 控制器模板
            );
            //注入配置，通过该配置，可注入自定义参数等操作以实现个性化操作
            generator.setCfg(
                    new InjectionConfig() {
                        //注入自定义 Map 对象(注意需要setMap放进去)
                        @Override
                        public void initMap() {
                            Map<String, Object> map = new HashMap<>();
//                            map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                            this.setMap(map);
                        }
                    }
//                    .setMap()//自定义返回配置 Map 对象
//                    .setFileOutConfigList()//配置 FileOutConfig 指定模板文件、输出文件达到自定义文件生成目的
//                    .setFileCreate()//自定义判断是否创建文件 实现 IFileCreate 接口   该配置用于判断某个类是否需要覆盖创建，当然你可以自己实现差异算法 merge 文件
            );
            //执行代码生成
            generator.execute();
        }//while end

    }
}
