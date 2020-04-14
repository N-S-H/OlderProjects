
<%@page import="java.util.List"%>
<%@page import="com.nit.beans.Packet"%>
<%@page import="com.nit.dao.UserDAO"%><jsp:include page="header.jsp"></jsp:include>

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
            int id=Integer.parseInt(request.getParameter("id"));
            
            List<String> data=dao.getAvailablePaths(id);
            List<Integer> sum=dao.getSumData();
            if(data!=null && !data.isEmpty())
            {
            %>
            <table class="table table-striped">
            <tr>
            
            
            <th>Sender</th>
            <th>Receiver</th>
            <th>Data</th>
            <th>Energy</th>
            </tr>
            <%
            int i=-1;
            for(String s:data)
            {
            i++;
             %>
            <tr>
            
            <td><%=s.charAt(0)%></td>
            <td><%=s.charAt(s.length()-1) %></td>
            <td><%=s %></td>
            <td><%=sum.get(i)%></td>
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
