package codeu.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.UUID;
import java.time.Instant;

import codeu.model.data.Conversation;
import codeu.model.data.Message;
import codeu.model.data.User;
import codeu.model.store.basic.ConversationStore;
import codeu.model.store.basic.MessageStore;
import codeu.model.store.basic.UserStore;

public class ProfileServletTest {

 private HttpSession mockSession;
 private HttpServletRequest mockRequest;
 private HttpServletResponse mockResponse;
 private RequestDispatcher mockRequestDispatcher;

 private UserStore mockUserStore;
 private ProfileServlet profileServlet;


  @Before

  public void setup() throws IOException {

    profileServlet = new ProfileServlet();

    mockRequest = Mockito.mock(HttpServletRequest.class);

    mockResponse = Mockito.mock(HttpServletResponse.class);

    mockRequestDispatcher = Mockito.mock(RequestDispatcher.class);
    mockSession = Mockito.mock(HttpSession.class);
    Mockito.when(mockRequest.getSession()).thenReturn(mockSession);
    Mockito.when(mockRequest.getRequestDispatcher("/WEB-INF/view/profile.jsp")).thenReturn(mockRequestDispatcher);

    mockUserStore = Mockito.mock(UserStore.class);
    profileServlet.setUserStore(mockUserStore);
  }


  @Test

  public void testDoGet() throws IOException, ServletException {
    Mockito.when(mockRequest.getRequestURI()).thenReturn("/user/test");
    UUID fakeUserId = UUID.randomUUID();
    User fakeUser = new User(fakeUserId, "test", "test", Instant.now());

    Mockito.when(mockUserStore.getUser("test")).thenReturn(fakeUser);

    profileServlet.doGet(mockRequest, mockResponse);

    Mockito.verify(mockRequest).setAttribute("user", fakeUser);
    Mockito.verify(mockRequestDispatcher).forward(mockRequest, mockResponse);
  }
}