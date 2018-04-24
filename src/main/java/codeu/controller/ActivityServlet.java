// Copyright 2017 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package codeu.controller;

import codeu.model.data.Conversation;
import codeu.model.data.Message;
import codeu.model.data.User;
import codeu.model.data.Activity;
import codeu.model.store.basic.ConversationStore;
import codeu.model.store.basic.MessageStore;
import codeu.model.store.basic.UserStore;
import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.Comparator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import java.util.ArrayList;

/** Servlet class responsible for the activity feed. */
public class ActivityServlet extends HttpServlet {

  /** Store class that gives access to Conversations. */
  private ConversationStore conversationStore;

  /** Store class that gives access to Messages. */
  private MessageStore messageStore;

  /** Store class that gives access to Users. */
  private UserStore userStore;


  /**
   * Set up state for handling conversation-related requests. This method is only called when
   * running in a server, not when running in a test.
   */
  @Override
  public void init() throws ServletException {
    super.init();
    setUserStore(UserStore.getInstance());
    setConversationStore(ConversationStore.getInstance());
    setMessageStore(MessageStore.getInstance());
  }

  /**
   * Sets the UserStore used by this servlet. This function provides a common setup method for use
   * by the test framework or the servlet's init() function.
   */
  void setUserStore(UserStore userStore) {
    this.userStore = userStore;
  }

  /**
   * Sets the ConversationStore used by this servlet. This function provides a common setup method
   * for use by the test framework or the servlet's init() function.
   */
  void setConversationStore(ConversationStore conversationStore) {
    this.conversationStore = conversationStore;
  }

  /**
   * Sets the MessageStore used by this servlet. This function provides a common setup method for
   * use by the test framework or the servlet's init() function.
   */
  void setMessageStore(MessageStore messageStore) {
    this.messageStore = messageStore;
  }
  /*
  * class needed to implement the comparator for sorting the activity feed.
  */
  class SortbyTime implements Comparator<Activity>{
    public int compare(Activity a, Activity b){
        return b.getCreationTime().compareTo(a.getCreationTime());
    }
  }

  /**
   * This function fires when a user navigates to the activty page. It gets all of the
   * conversations and their messages from the model and forwards to activity.jsp for rendering the list.
   */
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {

      List<Activity> activity = new ArrayList<>();

      List<Conversation> conversations = conversationStore.getAllConversations();
      if(conversations==null){
        System.out.println("No activity present.");
        response.sendRedirect("/conversations");
        return;
      }

      //loop through all conversations and add to activity
      for (int i=0; i<conversations.size(); i++){
        Conversation curr = conversations.get(i);
        UUID conversationId = curr.getId();
        String creator = UserStore.getInstance().getUser(curr.getOwnerId()).getName();
        Activity convo = new Activity( (creator + " created a new conversation: " + curr.getTitle()) , curr.getCreationTime());
        activity.add(convo);

        List<Message> currMessages = messageStore.getMessagesInConversation(conversationId);

        //loop through messages in conversation and add to activity
        for(int j=0; j<currMessages.size(); j++){
          Message currMess = currMessages.get(j);
          String author = UserStore.getInstance().getUser(currMess.getAuthorId()).getName();
          String title = ConversationStore.getInstance().getConversationWithId(currMess.getConversationId()).getTitle();
          Activity mess = new Activity( (author + " sent a message in " + title + ": " + currMess.getContent()), currMess.getCreationTime() );
          activity.add(mess);
        }
      }
      //sorts all activity by time.
      activity.sort(new SortbyTime());

      request.setAttribute("activity", activity);
      request.getRequestDispatcher("/WEB-INF/view/activity.jsp").forward(request, response);

  }



}
