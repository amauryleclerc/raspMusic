package fr.aleclerc.rasp.music.storage;

import java.util.List;

public interface IStorage<T> {
	public List<T> getAll();
	public void add(T object);
 }
