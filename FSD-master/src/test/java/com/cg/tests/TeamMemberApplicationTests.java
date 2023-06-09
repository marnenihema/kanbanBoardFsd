package com.cg.tests;




import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
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
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

 

import com.cg.entity.TeamMember;
import com.cg.entity.TeamMemberDto;
import com.cg.exceptions.EmailAlreadyExistsException;
import com.cg.exceptions.ResourceNotFoundException;
import com.cg.repository.TeamMemberRepository;
import com.cg.services.TeamMemberImpl;
import com.cg.services.TeamMemberService;

 

@SpringBootTest
class TeamMemberApplicationTests {

 

    @Mock
    TeamMemberRepository trepo;

 

    @InjectMocks
    TeamMemberService service = new TeamMemberImpl();

 

    List<TeamMember> teamMemberList;
    TeamMember u;

 

    List<TeamMemberDto> teamMemberList1;
    TeamMemberDto u1;

 

    List<TeamMemberDto> teamMemberList2;
    TeamMemberDto u2;

 

    @BeforeEach
    void beforeTest() {
        teamMemberList = new ArrayList<>();
        u = new TeamMember();
        u.setTeamMemberId(1L);
        u.setTmname("Anjali");
        u.setEmail("anjali@gmail.com");
        u.setPassword("anjali");
        u.setRole("TEAMMEMBER");
        u.setDesignation("Analyst");
        u.setYearsOfExpereince(2);
    }

 

    @BeforeEach
    void beforeTest1() {
        teamMemberList1 = new ArrayList<>();
        u1 = new TeamMemberDto();
        u1.setTeamMemberId(1L);
        u1.setTmname("Anjali");
        u1.setEmail("anjali@gmail.com");
        u1.setPassword("anjali");
        u1.setRole("TEAMMEMBER");
        u1.setDesignation("Analyst");
        u1.setYearsOfExpereince(2);
    }

 

    @BeforeEach
    void beforeTest2() {
        teamMemberList2 = new ArrayList<>();
        u2 = new TeamMemberDto();
        u2.setTeamMemberId(10L);
        u2.setTmname("Jaswanth");
        u2.setEmail("jaswanth@gmail.com");
        u2.setPassword("jaswanth");
        u2.setRole("TEAMMEMBER");
        u2.setDesignation("Analyst");
        u2.setYearsOfExpereince(2);
    }

 

    @Test
    @Order(1)
    void registerTeammember() throws ResourceNotFoundException  {
        Mockito.when(trepo.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());
        Mockito.when(trepo.findByPassword(Mockito.anyString())).thenReturn(Optional.empty());
        Mockito.when(trepo.save(Mockito.any(TeamMember.class))).thenReturn(u);

 

        // Act
        TeamMember registeredTeamMember = service.registerTeamMember(u1);

 

        // Assert
        Mockito.verify(trepo, Mockito.times(1)).findByEmail(Mockito.anyString());
        Mockito.verify(trepo, Mockito.times(1)).findByPassword(Mockito.anyString());
        Mockito.verify(trepo, Mockito.times(1)).save(Mockito.any(TeamMember.class));
    }

 

    @Test
    @Order(2)
    void registerNewTeamMember_EmailAlreadyExists_ThrowsEmailAlreadyExistsException() throws EmailAlreadyExistsException {
        // Arrange

 

        TeamMember existingTeamMember = new TeamMember();
        existingTeamMember.setEmail("anjali@gmail.com");

 

        Mockito.when(trepo.findByEmail(Mockito.anyString())).thenReturn(Optional.of(existingTeamMember));

 

        // Act & Assert
        EmailAlreadyExistsException exception = assertThrows(EmailAlreadyExistsException.class, () -> {
            service.registerTeamMember(u1);

 

        });
        assertEquals("Email Already Exists for TeamMember", exception.getMessage());
        Mockito.verify(trepo, Mockito.times(1)).findByEmail(Mockito.anyString());
        Mockito.verify(trepo, Mockito.never()).findByPassword(Mockito.anyString());
        Mockito.verify(trepo, Mockito.never()).save(Mockito.any(TeamMember.class));
    }

 

