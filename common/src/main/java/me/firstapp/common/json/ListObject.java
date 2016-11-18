package me.firstapp.common.json;

import java.util.List;

public class ListObject<T> extends AbstractJsonObject {

	private static final long serialVersionUID = -8694554282578181597L;

	// 列表对象
	private List<T> items;

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

}
