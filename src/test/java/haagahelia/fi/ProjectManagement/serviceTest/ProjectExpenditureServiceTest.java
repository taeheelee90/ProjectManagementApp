package haagahelia.fi.ProjectManagement.serviceTest;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import haagahelia.fi.ProjectManagement.model.project.Project;
import haagahelia.fi.ProjectManagement.model.project.ProjectExpenditure;
import haagahelia.fi.ProjectManagement.repository.ProjectExpenditureRepository;
import haagahelia.fi.ProjectManagement.service.ProjectExpenditureService;


@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class ProjectExpenditureServiceTest {
	
	@PersistenceContext EntityManager em;
	@Autowired ProjectExpenditureService service;
	@Autowired ProjectExpenditureRepository repository;
	
	
	
	@Test
	public void addExpenditureTest() throws ParseException {
		// Given
		Project p = new Project();
		p.setBudget(100);
		em.persist(p);
		
		int cost = 50;
		String description = "test";
		
		
		// When
		Long expenditureId = service.addExpenditure(p.getId(), cost, description);
	
		
		// Then
		//assertEquals(repository.findById(e.getId()), e);
		//assertThat(repository.findById(e.getId()).equals(e));
		//assertEquals("Project ID must be same: ",  p.getId());
		//assertEquals("Left budget = 50", 50, p.getBudget());
		assertEquals("Exp saved", repository.findById(expenditureId));
	}
	/*
			assertEquals("Order Status : ORDER", OrderStatus.ORDER, getOrder.getOrderStatus());
			assertEquals("Number of ordered item: 1", 1, getOrder.getOrderItems().size());
			assertEquals("Order price = price * quantity", 10000 * 2, getOrder.getTotalPrice());
			assertEquals("Left stock = Stock Quantity - Sold Quantity", 8, book.getStockQuantity());*/
}
