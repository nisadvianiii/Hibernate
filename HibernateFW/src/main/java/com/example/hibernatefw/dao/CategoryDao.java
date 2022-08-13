package com.example.hibernatefw.dao;

import com.example.hibernatefw.model.CategoryEntity;
import com.example.hibernatefw.utility.HiberUtility;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public class CategoryDao implements DaoInterface<CategoryEntity>{
    @Override
    public ObservableList<CategoryEntity> getData() {
        ObservableList<CategoryEntity> clist = FXCollections.observableArrayList();
        Session s = HiberUtility.getSession();
        CriteriaBuilder bob = s.getCriteriaBuilder();
        CriteriaQuery<CategoryEntity> q = bob.createQuery(CategoryEntity.class);
        q.from(CategoryEntity.class);
        clist.addAll(s.createQuery(q).getResultList());
        s.close();
        return clist;
    }

    @Override
    public void addData(CategoryEntity data) {
        Session s = HiberUtility.getSession();
        s.beginTransaction();
        s.save(data);
        s.getTransaction().commit();
        s.close();
    }

    @Override
    public void setData(CategoryEntity data) {
        Session s = HiberUtility.getSession();
        s.beginTransaction();
        s.update(data);
        s.getTransaction().commit();
        s.close();
    }

    @Override
    public void delData(CategoryEntity data) {
        Session s = HiberUtility.getSession();
        s.beginTransaction();
        s.delete(data);
        s.getTransaction().commit();
        s.close();
    }
}
