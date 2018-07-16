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