package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Producto;

public class EliminaProducto {
	
	public static void main(String[] args) {
		
		EntityManagerFactory fabrica= Persistence.createEntityManagerFactory("mysql");
		
		EntityManager em= fabrica.createEntityManager();
		
		Producto p= em.find(Producto.class, "P0032");
		
		if(p==null) {
			System.out.println("IdProducto no existe");
		}else {
			
			em.getTransaction().begin();
			em.remove(p);
			em.getTransaction().commit();
			System.out.println("Eliminación exitosa");
			
			
		}
		em.close();
	}

}
