package com.tianjie.study.gennerator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.apache.commons.lang3.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @version v1.0
 * @description TODO
 * @auth tian.jie
 * @date {date} {time}
 */
public class CodeGenerator {

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
        //用来获取Mybatis-Plus.properties文件的配置信息
        AutoGenerator  mpg = new AutoGenerator();


        GlobalConfig gc = new GlobalConfig();

        gc.setOutputDir(System.getProperty("user.dir"));


        gc.setFileOverride(true);
        gc.setActiveRecord(true);// 开启 activeRecord 模式
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(false);// XML columList
        gc.setAuthor("tian.jie");
        gc.setSwagger2(true);
        mpg.setGlobalConfig(gc);


        String url = "jdbc:mysql://192.168.11.250:10144/cpm_prj";
        String userName ="root";
        String passWord ="9b89f743";

        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setTypeConvert(new MySqlTypeConvert());
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername(userName);
        dsc.setDbType(DbType.MYSQL);
        dsc.setPassword(passWord);
        dsc.setUrl(url);
        mpg.setDataSource(dsc);


// 策略配置
        StrategyConfig strategy = new StrategyConfig();
//strategy.setTablePrefix(new String[] { "bmd_", "mp_" });// 此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        List<String> tables = getTables(url,userName,passWord);
        strategy.setInclude(tables.toArray(new String[tables.size()]));// 需要生成的表
// 字段名生成策略
// strategy.setf(NamingStrategy.underline_to_camel);
//strategy.setSuperServiceImplClass("com.baomidou.springwind.service.support.BaseServiceImpl");
        mpg.setStrategy(strategy);


// 包配置
        PackageConfig pc = new PackageConfig();
// pc.setModuleName("test");
        pc.setParent("com.rongzer.rdp.cpm.common.model");// 自定义包路径
        pc.setController("controller");// 这里是控制器包名，默认 web
        pc.setEntity("mq");
        pc.setMapper("dao");
        pc.setXml("mapping");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        mpg.setPackageInfo(pc);
// 执行生成
        mpg.execute();
    }


    /**
     *
     * @param Url 数据库连接url
     * @param username 用户名
     * @param passWord 密码
     * @return
     */
    private static List<String> getTables(String Url,String username,String passWord){
        List<String> tablenames = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("加载驱动成功！");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("加载驱动失败！");
        }

        Connection con = null;
        try {
            //获取数据库连接
            con = DriverManager.getConnection(Url, username, passWord);
            System.out.println("获取数据库连接成功！");
            System.out.println("进行数据库操作！");
            DatabaseMetaData databaseMetaData = con.getMetaData();
            ResultSet rs = databaseMetaData.getTables(null, null, null, new String[]{"TABLE"});
            while(rs.next()){
                String tableName = rs.getString("TABLE_NAME");
                tablenames.add(tableName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("获取数据库连接失败！");
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        tablenames.remove("trace_xe_event_map");
        tablenames.remove("trace_xe_action_map");
        return tablenames;
    }
}
