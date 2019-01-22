package els.business;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class EmployeeTest {
	private static final String API_EMPLOYEE_PATH = "http://localhost:8080/employee?unicity=trigramme";

	@Autowired
	private RestTemplate restTemplate = new RestTemplate();;

	@Test
	public void testGetEmployees() {
		int nbrEmployeesWithUnicity = 8;

		// Récupération des ressources sur le serveur
		ResponseEntity<Employee[]> response = this.restTemplate.getForEntity(API_EMPLOYEE_PATH, Employee[].class);

		// Vérification du test
		assertTrue(response.getBody().length >= nbrEmployeesWithUnicity);
	}
}
