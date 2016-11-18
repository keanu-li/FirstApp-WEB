package me.firstapp.common.json;

public class PageObject<T> extends AbstractJsonObject {

	private static final long serialVersionUID = 1248532128639785995L;

	// 分页对象
	private JsonPage<T> page;

	public JsonPage<T> getPage() {
		return page;
	}

	public void setPage(JsonPage<T> page) {
		this.page = page;
	}

}
