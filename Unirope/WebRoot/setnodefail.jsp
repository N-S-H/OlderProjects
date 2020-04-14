
<%@page import="java.util.List"%>
<%@page import="com.nit.beans.Packet"%>
<%@page import="com.nit.dao.UserDAO"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.nit.beans.Node"%><jsp:include page="header.jsp"></jsp:include>

<section id="pricing-table">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <div class="pricing-table-area">
            <div class="title-area">
              <h2 class="tittle">Set Node Path Failed</h2>
              <span class="tittle-line"></span>
              
            </div>



<center>
<font color="red" size="4">
<b>

 <%
 UserDAO dao=new UserDAO();
 List<Node> data= dao.getFailedNodes();
    String status=request.getParameter("status");
    if(status!=null)
    {
    	out.println(status);
    }
    %>
    </b>
    </font>
    </center>
             <form action="SetNodeFailed">
          <table class="table table-striped">
            <tr>
            
            
            <th>Source Node</th>
            <th>
            <select name="src" class="form-control">
            <option>A</option>
            <option>B</option>
            <option>C</option>
            <option>D</option>
            <option>E</option>
            <option>F</option>
            <option>G</option>
            </select>
            </th>
            </tr>
           
           
            <tr>
            
            
            <th>Destination Node</th>
            <th>
            <select name="dest" class="form-control">
            <option>A</option>
            <option>B</option>
            <option>C</option>
            <option>D</option>
            <option>E</option>
            <option>F</option>
            <option>G</option>
            </select>
            </th>
            </tr>
            
            </table>
          <center><input type="submit" value="Submit" class="btn btn-danger"/></center>  
          </form>
          
          
          
          
          
          
          
          
          
          
          <section id="pricing-table">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <div class="pricing-table-area">
            <div class="title-area">
              <h2 class="tittle">Failed Node Paths</h2>
              <span class="tittle-line"></span>
              
            </div>




             
          <%
          if(data!=null && !data.isEmpty())
          {
          
          
           %>
          <table class="table table-striped">
            <tr>
            <th>Source Node</th>
            <th>Destination Node</th>
            <th></th>
            </tr>
            <%
            for(Node n:data)
            {
            
             %>
             
            <tr><td><%=n.getSrc() %></td><td><%=n.getDest() %></td>
            <td><a href="RecoverNode?src=<%=n.getSrc() %>&dest=<%=n.getDest() %>"><button type="button"  value="Recover" class="btn btn-success">Recover Node</button></a></td>
            </tr>
            <%
            }
            }
             %>
            
            </table>
          
          
          
          
          
          
  <!-- initialize jQuery Library --> 
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <!-- Include all compiled plugins (below), or include individual files as needed -->
  <!-- Bootstrap -->
  <script src="assets/js/bootstrap.js"></script>
  <!-- Slick Slider -->
  <script type="text/javascript" src="assets/js/slick.js"></script>
  <!-- Counter -->
  <script type="text/javascript" src="assets/js/waypoints.js"></script>
  <script type="text/javascript" src="assets/js/jquery.counterup.js"></script>
  <!-- mixit slider -->
  <script type="text/javascript" src="assets/js/jquery.mixitup.js"></script>
  <!-- Add fancyBox -->        
  <script type="text/javascript" src="assets/js/jquery.fancybox.pack.js"></script>
  <!-- Wow animation -->
  <script type="text/javascript" src="assets/js/wow.js"></script> 

  <!-- Custom js -->
  <script type="text/javascript" src="assets/js/custom.js"></script>
    
  </body>
</html>
