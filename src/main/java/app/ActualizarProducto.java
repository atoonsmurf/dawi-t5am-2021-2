package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Producto;

public class ActualizarProducto {

	public static void main(String[] args) {
		
		EntityManagerFactory fabrica= Persistence.createEntityManagerFactory("mysql");
		
		EntityManager em= fabrica.createEntityManager();
		
		Producto p = new Producto();
		
		p.setIdprod("P0032");
		p.setDescripcion("Fortarem");
		p.setStock(50);
		p.setPrecio(7.50);
		p.setIdcategoria(5);
		p.setEstado(1);
		
		em.getTransaction().begin();
		
		em.merge(p);
		
		em.getTransaction().commit();
		
		System.out.println("Actualización exitosa");
		
		em.close();
		
		
	}
	
}
