package com.book_store.domain;

public class CartItem {
	private Book book;//���ﳵ��ŵ��鼮
	private int count;//��Ŵ��������
	
	public CartItem(Book book,int count) {
		this.book=book;
		this.count=count;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
}
