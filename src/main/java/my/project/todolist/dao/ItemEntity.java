package my.project.todolist.dao;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Nikol on 1/5/2017.
 */
@Entity
@Table(name = "item", schema = "public", catalog = "TodoList")
public class ItemEntity {
    private int id;
    private String desc;
    private Integer created;
    private Boolean done;

    @Basic
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "desc")
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Basic
    @Column(name = "created")
    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    @Basic
    @Column(name = "done")
    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemEntity that = (ItemEntity) o;

        if (id != that.id) return false;
        if (desc != null ? !desc.equals(that.desc) : that.desc != null) return false;
        if (created != null ? !created.equals(that.created) : that.created != null) return false;
        if (done != null ? !done.equals(that.done) : that.done != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (desc != null ? desc.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (done != null ? done.hashCode() : 0);
        return result;
    }
}
