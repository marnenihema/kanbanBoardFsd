//package com.cg.tests;
//
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.util.Optional;
//
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ContextConfiguration;
//
//import com.cg.Demo2Application;
//import com.cg.entity.TeamMember;
//import com.cg.exceptions.EmailAlreadyExistsException;
//import com.cg.repository.TeamMemberRepository;
//import com.cg.services.TeamMemberImpl;
//import com.cg.services.TeamMemberService;
//
// 
//
//
//@SpringBootTest
//@ContextConfiguration(classes = Demo2Application.class)
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//
//
//
//class TeamMemberTest {
//
//     @Mock
//     private TeamMemberRepository repository;
//
//    @Mock
//     private TeamMemberService tmservice;
//
//    @InjectMocks
//    private TeamMemberImpl service;
//
//
//   
//
//
//    @Test
//     void testRegisterTeamMember() throws Exception {
//           
//            String email = "johndoe@example.com";
//            String password = "password123";
//            String designation = "Analyst";
//            String tmname ="john";
//            Integer yearsofexperience = 1;
//            TeamMember teamMember = new TeamMember();
//            teamMember.setEmail(email);
//            teamMember.setPassword(password);
//            teamMember.setDesignation(designation);
//            teamMember.setTmname(tmname);
//            teamMember.setYearsOfExpereince(yearsofexperience);
//
//            when(repository.findByEmail(email)).thenReturn(Optional.empty());
//            when(repository.findByPassword(password)).thenReturn(Optional.empty());
//
//            when(repository.save(any(TeamMember.class))).thenReturn(teamMember);
//            TeamMember result = service.registerTeamMember(teamMember);
//
//
//            assertEquals(email, result.getEmail());
//            assertEquals(tmname, result.getTmname());
//            assertEquals(yearsofexperience,result.getYearsOfExpereince());
//            assertEquals(designation, result.getDesignation());
//            assertEquals(password, result.getPassword());
//            assertEquals("TEAMMEMBER", result.getRole());
//
// 
//
//            verify(repository).findByEmail(email);
//            verify(repository).findByPassword(password);
//            verify(repository).save(teamMember);
//        }
//
// 
//
//        @Test
//        void testRegisterTeamMember_EmailAlreadyExists() throws Exception {
//          
//            String email = "johndoe@example.com";
//            String password = "password123";
//            TeamMember teamMember = new TeamMember();
//            teamMember.setEmail(email);
//            teamMember.setPassword(password);
//
//            when(repository.findByEmail(email)).thenReturn(Optional.of(teamMember));
//            Exception exception = assertThrows(EmailAlreadyExistsException.class, () -> {
//                service.registerTeamMember(teamMember);
//            });
//           
//        }
//
// 
//
//        @Test
//         void testRegisterTeamMember_PasswordAlreadyExists() throws Exception {
//         
//            String email = "johndoe@example.com";
//            String password = "password123";
//            TeamMember teamMember = new TeamMember();
//            teamMember.setEmail(email);
//            teamMember.setPassword(password);
//
// 
//
//            when(repository.findByEmail(email)).thenReturn(Optional.empty());
//            when(repository.findByPassword(password)).thenReturn(Optional.of(teamMember));
//            Exception exception = assertThrows(EmailAlreadyExistsException.class, () -> {
//                service.registerTeamMember(teamMember);
//            });
//
//
//        }
//
//        @Test 
//         void  testDeleteTeammemberById() throws Exception{
//
//            TeamMember teamMember = new TeamMember(12L,"akash","akash@gmail.com","akash","Analyst", 12, "TEAMMEMBER");
//            repository.save(teamMember);
//
//            
//            assertEquals(teamMember.getTeamMemberId(), 12L);
//            tmservice.deleteTeamMember(12L);
//            Optional<TeamMember> tm = repository.findById(12L);
//            assertFalse(tm.isPresent());
//        }
//
//
//        @Test
//        void testFindTeamMemberById() throws Exception{
//
//            TeamMember teamMember = new TeamMember(12L,"akash","akash@gmail.com","akash","Analyst", 12, "TEAMMEMBER");
//            repository.save(teamMember);
//            assertEquals(teamMember.getTeamMemberId(), 12L);
//            tmservice.findTeamMemberById(12L);
//
//
//        }
//
//        @Test
//        void testLoginTeamMember() throws Exception{
//            TeamMember teamMember = new TeamMember(12L,"akash","akash@gmail.com","akash","Analyst", 12, "TEAMMEMBER");
//            repository.save(teamMember);
//            assertEquals(teamMember.getTmname(), "akash");
//            assertEquals(teamMember.getPassword(), "akash");
//
//            tmservice.loginTeamMember("akash", "akash");
//        }
//
//      
//
//
//}
