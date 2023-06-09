package com.cg.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.entity.TeamLead;
import com.cg.entity.TeamLeadDto;
import com.cg.exceptions.EmailAlreadyExistsException;
import com.cg.exceptions.ResourceNotFoundException;
import com.cg.repository.TeamLeadrepository;
import com.cg.services.TeamLeadImpl;
import com.cg.services.TeamLeadService;

@SpringBootTest
class TeamLeadTest {

	@Mock
	TeamLeadrepository trepo;

	@InjectMocks
	TeamLeadService service = new TeamLeadImpl();

	List<TeamLead> teamLeadList;
	TeamLead u;

	List<TeamLeadDto> teamLeadList1;
	TeamLeadDto u1;

	@BeforeEach
	void beforeTest() {
		teamLeadList = new ArrayList<>();
		u = new TeamLead();
		u.setTeamLeadId(1L);
		u.setFirstName("swathi");
		u.setLastName("roopa");
		u.setEmail("swathi38@gmail.com");
		u.setPassword("swathi");
		u.setRole("TEAMLEAD");
		u.setYearsOfExpereince(2);
	}

	@BeforeEach
	void beforeTest1() {
		teamLeadList1 = new ArrayList<>();
		u1 = new TeamLeadDto();
		u1.setTeamLeadId(1L);
		u1.setFirstName("swathi");
		u1.setLastName("roopa");
		u1.setEmail("swathi38@gmail.com");
		u1.setPassword("swathi");
		u1.setRole("TEAMLEAD");
		u1.setYearsOfExpereince(2);
	}

	@Test
	@Order(1)
	void testCreateTeamLead() throws Exception {
		// Mock the behavior of the repository
		when(trepo.findByEmail(u.getEmail())).thenReturn(Optional.empty());
		when(trepo.findByPassword(u.getPassword())).thenReturn(Optional.empty());
		when(trepo.save(any(TeamLead.class))).thenReturn(u); // Use 'any(TeamLead.class)' to match any TeamLead object

		// Call the createUser method and assert the result
		TeamLead result = service.createUser(u1);
		assertEquals(u, result);
		assertEquals("swathi", result.getFirstName());

		// Verify that the repository's methods were called as expected
		verify(trepo, times(1)).findByEmail(u.getEmail());
		verify(trepo, times(1)).findByPassword(u.getPassword());
		verify(trepo, times(1)).save(any(TeamLead.class));
	}

	@Test
	void testCreateTeamLead_EmailAlreadyExists() {
		// Mock the behavior of the repository to return an existing user with the same
		// email
		TeamLead existingUser = new TeamLead();
		when(trepo.findByEmail(u1.getEmail())).thenReturn(Optional.of(existingUser));

		// Call the createUser method and assert that it throws the expected exception
		assertThrows(EmailAlreadyExistsException.class, () -> service.createUser(u1));

		// Verify that the repository's methods were called as expected
		verify(trepo, times(1)).findByEmail(u1.getEmail());
		verify(trepo, never()).findByPassword(anyString());
		verify(trepo, never()).save(any(TeamLead.class));
	}

	@Test
	void testCreateTeamLead_PasswordAlreadyExists() {
		// Mock the behavior of the repository to return an existing user with the same
		// password
		TeamLead existingUser = new TeamLead();
		when(trepo.findByEmail(u1.getEmail())).thenReturn(Optional.empty());
		when(trepo.findByPassword(u1.getPassword())).thenReturn(Optional.of(existingUser));

		// Call the createUser method and assert that it throws the expected exception
		assertThrows(EmailAlreadyExistsException.class, () -> service.createUser(u1));

		// Verify that the repository's methods were called as expected
		verify(trepo, times(1)).findByEmail(u1.getEmail());
		verify(trepo, times(1)).findByPassword(u1.getPassword());
		verify(trepo, never()).save(any(TeamLead.class));
	}

	@Test
	@Order(2)
	void testUpdateUser() throws ResourceNotFoundException {
		// Arrange
		TeamLeadDto updatedUser = new TeamLeadDto();
		updatedUser.setFirstName("divya");
		updatedUser.setLastName("saisai");
		updatedUser.setEmail("divya38@gmail.com");

		Long uid = 1L;
		TeamLead existingUser = u;

		when(trepo.findById(uid)).thenReturn(Optional.of(existingUser));
		when(trepo.save(any(TeamLead.class))).thenReturn(existingUser); // Mock the save method to return the
																		// existingUser

		// Act
		TeamLead result = service.updateUser(updatedUser, uid);

		// Assert

		assertEquals("TEAMLEAD", result.getRole());
		assertEquals(uid, result.getTeamLeadId());

		// Verify
		verify(trepo, times(1)).findById(uid);
		verify(trepo, times(1)).save(any(TeamLead.class));
	}

