# Demo Notes App Spring + Hibernate Repository

This shows it's possible to use Spring-Boot with Hibernate and use Springs built in `JpaRepository` for all CRUD and create custom Criteria Queries using EntityManager to get hold of the `session` in order to make use of `Criteria`. See `NotRepositoryImpl.java`

``` java
  /* Get entity manager from spring via injection */
    @Autowired
    EntityManager entityManager;

  /* define our custom query and use the entityManager */
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
    
```

To create custom queries using `criteria` and still extend `JpaRepository` then the folling must be done:
 
 * Create a Repository interface (as normal) for the model. Eg `NoteRepository` for the model `Note`. Extend `JpaRepository` (as normal, too)
 * For CRUD, this doesn't need to be populated with metod signitures - but for custom queries, read on
 * Add method signiture for custom query
 * Create second interface `NoteRepositoryCustom` which defines the same method sig'.
 * Add class `NoteRepositoryImpl` which implements the Custom interface just created.
 * Do the above in the Impl.