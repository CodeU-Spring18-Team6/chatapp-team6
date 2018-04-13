<!DOCTYPE html>
<html>
<head>
  <title>Profile Page</title>
  <link rel="stylesheet" href="/css/main.css">
</head>
<body>
  <%@ include file="/nav.jsp" %>

  <div id="container">
    <div
      style="width:75%; margin-left:auto; margin-right:auto; margin-top: 50px;">

      <h1>Profile Page</h1>
      <h2>Welcome, <%= request.getSession().getAttribute("user") %>!</h2>

      <ul>
        <li><a href="/login">Login</a> to get started.</li>
        <li>Go to the <a href="/conversations">conversations</a> page to
            create or join a conversation.</li>
        <li>View the <a href="/about.jsp">about</a> page to learn more about the
            project.</li>
        <li>You can <a href="/testdata">load test data</a> to fill the site with
            example data.</li>
      </ul>
    </div>
  </div>
</body>
</html>
