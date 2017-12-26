package com.ande.chart.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ande.chart.entity.Sales;
import com.ande.chart.entity.China;
import com.ande.chart.entity.Domain;
import com.ande.chart.entity.Sales;
import com.ande.chart.entity.Provinces;
import com.ande.chart.entity.Product;
import com.github.abel533.echarts.Label;
import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.Title;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.LineType;
import com.github.abel533.echarts.code.Magic;
import com.github.abel533.echarts.code.MarkType;
import com.github.abel533.echarts.code.Orient;
import com.github.abel533.echarts.code.PointerType;
import com.github.abel533.echarts.code.SelectedMode;
import com.github.abel533.echarts.code.Tool;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.code.X;
import com.github.abel533.echarts.code.Y;
import com.github.abel533.echarts.data.Data;
import com.github.abel533.echarts.data.PointData;
import com.github.abel533.echarts.feature.MagicType;
import com.github.abel533.echarts.series.Bar;
import com.github.abel533.echarts.series.Funnel;
import com.github.abel533.echarts.series.Line;
import com.github.abel533.echarts.series.Map;
import com.github.abel533.echarts.series.MapLocation;
import com.github.abel533.echarts.series.Pie;
import com.github.abel533.echarts.style.ItemStyle;
import com.github.abel533.echarts.style.LineStyle;


/**
 * 2 服务层代码
 *
 * 2015-6-12下午1,34,50
 *
 *springechart.com.service.EchartsT
 */
public class EchartsT {

