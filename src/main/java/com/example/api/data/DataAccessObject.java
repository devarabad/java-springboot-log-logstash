package com.example.api.data;

import java.util.Map;

public interface DataAccessObject<DataModel>
{
  public DataModel find(Map<String, Object> query);
  public String save(DataModel dao);
  public boolean update(Map<String, Object> query, DataModel dao);
  public boolean remove(Map<String, Object> query);
}
