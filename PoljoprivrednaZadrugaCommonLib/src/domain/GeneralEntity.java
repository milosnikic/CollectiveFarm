/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author kicnilec
 */
public interface GeneralEntity extends Serializable {

    public String getTableName();

    public List<GeneralEntity> getList(ResultSet resultSet) throws Exception;

    public String getColumnsForInsert();

    public String getValuesForInsert();

    public String getPrimaryKey();

    public String getValuesForUpdate();

    public GeneralEntity getObjectFromRs(ResultSet resultSet);

    public String getPrimaryKeyName();
    
    public Long getLastId(ResultSet resultSet);
}
