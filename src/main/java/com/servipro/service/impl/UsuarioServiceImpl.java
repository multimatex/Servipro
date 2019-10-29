/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servipro.service.impl;

import com.servipro.entity.UsuarioEntity;
import com.servipro.repository.UsuarioCRUDRepository;
import com.servipro.repository.UsuarioJpaRepository;
import com.servipro.service.UsuarioService;
import com.servipro.service.UsuarioServiceImplAbstract;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author PRACTICANTE
 */
@Service("UsuarioServiceImpl")
public class UsuarioServiceImpl extends UsuarioServiceImplAbstract<UsuarioEntity, Long> implements UsuarioService{
    
    @Autowired//se le indica a springboot que busque en sus "Beans" lo que sigue debajo (son todo aquelllo que comienza con @ como unos repositorios raros)
    @Qualifier("usuarioJpaRepository")//este inyecta la dependecnia (bean) (repositorio) "usuarioJpaRepository" que fue especificado en los parentesis para que se pueda usar en este archvio
    private UsuarioJpaRepository userJPARepository;//aca es como una especie de instanciamiento???, esta instruccion es necesaria
    
    @Autowired//se le indica a springboot que busque en sus "Beans" lo que sigue debajo (son todo aquelllo que comienza con @ como unos repositorios raros)
    @Qualifier("UsuarioCRUDRepository")//este inyecta la dependecnia (bean) (repositorio) "usuarioJpaRepository" que fue especificado en los parentesis para que se pueda usar en este archvio
    private UsuarioCRUDRepository userCRUDRepository;//aca es como una especie de instanciamiento???, esta instruccion es necesaria
    
    
    
    //public abstract CrudRepository<UsuarioEntity,Long> getDao();


    /*@Override
    public UsuarioEntity save(UsuarioEntity usuario) {
        //userJPARepository.save(usuario); //To change body of generated methods, choose Tools | Templates.
        
        return userCRUDRepository.save(usuario);
    }*/

    /*@Override
    public List<UsuarioEntity> getAll() {
        List<UsuarioEntity> returnList = new ArrayList<>();
        userCRUDRepository.findAll().forEach(obj -> returnList.add(obj));
        return returnList;//userJPARepository.findAll();
    }*/

    @Override
    public CrudRepository<UsuarioEntity,Long> getDao() {
        return userCRUDRepository;
    }
    
}