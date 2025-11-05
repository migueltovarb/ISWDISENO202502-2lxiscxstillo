package co.edu.editorial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.editorial.modelo.Disco;
import co.edu.editorial.repository.DiscoRepository;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DiscoService implements IDiscoService {
    
    @Autowired
    private DiscoRepository discoRepository;
    
    @Override
    public Disco crearDisco(Disco disco) {
        return discoRepository.save(disco);
    }
    
    @Override
    public Disco actualizarDisco(Disco disco) {
        Optional<Disco> discoDb = this.discoRepository.findById(disco.getId());
        
        if(discoDb.isPresent()) {
            Disco discoUpdate = discoDb.get();
            discoUpdate.setId(disco.getId());
            discoUpdate.setTitulo(disco.getTitulo());
            discoUpdate.setPrecio(disco.getPrecio());
            discoUpdate.setDuracion(disco.getDuracion());
            discoRepository.save(discoUpdate);
            return discoUpdate;
        } else {
            throw new RuntimeException("Disco no encontrado con id : " + disco.getId());
        }
    }
    
    @Override
    public List<Disco> getTodosLosDiscos() {
        return this.discoRepository.findAll();
    }
    
    @Override
    public Disco getDiscoPorId(ObjectId discoId) {
        Optional<Disco> discoDb = this.discoRepository.findById(discoId);
        
        if(discoDb.isPresent()) {
            return discoDb.get();
        } else {
            throw new RuntimeException("Disco no encontrado con id : " + discoId);
        }
    }
    
    @Override
    public void eliminarDisco(ObjectId discoId) {
        Optional<Disco> discoDb = this.discoRepository.findById(discoId);
        
        if(discoDb.isPresent()) {
            this.discoRepository.delete(discoDb.get());
        } else {
            throw new RuntimeException("Disco no encontrado con id : " + discoId);
        }
    }
}