<html>
  <body bgcolor="white">
    <div style="font-size: 150%; color: #850F0F">
      <span>Enter your name: </span><br />
      <form method="post" action="hello">
        <input type=text size="15" name="user" >
        <input type=submit name="submit" value="Ok">
      </form>
    </div>
    <div>
      <%
          {
            java.lang.String answer = (java.lang.String)request.getAttribute("greeting");   
      %>
      <span><%=answer%></span>
      <%
          }
      %>
    </div>
    <p>Open <a href="http://guizhou:8072/helloweb/" >helloweb</a> behind the gateway</p>
    <p> embed the program in IFrame</p>
    <div style="width:100%">

<iframe style="border:none;width:100%;height:80%" src = "http://guizhou:8072/helloweb/"/>
</div>
  <p> In the  embeded page, X-Frame-Options SHOULD NOT BE DENY </p>
  </body>
</html>
