package com.example.aula11_agenda_contatos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface ContatoDAO {
    @Insert
    public void insertContato(Contato contato);
    @Query("SELECT * FROM contatos ORDER BY id_contato DESC LIMIT 1")
    public Contato selectUltimoContato();
    @Query("SELECT * FROM contatos")
    public List<Contato> selectTodosContatos();
    @Update
    public void updateContato(Contato contato);
    @Delete
    public void deleteContato(Contato contato);
}
