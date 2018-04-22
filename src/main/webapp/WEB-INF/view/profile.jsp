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

 <h1><%=userProfile.getName()%>'s profile</h1>
 <p><b>About <%=userProfile.getName()%></b></p>
 <p><%=userProfile.getDescription()%></p>

 <!-- <%//if (request.getSession().getAttribute("user") != null) { %>
  <%//if(((User)request.getSession().getAttribute("user")).getName().equals(userProfile.getName())){ %>
    <h1>Own Profile</h1> 
  <%//} else { %> 
    <h1>Other Profile</h1>
  <%//}%>
 <%//} else { %>
    <h1>Other Profile</h1>
 <%//} %> -->

 

</body>

</html>
