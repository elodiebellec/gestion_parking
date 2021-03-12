package fr.eni.dal;

import java.util.List;

import fr.eni.bo.Voiture;


public interface VoitureDal 
{
	public void insert(final Voiture voiture) throws DALException;
	public List<Voiture> getList() throws DALException;
	public Voiture getById(final int id) throws DALException;
	public void update(final Voiture voiture) throws DALException;
	public void delete(final Voiture voiture) throws DALException;
}