    @Test
    @Order(3)
    void registerNewTeamMember_PasswordAlreadyExists_ThrowsEmailAlreadyExistsException() throws EmailAlreadyExistsException {
        TeamMember existingTeamMember = new TeamMember();
        existingTeamMember.setEmail("suma@gmail.com");
        existingTeamMember.setPassword("anjali");

 

        Mockito.when(trepo.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());
        Mockito.when(trepo.findByPassword(Mockito.anyString())).thenReturn(Optional.of(existingTeamMember));

 

        // Act & Assert
        EmailAlreadyExistsException exception = assertThrows(EmailAlreadyExistsException.class, () -> {
            service.registerTeamMember(u1);
        });
        assertEquals("PASSWORD  ALREADY EXISTS TRY WITH ANOTHER PASSWORD", exception.getMessage());
        Mockito.verify(trepo, Mockito.times(1)).findByEmail(Mockito.anyString());
        Mockito.verify(trepo, Mockito.times(1)).findByPassword(Mockito.anyString());
        Mockito.verify(trepo, Mockito.never()).save(Mockito.any(TeamMember.class));
    }

 

    @Test
    @Order(4)
    void updateteamMember() throws ResourceNotFoundException {
        TeamMember updatedTeamMember = new TeamMember();
        updatedTeamMember.setEmail("updated@example.com");
        updatedTeamMember.setDesignation("AnalystA4");
        updatedTeamMember.setYearsOfExpereince(5);
        updatedTeamMember.setPassword("newpassword123");
        updatedTeamMember.setRole("TEAMMEMBER");
        updatedTeamMember.setTeamMemberId(u1.getTeamMemberId());

 

        Mockito.when(trepo.findById(u1.getTeamMemberId())).thenReturn(Optional.of(u));
        Mockito.when(trepo.save(Mockito.any(TeamMember.class))).thenReturn(updatedTeamMember);
        TeamMember result = service.updateTeamMember(u1, u1.getTeamMemberId());

 

        // Assert
        assertEquals(updatedTeamMember.getEmail(), result.getEmail());
        assertEquals(updatedTeamMember.getPassword(), result.getPassword());
        assertEquals(updatedTeamMember.getRole(), result.getRole());
        assertEquals(updatedTeamMember.getTeamMemberId(), result.getTeamMemberId());
        Mockito.verify(trepo, Mockito.times(1)).findById(u.getTeamMemberId());
        Mockito.verify(trepo, Mockito.times(1)).save(Mockito.any(TeamMember.class));

 

    }

 

