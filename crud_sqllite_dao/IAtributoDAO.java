package com.fdananda.crudsqlite2;

import java.util.List;

public interface IAtributoDAO {

    public boolean create(Atributo atributo);
    public boolean update(Atributo atributo);
    public boolean delete(Atributo atributo);
    public List<Atributo> read();
}
