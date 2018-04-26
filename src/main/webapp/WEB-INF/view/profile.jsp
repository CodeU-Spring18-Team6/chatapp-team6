<%@ page import="codeu.model.data.User" %>
<%@ page import="codeu.model.store.basic.UserStore" %>
<%@ page import="java.util.List" %>
<%@ page import="codeu.model.data.Conversation" %>
<%@ page import="codeu.model.data.Message" %>

<%User userProfile = (User) request.getAttribute("user");
//List<Message> messages = (List<Message>) request.getAttribute("messages");
%>
<!DOCTYPE html>
<html>
<head>
 <title> Profile <%=userProfile.getName()%></title>

 <!--          Style        -->
 <link rel="stylesheet" href="/css/main.css" type="text/css">

  <style>
    #chat {
      background-color: white;
      height: 500px;
      overflow-y: scroll
    }
  </style>
  <!-- ...................... -->
</head>

<body>
  <%@ include file="/nav.jsp" %>
  <div id="container">
    <h1><%=userProfile.getName()%>'s profile</h1>
    <p><b>About <%=userProfile.getName()%></b></p>
    <p><%=userProfile.getDescription()%></p>

    <h1>Edit description</h1>
    <%if (request.getSession().getAttribute("user") != null) { %>
     <%if(request.getSession().getAttribute("user").equals(userProfile.getName()) ){ %>
      <form action="/profile" method="POST">
       <label for="description">New description:</label>
       <input type="text" name="description" id="description">
       <br/><br/>
       <button type="submit">Submit</button>
      </form>
     <%} else { %> 
      <p><b>You don't have permition to change this account's description</b></p>
     <%}%>
    <%} else { %>
      <p><b>Log in to change this account's description</b></p>
    <%} %>
 </div>
</body>

</html>