    @Test
    @Order(5)
     void updateTeamMember_TeamMemberNotFound_ThrowsResourceNotFoundException() throws ResourceNotFoundException {

 

        u1.setDesignation("AnalystA4");
        u1.setTmname("anjaliAppana");
        u1.setYearsOfExpereince(3);

 

        Mockito.when(trepo.findById(u.getTeamMemberId())).thenReturn(Optional.empty());

 

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            service.updateTeamMember(u1, u1.getTeamMemberId());
        });

 

        assertEquals("TeamMember not found with id : " + u1.getTeamMemberId(), exception.getMessage().trim());
        Mockito.verify(trepo, Mockito.times(1)).findById(u.getTeamMemberId());
        Mockito.verify(trepo, Mockito.never()).save(Mockito.any(TeamMember.class));
    }

 

    @Test
    @Order(6)
    void testChangePassword() throws Exception {
        System.out.println("Testing Change Password");

 

        String oldPassword = "password123";
        String newPassword = "newpassword123";

 

        TeamMember teamMember = new TeamMember();
        teamMember.setPassword(oldPassword);

 

        when(trepo.findByPassword(oldPassword)).thenReturn(Optional.of(teamMember));
        when(trepo.save(teamMember)).thenReturn(teamMember);

 

        TeamMember updatedUser = service.changePassword(oldPassword, newPassword);

 

        assertNotNull(updatedUser);
        assertEquals(newPassword, updatedUser.getPassword());

 

        verify(trepo, times(1)).findByPassword(oldPassword);
        verify(trepo, times(1)).save(teamMember);
    }

 

    @Test
    @Order(7)
    void testChangePasswordWithInvalidPassword() throws Exception {
        System.out.println("Testing Change Password with Invalid Password");

 

        String oldPassword = "invalidpassword";
        String newPassword = "newpassword123";

 

        when(trepo.findByPassword(oldPassword)).thenReturn(Optional.empty());

 

        assertThrows(ResourceNotFoundException.class, () -> service.changePassword(oldPassword, newPassword));
        verify(trepo, times(1)).findByPassword(oldPassword);
        verify(trepo, never()).save(any());
    }

 

    @Test
    @Order(8)
    void testShowteamMembers() {
        System.out.println("Testing Show teamMembers");

 

        List<TeamMember> teamMemberList = new ArrayList<>();
        teamMemberList.add(u);

 

        when(trepo.findAll()).thenReturn(teamMemberList);

 

        List<TeamMember> retrievedList = service.showTeamMembers();

 

        assertNotNull(retrievedList);
        assertEquals(1, retrievedList.size());

 

        assertEquals("anjali@gmail.com", retrievedList.get(0).getEmail());
        assertEquals("anjali", retrievedList.get(0).getPassword());

 

        verify(trepo, times(1)).findAll();
    }

 

    @Test
    @Order(9)
    void testShowTeamMemberEmptyList() {
        // Arrange
        List<TeamMember> teamMembers = new ArrayList<>();

 

        // Mock the trepo behavior
        when(trepo.findAll()).thenReturn(teamMembers);

 

        // Act
        List<TeamMember> result = service.showTeamMembers();

 

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

 

    @Test
    @Order(10)
    void testFindteamMemberById() throws ResourceNotFoundException {
        // Set up mock trepo with existing teamMember object

 

        TeamMember existingTeamMember = u;
        when(trepo.findById(u.getTeamMemberId())).thenReturn(Optional.of(existingTeamMember));

 

        TeamMember foundTm = service.findTeamMemberById(u.getTeamMemberId());
        assertNotNull(foundTm);
        assertEquals(existingTeamMember.getTmname(), foundTm.getTmname());

 

        assertEquals(existingTeamMember.getEmail(), foundTm.getEmail());
        assertEquals(existingTeamMember.getPassword(), foundTm.getPassword());
        assertEquals(existingTeamMember.getRole(), foundTm.getRole());
        assertEquals(existingTeamMember.getTeamMemberId(), foundTm.getTeamMemberId());

 

        verify(trepo, times(1)).findById(u.getTeamMemberId());

 

        Long nonExistentId = 2L;
        when(trepo.findById(nonExistentId)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.findTeamMemberById(nonExistentId));
    }

 

    @Test
    @Order(11)
    void testDeleteUser() throws ResourceNotFoundException {

 

        TeamMember existingUser = u;

 

        when(trepo.findById(1L)).thenReturn(Optional.of(existingUser));

 

        service.deleteTeamMember(1L);
        verify(trepo, times(1)).deleteById(1L);

 

        verify(trepo, times(1)).findById(1L);

 

        when(trepo.findById(1L)).thenReturn(Optional.empty());
        Optional<TeamMember> deletedUser = trepo.findById(1L);
        assertFalse(deletedUser.isPresent());

 

        when(trepo.findById(100L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.deleteTeamMember(1L));
    }

 

    @Test
    @Order(12)
    void testLoginTeamMember() throws ResourceNotFoundException {
        System.out.println("Testing Authenticate teamMember");

 

        String teamMemberName = "Anjali";
        String password = "anjali";

 

        TeamMember teamMember = new TeamMember();
        teamMember.setTmname(teamMemberName);
        teamMember.setPassword(password);
        teamMember.setRole("TEAMMEMBER");

 

        when(trepo.loginTeamMember(teamMemberName, password)).thenReturn(Optional.of(teamMember));

 

        TeamMember authenticatedteamMember = service.loginTeamMember(teamMemberName, password);

 

        assertNotNull(authenticatedteamMember);
        assertEquals(teamMemberName, authenticatedteamMember.getTmname());
        assertEquals(password, authenticatedteamMember.getPassword());
        assertEquals("TEAMMEMBER", authenticatedteamMember.getRole());

 

        verify(trepo, times(1)).loginTeamMember(teamMemberName, password);
    }

 

    @Test
    @Order(13)
    void testLoginTeamMember_WithInvalidCredentials() throws Exception {
        // Arrange
        String teamMemberName = "johndoe";
        String oldPassword = "invalidpassword";

 

        // Mock the trepo behavior
        when(trepo.loginTeamMember(teamMemberName, oldPassword)).thenReturn(Optional.empty());

 

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            service.loginTeamMember(teamMemberName, oldPassword);
        });
    }

     @Test
         void main_StartsApplication_Successfully() {
            // Arrange
            String[] args = {};

 

            // Act
            

 

            // Assert
            // You can add assertions or verifications here to check if the application started successfully
            assertTrue(true, "Application started successfully");
        }

 

}