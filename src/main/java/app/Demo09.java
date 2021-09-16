package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Usuario;

public class Demo09 {

	public static void main(String[] args) {
		//obtener la conexion -> según la unidad de persistencia
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		
		//crear los DAO usando fabrica
		EntityManager em= fabrica.createEntityManager();
		
		//proceso : Validar un Usuario segun su usuario y clave usando SP
		
		String sql2= "{call usp_validaAccesos(:xusr, :xcla)}";//JPA
		
		//TypedQuery<Usuario>query = em.createQuery(sql2, Usuario.class);  JPA
		Query query= em.createNativeQuery(sql2,Usuario.class); //Object
		
		query.setParameter("xusr", "U001@gmail.com");
		query.setParameter("xcla", "10001");
		
		Usuario u= null;
		
	try {
		
		u = (Usuario)query.getSingleResult();
		System.out.println("Bienvenido : "+ u.getNombre() );
		System.out.println(u);
		
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
