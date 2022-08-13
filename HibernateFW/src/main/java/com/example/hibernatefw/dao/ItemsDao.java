package com.example.hibernatefw.dao;

import com.example.hibernatefw.model.ItemsEntity;
import com.example.hibernatefw.utility.HiberUtility;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public class ItemsDao implements DaoInterface<ItemsEntity> {
    @Override
    public ObservableList<ItemsEntity> getData() {
        ObservableList<ItemsEntity> ilist = FXCollections.observableArrayList();
        Session s = HiberUtility.getSession();
        CriteriaBuilder bob = s.getCriteriaBuilder();
        CriteriaQuery<ItemsEntity> q = bob.createQuery(ItemsEntity.class);
        q.from(ItemsEntity.class);
        ilist.addAll(s.createQuery(q).getResultList());
        s.close();
        return ilist;
    }

    @Override
    public void addData(ItemsEntity data) {
        Session s = HiberUtility.getSession();
        s.beginTransaction();
        s.save(data);
        s.getTransaction().commit();
        s.close();
    }

    @Override
    public void setData(ItemsEntity data) {
        Session s = HiberUtility.getSession();
        s.beginTransaction();
        s.update(data);
        s.getTransaction().commit();
        s.close();
    }

    @Override
    public void delData(ItemsEntity data) {
        Session s = HiberUtility.getSession();
        s.beginTransaction();
        s.delete(data);
        s.getTransaction().commit();
        s.close();
    }
}
