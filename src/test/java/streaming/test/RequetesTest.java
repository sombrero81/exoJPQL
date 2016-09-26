/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package streaming.test;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import junit.framework.Assert;
import org.junit.Test;
import streaming.entity.Film;

/**
 *
 * @author admin
 */
public class RequetesTest {
    
    //constructeur qui sert a rien
   // public RequetesTest() {
   // }
    
    
   // @Test
    public void test1(){
        
        EntityManager em=Persistence.createEntityManagerFactory("PU").createEntityManager();
        
        Query q = em.createQuery("SELECT f FROM Film f WHERE f.id=4");//on cree l'objet query contenant la requete JPQL (JPA)
        
        //execute  la requete en BD on recupere ensuite le record (JPA)
        Film f = (Film) q.getSingleResult();
        
        //assertion sur le titre (Junit)
        Assert.assertEquals("Fargo", f.getTitre());
        
    }
    
   // @Test
    public void test3(){
        
        EntityManager em=Persistence.createEntityManagerFactory("PU").createEntityManager();
        long l = (long) em.createQuery("SELECT COUNT(f) FROM Film f").getSingleResult();
        Assert.assertEquals(4L, l);
    }
    
    
   // @Test
    public void test5(){
        
        EntityManager em=Persistence.createEntityManagerFactory("PU").createEntityManager();
        
        int n =(int) em.createQuery("SELECT MIN(f.annee) FROM Film f").getSingleResult();
        Assert.assertEquals(1968, n);
        
    }
    
   // @Test
    public void test7(){
        
        EntityManager em=Persistence.createEntityManagerFactory("PU").createEntityManager();
        
        long l= (long) em.createQuery("SELECT COUNT(l) FROM Film f JOIN f.liens l WHERE f.titre LIKE '%Big Lebowski%'").getSingleResult();
        Assert.assertEquals(1L, l);
    }
    
   // @Test
    public void test8(){
        EntityManager em=Persistence.createEntityManagerFactory("PU").createEntityManager();
        
        long l= (long) em.createQuery("SELECT COUNT(f) FROM Film f WHERE f.annee='2013'").getSingleResult();
        Assert.assertEquals(1L, l);
        
    }
    
   // @Test 
    public void test9(){
        EntityManager em=Persistence.createEntityManagerFactory("PU").createEntityManager();
        
        long l= (long) em.createQuery("SELECT COUNT(f) FROM Film f JOIN f.realisateurs r WHERE r.nom='Polanski'").getSingleResult();
        Assert.assertEquals(2L, l);
        
    }
    
    //@Test
    public void test11(){
        EntityManager em=Persistence.createEntityManagerFactory("PU").createEntityManager();
        
        long l= (long) em.createQuery("SELECT COUNT(f) FROM Film f JOIN f.acteurs a WHERE a.nom='Polanski'").getSingleResult();
        Assert.assertEquals(1L, l);
    }
    
   // @Test
    public void test13(){
        
        EntityManager em=Persistence.createEntityManagerFactory("PU").createEntityManager();
        
        List<Film> l=  em.createQuery("SELECT f FROM Film f JOIN f.acteurs a JOIN f.realisateurs r WHERE a.nom='Polanski' AND r.nom='Polanski' ").getResultList();
        //System.out.println(l);
        Assert.assertEquals(1L, l.size());
        
    }
    
    @Test
    public void test15(){
        
        EntityManager em=Persistence.createEntityManagerFactory("PU").createEntityManager();
        Film l=   (Film) em.createQuery("SELECT f FROM Film f JOIN f.genre g JOIN f.realisateurs r WHERE g.nom='Horreur' AND r.nom='Polanski' ").getSingleResult();
        
        
        System.out.println(l.getTitre());
        
        
    }
    
    
    
    
}




