package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Producto;

public class RegistrarProducto {
	
	public static void main(String[] args) {
		
	
	EntityManagerFactory fabrica= Persistence.createEntityManagerFactory("mysql");
	
	EntityManager em= fabrica.createEntityManager();
	
	Producto p = new Producto();
	
	p.setIdprod("P0032");
	p.setDescripcion("Cetirizina");
	p.setStock(30);
	p.setPrecio(9.50);
	p.setIdcategoria(1);
	p.setEstado(1);
	
	em.getTransaction().begin();
	
	em.persist(p);
	
	em.getTransaction().commit();
	
	System.out.println("Registro exitoso");
	
	em.close();
	
	
}

}
