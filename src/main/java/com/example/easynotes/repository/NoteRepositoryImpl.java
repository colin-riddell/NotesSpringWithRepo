package com.example.easynotes.repository;

import com.example.easynotes.model.Note;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

public class NoteRepositoryImpl implements NoteRepositoryCustom {

    /* Get the entity manager from */
    @Autowired
    EntityManager entityManager;


    @Transactional
    public List<Note> noteByTitle(String title){
        List<Note> result = null;
        Session session = entityManager.unwrap(Session.class);

        try {
            Criteria cr = session.createCriteria(Note.class);
            cr.add(Restrictions.eq("title", title));
            result = (List<Note>) cr.list();
        }catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }

}
