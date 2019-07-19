/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage;

import domain.GeneralEntity;
import java.util.List;

/**
 *
 * @author kicnilec
 */
public interface IDatabaseBroker {

    public List<GeneralEntity> getAll(GeneralEntity entity, String join);

    public GeneralEntity getById(GeneralEntity entity, String join);

    public List<GeneralEntity> getAll(GeneralEntity entity, String attrs, String join, String condition, String groupBy, String orderBy);

    public List<GeneralEntity> getByCondition(GeneralEntity entity, String join, String condition);

    public void insert(GeneralEntity entity);

    public void update(GeneralEntity entity);

    public void delete(GeneralEntity entity);

    public Long getLastId(GeneralEntity entity);
}
