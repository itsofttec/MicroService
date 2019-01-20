package els.rest;

import java.util.HashSet;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import els.business.Employee;

@CrossOrigin(origins = {"http://localhost:4200"}, maxAge = 4800, allowCredentials = "false")
@RestController
public class EmployeeController {
    
	private ClassLoader loader = EmployeeController.class.getClassLoader();
	private File file = new File(loader.getResource("employees.json").getFile());
	
	@CrossOrigin(origins = {"http://localhost:4200"}, maxAge = 6000)
    @RequestMapping("/employee")
    public List<Employee> getEmployeesWithNoDupliCation(@RequestParam(value="unicity", defaultValue="trigramme") String unicity) {
        return getEmployeesWithUnicity(parseJsonFile(file), unicity);
    }
    
	public List<Employee> getEmployeesWithUnicity(List<Employee> list, String unicity) {
		
		HashSet<Object> temp=new HashSet<>();
		
		switch(unicity) {
		    case "id":
		    	list.removeIf(e->!temp.add(e.getId()));
		        break;
		    case "trigramme":
		    	list.removeIf(e->!temp.add(e.getTrigramme()));
		        break;
		    case "prenom":
		    	list.removeIf(e->!temp.add(e.getPrenom()));
		        break;
		    case "nom":
		    	list.removeIf(e->!temp.add(e.getNom()));
		        break;
		    default:list.removeIf(e->!temp.add(e.getJob()));
		}

		return list;
	}
	
	public List<Employee> parseJsonFile(File file) {

		List<Employee> list = null;
		
		try {
    			ObjectMapper objectMapper = new ObjectMapper();
    			objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
    			
				list = objectMapper.readValue(file, new TypeReference<List<Employee>>(){});
				
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
    	return list;
    }

}