	@Test
	@Order(3)
	void testDeleteUser() throws ResourceNotFoundException {

		TeamLead existingUser = u;

		when(trepo.findById(1L)).thenReturn(Optional.of(existingUser));

		service.deleteUser(1L);
		verify(trepo, times(1)).deleteById(1L);

		verify(trepo, times(1)).findById(1L);

		when(trepo.findById(1L)).thenReturn(Optional.empty());
		Optional<TeamLead> deletedUser = trepo.findById(1L);
		assertFalse(deletedUser.isPresent());
	}

	@Test
	@Order(4)
	void testFindUserById() throws ResourceNotFoundException {
		// Set up mock repository with existing user object

		TeamLead existingUser = u;
		when(trepo.findById(u.getTeamLeadId())).thenReturn(Optional.of(existingUser));

		TeamLead foundUser = service.findUserById(u.getTeamLeadId());
		assertNotNull(foundUser);
		assertEquals(existingUser.getFirstName(), foundUser.getFirstName());
		assertEquals(existingUser.getLastName(), foundUser.getLastName());
		assertEquals(existingUser.getEmail(), foundUser.getEmail());
		assertEquals(existingUser.getPassword(), foundUser.getPassword());
		assertEquals(existingUser.getRole(), foundUser.getRole());
		assertEquals(existingUser.getTeamLeadId(), foundUser.getTeamLeadId());

		verify(trepo, times(1)).findById(u.getTeamLeadId());

		Long nonExistentId = 2L;
		when(trepo.findById(nonExistentId)).thenReturn(Optional.empty());
		assertThrows(ResourceNotFoundException.class, () -> service.findUserById(nonExistentId));
	}

	@Test
	@Order(5)
	void testAuthenticateUser() throws ResourceNotFoundException {
		System.out.println("Testing Authenticate User");

		String userName = "johnsmith";
		String password = "password123";

		TeamLead teamLead = new TeamLead();
		teamLead.setFirstName(userName);
		teamLead.setPassword(password);
		teamLead.setRole("TEAMLEAD");

		when(trepo.loginTeamLead(userName, password)).thenReturn(Optional.of(teamLead));

		TeamLead authenticatedUser = service.authenticateUser(userName, password);

		assertNotNull(authenticatedUser);
		assertEquals(userName, authenticatedUser.getFirstName());
		assertEquals(password, authenticatedUser.getPassword());
		assertEquals("TEAMLEAD", authenticatedUser.getRole());

		verify(trepo, times(1)).loginTeamLead(userName, password);
	}

	@Test
	@Order(6)
	void testChangePassword() throws Exception {
		System.out.println("Testing Change Password");

		String oldPassword = "password123";
		String newPassword = "newpassword123";

		TeamLead teamLead = new TeamLead();
		teamLead.setPassword(oldPassword);

		when(trepo.findByPassword(oldPassword)).thenReturn(Optional.of(teamLead));
		when(trepo.save(teamLead)).thenReturn(teamLead);

		TeamLead updatedUser = service.changePassword(oldPassword, newPassword);

		assertNotNull(updatedUser);
		assertEquals(newPassword, updatedUser.getPassword());

		verify(trepo, times(1)).findByPassword(oldPassword);
		verify(trepo, times(1)).save(teamLead);
	}

	@Test
	@Order(7)
	void testShowUsers() {
		System.out.println("Testing Show Users");

		List<TeamLead> userList = new ArrayList<>();
		userList.add(u);

		when(trepo.findAll()).thenReturn(userList);

		List<TeamLead> retrievedList = service.showUsers();

		assertNotNull(retrievedList);
		assertEquals(1, retrievedList.size());

		assertEquals("swathi38@gmail.com", retrievedList.get(0).getEmail());
		assertEquals("swathi", retrievedList.get(0).getPassword());

		verify(trepo, times(1)).findAll();
	}

	@Test
	@Order(8)
	void testResetForgotPassword() throws ResourceNotFoundException {

		String adminEmail = "roopa.com";
		String newPassword = "roopa";
		TeamLead teamLead = new TeamLead();
		teamLead.setEmail(adminEmail);
		teamLead.setPassword("oldPassword");
		when(trepo.findByEmail(adminEmail)).thenReturn(Optional.of(teamLead));
		when(trepo.save(teamLead)).thenReturn(teamLead);
		String result = service.resetForgotPassword(adminEmail, newPassword);
		assertEquals("Password reset successfully", result);
		assertEquals(newPassword, teamLead.getPassword());
		verify(trepo, times(1)).findByEmail(adminEmail);
		verify(trepo, times(1)).save(teamLead);
	}

	@Test
	@Order(9)
	void testResetForgotPassword_InvalidEmail() {

		String adminEmail = "example@gmail.com";
		String newPassword = "newPassword";
		when(trepo.findByEmail(adminEmail)).thenReturn(Optional.empty());
		assertThrows(ResourceNotFoundException.class, () -> service.resetForgotPassword(adminEmail, newPassword));
		verify(trepo, times(1)).findByEmail(adminEmail);
	}

}
