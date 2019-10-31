package com.good.one.reconsitution.one;

public class Movie {
	
	// 儿童片
	public static final int CHILDRENS = 2;
	
	// 普通片
	public static final int REGULAR = 0;
	
	// 新片
	public static final int NEW_RELEASE = 1;
	
	// 名字
	private String _title;

	// 价格
	private int _priceCode;

	
	public Movie(String _title, int _priceCode) {
		super();
		this._title = _title;
		this._priceCode = _priceCode;
	}

	public String get_title() {
		return _title;
	}

	public void set_title(String _title) {
		this._title = _title;
	}

	public int get_priceCode() {
		return _priceCode;
	}

	public void set_priceCode(int _priceCode) {
		this._priceCode = _priceCode;
	}
	
	
	
}
