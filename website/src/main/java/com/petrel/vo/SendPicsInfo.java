package com.petrel.vo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

public class SendPicsInfo {
	private String Count;
	private List<Item> item;

	@XmlElement(name="Count")
	public String getCount() {
		return Count;
	}
	public void setCount(String count) {
		Count = count;
	}
	@XmlElementWrapper(name="PicList")
	@XmlElement(name="item")
	public List<Item> getItem() {
		return item;
	}
	public void setItem(List<Item> item) {
		this.item = item;
	}

	
	
	
}
