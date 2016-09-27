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
    
    //@Test
    public void test15(){
        
        EntityManager em=Persistence.createEntityManagerFactory("PU").createEntityManager();
        Film l=   (Film) em.createQuery("SELECT f FROM Film f JOIN f.genre g JOIN f.realisateurs r WHERE g.nom='Horreur' AND r.nom='Polanski' ").getSingleResult();
        
        
        System.out.println(l.getTitre());
        Assert.assertEquals("Le bal des vampires", l.getTitre());
        
    }
    
    //@Test
    public void test17(){
        EntityManager em=Persistence.createEntityManagerFactory("PU").createEntityManager();
        long l= (long) em.createQuery("SELECT COUNT(f) FROM Film f JOIN f.realisateurs r WHERE r.nom='Coen' AND r.prenom='Joel'").getSingleResult();
        Assert.assertEquals(2L, l);
    }
    
    //@Test
    public void test19(){
        EntityManager em=Persistence.createEntityManagerFactory("PU").createEntityManager();
        long l= (long) em.createQuery("SELECT COUNT(f) FROM Film f JOIN f.realisateurs r WHERE r.nom='Coen'").getSingleResult();
        Assert.assertEquals(4L, l);
        
    }
    
    //@Test
    public void test21(){
        EntityManager em=Persistence.createEntityManagerFactory("PU").createEntityManager();
        long l= (long) em.createQuery("SELECT COUNT(f) FROM Film f JOIN f.realisateurs r1 JOIN f.realisateurs r2 JOIN f.acteurs a  WHERE r1.nom='Coen' AND r1.prenom='Ethan' AND r2.prenom='Joel' AND a.nom='Buscemi'").getSingleResult();
        Assert.assertEquals(2L, l);
        
    }
    
    //@Test
    public void test23(){
        EntityManager em=Persistence.createEntityManagerFactory("PU").createEntityManager();
        long l= (long) em.createQuery("SELECT COUNT(f) FROM Film f JOIN f.realisateurs r JOIN f.acteurs a JOIN f.genre g WHERE r.nom='Coen' AND a.nom='Buscemi' AND g.nom='Policier'").getSingleResult();
        Assert.assertEquals(2L, l);
        
    }
    
    //@Test
    public void test25(){
        EntityManager em=Persistence.createEntityManagerFactory("PU").createEntityManager();
        long l = (long) em.createQuery("SELECT COUNT(s) FROM Saison s JOIN s.serie r WHERE r.titre='Dexter'").getSingleResult();
        //s.serie.titre une autre facon sans la jointure
        Assert.assertEquals(8L, l);
    }
    
    //@Test
    public void test27(){
        EntityManager em=Persistence.createEntityManagerFactory("PU").createEntityManager();
        long l = (long) em.createQuery("SELECT COUNT(e) FROM Episode e JOIN e.saison s JOIN s.serie r WHERE r.titre='Dexter' AND s.numSaison='8'").getSingleResult();
        Assert.assertEquals(12L, l);//e.saison.serie.titre=dexter
        
    }
    
    //@Test
    public void test29(){
        EntityManager em=Persistence.createEntityManagerFactory("PU").createEntityManager();
        long l = (long) em.createQuery("SELECT COUNT(e) FROM Episode e JOIN e.saison s JOIN s.serie r WHERE r.titre='Dexter'").getSingleResult();
        Assert.assertEquals(96L, l);
        
    }
    
   // @Test
    public void test31(){
        EntityManager em=Persistence.createEntityManagerFactory("PU").createEntityManager();
        long l = (long) em.createQuery("SELECT COUNT(l) FROM Lien l JOIN l.film f JOIN f.pays p JOIN f.genre g WHERE p.nom='USA' AND g.nom='Policier'").getSingleResult();
        Assert.assertEquals(3L, l);
        
    }
    
    //@Test
    public void test33(){
        EntityManager em=Persistence.createEntityManagerFactory("PU").createEntityManager();
        long l = (long) em.createQuery("SELECT COUNT(l) FROM Lien l JOIN l.film f JOIN f.acteurs a JOIN f.genre g WHERE a.nom='Polanski' AND g.nom='Horreur'").getSingleResult();
        Assert.assertEquals(1L, l);
        
    }
    
   //@Test
    public void test35(){
        EntityManager em=Persistence.createEntityManagerFactory("PU").createEntityManager();
        List<Film> l =(List<Film>) em.createQuery("SELECT COUNT(f) FROM Film f JOIN f.genre g WHERE g.nom='Horreur' EXCEPT SELECT COUNT(f) FROM Film f JOIN f.acteurs a WHERE a.nom='Polanski'").getResultList();
        Assert.assertEquals(0L, l.size());
        Assert.assertTrue(l.size()==0);
        Assert.assertTrue(l.isEmpty());
        
    }
    
    //@Test
    public void test37(){
        EntityManager em=Persistence.createEntityManagerFactory("PU").createEntityManager();
        //long l = (long) em.createQuery("SELECT COUNT(f) FROM Film f JOIN f.acteurs a WHERE a.nom='Polanski'").getSingleResult();
        long l = (long) em.createQuery("SELECT COUNT(f) FROM Film f INTERSECT SELECT f FROM Film f JOIN f.acteurs a WHERE a.nom='Polanski'").getSingleResult();
        Assert.assertEquals(1L, l);
        
    }
    
    //@Test
    public void test39(){
        EntityManager em=Persistence.createEntityManagerFactory("PU").createEntityManager();
        List<Film> l = (List<Film>) em.createQuery("SELECT (f) FROM Film f JOIN f.genre g WHERE g.nom='Horreur' UNION SELECT f FROM Film f JOIN f.acteurs a WHERE a.nom='Polanski'").getResultList();
        Assert.assertEquals(1L, l.size());
    }
     
    
}




