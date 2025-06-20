package lab.services;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lab.data.ComplaintRepository;
import lab.dto.ComplaintDTO;
import lab.entities.Complaint;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import java.util.List;
import java.lang.reflect.Type;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ComplaintService {

    @Inject
    private ComplaintRepository repository;
    
    @Inject
    private ModelMapper mapper;

    @Transactional
    public void create(ComplaintDTO dto) {
        
        repository.create(mapper.map(dto, Complaint.class));
    }
    @Transactional
    public void edit(ComplaintDTO dto){
        
        Complaint entity = mapper.map(dto, Complaint.class);
        Complaint complaint = repository.find(entity.getId());
        if(complaint != null){
            repository.edit(entity);
        }
    }

    @Transactional
    public void remove(ComplaintDTO dto){
        Complaint entity = mapper.map(dto, Complaint.class);
        Complaint complaint = repository.find(entity.getId());
        if(complaint != null){
            repository.remove(entity);
        }
    }

    @Transactional
    public ComplaintDTO find(long id){
        
        return mapper.map(repository.find(id), ComplaintDTO.class);
    }

    @Transactional
    public List<ComplaintDTO> findAll(String status) {
        
        List<Complaint> entityList = repository.findAll(status);
        Type listType =
                new TypeToken<List<ComplaintDTO>>() {}.getType();
        List<ComplaintDTO> dtoList =
                mapper.map(entityList, listType);
        return dtoList;
    }

}
