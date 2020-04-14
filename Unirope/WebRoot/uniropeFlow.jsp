
<%@page import="java.util.List"%>
<%@page import="com.nit.beans.Packet"%>
<%@page import="com.nit.dao.UserDAO"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.ArrayList"%><jsp:include page="header.jsp"></jsp:include>

<section id="pricing-table">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <div class="pricing-table-area">
            <div class="title-area">
              <h2 class="tittle">Alternative  Paths</h2>
              <span class="tittle-line"></span>
              
            </div>
            <%
            UserDAO dao=new UserDAO();
            int id=Integer.parseInt(request.getParameter("id"));
            List<String> data=dao.getAvailablePaths(id);
            
            List<String> alternatives=dao.getAvailablePathsAvail(id);
            List<Integer> sum=dao.getSumData();
            List<Integer> sum1=dao.getSumData1();
          /* 
           int low=100;
           int loc=0;
           int z=0;
           for(Integer i:sum)
           {
           if(i<low)
           {
           low=i;
           loc=z;
           }
           z++;
           
           }*/
            
           
         
            int minIndex = sum.indexOf(0);
            if(data!=null && !data.isEmpty())
            {
             %>
             <table class="table table-striped">
            <tr>
            
            <th>Sender</th>
            <th>Receiver</th>
            <th>HashValue</th>
            <th>PathValue</th>
            
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
            <td><%=sum.get(i) %></td>
           
            
            </tr>
            

			<%    
			    }  
            }
             %>
             
            </table>
            
<section id="pricing-table">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <div class="pricing-table-area">
            <div class="title-area">
              <h2 class="tittle">Available Paths</h2>
              <span class="tittle-line"></span>
              
            </div>
            <%
             if(alternatives!=null && !alternatives.isEmpty())
            {
            %>
             <table class="table table-striped">
            <tr>
            
            <th>Sender</th>
            <th>Receiver</th>
            <th>HashValue</th>
            <th>PathValue</th>
            
            </tr>
            <%
            int j=-1;
          for(String s:alternatives)
          {
          j++;
            %>
           
           
            <tr>
            
            <td><%=s.charAt(0)%></td>
            <td><%=s.charAt(s.length()-1) %></td>
            <td><%=s %></td>
            <td><%=sum1.get(j) %></td>
           
            
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
