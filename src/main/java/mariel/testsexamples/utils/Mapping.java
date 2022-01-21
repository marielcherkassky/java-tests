package mariel.testsexamples.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Mapping {

    public static  <T,C> C convertObjectTo(ObjectMapper mapper, T src, Class<C> c){
        try {
            return mapper.convertValue(src,c);
        }
        catch(Exception e){
            log.error("Failed converting object of type {} to {}",src.getClass().getSimpleName(),c.getSimpleName(),e);
            return null;
        }
    }

}
