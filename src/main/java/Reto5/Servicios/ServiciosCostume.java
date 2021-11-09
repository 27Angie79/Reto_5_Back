package Reto5.Servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Reto5.Modelo.Costume;
import Reto5.Repositorio.CostumeRepositorio;

/**
 *
 * @author USUARIO
 */
@Service
public class ServiciosCostume {
    @Autowired
    private CostumeRepositorio metodosCrud;
    
    public List<Costume> getAll(){
        return metodosCrud.getAll();
    }
    
    public Optional<Costume> getCostume(int idCostume){
        return metodosCrud.getCostume(idCostume);
    }
    
    public Costume save(Costume costume){
        if(costume.getId()==null){
            return metodosCrud.save(costume);
        }else{
            Optional<Costume> evt=metodosCrud.getCostume(costume.getId());
            if(evt.isEmpty()){
                return metodosCrud.save(costume);
            }else{
                return costume;
            }
        }
    }
    public Costume update(Costume costume){
        if(costume.getId()!=null){
            Optional<Costume> e=metodosCrud.getCostume(costume.getId());
            if(!e.isEmpty()){
                if(costume.getName()!=null){
                    e.get().setName(costume.getName());
                }
                if(costume.getBrand()!=null){
                    e.get().setBrand(costume.getBrand());
                }
                if(costume.getYear()!=null){
                    e.get().setYear(costume.getYear());
                }
                if(costume.getDescription()!=null){
                    e.get().setDescription(costume.getDescription());
                }
                if(costume.getCategory()!=null){
                    e.get().setCategory(costume.getCategory());
                }
                metodosCrud.save(e.get());
                return e.get();
            }else{
                return costume;
            }
        }else{
            return costume;
        }
    }


    public boolean deleteCostume(int gameId) {
        Boolean aBoolean = getCostume(gameId).map(game -> {
            metodosCrud.delete(game);
            return true;
        }).orElse(false);
        return aBoolean;
    }
    
 
    
}
