package fr.motaz.rasp.music.storage;

import java.util.List;

public interface Storage<T> {
	public List<T> getAll();
	public void add(T object);
 }
