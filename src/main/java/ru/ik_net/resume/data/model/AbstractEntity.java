package ru.ik_net.resume.data.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Igor Kovalkov
 * @link http://ik-net.ru
 * 15.04.2018
 */
public abstract class AbstractEntity<T> implements Serializable {
    public abstract T getId();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractEntity<?> that = (AbstractEntity<?>) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return String.format("%S[id-%s]", getClass().getSimpleName(), getId());
    }
}
