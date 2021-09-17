package app;



import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import lombok.experimental.var;
import model.Usuario;

public class Demo08 {

	public static void main(String[] args) {
		//obtener la conexion -> según la unidad de persistencia
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		
		//crear los DAO usando fabrica
		EntityManager em= fabrica.createEntityManager();
		
		//proceso : Validar un Usuario segun su usuario y clave
		
		String sql2= "select u from Usuario u where u.usuario = :xusr and u.clave= :xcla";//JPA
		
		TypedQuery<Usuario>query = em.createQuery(sql2, Usuario.class);
		query.setParameter("xusr", "U001@gmail.com");
		query.setParameter("xcla", "10001");
		
		Usuario u= null;
		
	try {
		
		u = query.getSingleResult();
		System.out.println("Bienvenido : "+ u.getNombre() +"\n" +u);
		
		
	} catch (Exception e) {
		System.out.println("Codigo no existe");
		
		
	}
	
	
		
	/*if(u== null) {
		
				System.out.println("Codigo no existe");
		}else
		{
			System.out.println("Bienvenido : "+ u.getNombre() );
			System.out.println(u);
		}*/
		
		em.close();
		
		
	}
	
	
}
