package fr.eni.dal;

import java.util.List;

import fr.eni.bo.Personne;


public interface PersonneDal 
{
	public void insert(final Personne personne) throws DALException;
	public List<Personne> getList() throws DALException;
	public Personne getById(final int id) throws DALException;
	public void update(final Personne personne) throws DALException;
	public void delete(final Personne personne) throws DALException;

}
