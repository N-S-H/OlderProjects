
<%@page import="java.util.List"%>
<%@page import="com.nit.beans.Packet"%>
<%@page import="com.nit.dao.UserDAO"%>
<%@page import="com.nit.beans.FileTest"%>
<%@page import="com.nit.beans.GetColl"%><jsp:include page="header.jsp"></jsp:include>

<section id="pricing-table">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <div class="pricing-table-area">
            <div class="title-area">
              <h2 class="tittle">Data Flows</h2>
              <span class="tittle-line"></span>
              
            </div>
            <%
            UserDAO dao=new UserDAO();
            List<Packet> data=dao.getData();
            if(data!=null && !data.isEmpty())
            {
            
            %>
            <table class="table table-striped">
            <tr>
            
            
            <th>Sender</th>
            <th>Receiver</th>
            <th>Data</th>
            <th>No.Of Paths</th>
            <th>Collision Probability</th>
            <th></th>
            </tr>
            <%
            FileTest f=new FileTest();
            for(Packet p:data)
            {
            int count=f.getCount(p.getSender(),p.getReceiver());
          
         double val= GetColl.main(count);
             %>
            <tr>
            
            <td><%=p.getSender() %></td>
            <td><%=p.getReceiver() %></td>
            <td><%=p.getData() %></td>
            <td><%=count %></td>
            <td><%=val %></td>
            <td><a href="showFlow.jsp?id=<%=p.getId() %>"><button type="button" class="btn btn-success">Flow</button></a></td>
            
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
