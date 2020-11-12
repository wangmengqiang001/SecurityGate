<html>
  <head>
     <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
  </head>
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
    <div id="showHello">Here show hello message</div>
    <div>
         <p class="greeting-id">The ID is </p>
         <p class="greeting-content">The content is </p>
	 <p class="datavalue"> value:</p>
    </div>
    <p>Open <a href="http://guizhou:8072/helloweb/" >helloweb</a> behind the gateway</p>
    <p> embed the program in IFrame</p>
    <p> In the  embeded page, X-Frame-Options SHOULD NOT BE DENY </p>
    <div style="width:100%;height:500">

       <iframe style="border:none;width:100%;height:500" src = "http://guizhou:8072/helloweb/"> </iframe>
    </div>
   <script src="../hello.js"></script>

  </body>
</html>
