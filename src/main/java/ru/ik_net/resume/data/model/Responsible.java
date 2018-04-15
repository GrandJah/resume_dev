package ru.ik_net.resume.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Igor Kovalkov
 * @link http://ik-net.ru
 * 15.04.2018
 */
@Entity
@Table(name = "responsible")
public class Responsible extends AbstractEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_GEN")
    @SequenceGenerator(name = "ID_GEN", sequenceName = "ID_SEQ", allocationSize = 1)
    @Column(unique = true, nullable = false)
    private int id;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
