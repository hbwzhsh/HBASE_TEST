package com.ande.temp;

import java.util.ArrayList;
import java.util.List;

/**
 * 封装的echarts类
 * 
 * 类功能说明：
 *	为简单起见，只封装x轴数据和c
 *
 *	xAxis 	直角坐标系中的横轴，通常并默认为类目型 
 *  series 	数据系列，一个图表可能包含多个系列，每一个系列可能包含多个数据 
 * @author Ande,微博@阿里七哥，微信:Alilangman
 * @version 
 *       1.0 , 2016年3月8日 下午9:08:35
 * 
 *  教师是人类灵魂的工程师
 */
public class Echarts {
	//横坐标数据
	private List<String> category = new ArrayList<String>();
	
	//series的data数据
	private List<Float> seriesData = new ArrayList<Float>();//这里要用int 不能用String 不然前台显示不正常

	public List<String> getCategory() {
		return category;
	}

	public void setCategory(List<String> category) {
		this.category = category;
	}

	public List<Float> getSeriesData() {
		return seriesData;
	}

	public void setSeriesData(List<Float> seriesData) {
		this.seriesData = seriesData;
	}
	
}
