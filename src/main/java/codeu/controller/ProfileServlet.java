package codeu.controller;

import java.io.IOException;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import codeu.model.data.Conversation;
import codeu.model.data.Message;
import codeu.model.data.User;
import codeu.model.store.basic.ConversationStore;
import codeu.model.store.basic.MessageStore;
import codeu.model.store.basic.UserStore;


public class ProfileServlet extends HttpServlet {
  private UserStore userStore;
  private MessageStore messageStore;
  private ConversationStore conversationStore;

  @Override
  public void init() throws ServletException {
    super.init();
    setUserStore(UserStore.getInstance());
    setMessageStore(MessageStore.getInstance());
    setConversationStore(ConversationStore.getInstance());
  }

  void setUserStore(UserStore userStore) {
    this.userStore = userStore;
  }

  void setMessageStore(MessageStore messageStore){
    this.messageStore = messageStore;
  }

  void setConversationStore(ConversationStore conversationStore){
    this.conversationStore = conversationStore;
  }

  @Override

  public void doGet(HttpServletRequest request, HttpServletResponse response)

    throws IOException, ServletException {

    String requestUrl = request.getRequestURI();
    String userName = requestUrl.substring("/user/".length());
  
    User user = userStore.getUser(userName);
    request.setAttribute("user", user);
    request.getRequestDispatcher("/WEB-INF/view/profile.jsp").forward(request, response);
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {

    String requestUrl = request.getRequestURI();
    String userName = requestUrl.substring("/user/".length());
    String newDescription = request.getParameter("description");

    userStore.updateDescription(userName, newDescription);

    response.sendRedirect("/user/" + userName);
  }
}