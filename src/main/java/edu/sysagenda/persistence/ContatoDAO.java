package edu.sysagenda.persistence;

import edu.sysagenda.model.entity.Contato;
import edu.sysagenda.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ContatoDAO {

    public void adicionar(Contato contato) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.save(contato);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Contato> listarTodos() {
        try (Session session = HibernateUtil.getSession()) {
            return session.createQuery("from Contato", Contato.class).list();
        }
    }

    public void atualizar(Contato contato) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.update(contato);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void removerPorId(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            Contato contato = session.get(Contato.class, id);
            if (contato != null) {
                session.delete(contato);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}
