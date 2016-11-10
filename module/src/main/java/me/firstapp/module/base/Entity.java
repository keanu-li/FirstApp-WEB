package me.firstapp.module.base;

public interface Entity<T> {
	public T getId();

	public void setId(T id);
}
