package com.dao;

import java.util.List;

public abstract class DAO<T> {
	
	private DAOFactory daoFactory;

	DAO(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public DAOFactory getDaoFactory() {
		return this.daoFactory;
	}

	public abstract void creer(T objet);
	public abstract List<T> trouver(T objet);
	public abstract void modifier(T objet);
	public abstract void supprimer(T objet);
	public abstract List<T> lister();

}