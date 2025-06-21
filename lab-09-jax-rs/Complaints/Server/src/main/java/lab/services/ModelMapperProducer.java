package lab.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

import org.modelmapper.ModelMapper;

@ApplicationScoped
public class ModelMapperProducer {
    @Produces
    @ApplicationScoped
    public ModelMapper createModelMapperInstance(){
        return new ModelMapper();
    }
}
