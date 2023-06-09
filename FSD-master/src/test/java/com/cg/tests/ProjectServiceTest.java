package com.cg.tests;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import com.cg.entity.Project;
import com.cg.entity.ProjectDto;
import com.cg.exceptions.ErrorDetails;
import com.cg.exceptions.GlobalExceptionHandler;
import com.cg.exceptions.ResourceNotFoundException;
import com.cg.repository.Projectrepository;
import com.cg.services.ProjectImpl;
import com.cg.services.ProjectService;

 class ProjectServiceTest {
    @Mock
    private Projectrepository repository;
    @Mock
    private ResourceNotFoundException resourceNotFoundException;

    @Mock
    private Exception exception;

    
    @Mock
    private WebRequest webRequest;

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    
    @InjectMocks
    private ProjectService projectService=new ProjectImpl();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
     void testCreateProject_Success() throws ResourceNotFoundException {
        // Arrange
        ProjectDto projectDto = new ProjectDto();
        projectDto.setProjectname("Test Project");
        projectDto.setDescription("Test Description");
        projectDto.setCreatedate(LocalDate.now());

        Project expectedProject = new Project();
        expectedProject.setProjectId(1L);
        expectedProject.setProjectname("Test Project");
        expectedProject.setDescription("Test Description");
        expectedProject.setCreatedate(LocalDate.now());

        when(repository.save(any(Project.class))).thenReturn(expectedProject);

        // Act
        Project createdProject = projectService.createProject(projectDto);

        // Assert
        assertNotNull(createdProject);
        assertEquals(expectedProject.getProjectId(), createdProject.getProjectId());
        assertEquals(expectedProject.getProjectname(), createdProject.getProjectname());
        assertEquals(expectedProject.getDescription(), createdProject.getDescription());
        assertEquals(expectedProject.getCreatedate(), createdProject.getCreatedate());

        verify(repository, times(1)).save(any(Project.class));
    }
    @Test
    void testCreateProject_EmptyNameAndDescription_ThrowsException() {
        // Arrange
        ProjectDto projectDto = new ProjectDto();
        projectDto.setProjectname("");
        projectDto.setDescription("");

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> projectService.createProject(projectDto));
        verify(repository, never()).save(any(Project.class));
    }

    @Test
     void testCreateProject_EmptyFields_ThrowsException() {
        // Arrange
        ProjectDto projectDto = new ProjectDto();
        projectDto.setProjectname("");
        projectDto.setDescription("");

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> projectService.createProject(projectDto));
    }
    
    @Test
     void testUpdateProject_Success() throws ResourceNotFoundException {
        // Arrange
        Long projectId = 1L;
        ProjectDto projectDto = new ProjectDto();
        projectDto.setProjectname("Updated Project");
        projectDto.setDescription("Updated Description");
        projectDto.setCreatedate(LocalDate.now());

        Project existingProject = new Project();
        existingProject.setProjectId(projectId);
        existingProject.setProjectname("Old Project");
        existingProject.setDescription("Old Description");
        existingProject.setCreatedate(LocalDate.now());

        Project expectedProject = new Project();
        expectedProject.setProjectId(projectId);
        expectedProject.setProjectname("Updated Project");
        expectedProject.setDescription("Updated Description");
        expectedProject.setCreatedate(LocalDate.now());

        when(repository.findById(projectId)).thenReturn(Optional.of(existingProject));
        when(repository.save(any(Project.class))).thenReturn(expectedProject);

        // Act
        Project updatedProject = projectService.updateProject(projectDto, projectId);

        // Assert
        assertNotNull(updatedProject);
        assertEquals(expectedProject.getProjectId(), updatedProject.getProjectId());
        assertEquals(expectedProject.getProjectname(), updatedProject.getProjectname());
        assertEquals(expectedProject.getDescription(), updatedProject.getDescription());
        assertEquals(expectedProject.getCreatedate(), updatedProject.getCreatedate());

        verify(repository, times(1)).findById(projectId);
        verify(repository, times(1)).save(any(Project.class));
    }

    @Test
     void testUpdateProject_ProjectNotFound_ThrowsException() {
        // Arrange
        Long projectId = 1L;
        ProjectDto projectDto = new ProjectDto();
        projectDto.setProjectname("Updated Project");
        projectDto.setDescription("Updated Description");

        when(repository.findById(projectId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> projectService.updateProject(projectDto, projectId));
        verify(repository, times(1)).findById(projectId);
        verify(repository, never()).save(any(Project.class));
    }
    
    @Test
     void testDeleteProject_Success() throws ResourceNotFoundException {
        // Arrange
        Long projectId = 1L;

        Project existingProject = new Project();
        existingProject.setProjectId(projectId);

        when(repository.findById(projectId)).thenReturn(Optional.of(existingProject));

        // Act
        projectService.deleteproject(projectId);

        // Assert
        verify(repository, times(1)).findById(projectId);
        verify(repository, times(1)).deleteById(projectId);
    }

    @Test
     void testDeleteProject_ProjectNotFound_ThrowsException() {
        // Arrange
        Long projectId = 1L;

        when(repository.findById(projectId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> projectService.deleteproject(projectId));
        verify(repository, times(1)).findById(projectId);
        verify(repository, never()).deleteById(projectId);
    }
    @Test
     void testFindProjectById_Success() throws ResourceNotFoundException {
        // Arrange
        Long projectId = 1L;
        Project expectedProject = new Project();
        expectedProject.setProjectId(projectId);

        when(repository.findById(projectId)).thenReturn(Optional.of(expectedProject));

        // Act
        Project foundProject = projectService.findProjectById(projectId);

        // Assert
        assertNotNull(foundProject);
        assertEquals(expectedProject.getProjectId(), foundProject.getProjectId());

        verify(repository, times(1)).findById(projectId);
    }

    @Test
     void testFindProjectById_ProjectNotFound_ThrowsException() {
        // Arrange
        Long projectId = 1L;

        when(repository.findById(projectId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> projectService.findProjectById(projectId));
        verify(repository, times(1)).findById(projectId);
    }
    @Test
     void testShowProjects_Success() {
        // Arrange
        Project project1 = new Project();
        project1.setProjectId(1L);
        Project project2 = new Project();
        project2.setProjectId(2L);
        List<Project> expectedProjects = Arrays.asList(project1, project2);

        when(repository.findAll()).thenReturn(expectedProjects);

        // Act
        List<Project> projects = projectService.showProjects();

        // Assert
        assertNotNull(projects);
        assertEquals(expectedProjects.size(), projects.size());
        assertEquals(expectedProjects.get(0).getProjectId(), projects.get(0).getProjectId());
        assertEquals(expectedProjects.get(1).getProjectId(), projects.get(1).getProjectId());

        verify(repository, times(1)).findAll();
    }
    
    @Test
    void testResourceNotFoundExceptionHandler() {
        // Mock the necessary objects and setup the test case
        String message = "Resource not found";
        when(resourceNotFoundException.getMessage()).thenReturn(message);
        String description = "Error occurred while processing the request";
        when(webRequest.getDescription(false)).thenReturn(description);

        // Invoke the exception handler method
        ResponseEntity<ErrorDetails> responseEntity = globalExceptionHandler.resourceNotFoundException(resourceNotFoundException, webRequest);

        // Verify that the necessary methods were invoked
        verify(resourceNotFoundException, times(1)).getMessage();
        verify(webRequest, times(1)).getDescription(false);

        // Assert the response entity and its contents
        assertErrorResponse(HttpStatus.NOT_FOUND, message, description, responseEntity);
    }
    
    @Test
    void testGlobleExcpetionHandler() {
        // Mock the necessary objects and setup the test case
        String message = "Internal server error";
        when(exception.getMessage()).thenReturn(message);
        String description = "Error occurred while processing the request";
        when(webRequest.getDescription(false)).thenReturn(description);

        // Invoke the exception handler method
        ResponseEntity<ErrorDetails> responseEntity = globalExceptionHandler.globleExcpetionHandler(exception, webRequest);

        // Verify that the necessary methods were invoked
        verify(exception, times(1)).getMessage();
        verify(webRequest, times(1)).getDescription(false);

        // Assert the response entity and its contents
        assertErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, message, description, responseEntity);
    }

    private void assertErrorResponse(HttpStatus expectedStatus, String expectedMessage, String expectedDescription, ResponseEntity<ErrorDetails> responseEntity) {
        assertEquals(expectedStatus, responseEntity.getStatusCode());
        ErrorDetails errorDetails = responseEntity.getBody();
        assertEquals(expectedMessage, errorDetails.getMessage());
        
    }


}