    /**
     * 读取json文件
     */
    public String jsonRead(String fullFileName)
    {

        File file = new File(fullFileName);
        Scanner scanner = null;
        StringBuilder buffer = new StringBuilder();
        try {
            scanner = new Scanner(file, "utf-8");
            while (scanner.hasNextLine()) {
                buffer.append(scanner.nextLine());
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println(e);
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        //返回字符串
        return buffer.toString();
    }


    /**
     * @return
     * 堆栈图
     */
    public Option stackYear()
    {
        //Json文件所在的路径
        String fullFileName="./data/year.json";

        //调用读取文件函数
        String data = jsonRead(fullFileName);

        //把字符串转换成Json对象
        JSONObject obj =JSON.parseObject(data);
        //提取json文件里面"product"对应的数组并保存为Json数组
        JSONArray proArr = obj.getJSONArray("product");

        //获取用例说明名称
        String legendName[] = new String[proArr.size()];
        for (int i = 0; i < proArr.size(); i++) {
            //根据"name"获得JOSN数组中的名称
            legendName[i]=proArr.getJSONObject(i).getString("name");
        }

        //PC数据
        //根据"sales"获得JOSN数组
        JSONArray pcArr =JSON.parseArray(proArr.getJSONObject(0).getString("sales").toString());
        //把JSON数组保存为List列表
        List<Sales> pcList = JSON.parseArray(pcArr.toString(), Sales.class);
        //计算列表大小
        int length =pcList.size();
        //保存 年份数组
        String[] pcYear =new String[length];
        //保存 利润数组
        int[] pcProfit = new int[length];
        //获得数据 保存在数组中
        for (int i = 0; i <length; i++) {
            pcYear[i]=pcList.get(i).getYear();
            pcProfit[i]=pcList.get(i).getProfit();
        }

        //SmartPhone数据
        JSONArray spArr =JSON.parseArray(proArr.getJSONObject(1).getString("sales").toString());
        List<Sales> spList =JSON.parseArray(spArr.toString(), Sales.class);

        String[] spYear =new String[length];
        int[] spProfit = new int[length];

        for (int i = 0; i <length; i++) {
            spYear[i]=spList.get(i).getYear();
            spProfit[i]=spList.get(i).getProfit();
        }

        //Software数据
        JSONArray swArr = JSON.parseArray(proArr.getJSONObject(2).getString("sales").toString());
        List<Sales> swList =JSON.parseArray(swArr.toString(), Sales.class);

        String[] swYear =new String[length];
        int[] swProfit = new int[length];

        for (int i = 0; i <length; i++) {
            swYear[i]=swList.get(i).getYear();
            swProfit[i]=swList.get(i).getProfit();
        }

        //定义Option对象
        Option option = new Option();
        //标题栏
        option.title(obj.getString("name"),"纯属虚构");
        option.title().x(X.center);
        //提示框
        option.tooltip().trigger(Trigger.axis);
        option.tooltip().axisPointer().type(PointerType.shadow);
        //工具栏
        option.toolbox().show(true).orient(Orient.vertical).x(X.right).y(Y.center).feature(Tool.mark,Tool.dataView,Tool.dataZoom,Tool.restore,Tool.saveAsImage,new MagicType(Magic.bar,Magic.line,
                Magic.stack,Magic.tiled));
        //用例说明
        option.legend().data(legendName).orient(Orient.horizontal).x(X.right);
        //可计算
        option.calculable(true);
        //x轴-类目轴
        option.xAxis(new CategoryAxis().data(
                pcYear

        ));
        //y轴 - 值轴
        option.yAxis(new ValueAxis());


        //PC柱状体
        Bar pcBar = new Bar(legendName[0]).stack("PC");

        /*bar.data(provinceProfit);*/ //返回的js代码中 data数据比需要的数据会多一对中括号，取不到数据，导致图形不能正常显示
        //这里通过add()逐个添加数据
        for (int i = 0; i < length; i++) {
            pcBar.data().add(pcProfit[i]);
        }

        //smartPhone柱状体
        Bar spBar = new Bar(legendName[1]).stack("SmartPhone");
        for (int i = 0; i < length; i++) {
            spBar.data().add(spProfit[i]);
        }

        //software柱状体
        Bar swBar = new Bar(legendName[2]).stack("Software");

        for (int i = 0; i < length; i++) {
            swBar.data().add(swProfit[i]);
        }

        //设置系列series
        option.series(pcBar,spBar,swBar);
        return option;

    }

    /**
     * @return
     * 堆积图
     */
    public Option stackChart()
    {
        Option option = new Option();

        //标题
        option.title("获取信息途径所占比重");
        //提示框
        option.tooltip().trigger(Trigger.axis);
        option.tooltip().axisPointer().type(PointerType.shadow);
        //图例说明
        option.legend().data("直接访问","邮件营销","联盟广告","视频广告","搜索引擎","百度","谷歌","必应","其他");
        //工具栏
        option.toolbox().show(true).orient(Orient.vertical).x(X.right).y(Y.center)
                .feature(Tool.mark,Tool.dataView,Tool.dataZoom,new MagicType(Magic.bar,Magic.line,Magic.stack,Magic.tiled),
                        Tool.restore,Tool.saveAsImage);

        //是否启用拖拽重计算特性
        option.calculable(true);

        //x轴 -类目轴
        option.xAxis(new CategoryAxis().data("周一","周二","周三","周四","周五","周六","周日"));
        //Y轴 -值轴
        option.yAxis(new ValueAxis());

        //系列 ,其中stack表示堆栈图的设置
        Bar bar1 = new Bar("直接访问");
        bar1.data(320, 332, 301, 334, 390, 330, 320);

        Bar bar2 = new Bar("邮件营销");
        bar2.stack("广告");
        bar2.data(120, 132, 101, 134, 90, 230, 210);

        Bar bar3 = new Bar("联盟广告");
        bar3.stack("广告");
        bar3.data(220, 182, 191, 234, 290, 330, 310);

        Bar bar4 = new Bar("视频广告");
        bar4.stack("广告");
        bar4.data(150, 232, 201, 154, 190, 330, 410);

        Bar bar5 = new Bar("搜索引擎");
        bar5.data(862, 1018, 964, 1026, 1679, 1600, 1570);
        //标记线
        bar5.markLine().itemStyle().normal().lineStyle(new LineStyle().type(LineType.dashed));
        bar5.markLine().data(new PointData().type(MarkType.min),new PointData().type(MarkType.max));

        Bar bar6 = new Bar("百度");
        bar6.stack("搜索引擎");
        bar6.data(620, 732, 701, 734, 1090, 1130, 1120);

        Bar bar7 = new Bar("谷歌");
        bar7.stack("搜索引擎");
        bar7.data(120, 132, 101, 134, 290, 230, 220);

        Bar bar8 = new Bar("必应");
        bar8.stack("搜索引擎");
        bar8.data(60, 72, 71, 74, 190, 130, 110);

        Bar bar9 = new Bar("其他");
        bar9.stack("搜索引擎");
        bar9.data(62, 82, 91, 84, 109, 110, 120);

        //添加到系列中
        option.series(bar1,bar2,bar3,bar4,bar5,bar6,bar7,bar8,bar9);

        return option;
    }

    public static void main(String[] args) {
        //Json文件所在的路径
        String fullFileName ="D:\\software\\AMS\\web\\src\\main\\webapp\\data\\year.json";
        //调用读取文件函数
        EchartsT EchartsT=new EchartsT();
        String data = EchartsT.jsonRead(fullFileName);
        //System.out.println(data);
        Option a=EchartsT.stackYear();
        System.out.println(a.getSeries().get(1).name());
    }

}