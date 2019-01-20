package els.rest;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

import els.business.Employee;
import els.rest.EmployeeController;

public class EmployeeControllerTest {
	
	private EmployeeController EmpContr = new EmployeeController();
	
	private ClassLoader loader = EmployeeController.class.getClassLoader();
	private File file = new File(loader.getResource("employees.json").getFile());
	
	   @Test
	   public void testGetEmployeesWithUnicity() {
		   int nbrEmployeesWithUnicity = 2;
		   String unicity = "trigramme";
		   List<Employee> list = EmpContr.parseJsonFile(file);
		   
		   List<Employee> listEmpUniq = EmpContr.getEmployeesWithUnicity(list, unicity);

	      // Vérification du test
	      assertTrue(listEmpUniq.size() >= nbrEmployeesWithUnicity);
	   }
	   
	   @Test
	   public void testParseJsonFile() {
			
			int nbrEmployees = 3;
			
			List<Employee> list = EmpContr.parseJsonFile(file);
			assertTrue(list.size() >= nbrEmployees);
	   }
}
