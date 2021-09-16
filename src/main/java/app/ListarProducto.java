package app;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;


import model.Producto;

public class ListarProducto {
	
	public static void main(String[] args) {
		
		EntityManagerFactory fabrica= Persistence.createEntityManagerFactory("mysql");
		
		EntityManager em= fabrica.createEntityManager();
		
		TypedQuery<Producto>query=em.createQuery("select a from Producto a",Producto.class);
		
		
		List<Producto>lstProducto=query.getResultList();
		
		for(Producto p:lstProducto) {
			System.out.println(">>>>>>>>> " + p);
		}

	
	}
}
